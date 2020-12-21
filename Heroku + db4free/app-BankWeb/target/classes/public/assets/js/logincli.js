
var pass = document.getElementById('txtContra');
var loginId = document.getElementById('txtCorreo');
var btnSesion = document.getElementById('btnSesion');
var form = document.getElementById('f2');

btnSesion.addEventListener('click',function(){    
    var id =document.getElementById('txtCorreo').value;
    const parametros=new URLSearchParams();
    parametros.append('PrmID', id);
    axios.post('http://localhost:4567/loginClientes', {
        correo : document.getElementById('txtCorreo').value
    }).then(function (response) {
        console.log(response)
        console.log(response.data)
        console.log(response.statusText)
        if(document.getElementById('txtContra').value == response.data.contrasena) {
            sessionStorage.setItem('cliente', response.data.correo);
            form.submit();       
        }else{ 
            alert('Datos erroneos');
        }
    }).catch(function (error) {
        console.log(error);
    })
})