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
        imagen = wx.Bitmap('./imagenes/icono.png', wx.BITMAP_TYPE_PNG)
        icono = wx.Icon()
        icono.CopyFromBitmap(imagen)
        self.SetIcon(icono)

        # establecer dimensionado de los elementos
        boxSizer = wx.BoxSizer( wx.VERTICAL )
        boxSizer.SetMinSize( wx.Size( 250,200 ) )

        # agregar los botones, las acciones que desencadenan y el espaciado vertical entre ellos
        self.botRegistrar = wx.Button( self, wx.ID_ANY, _(u"Registrar nuevo vehículo"), wx.DefaultPosition, wx.Size(180, 30), 0 )
        boxSizer.Add((0, 24), 0, 0, 0)
        boxSizer.Add( self.botRegistrar, 0, wx.CENTER, 5 )
        #self.botRegistrar.Bind(wx.EVT_BUTTON, self.botRegistrarClicked)

        self.botConsultar = wx.Button( self, wx.ID_ANY, _(u"Consultar lista vehículos"), wx.DefaultPosition, wx.Size(180, 30), 0 )
        boxSizer.Add((0, 6), 0, 0, 0)
        boxSizer.Add( self.botConsultar, 0, wx.CENTER, 5 )
        self.botConsultar.Bind(wx.EVT_BUTTON, self.botConsultarClicked)

        self.botSalir = wx.Button( self, wx.ID_ANY, _(u"Salir"), wx.DefaultPosition, wx.Size(180, 30), 0 )
        boxSizer.Add((0, 18), 0, 0, 0)
        boxSizer.Add( self.botSalir, 0, wx.CENTER, 5 )
        self.botSalir.Bind(wx.EVT_BUTTON, self.botSalirClicked)

        self.SetSizer( boxSizer )
        self.Layout()
        self.Centre( wx.BOTH )
        self.Show()


    ## Metodo que se encarga de la accion realizada al pulsar el boton de salir
    def botSalirClicked(self, event):

        # comprobamos que no quede ninguna ventana abierta
        if os.path.exists('./control/P_CONSULTA'):
            wx.MessageBox('Cierre todas las ventanas antes de salir del programa', 'Error', wx.OK | wx.ICON_ERROR)
        else:
            self.Close()


    def botConsultarClicked(self, event):

        # comprobamos si la ventana ya esta abierta
        if os.path.exists('./control/P_CONSULTA'):
            wx.MessageBox('Esta ventana ya se encuentra abierta', 'Error', wx.OK | wx.ICON_ERROR)
        else:
            subprocess.Popen(['python', './consulta.py'])


## Funcion main que se encargara de invocar la ventana
####
if __name__ == "__main__":
    app = wx.App()
    frame = VentanaPrincipal(None)
    app.MainLoop()
