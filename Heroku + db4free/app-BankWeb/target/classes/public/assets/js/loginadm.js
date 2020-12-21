var pass = document.getElementById('txtPass');
var loginId = document.getElementById('txtId');
var btnSesion = document.getElementById('btnInicio');
var form = document.getElementById('f1');

btnSesion.addEventListener('click',function(){    
    var id=document.getElementById('txtId').value;
    const parametros=new URLSearchParams();
    parametros.append('PrmID', id);
    axios.post('http://localhost:4567/admi', {
        id : document.getElementById('txtId').value
    }).then(function (response) {
        console.log(response)
        console.log(response.data)
        console.log(response.statusText)
        if(document.getElementById('txtPass').value == response.data.password) {
            form.submit();       
        }else{ 
            alert('Datos erroneos');
        }
    }).catch(function (error) {
        console.log(error)
    })
})