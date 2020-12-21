package mx.bank;

public class Tarjeta {
    private String numero;
    private String vencimiento;
    private String cvc;
    private double saldo;
    private String no_Cuenta;

    public Tarjeta(String numero, String vencimiento, String cvc, double saldo, String no_Cuenta) {
        this.numero = numero;
        this.vencimiento = vencimiento;
        this.cvc = cvc;
        this.saldo = saldo;
        this.no_Cuenta = no_Cuenta;
    }

    public Tarjeta() {
        this.numero = "";
    }

    public String getNumero() {
        return numero;
    }
    public String getVencimiento() {
        return vencimiento;
    }
    public String getCvc() {
        return cvc;
    }
    public double getSaldo() {
        return saldo;
    }
    public String getNo_Cuenta() {
        return no_Cuenta;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
    public void setVencimiento(String vencimiento) {
        this.vencimiento = vencimiento;
    }
    public void setCvc(String cvc) {
        this.cvc = cvc;
    }
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
    public void setNo_Cuenta(String no_Cuenta) {
        this.no_Cuenta = no_Cuenta;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString();
    }
}