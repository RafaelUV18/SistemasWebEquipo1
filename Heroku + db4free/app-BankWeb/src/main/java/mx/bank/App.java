package mx.bank;

import com.google.gson.*;

import static spark.Spark.*;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App {
    private static Gson gson = new Gson();
    private static JsonObject clienteTarjeta = new JsonObject();
    public static void main(String[] args){
        staticFiles.location("/public");
        // before((req, res) -> res.type("application/json"));
        port(getHerokuAssignedPort());

        options("/*", (request, response) -> {
            String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }

            String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
            if (accessControlRequestMethod != null) {
                response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
            }

            return "OK";
        });
        before((request, response) -> response.header("Access-Control-Allow-Origin", "*"));

        // Metodos para recuperar las Relaciones de la BD.
        //Apartado de ADMINISTRADOR
        get("/cliente", (req, res) -> gson.toJson(Cliente_DAO.readAll()));
        get("/cuenta", (req, res) -> gson.toJson(Cuenta_DAO.readAll()));
        get("/buscarEliminar", (req, res) -> gson.toJson(Cuenta_DAO.readAll()));
        //Loogin Administrador
        get("/admi", (req, res) -> gson.toJson(Admi_DAO.readAll()));
        //Login Clientes
        get("/loginClientes", (req, res) -> gson.toJson(Cliente_DAO.readAll()));

        //Apartado de CLIENTE
        // Funcion para recuperar los datos del Cliente.
        post("/cliente", (req, res) -> {
            String query = req.body();
            Cliente u = gson.fromJson(query, Cliente.class);
            Cliente aux = Cliente_DAO.read(u.getId());
            JsonObject cliJ = new JsonObject();
            cliJ.addProperty("nombre", aux.getNombre());
            cliJ.addProperty("apellidos", aux.getApellidos());
            cliJ.addProperty("edad", aux.getEdad());
            cliJ.addProperty("correo", aux.getCorreo());
            cliJ.addProperty("direccion", aux.getDireccion());
            cliJ.addProperty("contrasena", aux.getContrasena());
            cliJ.addProperty("telefono", aux.getTelefono());
            cliJ.addProperty("cuenta", aux.getCuenta());
            return cliJ;
        });
        // Funcion para AGREGAR.
        post("/clienteAgregar", (req, res) -> {
            String query = req.body();
            Cliente cliente = gson.fromJson(query, Cliente.class);
            Cliente_DAO.create(cliente);
            System.out.println("Cliente Agregado");
            return 1;
        });
        // Funcion para EDITAR.
        post("/clienteEditar", (req, res) -> {
            String query = req.body();
            Cliente editado = gson.fromJson(query, Cliente.class);
            System.out.println("Cliente Editado");
            return Cliente_DAO.update(editado);
        });
        // Funcion para BORRAR.
        post("/clienteBorrar", (req, res) -> {
            String query = req.body();
            Cliente u = gson.fromJson(query, Cliente.class);
            System.out.println("Cliente Borrado");
            return Cliente_DAO.delete(u.getId());
        });

        // Apartado de CUENTA.
        // Funcion para recuperar los datos de la cuenta a editar.
        post("/cuenta", (req, res) -> {
            String query = req.body();
            Cuenta c = gson.fromJson(query, Cuenta.class);
            Cuenta aux = Cuenta_DAO.read(c.getNo_Cuenta());
            JsonObject cuentaJS = new JsonObject();
            cuentaJS.addProperty("no_cuenta", aux.getNo_Cuenta());
            return cuentaJS;
        });
        // Funcion para recuperar los datos de la cuenta a eliminar.
        post("/buscarEliminar", (req, res) -> {
            String query = req.body();
            Cuenta c = gson.fromJson(query, Cuenta.class);
            Cuenta aux = Cuenta_DAO.read(c.getNo_Cuenta());
            JsonObject cuentaJS = new JsonObject();
            cuentaJS.addProperty("no_cuenta", aux.getNo_Cuenta());
            return cuentaJS;
        });
        // Funcion para AGREGAR.
        post("/cuentaAgregar", (req, res) -> {
            String query = req.body();
            Cuenta cuenta = gson.fromJson(query, Cuenta.class);
            System.out.println("Cuenta Agregada");
            return Cuenta_DAO.create(cuenta);
        });
        // Funcion para EDITAR.
        post("/cuentaEditar", (req, res) -> {
            JsonParser parser = new JsonParser();
            JsonElement arbol = parser.parse(req.body());
            JsonObject peticion = arbol.getAsJsonObject();
            Cuenta edicion = new Cuenta("" + peticion.get("no_Cuenta"));
            System.out.println("Cuenta Editada");
            return Cuenta_DAO.update(edicion, "" + peticion.get("anterior"));
        });
        // Funcion para BORRAR.
        post("/cuentaBorrar", (req, res) -> {
            String query = req.body();
            Cuenta c = gson.fromJson(query, Cuenta.class);
            System.out.println("Cuenta Borrada");
            return Cuenta_DAO.delete(c.getNo_Cuenta());
        });

        // Apartado de TARJETA.
        // Funcion para recuperar los datos de la tarjeta a eliminar.
        post("/tarjeta", (req, res) -> {
            String query = req.body();
            Tarjeta t = gson.fromJson(query, Tarjeta.class);
            Tarjeta aux = Tarjeta_DAO.read(t.getNumero());
            JsonObject tarJ = new JsonObject();
            tarJ.addProperty("numero", aux.getNumero());
            tarJ.addProperty("vencimiento", aux.getVencimiento());
            tarJ.addProperty("cvc", aux.getVencimiento());
            tarJ.addProperty("cuenta", aux.getNo_Cuenta());
            return tarJ;
        });
        // Funcion para AGREGAR.
        post("/agregarTarjeta", (req, res) -> {
            String query = req.body();
            Tarjeta tarjeta = gson.fromJson(query, Tarjeta.class);
            System.out.println("Tarjeta Agregada");
            return Tarjeta_DAO.create(tarjeta);
        });
        // Funcion para BORRAR.
        post("/tarjetaBorrar", (req, res) -> {
            String query = req.body();
            Tarjeta t = gson.fromJson(query, Tarjeta.class);
            System.out.println("Tarjeta Borrada");
            return Tarjeta_DAO.delete(t.getNumero());
        });

        // Apartado de TRANSFERENCIAS
        post("/transferencia", (req, res) -> {
            String query = req.body();
            Transferencia trans = gson.fromJson(query, Transferencia.class);
            double monto = trans.getMonto();
            Tarjeta origen = Tarjeta_DAO.read(trans.getOrigen());
            double saldo_Origen = origen.getSaldo() - monto;

            Tarjeta destino = Tarjeta_DAO.read(trans.getDestino());
            double saldo_Destino = destino.getSaldo() + monto;
            if (saldo_Origen > 0) {
                origen.setSaldo(saldo_Origen);
                Tarjeta_DAO.transferencia(origen);

                destino.setSaldo(saldo_Destino);
                Tarjeta_DAO.transferencia(destino);

                Transferencia_DAO.create(trans);
                System.out.println("¡Transferencia realizada Correctamente!");
            } else {
                System.out.println("Fondos Insuficientes");
            }
            return 1;
        });

        // Apartado de Login de Clientes
        //Recuperar Datos
        post("/i_Cliente", (req, res) -> {
            String query = req.body();
            Cliente u = gson.fromJson(query, Cliente.class);
            Cliente aux = Cliente_DAO.readLogin(u.getCorreo());
            JsonObject cliJ = new JsonObject();
            cliJ.addProperty("nombre", aux.getNombre());
            cliJ.addProperty("apellidos", aux.getApellidos());
            cliJ.addProperty("edad", aux.getEdad());
            cliJ.addProperty("correo", aux.getCorreo());
            cliJ.addProperty("direccion", aux.getDireccion());
            cliJ.addProperty("contrasena", aux.getContrasena());
            cliJ.addProperty("telefono", aux.getTelefono());
            cliJ.addProperty("cuenta", aux.getCuenta());
            clienteTarjeta=cliJ;
            return cliJ;
        });
        //Iniciar Sesión
        post("/loginClientes", (req, res) -> {
            String query = req.body();
            Cliente u = gson.fromJson(query, Cliente.class);
            Cliente aux = Cliente_DAO.readLogin(u.getCorreo());
            JsonObject cliJ = new JsonObject();
            cliJ.addProperty("nombre", aux.getNombre());
            cliJ.addProperty("apellidos", aux.getApellidos());
            cliJ.addProperty("edad", aux.getEdad());
            cliJ.addProperty("correo", aux.getCorreo());
            cliJ.addProperty("direccion", aux.getDireccion());
            cliJ.addProperty("contrasena", aux.getContrasena());
            cliJ.addProperty("cuenta", aux.getCuenta());
            cliJ.addProperty("telefono", aux.getTelefono());
            return cliJ;
        });

        // Apartado de Login de Administrador
        post("/admi", (req, res) -> {
            String query = req.body();
            Admi admi = gson.fromJson(query, Admi.class);
            Admi aux = Admi_DAO.readAll();
            JsonObject cliJ = new JsonObject();
            cliJ.addProperty("id", aux.getId());
            cliJ.addProperty("correo", aux.getCorreo());
            cliJ.addProperty("password", aux.getPass());
            return cliJ;
        });

        //Sección de Velocity
        get("/login", (req, res) -> {
            res.type("text/html");
            Map<String, Object> modelo = new HashMap<>();
            modelo.put("credenciales", null);
            return new ModelAndView(modelo, "velocity/loginCliente.vm");
        }, new VelocityTemplateEngine());
        //Apartado de Transferencias
        get("/trasferencias", (req, res) -> {
            Cliente client = gson.fromJson(clienteTarjeta, Cliente.class);
            Cliente aux=Cliente_DAO.readLogin(client.getCorreo());
            
            Map<String, Object> modelo = new HashMap<>();
            modelo.put("tarjetas",Tarjeta_DAO.readAllTarjetas(aux.getCuenta()));
            return new ModelAndView(modelo, "velocity/Transferencia.vm");
        }, new VelocityTemplateEngine());    

        //Apartado de Movimientos
        get("/movimientos", (req, res) -> {
            Cliente client = gson.fromJson(clienteTarjeta, Cliente.class);
            Cliente aux=Cliente_DAO.readLogin(client.getCorreo());
            
            List<Tarjeta> tar = Tarjeta_DAO.readAllTarjetas(aux.getCuenta());
            List<Transferencia> trans = new ArrayList<Transferencia>();

            for(int i = 0; i<tar.size(); i++){
                List<Transferencia> auxiliar =Transferencia_DAO.readAll(tar.get(i));
                trans.addAll(auxiliar);
            }
            Map<String, Object> modelo = new HashMap<>();
            modelo.put("transferencias", trans);
            return new ModelAndView(modelo, "velocity/Movimientos.vm");
        }, new VelocityTemplateEngine()); 

        //Apartado de Tarjetas
        get("/tarjetas", (req, res) -> {
            Cliente client = gson.fromJson(clienteTarjeta, Cliente.class);
            Cliente aux=Cliente_DAO.readLogin(client.getCorreo());
            
            Map<String, Object> modelo = new HashMap<>();
            modelo.put("tarjetas",Tarjeta_DAO.readAllTarjetas(aux.getCuenta()));
            return new ModelAndView(modelo, "velocity/Tarjetas.vm");
        }, new VelocityTemplateEngine());
    }
    
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }  
}