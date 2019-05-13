public class AgregarVeh {
    /**
     * NodoVeh que utilizara y donde guardara los datos que se le envien
     */
    private NodoVeh raiz;
    /**
     * Variables donde se guardaran cuantos elementos hay guardados
     */
    private int leght, leghtA;

    /**
     * Metodo constructor que hacer a la raiz, null
     */
    public AgregarVeh(){
        raiz = null;
    }

    /**
     * Metodo encargado de insertar el nodo de los vehiculos
     * @param tanque manda el vehiculo seleccionado
     * @param avion manda el vehiculo seleccionado
     * @param numeroRec manda el numero de reconocimiento
     */
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

    /**
     * Metodo encargado de insertar el nodo de las armas
     * @param armita manda el arma seleccionada
     * @param numRec manda el numero de reconocimiento
     */
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

    /**
     * Metodo que retorna el vehiculo que se está buscando por medio del numero de Reconocimiento
     * @param numeroRec para lograr encontrar el vehiculo
     * @return nuevo
     */
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

    /**
     * Metodo que retorna el arma que se esta buscando por medio del numero de reconocimiento
     * @param numRec para encontrar el armma
     * @return nulo1
     */
    public Arma buscarNodoA(int numRec){
        NodoVeh barrer;
        barrer = raiz;
        Arma nulo1= new Arma();
        while(barrer!=null){
            if (barrer.getNumeroRec1()==numRec){
                nulo1 = barrer.armita;
                return nulo1;
            }
            barrer=barrer.siguiente;
        }
        return nulo1;
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
