const transactionsData = [
    {
        fecha: '2024-01-15',
        tipo: 'Venta',
        producto: 'Chanel No. 5',
        cantidad: 2,
        total: 280.00,
        contacto: 'María Sánchez'
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
// Booleano de ordenamiento de campos
// Esto esta aqui para que ordene las filas de la tabla en sentido ascendente dependiendo de los valores
// de una columna si se le da click a su header y que la ordene en sentido descendente si, y solo si, se
// le da click a ese header otra vez. Si se le da click a otro, se ordena la tabla en sentido ascendente
// dependiendo de los valores de esa columna
var columnaOrden = [true, true, true, true, true, true]; 

// se detecta pulsacion sobre las casillas de visibilidad
$(document).ready(function() {
	$('#fechaCheckbox').change(function() { modificarColumna(1); });
	$('#tipoCheckbox').change(function() { modificarColumna(2); });
	$('#productoCheckbox').change(function() { modificarColumna(3); });
	$('#cantidadCheckbox').change(function() { modificarColumna(4); });
	$('#totalCheckbox').change(function() { modificarColumna(5); });
	$('#contactoCheckbox').change(function() { modificarColumna(6); });
    $('th').click(function(){reordenarColumna($(this).index())});
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

function reordenarColumna(indice) {
    const filas = table.rows;
    const nFilas = filas.length; //nFilas = nCeldas por columna + header (3 + 1, es decir, 0 a 3)
    //nColumnas = nCeldas por fila = 6, es decir 0 a 5
    var arrayColumna = []; //donde se guardan los valores de la columna indicada por indice
    var arrayPosiciones = []; //donde se guardan el orden nuevo de los valores

    //var celda=filas[0].getElementsByTagName("TD"); devuelve las celdas del header

    for (i = 1; i < nFilas; i++) { //Guarda los datos de la columna indicada por el indice para ordenarlos
        arrayColumna.push(filas[i].getElementsByTagName("TD")[indice]);
    }

    if (columnaOrden[indice]) { //Ordena los datos de la columna en sentido ascendente 
        arrayColumna.sort();
    } else { //O en sentido descendente
        arrayColumna.sort().reverse();
    }

    for (i = 1; i < nFilas; i++) { //Guarda las posiciones nuevas de los elementos tras reordenar la tabla
        for (j = i; j < nFilas; j++) {
            if (filas[j].getElementsByTagName("TD")[indice] == arrayColumna[i-1]) {
                arrayPosiciones.push(j);
            }
        }
    }

    var arrayTablas = [[], [], []];
    var arrayFila = [];
    var posicion = arrayPosiciones[i-1]; //Su indice indica a que posicion se cambia la fila y el valor que devuelve es cual fila cambia
    var fila;

    for (i = 1; i < nFilas; i++) { //Bucle que elimina toda la tabla
        for (j = 0; j < 6; j++) { //Guarda los valores previos de cada fila indicada por la posicion
            arrayFila.push(filas[posicion].getElementsByTagName("TD")[j])
        }
        arrayTablas.push(arrayFila);
        arrayFila = [];
        table.deleteRow(posicion); //Borra fila de la posicion en la que esta
    }

    for (i = 1; i < nFilas; i++) { //Bucle que la recrea
        fila = table.insertRow(i); //Recrea la fila en su nueva posicion sin valores
        for (j = 0; j < 6; j++) { //Reinserta los valores de las celdas de la fila
            let celda = fila.insertCell(j);
            celda.innerHTML(arrayTablas[i][j]);
        }
    }

    for(i = 0; i < columnaOrden.length; i++) {
        if (i != indice && !columnaOrden[i]) {
            columnaOrden[i] = true; //Cambia todos los valores al inicial
        }
    }
    columnaOrden[indice] = !columnaOrden[indice]; //Cambia el booleano de ordenamiento indicado por el indice a su otro valor    
}