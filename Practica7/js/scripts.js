const transactionsData = [
    {
        fecha: '2024-01-15',
        tipo: 'Venta',
        producto: 'Chanel No. 5',
        cantidad: 2,
        total: 280.00,
        contacto: 'María Sánchez'
    },
    {
        fecha: '2024-04-12',
        tipo: 'Compra',
        producto: 'Givenchy Gentleman',
        cantidad: 3,
        total: 420.00,
        contacto: 'Guthy Sanchez'
    },
    {
        fecha: '2024-05-09',
        tipo: 'Venta',
        producto: 'Hugo Boss Boss Bottled',
        cantidad: 5,
        total: 550.00,
        contacto: 'Nico Muñiz'
    },
    {
        fecha: '2024-03-27',
        tipo: 'Compra',
        producto: 'Eros Versace',
        cantidad: 2,
        total: 300.00,
        contacto: 'Moises Villegas'
    },
    {
        fecha: '2024-11-01',
        tipo: 'Venta',
        producto: 'Invictus Paco Rabanne',
        cantidad: 1,
        total: 120.00,
        contacto: 'Zin Campeador'
    },
    {
        fecha: '2024-03-02',
        tipo: 'Compra',
        producto: 'Eternity Calvin Klein',
        cantidad: 2,
        total: 320.00,
        contacto: 'Manolo Trujillo'
    },
    {
        fecha: '2024-12-14',
        tipo: 'Venta',
        producto: 'Aqua di Gio',
        cantidad: 8,
        total: 1200.00,
        contacto: 'Paco Botella'
    },
    {
        fecha: '2024-07-30',
        tipo: 'Compra',
        producto: 'Muy mio',
        cantidad: 2,
        total: 80.00,
        contacto: 'David Bustamante'
    },
    {
        fecha: '2024-08-17',
        tipo: 'Venta',
        producto: '001 Man',
        cantidad: 8,
        total: 880.00,
        contacto: 'Vinicius Junior'
    },
    {
        fecha: '2024-01-25',
        tipo: 'Compra',
        producto: 'Amor Amor',
        cantidad: 2,
        total: 240.00,
        contacto: 'Oscar Entrecanales'
    },
    {
        fecha: '2024-09-13',
        tipo: 'Venta',
        producto: 'Polo Red Polo Ralph Lauren',
        cantidad: 9,
        total: 560.00,
        contacto: 'Aitor Tilla'
    },
    {
        fecha: '2024-01-31',
        tipo: 'Compra',
        producto: 'Vibrant Leather',
        cantidad: 1,
        total: 110.00,
        contacto: 'Elena Sanchez'
    },
    {
        fecha: '2024-09-09',
        tipo: 'Venta',
        producto: 'LEau dIssey',
        cantidad: 9,
        total: 1350.00,
        contacto: 'Mickey Mouse'
    },
    {
        fecha: '2024-03-25',
        tipo: 'Compra',
        producto: 'Oud Wood Tom Ford',
        cantidad: 1,
        total: 280.00,
        contacto: 'Bob Esponja'
    }
];

// DOM Elements
const table = document.getElementById('tablaCompraventa');
const tableBody = table.querySelector('tbody');
const generalColor = document.getElementById('generalColor');
const textoColor = document.getElementById('textoColor');
const fontSizeSelector = document.getElementById('fontSizeSelector');
const columnToggles = document.querySelectorAll('.column-toggle');

// Populate table with transactions
function populateTable(data) {
    tableBody.innerHTML = ''; // Clear existing rows
    data.forEach(transaction => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td class="fecha">${transaction.fecha}</td>
            <td class="tipo">${transaction.tipo}</td>
            <td class="producto">${transaction.producto}</td>
            <td class="cantidad">${transaction.cantidad}</td>
            <td class="total">${transaction.total.toFixed(2)}€</td>
            <td class="contacto">${transaction.contacto}</td>
        `;
        tableBody.appendChild(row);
    });
}

// Style Customization
generalColor.addEventListener('input', function() {
    document.body.style.backgroundColor = this.value;
    
});

textoColor.addEventListener('input', function() {
    document.body.style.color = this.value;
    const tableText = table.querySelectorAll('td, th');
    tableText.forEach(cell => {
        cell.style.color = this.value;
    });
});

fontSizeSelector.addEventListener('change', function() {
    document.body.style.fontSize = this.value;
});

// Column Toggle Functionality
// todas las columnas son visibles al principio
let visibilidadColumnas = [true, true, true, true, true, true];

// se detecta pulsacion sobre las casillas de visibilidad
$(document).ready(function() {
	$('#fechaCheckbox').change(function() { modificarColumna(1); });
	$('#tipoCheckbox').change(function() { modificarColumna(2); });
	$('#productoCheckbox').change(function() { modificarColumna(3); });
	$('#cantidadCheckbox').change(function() { modificarColumna(4); });
	$('#totalCheckbox').change(function() { modificarColumna(5); });
	$('#contactoCheckbox').change(function() { modificarColumna(6); });
});

// se oculta o hace visible una columna segun su estado actual
function modificarColumna(indice) {
	var tabla = $('#tablaCompraventa');
	let valor = visibilidadColumnas[indice-1];
	let selector = `td:nth-child(${indice}), th:nth-child(${indice})`;
	
	if (valor == true) {
		visibilidadColumnas.splice(indice-1, 1, false);
		tabla.find(selector).hide();
	} else {
		visibilidadColumnas.splice(indice-1, 1, true);
		tabla.find(selector).show();
	}
}

// aseguro coherencia de las casillas si se hace F5
function restablecerCasillas() {
	$('#fechaCheckbox').prop('checked', true);
	$('#tipoCheckbox').prop('checked', true);
	$('#productoCheckbox').prop('checked', true);
	$('#cantidadCheckbox').prop('checked', true);
	$('#totalCheckbox').prop('checked', true);
	$('#contactoCheckbox').prop('checked', true);
}

window.onload = restablecerCasillas;


// Initial table population and setup
document.addEventListener('DOMContentLoaded', () => {
    // Set initial color pickers to match default page style
    generalColor.value = '#ffffff';
    textoColor.value = '#000000';
    
    // Populate initial table
    populateTable(transactionsData);
});