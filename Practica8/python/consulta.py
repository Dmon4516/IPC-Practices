import wx
import wx.grid
import os
import datetime


class VentanaConsulta(wx.Frame):
    def __init__(self, parent):
        wx.Frame.__init__(self, parent, id=wx.ID_ANY, title="Consultar lista vehículos", pos=wx.DefaultPosition,
                          size=wx.Size(800, 600), style=wx.DEFAULT_FRAME_STYLE | wx.TAB_TRAVERSAL)

        self.SetSizeHints(wx.DefaultSize, wx.DefaultSize)
        self.SetBackgroundColour(wx.Colour(240, 240, 240))


        imagen = wx.Bitmap(os.path.join("imagenes", "icono.png"), wx.BITMAP_TYPE_PNG)
        icono = wx.Icon()
        icono.CopyFromBitmap(imagen)
        self.SetIcon(icono)


        boxSizer = wx.BoxSizer(wx.VERTICAL)

        # Add toolbar
        self.toolBar = wx.ToolBar(self, wx.ID_ANY, wx.DefaultPosition, wx.DefaultSize, wx.TB_FLAT | wx.TB_HORZ_TEXT)
        self.toolBar.SetBackgroundColour(wx.Colour(200, 200, 200))  # Toolbar background

        # Toolbar tools and events
        self.toolImprimir = self.toolBar.AddTool(1, "Imprimir",
                                                 wx.Bitmap(os.path.join("imagenes", "imprimir.png"), wx.BITMAP_TYPE_ANY),
                                                 wx.NullBitmap, wx.ITEM_NORMAL, wx.EmptyString,
                                                 "Imprime en papel la lista completa de vehículos en el taller.")
        self.Bind(wx.EVT_TOOL, self.toolImprimirClicked, id=1)

        self.toolActualizar = self.toolBar.AddTool(2, "Actualizar",
                                                   wx.Bitmap(os.path.join("imagenes", "actualizar.png"),
                                                             wx.BITMAP_TYPE_ANY), wx.NullBitmap, wx.ITEM_NORMAL,
                                                   wx.EmptyString,
                                                   "Actualiza los datos de todos los vehículos a su última revisión.")
        self.Bind(wx.EVT_TOOL, self.toolActualizarClicked, id=2)

        self.toolBar.AddSeparator()

        self.toolSalir = self.toolBar.AddTool(3, "Salir",
                                              wx.Bitmap(os.path.join("imagenes", "salir.png"), wx.BITMAP_TYPE_ANY),
                                              wx.NullBitmap, wx.ITEM_NORMAL, wx.EmptyString, "Salir de esta ventana.")
        self.Bind(wx.EVT_TOOL, self.toolSalirClicked, id=3)

        self.toolBar.Realize()
        boxSizer.Add(self.toolBar, 0, wx.EXPAND, 5)

        self.grid = wx.grid.Grid(self)
        self.grid.CreateGrid(5, 6)

        self.headers = ["Fecha", "Tipo Operación", "Producto", "Cantidad", "Total", "Proveedor/Cliente"]
        for col, header in enumerate(self.headers):
            self.grid.SetColLabelValue(col, header)
            self.grid.SetColSize(col, 120 if col == 0 else 100)
            self.grid.SetColFormatFloat(col, width=-1, precision=2)
            self.grid.SetColLabelAlignment(wx.ALIGN_CENTER, wx.ALIGN_CENTER)
            self.grid.SetColLabelSize(20)


        self.data = [
            (datetime.date(2024, 3, 15), "Compra", "Chanel N5", 10, 150.00, "Perfumes Luxury S.L."),
            (datetime.date(2024, 3, 16), "Venta", "Light Blue D&G", 2, 180.00, "Maria Gonzalez"),
            (datetime.date(2024, 3, 17), "Compra", "La Vie Est Belle", 15, 120.00, "Lancôme Distribuciones"),
            (datetime.date(2024, 3, 18), "Venta", "Chanel N5", 1, 180.00, "Juan Perez"),
            (datetime.date(2024, 3, 19), "Venta", "La Vie Est Belle", 3, 100.00, "Ana Martinez")
        ]
        self.populate_table(self.data)

        # Configure grid properties
        self.grid.EnableEditing(False)
        self.grid.AutoSizeColumns(False)
        self.grid.SetRowLabelSize(0)  # Hide row numbers
        self.grid.SetDefaultCellAlignment(wx.ALIGN_CENTER, wx.ALIGN_CENTER)

        # Add grid to layout
        boxSizer.Add(self.grid, 1, wx.EXPAND | wx.ALL, 10)

        # Add status bar
        fecha_hora = datetime.datetime.now()
        fecha_hora_string = fecha_hora.strftime("%d/%m/%Y %H:%M:%S")
        self.statusBar = self.CreateStatusBar(1, wx.STB_SIZEGRIP, wx.ID_ANY)
        self.statusBar.SetStatusText(f"Datos actualizados a {fecha_hora_string}")
        self.statusBar.SetBackgroundColour(wx.Colour(220, 220, 220))  # Light gray status bar

        # Add event for sorting columns
        self.grid.Bind(wx.grid.EVT_GRID_LABEL_LEFT_CLICK, self.on_sort)

        # Finalize layout
        self.SetSizer(boxSizer)
        self.Layout()
        self.Centre(wx.BOTH)
        self.Show()

    def populate_table(self, data):
        self.grid.ClearGrid()
        if self.grid.GetNumberRows() > 0:
            self.grid.DeleteRows(0, self.grid.GetNumberRows())

        self.grid.AppendRows(len(data))

        for row, transaction in enumerate(data):
            self.grid.SetCellValue(row, 0, str(transaction[0]))  # Fecha
            self.grid.SetCellValue(row, 1, transaction[1])       # Tipo Operación
            self.grid.SetCellValue(row, 2, transaction[2])       # Producto
            self.grid.SetCellValue(row, 3, str(transaction[3]))  # Cantidad
            self.grid.SetCellValue(row, 4, f"${transaction[4]:.2f}")  # Total
            self.grid.SetCellValue(row, 5, transaction[5])       # Proveedor/Cliente

    def on_sort(self, event):
        col = event.GetCol()  
        if col < 0 or col >= len(self.headers):
            return

        reverse = getattr(self, "reverse_sort", False)
        self.data.sort(key=lambda x: x[col], reverse=reverse)
        self.reverse_sort = not reverse

        self.populate_table(self.data)

    def toolImprimirClicked(self, event):
        wx.MessageBox("Función de impresión no implementada.", "Imprimir", wx.OK | wx.ICON_INFORMATION)

    def toolActualizarClicked(self, event):
        fecha_hora = datetime.datetime.now()
        fecha_hora_string = fecha_hora.strftime("%d/%m/%Y %H:%M:%S")
        self.statusBar.SetStatusText(f"Datos actualizados a {fecha_hora_string}")
        wx.MessageBox("Datos actualizados satisfactoriamente", "Mensaje", wx.OK | wx.ICON_INFORMATION)

    def toolSalirClicked(self, event):
        self.Close()


if __name__ == "__main__":
    # Signal window open/close with a control file
    try:
        open(os.path.join("control", "CONSULTA"), "w").close()
    except Exception as e:
        print(f"Error: {e}")
    else:
        print("VentanaConsulta abierta")

    app = wx.App()
    frame = VentanaConsulta(None)
    app.MainLoop()

    try:
        os.remove(os.path.join("control", "CONSULTA"))
    except Exception as e:
        print(f"Error: {e}")
    else:
        print("VentanaConsulta cerrada")