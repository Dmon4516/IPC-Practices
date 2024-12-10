import wx
import wx.xrc
import gettext
import os
import subprocess
_ = gettext.gettext
import sys
import json

## Ventana para el registro de coches en la base de datos (Paso 2)
##
## Autores: Oscar Entrecanales
## Version: Diciembre 2024
####

acabado = 0
# Archivo intermedio y archivo final
temp_file = 'temp_datos.json'
final_file = 'datos_combinados.json'

class VentanaRegistroPaso2 ( wx.Frame ):

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

        self.tituloPaso2 = wx.StaticText( self.pnlTitulo, wx.ID_ANY, _(u"Paso 2 de 2: Datos del usuario"), wx.DefaultPosition, wx.DefaultSize, 0 )
        self.tituloPaso2.Wrap( -1 )

        self.tituloPaso2.SetFont( wx.Font( 15, wx.FONTFAMILY_DEFAULT, wx.FONTSTYLE_NORMAL, wx.FONTWEIGHT_NORMAL, False, wx.EmptyString ) )
        self.tituloPaso2.SetForegroundColour( wx.SystemSettings.GetColour( wx.SYS_COLOUR_BTNTEXT ) )

        layoutTitulo.Add( self.tituloPaso2, 0, wx.ALL, 5 )


        self.pnlTitulo.SetSizer( layoutTitulo )
        self.pnlTitulo.Layout()
        layoutPaneles.Add( self.pnlTitulo, 1, wx.BOTTOM|wx.EXPAND, 1 )

        self.pnlFormulario = wx.Panel( self, wx.ID_ANY, wx.DefaultPosition, wx.Size( 400,200 ), wx.TAB_TRAVERSAL )
        self.pnlFormulario.SetBackgroundColour( wx.SystemSettings.GetColour( wx.SYS_COLOUR_WINDOW ) )

        layoutFormulario = wx.GridSizer( 0, 2, 0, 0 )

        self.lblNomYAp = wx.StaticText( self.pnlFormulario, wx.ID_ANY, _(u"Nombre y Apellidos:"), wx.DefaultPosition, wx.DefaultSize, 0 )
        self.lblNomYAp.Wrap( -1 )

        self.lblNomYAp.SetFont( wx.Font( wx.NORMAL_FONT.GetPointSize(), wx.FONTFAMILY_DEFAULT, wx.FONTSTYLE_NORMAL, wx.FONTWEIGHT_BOLD, False, wx.EmptyString ) )

        layoutFormulario.Add( self.lblNomYAp, 0, wx.ALL, 5 )

        self.txtNomYAp = wx.TextCtrl( self.pnlFormulario, wx.ID_ANY, wx.EmptyString, wx.DefaultPosition, wx.DefaultSize, 0 )
        layoutFormulario.Add( self.txtNomYAp, 0, wx.ALL|wx.EXPAND, 5 )

        self.lblNIF = wx.StaticText( self.pnlFormulario, wx.ID_ANY, _(u"Número de identificacion fiscal:"), wx.DefaultPosition, wx.DefaultSize, 0 )
        self.lblNIF.Wrap( -1 )

        self.lblNIF.SetFont( wx.Font( wx.NORMAL_FONT.GetPointSize(), wx.FONTFAMILY_DEFAULT, wx.FONTSTYLE_NORMAL, wx.FONTWEIGHT_BOLD, False, wx.EmptyString ) )

        layoutFormulario.Add( self.lblNIF, 0, wx.ALL, 5 )

        self.txtNIF = wx.TextCtrl( self.pnlFormulario, wx.ID_ANY, wx.EmptyString, wx.DefaultPosition, wx.DefaultSize, 0 )
        layoutFormulario.Add( self.txtNIF, 0, wx.ALL|wx.EXPAND, 5 )

        self.lblTelefono = wx.StaticText( self.pnlFormulario, wx.ID_ANY, _(u"Telefono:"), wx.DefaultPosition, wx.DefaultSize, 0 )
        self.lblTelefono.Wrap( -1 )

        self.lblTelefono.SetFont( wx.Font( wx.NORMAL_FONT.GetPointSize(), wx.FONTFAMILY_DEFAULT, wx.FONTSTYLE_NORMAL, wx.FONTWEIGHT_BOLD, False, wx.EmptyString ) )

        layoutFormulario.Add( self.lblTelefono, 0, wx.ALL, 5 )

        self.txtTelefono = wx.TextCtrl( self.pnlFormulario, wx.ID_ANY, wx.EmptyString, wx.DefaultPosition, wx.DefaultSize, 0 )
        layoutFormulario.Add( self.txtTelefono, 0, wx.ALL|wx.EXPAND, 5 )

        self.lblEmail = wx.StaticText( self.pnlFormulario, wx.ID_ANY, _(u"Correo electrónico:"), wx.DefaultPosition, wx.DefaultSize, 0 )
        self.lblEmail.Wrap( -1 )

        self.lblEmail.SetFont( wx.Font( wx.NORMAL_FONT.GetPointSize(), wx.FONTFAMILY_DEFAULT, wx.FONTSTYLE_NORMAL, wx.FONTWEIGHT_BOLD, False, wx.EmptyString ) )

        layoutFormulario.Add( self.lblEmail, 0, wx.ALL, 5 )

        self.txtEmail = wx.TextCtrl( self.pnlFormulario, wx.ID_ANY, wx.EmptyString, wx.DefaultPosition, wx.DefaultSize, 0 )
        layoutFormulario.Add( self.txtEmail, 0, wx.ALL|wx.EXPAND, 5 )

        self.lblFechaNac = wx.StaticText( self.pnlFormulario, wx.ID_ANY, _(u"Fecha de nacimiento:"), wx.DefaultPosition, wx.DefaultSize, 0 )
        self.lblFechaNac.Wrap( -1 )

        self.lblFechaNac.SetFont( wx.Font( wx.NORMAL_FONT.GetPointSize(), wx.FONTFAMILY_DEFAULT, wx.FONTSTYLE_NORMAL, wx.FONTWEIGHT_BOLD, False, wx.EmptyString ) )

        layoutFormulario.Add( self.lblFechaNac, 0, wx.ALL, 5 )

        self.txtFechaNac = wx.TextCtrl( self.pnlFormulario, wx.ID_ANY, wx.EmptyString, wx.DefaultPosition, wx.DefaultSize, 0 )
        layoutFormulario.Add( self.txtFechaNac, 0, wx.ALL|wx.EXPAND, 5 )


        self.pnlFormulario.SetSizer( layoutFormulario )
        self.pnlFormulario.Layout()
        layoutPaneles.Add( self.pnlFormulario, 4, wx.ALL|wx.EXPAND, 1 )

        self.pnlBotones = wx.Panel( self, wx.ID_ANY, wx.DefaultPosition, wx.DefaultSize, wx.TAB_TRAVERSAL )
        layoutBotones = wx.BoxSizer( wx.HORIZONTAL )

        layoutBotones.Add( ( 0, 0), 1, wx.EXPAND, 5 )

        self.botAnterior = wx.Button( self.pnlBotones, wx.ID_ANY, _(u"Anterior"), wx.DefaultPosition, wx.DefaultSize, 0 )
        layoutBotones.Add( self.botAnterior, 0, wx.ALL, 5 )
        self.botAnterior.Bind(wx.EVT_BUTTON, self.botAnteriorClicked)

        self.botFinalizar = wx.Button( self.pnlBotones, wx.ID_ANY, _(u"Finalizar"), wx.DefaultPosition, wx.DefaultSize, 0 )
        layoutBotones.Add( self.botFinalizar, 0, wx.ALL, 5 )
        self.botFinalizar.Bind(wx.EVT_BUTTON, self.botFinalizarClicked)

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

    def botAnteriorClicked(self, event):
        self.Close()

    def botFinalizarClicked(self, event):
        if self.txtNomYAp.IsEmpty() or self.txtNIF.IsEmpty() or self.txtTelefono.IsEmpty() or self.txtEmail.IsEmpty() or self.txtFechaNac.IsEmpty():
            wx.MessageBox("Rellene todos los campos antes de seguir", "Error", wx.OK | wx.ICON_ERROR)
        else:
            ## Si pusieramos un paso posterior:
            ## subprocess.Popen(["python", ".\\registro2.py"]) 
            ## self.Hide()
            # Leer datos del paso 1
            with open(os.path.join("datos", "temp_datos.json"), 'r') as file:
                datos_paso1 = json.load(file)

            # Guardar datos combinados
            datos_paso2 = {
                "nombre": self.txtNombre.GetValue(),
                "nif": self.txtNIF.GetValue(),
                "telefono": self.txtTelefono.GetValue(),
                "email": self.txtEmail.GetValue(),
                "nacimiento": self.txtNacimiento.GetValue()
            }
            nuevo_registro = {**datos_paso1, **datos_paso2}

            # Leer datos existentes
            if os.path.exists(os.path.join("datos", "datos.json")):
                with open(os.path.join("datos", "datos.json"), 'r') as file:
                    datos_existentes = json.load(file)
            else:
                datos_existentes = []

            # Añadir nuevo registro y guardar
            datos_existentes.append(nuevo_registro)
            with open(os.path.join("datos", "datos.json"), 'w') as file:
                json.dump(datos_existentes, file, indent=4)

            wx.MessageBox("Datos guardados correctamente", "Éxito", wx.OK | wx.ICON_INFORMATION)

            ## Si acaba aqui:
            global acabado
            acabado = 1
            self.Close()

## Funcion main que se encarga de invocar la ventana
## señalizamos la apertura y cierre de la misma mediante un fichero
####
if __name__ == "__main__" :
    try:
        open(os.path.join("control", "P_REGISTRO2"), "w").close()
    except Exception as e:
        print(f"error: {e}")
    else:
        print("VentanaRegistroPaso2 abierta")

    app = wx.App()
    frame = VentanaRegistroPaso2(None)
    app.MainLoop()
    
    try:
        if acabado == 1:
            os.remove(os.path.join("control", "P_REGISTRO2"))
            os.remove(os.path.join("datos", "temp_datos.json"))
        acabado = 0
    except Exception as e:
        print(f"error: {e}")
    else:
        print("VentanaRegistroPaso2 cerrada")


