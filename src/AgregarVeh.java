public class AgregarVeh {
    private NodoVeh raiz;
    private int leght;
    private int leghtA;

    public AgregarVeh(){
        raiz = null;
    }

    public void insertarNodo(Tanque tanque, Avion avion, int numeroRec){
        NodoVeh tmp = new NodoVeh(tanque, avion, numeroRec);
        if (raiz != null){
            NodoVeh tmp2 = raiz;
            while (tmp2.siguiente!=null){
                tmp2 = tmp2.siguiente;
            }
            tmp2.siguiente = tmp;
        } else {
            raiz = tmp;
        }
        setLeght(1);
    }
    public void insertarNodo(Arma armita, int numRec){
        NodoVeh tmp = new NodoVeh(armita, numRec);
        if (raiz != null){
            NodoVeh tmp2 = raiz;
            while (tmp2.siguiente!=null){
                tmp2 = tmp2.siguiente;
            }
            tmp2.siguiente = tmp;
        } else {
            raiz = tmp;
        }
        setLeghtA(1);
    }
    public Vehiculos buscarNodo(int numeroRec){
        NodoVeh barrer;
        barrer = raiz;
        while(barrer!=null){
            if (barrer.getNumeroRec()==numeroRec){
                if(barrer.nuevo!=null){
                    return barrer.nuevo;
                } else {
                    return barrer.nuevo1;
                }
            }
            barrer=barrer.siguiente;
        }
        return barrer.nuevo;
    }
    public Arma buscarNodoA(int numRec, String num){
        NodoVeh barrer;
        barrer = raiz;
        Arma tuCulo = new Arma();
        while(barrer!=null){
            if (barrer.getNumeroRec1()==numRec){
                tuCulo = barrer.armita;
                return tuCulo;
            }
            barrer=barrer.siguiente;
        }
        return tuCulo;
    }

    public int getLeght() {
        return leght;
    }

    public void setLeght(int leght) {
        this.leght += leght;
    }

    public int getLeghtA() {
        return leghtA;
    }

    public void setLeghtA(int leghtA) {
        this.leghtA += leghtA;
    }
}
