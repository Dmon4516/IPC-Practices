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
const bgColorPicker = document.getElementById('generalColor');
const textColorPicker = document.getElementById('textoColor');
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
bgColorPicker.addEventListener('input', function() {
    document.body.style.backgroundColor = this.value;
    
});

textColorPicker.addEventListener('input', function() {
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
columnToggles.forEach(toggle => {
    toggle.addEventListener('change', function() {
        const columnClass = this.value;
        const cells = document.querySelectorAll(`.${columnClass}`);
        cells.forEach(cell => {
            cell.style.display = this.checked ? '' : 'none';
        });
    });
});

// Initial table population and setup
document.addEventListener('DOMContentLoaded', () => {
    // Set initial color pickers to match default page style
    bgColorPicker.value = '#ffffff';
    textColorPicker.value = '#000000';
    
    // Populate initial table
    populateTable(transactionsData);
});