package mx.bank;

public class Cliente {
    private int id;
    private String nombre;
    private String apellidos;
    private String edad;
    private String correo;
    private String direccion;
    private String contrasena;
    private String telefono;
    private String no_Cuenta;

    public Cliente( int id, String nombre, String apellidos, String edad, String correo,
                    String direccion, String contrasena, String telefono, String no_Cuenta) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
        this.correo = correo;
        this.direccion = direccion;
        this.contrasena = contrasena;
        this.telefono = telefono;
        this.no_Cuenta = no_Cuenta;
    }

    public Cliente() {
        this.id = 0;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getEdad() {
        return edad;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setCuenta(String cuenta) {
        this.no_Cuenta = cuenta;
    }

    public String getCuenta() {
        return no_Cuenta;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTelefono() {
        return telefono;
    }
    
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString();
    }   
}