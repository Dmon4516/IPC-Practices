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
import os
_ = gettext.gettext

###########################################################################
## Class MyFrame1
###########################################################################

class MyFrame1 ( wx.Frame ):

    def __init__( self, parent ):
        wx.Frame.__init__ ( self, parent, id = wx.ID_ANY, title = wx.EmptyString, pos = wx.DefaultPosition, size = wx.Size( 500,300 ), style = wx.DEFAULT_FRAME_STYLE|wx.TAB_TRAVERSAL )

        self.SetSizeHints( wx.DefaultSize, wx.DefaultSize )

        bSizer1 = wx.BoxSizer( wx.VERTICAL )
        bSizer1.SetMinSize( wx.Size( 500,300 ) )

        
        self.m_staticText1 = wx.StaticText( self, wx.ID_ANY, _(u"MyLabel"), wx.DefaultPosition, wx.DefaultSize, 0 )
        self.m_staticText1.Wrap( -1 )
        bSizer1.Add( self.m_staticText1, 0, wx.ALL, 5 )

        self.SetSizer( bSizer1 )
        self.Layout()

        self.Centre( wx.BOTH )
        self.Show()

    def __del__( self ):
        pass


if __name__ == "__main__":
    try:
        open('./control/P_CONSULTA', "w").close()
    except OSError:
        print("fail")
    else:
        print("ok")

    app = wx.App()
    frame = MyFrame1(None)
    app.MainLoop()
    
    try:
        os.remove('./control/P_CONSULTA')
    except Exception as e:
        print(f"fail {e}")
    else:
        print("ok")