var correo=sessionStorage.getItem('cliente');

function prueba(){
    axios.post('http://localhost:4567/i_Cliente', {
        correo: correo
    }).then(function(response){
        document.getElementById('no_cuenta').innerHTML = response.data.cuenta
        document.getElementById('correo').innerHTML = response.data.correo
    })
    //alert('ok'
}
window.onload = prueba;