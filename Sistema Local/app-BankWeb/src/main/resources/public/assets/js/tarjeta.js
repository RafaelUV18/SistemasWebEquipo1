//Apartado de AGREGAR 
var botonAT = document.getElementById('agregarTarjeta');

botonAT.addEventListener('click', function() {
    axios.post('http://localhost:4567/agregarTarjeta', {
        numero: document.getElementById('numero').value,
        vencimiento: document.getElementById('vencimiento').value,
        cvc: document.getElementById('cvc').value,
        no_Cuenta: document.getElementById('cuenta').value,
    }).then(function (response)  {    
        console.log(response)    
        console.log(response.data)    
        console.log(response.statusText)
        document.getElementById('numero').value = null
        document.getElementById('vencimiento').value = null
        document.getElementById('cvc').value = null
        document.getElementById('cuenta').value = null
    }).catch(function (error)  {  
        console.log(error)
    })
})

//Apartado de BORRAR
var idDelt = document.getElementById('idDeletet');
var botonDelt = document.getElementById('btnDeletet');

//Obtener datos
idDelt.addEventListener('click', function() {
    var id = document.getElementById('idDelt').value;
    const parametros = new URLSearchParams();
    parametros.append('PrmID', id);
    axios.post('http://localhost:4567/tarjeta',   {    
        numero:  document.getElementById('idDelt').value
    }).then(function (response)  {        
        console.log(response)        
        console.log(response.data)     
        document.getElementById('del_numero').value =  response.data.numero
        document.getElementById('del_vencimiento').value = response.data.vencimiento
        document.getElementById('del_cvc').value = response.data.cvc
        document.getElementById('del_cuenta').value = response.data.cuenta
    }).catch(function (error)  {   
        console.log(error)     
    })
})
//Borrar Tarjeta
botonDelt.addEventListener('click', function() {
    axios.post('http://localhost:4567/tarjetaBorrar', {    
        numero:  document.getElementById('idDelt').value,
    }).then(function (response)  {        
        console.log(response)        
        console.log(response.data)        
        console.log(response.statusText)
        document.getElementById('idDelt').value = null
        document.getElementById('del_numero').value = null
        document.getElementById('del_vencimiento').value = null
        document.getElementById('del_cvc').value = null
        document.getElementById('del_cuenta').value = null
    }).catch(function (error)  {        
        console.log(error)    
    })
})