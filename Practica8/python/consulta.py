import wx
import wx.xrc
import gettext
import datetime
import json
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
        wx.Frame.__init__ ( self, parent, id = wx.ID_ANY, title = "Consultar lista vehículos", pos = wx.DefaultPosition, size = wx.Size( 1100,440 ), style = wx.DEFAULT_FRAME_STYLE|wx.TAB_TRAVERSAL )

        self.SetSizeHints( wx.DefaultSize, wx.DefaultSize )
        self.SetBackgroundColour( wx.Colour( 255, 255, 255 ) )
        self.clientesUltimo = []

        # establecer icono de la ventana
        imagen = wx.Bitmap(os.path.join("imagenes", "icono.png"), wx.BITMAP_TYPE_PNG)
        icono = wx.Icon()
        icono.CopyFromBitmap(imagen)
        self.SetIcon(icono)

        # establecer dimensionado de los elementos
        self.boxSizer = wx.BoxSizer( wx.VERTICAL )
        self.SetMinSize( wx.Size( 500,200 ) )

        # agregar la barra de herramientas
        self.toolBar = wx.ToolBar( self, wx.ID_ANY, wx.DefaultPosition, wx.DefaultSize, wx.TB_FLAT|wx.TB_HORZ_TEXT )
        self.toolBar.SetMargins( wx.Size( 10,10 ) )
        self.toolBar.SetBackgroundColour( wx.SystemSettings.GetColour( wx.SYS_COLOUR_3DLIGHT ) )

        # agregar los elementos a la barra de herramientas junto a las acciones que desencadenan
        self.toolImprimir = self.toolBar.AddTool( 1, _(u"\u0332".join("Im") + "primir"), wx.Bitmap(os.path.join("imagenes", "imprimir.png"), wx.BITMAP_TYPE_ANY), wx.NullBitmap, wx.ITEM_NORMAL, wx.EmptyString, "Imprime en papel la lista completa de vehículos en el taller.", None )
        self.Bind(wx.EVT_TOOL, self.toolImprimirClicked, id = 1)

        self.toolActualizar = self.toolBar.AddTool( 2, _(u"\u0332".join("Ac") + "tualizar"), wx.Bitmap(os.path.join("imagenes", "actualizar.png"), wx.BITMAP_TYPE_ANY), wx.NullBitmap, wx.ITEM_NORMAL, wx.EmptyString, "Actualiza los datos de todos los vehículos a su última revisión.", None )
        self.Bind(wx.EVT_TOOL, self.toolActualizarClicked, id = 2)
        self.toolBar.AddSeparator()

        self.toolSalir = self.toolBar.AddTool( 3, _(u"\u0332".join("Sa") + "lir"), wx.Bitmap(os.path.join("imagenes", "salir.png"), wx.BITMAP_TYPE_ANY), wx.NullBitmap, wx.ITEM_NORMAL, wx.EmptyString, "Salir de esta ventana.", None )
        self.Bind(wx.EVT_TOOL, self.toolSalirClicked, id = 3)

        self.toolBar.Realize()
        self.boxSizer.Add( self.toolBar, 0, wx.EXPAND, 5 )

        # agregar la tabla de entradas de los vehiculos
        # el scroll panel permite que se haga scroll dinamico del contenido, mientras que
        # el margin sizer permite agregarle un margen a la tabla sin interferir con el scroll
        # estos elementos quedan anidados unos dentro de otros para obtener el resultado deseado
        self.scrollPanel = wx.ScrolledWindow(self, -1)
        self.scrollPanel.SetScrollRate(5, 5)
        self.flexGridSizer = wx.FlexGridSizer(cols=10, vgap=8, hgap=16)
        self.procRellenarTabla()
        self.marginSizer = wx.BoxSizer(wx.VERTICAL)
        self.marginSizer.Add(self.flexGridSizer, 1, wx.ALL | wx.EXPAND, 16)
        self.scrollPanel.SetSizer(self.marginSizer)
        self.scrollPanel.SetAutoLayout(True)
        self.scrollPanel.Layout()
        self.scrollPanel.SetVirtualSize(self.marginSizer.ComputeFittingWindowSize(self.scrollPanel))
        self.scrollPanel.SetScrollbars(5, 5, 160, 120)
        self.boxSizer.Add(self.scrollPanel, 1, wx.EXPAND)

        # obtener la fecha y hora actual en el formato adecuado
        fecha_hora = datetime.datetime.now()
        fecha_hora_string = fecha_hora.strftime("%d/%m/%Y %H:%M:%S")

        # agregar la barra de estado y mostrar el texto por defecto
        self.statusBar = self.CreateStatusBar( 1, wx.STB_SIZEGRIP, wx.ID_ANY )
        self.statusBar.SetStatusText(f"Datos actualizados a {fecha_hora_string}")
        self.statusBar.SetBackgroundColour( wx.SystemSettings.GetColour( wx.SYS_COLOUR_3DLIGHT ) )

        # establecer atajos de teclado
        self.Bind(wx.EVT_KEY_DOWN, self.keyImprimirPressed)
        self.Bind(wx.EVT_KEY_DOWN, self.keyActualizarPressed)
        self.Bind(wx.EVT_KEY_DOWN, self.keySalirPressed)

        # ajustar posicionamiento de la ventana
        self.SetSizer( self.boxSizer )
        self.SetFocus()
        self.Layout()
        self.SetPosition((200, 200))
        self.Show()


    ## Metodo que se encarga de la accion realizada al pulsar el boton de imprimir
    def toolImprimirClicked(self, event):

        # mostrar dialogo de configuracion de pagina
        printData = wx.PrintData()
        pageData = wx.PageSetupDialogData()
        pageDataDialog = wx.PageSetupDialog(self, pageData)
        if pageDataDialog.ShowModal() == wx.ID_OK:
            pageData = pageDataDialog.GetPageSetupData()

        # mostrar dialogo de configuracion de impresora y invocar la impresion
        printData = wx.PrintData(pageData.GetPrintData())
        printDataDialog = wx.PrintDialogData().SetPrintData(printData)
        printer = wx.Printer(printDataDialog)
        printout = MyPrintout(self.clientesUltimo, "Lista de vehiculos")
        printproc = printer.Print(self, printout, True)
        if not printproc:
            wx.MessageBox("Error al intentar imprimir", "Error", wx.OK | wx.ICON_ERROR)
        printout.Destroy()
        self.SetFocus()


    ## Metodo que se encarga de la accion realizada al pulsar el boton de actualizar
    def toolActualizarClicked(self, event):

        # indicar en la barra de estado el proceso
        self.statusBar.SetStatusText(f"Actualizando datos...")

        # borrar todas las filas de la tabla y forzar refresco
        for elemento in self.flexGridSizer.GetChildren():
            elemento.GetWindow().Destroy()
        self.boxSizer.Layout()

        # rellenar todas las filas de la tabla con datos actualizados y forzar refresco
        self.procRellenarTabla()
        self.boxSizer.Layout()

        # actualizar la fecha y hora de la barra de estado
        fecha_hora = datetime.datetime.now()
        fecha_hora_string = fecha_hora.strftime("%d/%m/%Y %H:%M:%S")
        self.statusBar.SetStatusText(f"Datos actualizados a {fecha_hora_string}")
        wx.MessageBox("Datos actualizados satisfactoriamente", "Mensaje", wx.OK | wx.ICON_INFORMATION)
        self.SetFocus()


    ## Metodo que se encarga de la accion realizada al pulsar el boton de salir
    def toolSalirClicked(self, event):
        self.Close()


    ## Metodo que se encarga de la accion realizada al hacer Alt + I
    ## el cual corresponde al atajo de teclado para imprimir
    def keyImprimirPressed(self, event):
        keycode = event.GetKeyCode()
        altDown = event.AltDown()
        if keycode == ord('I') and altDown:
            print("ALT + I detectado")
            self.toolImprimirClicked(self)
        else:
            event.Skip()

    ## Metodo que se encarga de la accion realizada al hacer Alt + A
    ## el cual corresponde al atajo de teclado para actualizar
    def keyActualizarPressed(self, event):
        keycode = event.GetKeyCode()
        altDown = event.AltDown()
        if keycode == ord('A') and altDown:
            print("ALT + A detectado")
            self.toolActualizarClicked(self)
        else:
            event.Skip()


    ## Metodo que se encarga de la accion realizada al hacer Alt + S
    ## el cual corresponde al atajo de teclado para salir
    def keySalirPressed(self, event):
        keycode = event.GetKeyCode()
        altDown = event.AltDown()
        if keycode == ord('S') and altDown:
            print("ALT + S detectado")
            self.toolSalirClicked(self)
        else:
            event.Skip()


    ## Metodo que se encarga de rellenar las filas de la tabla mediante un fichero json
    def procRellenarTabla(self):

        # inicializar la cabecera con su correspondiente texto y fuente
        fuenteCabecera = wx.Font(10, wx.DEFAULT, wx.NORMAL, wx.BOLD, True)
        cabecera = ["Marca", "Modelo", "Matrícula", "Fecha de alta", "Kilometraje", "Nombre completo", "DNI", "Teléfono", "E-mail", "Fecha de nacimiento"]
        clientes = []
        clientes.append(cabecera)

        # cargar los datos desde el fichero json
        with open(os.path.join("datos", "datos.json"), 'r') as file:
            datos = json.load(file)

        # iterar por las entradas del json y almacenarlas en una array
        for entrada in datos:
            cliente = []
            for campo, valor in entrada.items():
                cliente.append(f"{valor}")
            clientes.append(cliente)
        self.clientesUltimo = clientes

        # agregar las entradas en forma tabular
        for i, fila in enumerate(clientes):
            for j, valor in enumerate(fila):
                celdaTexto = wx.StaticText(self.scrollPanel, wx.ID_ANY, valor)
                if i == 0:
                    celdaTexto.SetFont(fuenteCabecera)
                self.flexGridSizer.Add(celdaTexto, 0, wx.ALIGN_LEFT)


## Clase que se encarga de organizar la tarea de impresion
## posicionando en el papel las lineas de la tabla de la forma correcta
####
class MyPrintout(wx.Printout):
    def __init__(self, text, title):
        wx.Printout.__init__(self, title)
        self.text = text

    def OnPrintPage(self, page):
        dc = self.GetDC()
        posY = 150
        posCol = [600, 1200, 600, 800, 600, 1200, 500, 500, 1000, 500]
        for row in self.text:
            posX = 150
            numCol = 0
            for line in row:
                dc.DrawText(line, posX, posY)
                posX = posX + posCol[numCol]
                numCol = numCol + 1
            posY = posY + 200
        return True


## Funcion main que se encarga de invocar la ventana
## señalizamos la apertura y cierre de la misma mediante un fichero
####
if __name__ == "__main__":
    try:
        open(os.path.join("control", "P_CONSULTA"), "w").close()
    except Exception as e:
        print(f"error: {e}")
    else:
        print("VentanaConsulta abierta")

    app = wx.App()
    frame = VentanaConsulta(None)
    app.MainLoop()
    
    try:
        os.remove(os.path.join("control", "P_CONSULTA"))
    except Exception as e:
        print(f"error: {e}")
    else:
        print("VentanaConsulta cerrada")
