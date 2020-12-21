//Realizar Transferencia
var transfeT = document.getElementById('btnTransferencia');

transfeT.addEventListener('click', function() {
    axios.post('http://localhost:4567/transferencia', {
        origen: document.getElementById('origen').value,
        destino: document.getElementById('destino').value,
        monto: document.getElementById('monto').value,
        referencia: document.getElementById('referencia').value
    }).then(function(response) {
        console.log(response)    
        console.log(response.data)    
        console.log(response.data.concep)
        document.getElementById('origen').value = null
        document.getElementById('destino').value = null
        document.getElementById('monto').value = null
        document.getElementById('referencia').value = null
    }).catch(function (error)  {  
        console.log(error)
        })
})