/**
 * Clase para guardar vehiculos y armas que el usuario haya comprado o creado
 */
public class NodoVeh {
    /**
     * Apuntador del proximo nodo
     */
    NodoVeh siguiente;
    /**
     * Variable que guarda el tanque que el jugador selecciono
     */
    Tanque nuevo;
    /**
     * Variable que guarda el avion que el jugador selecciono
     */
    Avion nuevo1;
    /**
     * Variable que guarda el arma que el jugador selecciono
     */
    Arma armita;
    /**
     * Numeros de reconocimiento del nodo
     */
    private int numeroRec, numeroRec1;

    /**
     * Metodo que sirve para agregar un nuevo nodo
     * @param nuevo en dado caso sea un tanque lo que se le mando, lo guardara en tanque y avion sera null
     * @param nuevo1 en dado caso sea un avion lo que se le mando, lo guardara en avion y tanque sera null
     * @param numeroRec numero para reconoer el nodo
     */
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

    /**
     * Metodo que sirve para agregar un nuevo nodo de arma
     * @param armita guarda el arma seleccionada
     * @param numeroRec guarda el numero de reconocimiento
     */
    public NodoVeh(Arma armita, int numeroRec){
        this.armita = armita;
        this.numeroRec1 = numeroRec;
        siguiente = null;
    }

    /**
     * Metodo que retorna el numero de reconocimiento
     * @return numeroRec
     */
    public int getNumeroRec() {
        return numeroRec;
    }

    /**
     * Metodo que retorna el numero de reconocimiento1
     * @return numeroRec1
     */
    public int getNumeroRec1() {
        return numeroRec1;
    }
}
