# -*- coding: utf-8 -*-

###########################################################################
## Python code generated with wxFormBuilder (version 4.2.1-0-g80c4cb6)
## http://www.wxformbuilder.org/
##
## PLEASE DO *NOT* EDIT THIS FILE!
###########################################################################

import wx
import wx.xrc

import gettext
_ = gettext.gettext

###########################################################################
## Class MyFrame1
###########################################################################

class MyFrame1 ( wx.Frame ):

    def __init__( self, parent ):
        wx.Frame.__init__ ( self, parent, id = wx.ID_ANY, title = "Taller Pepe", pos = wx.DefaultPosition, size = wx.Size( 250,200 ), style = wx.MINIMIZE_BOX | wx.SYSTEM_MENU | wx.CAPTION | wx.CLOSE_BOX | wx.CLIP_CHILDREN )

        self.SetSizeHints( wx.DefaultSize, wx.DefaultSize )
        self.SetBackgroundColour( wx.SystemSettings.GetColour( wx.SYS_COLOUR_MENU ) )

        # establecer icono
        bitmap = wx.Bitmap('./imagenes/icono.png', wx.BITMAP_TYPE_PNG)
        icon = wx.Icon()
        icon.CopyFromBitmap(bitmap)
        self.SetIcon(icon)

        bSizer1 = wx.BoxSizer( wx.VERTICAL )

        bSizer1.SetMinSize( wx.Size( 250,200 ) )
        self.m_botRegistrar = wx.Button( self, wx.ID_ANY, _(u"Registrar nuevo vehículo"), wx.DefaultPosition, wx.DefaultSize, 0 )
        bSizer1.Add( self.m_botRegistrar, 0, wx.ALL, 5 )

        self.m_botConsultar = wx.Button( self, wx.ID_ANY, _(u"Consultar lista vehículos"), wx.DefaultPosition, wx.DefaultSize, 0 )
        bSizer1.Add( self.m_botConsultar, 0, wx.ALL, 5 )

        self.m_espaciado = wx.StaticText( self, wx.ID_ANY, wx.EmptyString, wx.DefaultPosition, wx.DefaultSize, 0 )
        self.m_espaciado.Wrap( -1 )

        bSizer1.Add( self.m_espaciado, 0, wx.ALL, 5 )

        self.m_botSalir = wx.Button( self, wx.ID_ANY, _(u"Salir"), wx.DefaultPosition, wx.DefaultSize, 0 )
        bSizer1.Add( self.m_botSalir, 0, wx.ALL, 5 )


        self.SetSizer( bSizer1 )
        self.Layout()

        self.Centre( wx.BOTH )
        self.Show()

    def __del__( self ):
        pass

if __name__ == "__main__":
    app = wx.App()
    frame = MyFrame1(None)
    app.MainLoop()


