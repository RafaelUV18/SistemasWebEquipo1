package mx.bank;

public class Cuenta {
    private String no_Cuenta;

    public Cuenta(){
        this.no_Cuenta = "";
    }

    public Cuenta(String no_Cuenta){
        this.no_Cuenta = no_Cuenta;
    }

    public void setNo_Cuenta(String no_Cuenta) {
        this.no_Cuenta = no_Cuenta;
    }

    public String getNo_Cuenta() {
        return no_Cuenta;
    }
}