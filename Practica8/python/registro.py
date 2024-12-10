import wx
import wx.xrc
import gettext
import os
import subprocess
import re
_ = gettext.gettext
import sys 
import json

## Ventana para el registro de coches en la base de datos (Paso 1)
##
## Autores: Oscar Entrecanales y David Montenegro
## Version: Diciembre 2024
####



class VentanaRegistroPaso1 ( wx.Frame ):

    def __init__( self, parent ):
        wx.Frame.__init__ ( self, parent, id = wx.ID_ANY, title = _(u"Alta de nuevo coche"), pos = wx.DefaultPosition, size = wx.Size( 500,350 ), style = wx.DEFAULT_FRAME_STYLE|wx.TAB_TRAVERSAL )

        self.SetSizeHints( wx.DefaultSize, wx.DefaultSize )
        self.SetBackgroundColour( wx.SystemSettings.GetColour( wx.SYS_COLOUR_MENU ) )

        imagen = wx.Bitmap(os.path.join("imagenes", "icono.png"), wx.BITMAP_TYPE_PNG)
        icono = wx.Icon()
        icono.CopyFromBitmap(imagen)
        self.SetIcon(icono)

        layoutPaneles = wx.BoxSizer( wx.VERTICAL )

        self.pnlTitulo = wx.Panel( self, wx.ID_ANY, wx.DefaultPosition, wx.DefaultSize, wx.TAB_TRAVERSAL )
        self.pnlTitulo.SetForegroundColour( wx.SystemSettings.GetColour( wx.SYS_COLOUR_MENU ) )
        self.pnlTitulo.SetBackgroundColour( wx.SystemSettings.GetColour( wx.SYS_COLOUR_MENU ) )

        layoutTitulo = wx.BoxSizer( wx.VERTICAL )

        self.tituloPaso1 = wx.StaticText( self.pnlTitulo, wx.ID_ANY, _(u"Paso 1 de 2: Datos del coche a registrar"), wx.DefaultPosition, wx.DefaultSize, 0 )
        self.tituloPaso1.Wrap( -1 )

        self.tituloPaso1.SetFont( wx.Font( 15, wx.FONTFAMILY_DEFAULT, wx.FONTSTYLE_NORMAL, wx.FONTWEIGHT_NORMAL, False, wx.EmptyString ) )
        self.tituloPaso1.SetForegroundColour( wx.SystemSettings.GetColour( wx.SYS_COLOUR_BTNTEXT ) )

        layoutTitulo.Add( self.tituloPaso1, 0, wx.ALL, 5 )


        self.pnlTitulo.SetSizer( layoutTitulo )
        self.pnlTitulo.Layout()
        layoutPaneles.Add( self.pnlTitulo, 1, wx.BOTTOM|wx.EXPAND, 1 )

        self.pnlFormulario = wx.Panel( self, wx.ID_ANY, wx.DefaultPosition, wx.Size( 400,200 ), wx.TAB_TRAVERSAL )
        self.pnlFormulario.SetBackgroundColour( wx.SystemSettings.GetColour( wx.SYS_COLOUR_WINDOW ) )

        layoutFormulario = wx.GridSizer( 0, 2, 0, 0 )

        self.lblMarca = wx.StaticText( self.pnlFormulario, wx.ID_ANY, _(u"Marca:"), wx.DefaultPosition, wx.DefaultSize, 0 )
        self.lblMarca.Wrap( -1 )

        self.lblMarca.SetFont( wx.Font( wx.NORMAL_FONT.GetPointSize(), wx.FONTFAMILY_DEFAULT, wx.FONTSTYLE_NORMAL, wx.FONTWEIGHT_BOLD, False, wx.EmptyString ) )

        layoutFormulario.Add( self.lblMarca, 0, wx.ALL, 5 )

        self.txtMarca = wx.TextCtrl( self.pnlFormulario, wx.ID_ANY, wx.EmptyString, wx.DefaultPosition, wx.DefaultSize, 0 )
        layoutFormulario.Add( self.txtMarca, 0, wx.ALL|wx.EXPAND, 5 )

        self.lblModelo = wx.StaticText( self.pnlFormulario, wx.ID_ANY, _(u"Modelo:"), wx.DefaultPosition, wx.DefaultSize, 0 )
        self.lblModelo.Wrap( -1 )

        self.lblModelo.SetFont( wx.Font( wx.NORMAL_FONT.GetPointSize(), wx.FONTFAMILY_DEFAULT, wx.FONTSTYLE_NORMAL, wx.FONTWEIGHT_BOLD, False, wx.EmptyString ) )

        layoutFormulario.Add( self.lblModelo, 0, wx.ALL, 5 )

        self.txtModelo = wx.TextCtrl( self.pnlFormulario, wx.ID_ANY, wx.EmptyString, wx.DefaultPosition, wx.DefaultSize, 0 )
        layoutFormulario.Add( self.txtModelo, 0, wx.ALL|wx.EXPAND, 5 )

        self.lblMatricula = wx.StaticText( self.pnlFormulario, wx.ID_ANY, _(u"Matricula:"), wx.DefaultPosition, wx.DefaultSize, 0 )
        self.lblMatricula.Wrap( -1 )

        self.lblMatricula.SetFont( wx.Font( wx.NORMAL_FONT.GetPointSize(), wx.FONTFAMILY_DEFAULT, wx.FONTSTYLE_NORMAL, wx.FONTWEIGHT_BOLD, False, wx.EmptyString ) )

        layoutFormulario.Add( self.lblMatricula, 0, wx.ALL, 5 )

        self.txtMatricula = wx.TextCtrl( self.pnlFormulario, wx.ID_ANY, wx.EmptyString, wx.DefaultPosition, wx.DefaultSize, 0 )
        layoutFormulario.Add( self.txtMatricula, 0, wx.ALL|wx.EXPAND, 5 )

        self.lblFechaMat = wx.StaticText( self.pnlFormulario, wx.ID_ANY, _(u"Fecha matriculacion:"), wx.DefaultPosition, wx.DefaultSize, 0 )
        self.lblFechaMat.Wrap( -1 )

        self.lblFechaMat.SetFont( wx.Font( wx.NORMAL_FONT.GetPointSize(), wx.FONTFAMILY_DEFAULT, wx.FONTSTYLE_NORMAL, wx.FONTWEIGHT_BOLD, False, wx.EmptyString ) )

        layoutFormulario.Add( self.lblFechaMat, 0, wx.ALL, 5 )

        self.txtFechaMat = wx.TextCtrl( self.pnlFormulario, wx.ID_ANY, wx.EmptyString, wx.DefaultPosition, wx.DefaultSize, 0 )
        layoutFormulario.Add( self.txtFechaMat, 0, wx.ALL|wx.EXPAND, 5 )

        self.lblKM = wx.StaticText( self.pnlFormulario, wx.ID_ANY, _(u"Kilometraje:"), wx.DefaultPosition, wx.DefaultSize, 0 )
        self.lblKM.Wrap( -1 )

        self.lblKM.SetFont( wx.Font( wx.NORMAL_FONT.GetPointSize(), wx.FONTFAMILY_DEFAULT, wx.FONTSTYLE_NORMAL, wx.FONTWEIGHT_BOLD, False, wx.EmptyString ) )

        layoutFormulario.Add( self.lblKM, 0, wx.ALL, 5 )

        self.txtKM = wx.TextCtrl( self.pnlFormulario, wx.ID_ANY, wx.EmptyString, wx.DefaultPosition, wx.DefaultSize, 0 )
        layoutFormulario.Add( self.txtKM, 0, wx.ALL|wx.EXPAND, 5 )


        self.pnlFormulario.SetSizer( layoutFormulario )
        self.pnlFormulario.Layout()
        layoutPaneles.Add( self.pnlFormulario, 4, wx.ALL|wx.EXPAND, 1 )

        self.pnlBotones = wx.Panel( self, wx.ID_ANY, wx.DefaultPosition, wx.DefaultSize, wx.TAB_TRAVERSAL )
        layoutBotones = wx.BoxSizer( wx.HORIZONTAL )


        layoutBotones.Add( ( 0, 0), 1, wx.EXPAND, 5 )

        self.botSalida = wx.Button( self.pnlBotones, wx.ID_ANY, _(u"Salir"), wx.DefaultPosition, wx.DefaultSize, 0 )
        layoutBotones.Add( self.botSalida, 0, wx.ALL, 5 )
        self.botSalida.Bind(wx.EVT_BUTTON, self.botSalidaClicked)

        self.botSiguiente = wx.Button( self.pnlBotones, wx.ID_ANY, _(u"Siguiente"), wx.DefaultPosition, wx.DefaultSize, 0 )
        layoutBotones.Add( self.botSiguiente, 0, wx.ALL, 5 )
        self.botSiguiente.Bind(wx.EVT_BUTTON, self.botSiguienteClicked)

        self.pnlBotones.SetSizer( layoutBotones )
        self.pnlBotones.Layout()
        layoutBotones.Fit( self.pnlBotones )
        layoutPaneles.Add( self.pnlBotones, 1, wx.ALL|wx.EXPAND, 10 )


        self.SetSizer( layoutPaneles )
        self.Layout()
        self.SetFocus()
        self.Centre( wx.BOTH )
        self.Show()

    def __del__( self ):
        pass

    def botSalidaClicked(self, event):
        self.Close()

    def botSiguienteClicked(self, event):
        if self.txtMarca.IsEmpty() or self.txtModelo.IsEmpty() or self.txtMatricula.IsEmpty() or self.txtFechaMat.IsEmpty() or self.txtKM.IsEmpty():
            wx.MessageBox("Rellene todos los campos antes de seguir", "Error", wx.OK | wx.ICON_ERROR)
        else:
            datos = {
                "marca": self.txtMarca.GetValue(),
                "modelo": self.txtModelo.GetValue(),
                "matricula": self.txtMatricula.GetValue(),
                "fecha": self.txtFechaMat.GetValue(),
                "km": self.txtKM.GetValue()
            }
            with open(os.path.join("datos", "temp_datos.json", 'w')) as file:
                json.dump(datos, file)
            
            proc = subprocess.Popen([sys.executable, os.path.join(".", "registro2.py")])
            self.Hide()
            proc.wait()
            if os.path.exists(os.path.join("control", "P_REGISTRO2")):
                self.Show()
            else:
                self.Close()



## Funcion main que se encarga de invocar la ventana
## señalizamos la apertura y cierre de la misma mediante un fichero
####
if __name__ == "__main__":
    try:
        open(os.path.join("control", "P_REGISTRO"), "w").close()
    except Exception as e:
        print(f"error: {e}")
    else:
        print("VentanaRegistroPaso1 abierta")

    app = wx.App()
    frame = VentanaRegistroPaso1(None)
    app.MainLoop()
    
    try:
        os.remove(os.path.join("control", "P_REGISTRO"))
    except Exception as e:
        print(f"error: {e}")
    else:
        print("VentanaRegistroPaso1 cerrada")


