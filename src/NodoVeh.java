public class NodoVeh {
    NodoVeh siguiente;
    Tanque nuevo;
    Avion nuevo1;
    Arma armita;
    private int numeroRec;
    private int numeroRec1;

    public NodoVeh(Tanque nuevo, Avion nuevo1, int numeroRec){
        if (nuevo!=null){
            this.nuevo = nuevo;
            this.nuevo.setNumRec(numeroRec);
        }
        if (nuevo1!=null){
            this.nuevo1 = nuevo1;
            this.nuevo1.setNumRec(numeroRec);
        }
        this.numeroRec = numeroRec;
        siguiente = null;
    }
    public NodoVeh(Arma armita, int numeroRec){
        this.armita = armita;
        this.numeroRec1 = numeroRec;
        siguiente = null;
    }

    public int getNumeroRec() {
        return numeroRec;
    }

    public int getNumeroRec1() {
        return numeroRec1;
    }
}
