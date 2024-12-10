import wx
import wx.xrc
import gettext
import datetime
import os
_ = gettext.gettext


## Ventana para la consulta de la lista de todos los vehiculos del taller
##
## Autores: Luis Setién, Victor Descalzo
## Version: Diciembre 2024
####
class VentanaConsulta ( wx.Frame ):

    ## Metodo que se encarga de inicializar el contenido de la ventana
    def __init__( self, parent ):
        wx.Frame.__init__ ( self, parent, id = wx.ID_ANY, title = "Consultar lista vehículos", pos = wx.DefaultPosition, size = wx.Size( 600,400 ), style = wx.DEFAULT_FRAME_STYLE|wx.TAB_TRAVERSAL )

        self.SetSizeHints( wx.DefaultSize, wx.DefaultSize )
        self.SetBackgroundColour( wx.Colour( 255, 255, 255 ) )

        # establecer icono de la ventana
        imagen = wx.Bitmap(".\\imagenes\\icono.png", wx.BITMAP_TYPE_PNG)
        icono = wx.Icon()
        icono.CopyFromBitmap(imagen)
        self.SetIcon(icono)

        # establecer dimensionado de los elementos
        boxSizer = wx.BoxSizer( wx.VERTICAL )
        self.SetMinSize( wx.Size( 500,300 ) )

        # agregar la barra de herramientas
        self.toolBar = wx.ToolBar( self, wx.ID_ANY, wx.DefaultPosition, wx.DefaultSize, wx.TB_FLAT|wx.TB_HORZ_TEXT )
        self.toolBar.SetMargins( wx.Size( 50,50 ) )
        self.toolBar.SetBackgroundColour( wx.SystemSettings.GetColour( wx.SYS_COLOUR_3DLIGHT ) )

        # agregar los elementos a la barra de herramientas junto a las acciones que desencadenan
        self.toolImprimir = self.toolBar.AddTool( 1, _(u"\u0332".join("Im") + "primir"), wx.Bitmap( u".\\imagenes\\imprimir.png", wx.BITMAP_TYPE_ANY ), wx.NullBitmap, wx.ITEM_NORMAL, wx.EmptyString, "Imprime en papel la lista completa de vehículos en el taller.", None )
        self.Bind(wx.EVT_TOOL, self.toolImprimirClicked, id = 1)

        self.toolActualizar = self.toolBar.AddTool( 2, _(u"\u0332".join("Ac") + "tualizar"), wx.Bitmap( u".\\imagenes\\actualizar.png", wx.BITMAP_TYPE_ANY ), wx.NullBitmap, wx.ITEM_NORMAL, wx.EmptyString, "Actualiza los datos de todos los vehículos a su última revisión.", None )
        self.Bind(wx.EVT_TOOL, self.toolActualizarClicked, id = 2)
        self.toolBar.AddSeparator()

        self.toolSalir = self.toolBar.AddTool( 3, _(u"\u0332".join("Sa") + "lir"), wx.Bitmap( u".\\imagenes\\salir.png", wx.BITMAP_TYPE_ANY ), wx.NullBitmap, wx.ITEM_NORMAL, wx.EmptyString, "Salir de esta ventana.", None )
        self.Bind(wx.EVT_TOOL, self.toolSalirClicked, id = 3)

        self.toolBar.Realize()
        boxSizer.Add( self.toolBar, 0, wx.EXPAND, 5 )

        # mostrar la tabla de entradas de los vehiculos
        #self.m_staticText1 = wx.StaticText( self, wx.ID_ANY, _(u"MyLabel"), wx.DefaultPosition, wx.DefaultSize, 0 )
        #self.m_staticText1.Wrap( -1 )
        #boxSizer.Add( self.m_staticText1, 0, wx.ALL, 5 )

        # obtener la fecha y hora actual en el formato adecuado
        fecha_hora = datetime.datetime.now()
        fecha_hora_string = fecha_hora.strftime("%d/%m/%Y %H:%M:%S")

        # agregar la barra de estado y mostrar el texto por defecto
        self.statusBar = self.CreateStatusBar( 1, wx.STB_SIZEGRIP, wx.ID_ANY )
        self.statusBar.SetStatusText(f"Datos actualizados a {fecha_hora_string}")
        self.statusBar.SetBackgroundColour( wx.SystemSettings.GetColour( wx.SYS_COLOUR_3DLIGHT ) )

        # ajustar posicionamiento de la ventana
        self.SetSizer( boxSizer )
        self.SetFocus()
        self.Layout()
        self.Centre( wx.BOTH )
        self.Show()


    ## Metodo que se encarga de la accion realizada al pulsar el boton de imprimir
    def toolImprimirClicked(self, event):
       
        # mostrar dialogo de configuracion de pagina
        data = wx.PageSetupDialogData()
        dlg = wx.PageSetupDialog(self, data)
        if dlg.ShowModal() == wx.ID_OK:
            data = dlg.GetPageSetupData()

        # mostrar dialogo de configuracion de impresora
        print_data = wx.PrintData()
        print_data.SetPaperId(data.GetPaperId())
        print_data.SetOrientation(data.GetEnableOrientation())
        print_dlg_data = wx.PrintDialogData(print_data)
        print_dlg = wx.PrintDialog(self, print_dlg_data)
        if print_dlg.ShowModal() == wx.ID_OK:
            print_dlg_data = print_dlg.GetPrintDialogData()
            self.pdata = wx.PrintData(print_dlg_data.GetPrintData())
            printer = wx.Printer(print_dlg_data)
            printout = MyPrintout("Lista de vehiculos", "Print Example", margins=(150, 150, 150, 150))
            useSetupDialog = False
            printer.Print(self, printout, useSetupDialog)
            if printer.GetLastError() == wx.PRINTER_ERROR:
                wx.MessageBox("Error al intentar imprimir", "Error", wx.OK | wx.ICON_ERROR)

            printout.Destroy()
            print_dlg.Destroy()
            dlg.Destroy()


    ## Metodo que se encarga de la accion realizada al pulsar el boton de actualizar
    ## se actualizan los valores de la tabla y se refresca la barra de estado
    def toolActualizarClicked(self, event):
        fecha_hora = datetime.datetime.now()
        fecha_hora_string = fecha_hora.strftime("%d/%m/%Y %H:%M:%S")
        self.statusBar.SetStatusText(f"Datos actualizados a {fecha_hora_string}")
        wx.MessageBox("Datos actualizados satisfactoriamente", "Mensaje", wx.OK | wx.ICON_INFORMATION)


    ## Metodo que se encarga de la accion realizada al pulsar el boton de salir
    def toolSalirClicked(self, event):
        self.Close()


## Clase que se encarga de realizar la tarea de impresion
class MyPrintout(wx.Printout):
    def __init__(self, text, title, margins):
        wx.Printout.__init__(self, title)
        self.text = text
        self.margins = margins

    def OnPrintPage(self, page):
        dc = self.GetDC()
        # Implement your printing logic here
        dc.DrawText(self.text, 100, 100)
        return True



## Funcion main que se encarga de invocar la ventana
## señalizamos la apertura y cierre de la misma mediante un fichero
####
if __name__ == "__main__":
    try:
        open(".\\control\\P_CONSULTA", "w").close()
    except Exception as e:
        print(f"error: {e}")
    else:
        print("VentanaConsulta abierta")

    app = wx.App()
    frame = VentanaConsulta(None)
    app.MainLoop()
    
    try:
        os.remove(".\\control\\P_CONSULTA")
    except Exception as e:
        print(f"error: {e}")
    else:
        print("VentanaConsulta cerrada")
