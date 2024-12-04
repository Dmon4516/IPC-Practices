import wx
import wx.xrc
import gettext
import subprocess
import os
_ = gettext.gettext


## Ventana principal que permite acceder a las demas secciones del programa
##
## Autores: Luis Setién, Victor Descalzo
## Version: Diciembre 2024
####
class VentanaPrincipal ( wx.Frame ):

    ## Metodo que se encarga de inicializar el contenido de la ventana
    def __init__( self, parent ):
        wx.Frame.__init__ ( self, parent, id = wx.ID_ANY, title = "Taller Pepe", pos = wx.DefaultPosition, size = wx.Size( 250,200 ), style = wx.MINIMIZE_BOX | wx.SYSTEM_MENU | wx.CAPTION | wx.CLOSE_BOX | wx.CLIP_CHILDREN )

        self.SetSizeHints( wx.DefaultSize, wx.DefaultSize )
        self.SetBackgroundColour( wx.SystemSettings.GetColour( wx.SYS_COLOUR_MENU ) )

        # establecer icono de la ventana
        imagen = wx.Bitmap(".\\imagenes\\icono.png", wx.BITMAP_TYPE_PNG)
        icono = wx.Icon()
        icono.CopyFromBitmap(imagen)
        self.SetIcon(icono)

        # establecer dimensionado de los elementos
        boxSizer = wx.BoxSizer( wx.VERTICAL )
        self.SetMinSize( wx.Size( 250,200 ) )

        # agregar los botones, las acciones que desencadenan y el espaciado vertical entre ellos
        self.botRegistrar = wx.Button( self, wx.ID_ANY, _(u"\u0332".join("Re") + "gistrar nuevo vehículo"), wx.DefaultPosition, wx.Size(180, 30), 0 )
        boxSizer.Add((0, 24), 0, 0, 0)
        boxSizer.Add( self.botRegistrar, 0, wx.CENTER, 5 )
        self.botRegistrar.Bind(wx.EVT_BUTTON, self.botRegistrarClicked)

        self.botConsultar = wx.Button( self, wx.ID_ANY, _(u"\u0332".join("Co") + "nsultar lista vehículos"), wx.DefaultPosition, wx.Size(180, 30), 0 )
        boxSizer.Add((0, 6), 0, 0, 0)
        boxSizer.Add( self.botConsultar, 0, wx.CENTER, 5 )
        self.botConsultar.Bind(wx.EVT_BUTTON, self.botConsultarClicked)

        self.botSalir = wx.Button( self, wx.ID_ANY, _(u"\u0332".join("Sa") + "lir"), wx.DefaultPosition, wx.Size(180, 30), 0 )
        boxSizer.Add((0, 18), 0, 0, 0)
        boxSizer.Add( self.botSalir, 0, wx.CENTER, 5 )
        self.botSalir.Bind(wx.EVT_BUTTON, self.botSalirClicked)

        # establecer atajos de teclado
        self.Bind(wx.EVT_KEY_DOWN, self.keyRegistrarPressed)
        self.Bind(wx.EVT_KEY_DOWN, self.keyConsultarPressed)
        self.Bind(wx.EVT_KEY_DOWN, self.keySalirPressed)

        # ajustar posicionamiento de la ventana
        self.SetSizer( boxSizer )
        self.SetFocus()
        self.Layout()
        self.SetPosition((125, 125))
        self.Show()


    ## Metodo que se encarga de la accion realizada al pulsar el boton de registrar
    ## comprobamos que dicha ventana no se encuentre ya abierta
    def botRegistrarClicked(self, event):
        if os.path.exists(".\\control\\P_REGISTRO"):
            wx.MessageBox("Esta ventana ya se encuentra abierta", "Error", wx.OK | wx.ICON_ERROR)
        else:
            subprocess.Popen(["python", ".\\registro.py"])


    ## Metodo que se encarga de la accion realizada al pulsar el boton de consultar
    ## comprobamos que dicha ventana no se encuentre ya abierta
    def botConsultarClicked(self, event):
        if os.path.exists(".\\control\\P_CONSULTA"):
            wx.MessageBox("Esta ventana ya se encuentra abierta", "Error", wx.OK | wx.ICON_ERROR)
        else:
            subprocess.Popen(["python", ".\\consulta.py"])


    ## Metodo que se encarga de la accion realizada al pulsar el boton de salir
    ## comprobamos que no quede ninguna ventana abierta antes de salir
    def botSalirClicked(self, event):
        if os.path.exists(".\\control\\P_REGISTRO") or os.path.exists(".\\control\\P_CONSULTA"):
            wx.MessageBox("Cierre todas las ventanas antes de salir del programa", "Error", wx.OK | wx.ICON_ERROR)
        else:
            self.Close()


    ## Metodo que se encarga de la accion realizada al hacer Alt + S
    ## el cual corresponde al atajo de teclado para registro
    def keyRegistrarPressed(self, event):
        keycode = event.GetKeyCode()
        altDown = event.AltDown()
        if keycode == ord('R') and altDown:
            print("ALT + R detectado")
            self.botRegistrarClicked(self)
        else:
            event.Skip()


    ## Metodo que se encarga de la accion realizada al hacer Alt + S
    ## el cual corresponde al atajo de teclado para consulta
    def keyConsultarPressed(self, event):
        keycode = event.GetKeyCode()
        altDown = event.AltDown()
        if keycode == ord('C') and altDown:
            print("ALT + C detectado")
            self.botConsultarClicked(self)
        else:
            event.Skip()


    ## Metodo que se encarga de la accion realizada al hacer Alt + S
    ## el cual corresponde al atajo de teclado para salir
    def keySalirPressed(self, event):
        keycode = event.GetKeyCode()
        altDown = event.AltDown()
        if keycode == ord('S') and altDown:
            print("ALT + S detectado")
            self.botSalirClicked(self)
        else:
            event.Skip()


## Funcion main que se encarga de invocar la ventana
####
if __name__ == "__main__":
    app = wx.App()
    frame = VentanaPrincipal(None)
    app.MainLoop()
