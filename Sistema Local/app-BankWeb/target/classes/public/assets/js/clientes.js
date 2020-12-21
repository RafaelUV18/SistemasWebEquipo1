//Apartado de AGREGAR Cliente
var botonAg = document.getElementById('agregarCliente');

botonAg.addEventListener('click', function() {
    axios.post('http://localhost:4567/clienteAgregar', {
        nombre: document.getElementById('nombreA').value,
        apellidos: document.getElementById('apellidoA').value,
        edad: document.getElementById('edadA').value,
        correo: document.getElementById('correoA').value,
        direccion: document.getElementById('direccionA').value,
        contrasena: document.getElementById('contrasenaA').value,
        telefono: document.getElementById('telefonoA').value,
        no_Cuenta: document.getElementById('cuentaA').value
    }).then(function (response)  {    
        console.log(response)    
        console.log(response.data)    
        console.log(response.statusText)
        document.getElementById('nombreA').value = null
        document.getElementById('apellidoA').value = null
        document.getElementById('edadA').value = null
        document.getElementById('correoA').value = null
        document.getElementById('direccionA').value = null
        document.getElementById('contrasenaA').value = null
        document.getElementById('contasenaC').value = null
        document.getElementById('telefonoA').value = null
        document.getElementById('cuentaA').value = null
    })
    .catch(function (error)  {  
        console.log(error)
    })
})

//Apartado de EDITAR Cliente
var findC = document.getElementById('buscarCliente');
var botonEC = document.getElementById('btnEditC');

//Obtener datos
findC.addEventListener('click',function(){    
    var id=document.getElementById('iDCliente').value;
    const parametros=new URLSearchParams();
    parametros.append('PrmID', id);
    axios.post('http://localhost:4567/cliente', {
        id : document.getElementById('iDCliente').value
    }).then(function (response) {
        console.log(response)
        console.log(response.data)
        console.log(response.statusText)
        document.getElementById('nombre').value = response.data.nombre,
        document.getElementById('apellido').value = response.data.apellidos,
        document.getElementById('edad').value = response.data.edad,
        document.getElementById('correo').value = response.data.correo,
        document.getElementById('direc').value= response.data.direccion,
        document.getElementById('contra').value = response.data.contrasena,
        document.getElementById('contraC').value = response.data.contrasena,
        document.getElementById('tel').value = response.data.telefono,
        document.getElementById('cuenta').value = response.data.cuenta       
    }).catch(function (error) {
        console.log(error)
    })
})
//Editar Cliente
botonEC.addEventListener('click',function(){ 
    axios.post('http://localhost:4567/clienteEditar',{
        id : document.getElementById('iDCliente').value,
        nombre: document.getElementById('nombre').value,
        apellidos: document.getElementById('apellido').value,
        edad: document.getElementById('edad').value,
        correo: document.getElementById('correo').value,
        direccion: document.getElementById('direc').value,
        contrasena: document.getElementById('contra').value,
        telefono: document.getElementById('tel').value,
        no_Cuenta: document.getElementById('cuenta').value
    }).then(function (response) {
        console.log(response)
        console.log(response.data)
        console.log(response.statusText)
        document.getElementById('iDCliente').value = null
        document.getElementById('nombre').value = null
        document.getElementById('apellido').value = null
        document.getElementById('edad').value = null
        document.getElementById('correo').value = null
        document.getElementById('direc').value = null
        document.getElementById('contra').value = null
        document.getElementById('contraC').value = null
        document.getElementById('tel').value = null
        document.getElementById('cuenta').value = null
    }).catch(function (error) {
        console.log(error)
    })
})

//Apartado de BORRAR Cliente
var idDel = document.getElementById('idDelete');
var botonDel = document.getElementById('btnDelete');

//Obtener datos
idDel.addEventListener('click',function(){    
    var id=document.getElementById('idDel').value;
    const parametros=new URLSearchParams();
    parametros.append('PrmID', id);
    axios.post('http://localhost:4567/cliente', {
        id : document.getElementById('idDel').value
    }).then(function (response) {
        console.log(response)
        console.log(response.data)
        console.log(response.statusText)
        document.getElementById('cuenta_Del').value = response.data.cuenta
    }).catch(function (error) {
        console.log(error)
    })
})
//Borrar Cliente
botonDel.addEventListener('click',function(){
    axios.post('http://localhost:4567/clienteBorrar',{
        id : document.getElementById('idDel').value,
    }).then(function (response) {
        console.log(response)
        console.log(response.data)
        console.log(response.statusText)
        document.getElementById('idDel').value = null
        document.getElementById('cuenta_Del').value = null
    }).catch(function (error) {
        console.log(error)
    })
})