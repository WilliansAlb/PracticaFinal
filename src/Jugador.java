public class Jugador {
    private String nombre;
    private AgregarVeh vehiculo, armas;
    private int dinero=200, vehiculosComprados, bootsComprados = 0;

    public Jugador(){
        vehiculo = new AgregarVeh();
        armas = new AgregarVeh();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public AgregarVeh getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(AgregarVeh vehiculo) {
        this.vehiculo = vehiculo;
    }

    public AgregarVeh getArmas() {
        return armas;
    }

    public void setArmas(AgregarVeh armas) {
        this.armas = armas;
    }

    public int getDinero() {
        return dinero;
    }

    public void setDinero(int dinero) {
        this.dinero += dinero;
    }

    public int getVehiculosComprados() {
        return vehiculosComprados;
    }

    public void setVehiculosComprados(int vehiculosComprados) {
        this.vehiculosComprados = vehiculosComprados;
    }

    public int getBootsComprados() {
        return bootsComprados;
    }

    public void setBootsComprados(int bootsComprados) {
        this.bootsComprados += bootsComprados;
    }
}
