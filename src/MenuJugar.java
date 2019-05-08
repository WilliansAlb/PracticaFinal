import javax.swing.*;

public class MenuJugar extends JFrame {
    FondoWWI fondito;
    AgregarVeh listadoAutos, listadoArmas;

    public MenuJugar(AgregarVeh listadoAutos, AgregarVeh listadoArmas){
        this.listadoAutos = listadoAutos;
        this.listadoArmas = listadoArmas;
        setSize(700,700); //se establece el tamaño de la ventana
        setLocationRelativeTo(null);
        setTitle("WWI"); // le coloca titulo a la ventana
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        iniciarComponentes();

        for (int i = 0; i < listadoAutos.getLeght(); i++)
        {
            if (listadoAutos.buscarNodo(i).isElegido()){
                System.out.println(listadoAutos.buscarNodo(i).getNombre());
            }
        }
        for (int i = 0; i < listadoArmas.getLeghtA(); i++)
        {
            if (listadoArmas.buscarNodoA(i).isElegido()){
                System.out.println(listadoArmas.buscarNodoA(i).getNombre());
            }
        }

    }
    public void iniciarComponentes(){
        fondito = new FondoWWI(1);
        this.getContentPane().add(fondito);
    }
}
