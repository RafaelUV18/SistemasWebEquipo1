package mx.bank;

public class Admi {
    int id;
    String correo;
    String password;

    public Admi(){
        this.id = 0;
        this.correo = "";
        this.password = "";
    }

    public Admi(int id, String correo, String pass){
        this.id=id;
        this.correo=correo;
        this.password=pass;
    }

    public void setId(int id){
        this.id=id;
    }

    public void setCorreo(String correo){
        this.correo=correo;
    }

    public void setPass(String pass){
        this.password=pass;
    }

    public int getId(){
        return this.id;
    }

    public String getCorreo(){
        return this.correo;
    }

    public String getPass(){
        return this.password;
    }
}
