//Apartado de AGREGAR Cuenta
var botonAgregarC = document.getElementById('agregarCuenta');

botonAgregarC.addEventListener('click', function() {
    axios.post('http://localhost:4567/cuentaAgregar', {
        no_Cuenta: document.getElementById('numCuentaA').value,
    }).then(function (response)  {    
        console.log(response)    
        console.log(response.data)    
        console.log(response.statusText)
        document.getElementById('numCuentaA').value = null
    }).catch(function (error)  {  
        console.log(error)
    })
})

//Apartado de EDITAR Cuenta
var busquedaC = document.getElementById('SCuenta');
var botonEditarC = document.getElementById('btn_EditarC');

//Obtener datos
busquedaC.addEventListener('click',function(){    
    var no_Cuenta = document.getElementById('No_Cuenta_S').value;
    const parametros = new URLSearchParams();
    parametros.append('No_Cuenta', no_Cuenta);
    axios.post('http://localhost:4567/cuenta', {
        no_Cuenta: document.getElementById('No_Cuenta_S').value
    }).then(function (response) {
        console.log(response)
        console.log(response.data)
        console.log(response.statusText)
        document.getElementById('No_Cuenta').value = response.data.no_cuenta
    }).catch(function (error) {
        console.log(error)
    })
})
//Editar Cuenta
botonEditarC.addEventListener('click',function(){ 
    axios.post('http://localhost:4567/cuentaEditar',{
        no_Cuenta: document.getElementById('No_Cuenta').value,
        anterior: document.getElementById('No_Cuenta_S').value
    }).then(function (response) {
        console.log(response)
        console.log(response.data)
        console.log(response.statusText)
        document.getElementById('No_Cuenta_S').value = null
        document.getElementById('No_Cuenta').value = null
    }).catch(function (error) {
        console.log(error)
    })
})

//Apartado de ELIMINAR Cuenta
var busquedaCE = document.getElementById('buscarCuentaE');
var botonEliminarC = document.getElementById('eliminarCuenta');

//Obtener datos
busquedaCE.addEventListener('click',function(){    
    var no_Cuenta = document.getElementById('numCuentaE').value;
    const parametros = new URLSearchParams();
    parametros.append('no_Cuenta', no_Cuenta);
    axios.post('http://localhost:4567/buscarEliminar', {
        no_Cuenta: document.getElementById('numCuentaE').value
    }).then(function (response) {
        console.log(response)
        console.log(response.data)
        console.log(response.statusText)
        document.getElementById('numCuentaEnco').value = response.data.no_cuenta
    }).catch(function (error) {
        console.log(error)
    })
})
//Eliminar Cuenta
botonEliminarC.addEventListener('click',function(){
    axios.post('http://localhost:4567/cuentaBorrar',{
        no_Cuenta : document.getElementById('numCuentaE').value,
    }).then(function (response) {
        console.log(response)
        console.log(response.data)
        console.log(response.statusText)
        document.getElementById('numCuentaE').value = null
        document.getElementById('numCuentaEnco').value = null
    }).catch(function (error) {
        console.log(error)
    })
})