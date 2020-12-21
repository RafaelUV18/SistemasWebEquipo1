package mx.bank;

public class Transferencia {
    private int id;
    private String origen;
    private String destino;
    private double monto;
    private String referencia;

    public Transferencia(int id, String origen, String destino, double monto, String referencia) {
        this.id = id;
        this.origen = origen;
        this.destino = destino;
        this.monto = monto;
        this.referencia = referencia;
    }

    public Transferencia() {
        this.id = 0;
    }

    public int getId() {
        return id;
    }
    public String getOrigen() {
        return origen;
    }
    public String getDestino() {
        return destino;
    }
    public double getMonto() {
        return monto;
    }
    public String getReferencia() {
        return referencia;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setOrigen(String origen) {
        this.origen = origen;
    }
    public void setDestino(String destino) {
        this.destino = destino;
    }
    public void setMonto(double monto) {
        this.monto = monto;
    }
    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString();
    }
}