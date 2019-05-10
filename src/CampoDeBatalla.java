import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import static java.awt.Color.*;

/**
 * Clase destinada a mostrar el juego en sus dos modalidades, un jugador, y dos jugadores.
 */
public class CampoDeBatalla extends JFrame {

    private boolean click = true;
    /**
     * Variables para guardar la posición del Jugador 1
     */
    private int posicionx, posiciony;
    /**
     * Variables para guardar la posicion del Jugador 2
     */
    private int posicion2x, posicion2y;
    /**
     * Variables para modificar el tamaño del tablero de juego, y para contar cuantos mensajes se muestran en la pantalla
     */
    private int cuantoX, cuantoY, contador;
    /**
     * Referencia, para colocarle un fondo al JFrame
     */
    FondoWWI fondito;
    /**
     * Botones para mover, disparar, tirar dados, seleccionar otro auto, anterior o siguiente
     */
    JButton mover, disparar, dados, sig, ant, u, d, l, r;
    /**
     * Matriz para crear el tablero de juego
     */
    Casilla[][] escenario1;
    /**
     * Para guardar la referencia de la tabla
     */
    private DefaultTableModel dtm;

    /**
     * Metodo constructor, inicia y posiciona el JFrame, así como manda a llamar el metodo iniciarComponentes()
     */
    public CampoDeBatalla(){
        setSize(700,700); //se establece el tamaño de la ventana
        setLocationRelativeTo(null);
        setTitle("WWI"); // le coloca titulo a la ventana
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        iniciarComponentes();
        setResizable(false);
    }

    /**
     * Metodo encargado de crear un nuevo objeto FondoWWI e inicializar todos los componentes
     */
    public void iniciarComponentes(){
        fondito = new FondoWWI(2);
        this.getContentPane().add(fondito);
        iniciarRPG();
        iniciarBotones();
        iniciarEscenarios();

    }
    /**
     * Metodo que inicializa el JTable encargado de mostrarle sus acciones al jugador
     */
    public void iniciarRPG() {
        String[][] data = {};
        String[] columnNames = {""};
        JTableHeader th;
        setDtm(new DefaultTableModel(data, columnNames));
        JTable tabla = new JTable(getDtm());
        th = tabla.getTableHeader();
        th.setOpaque(false);
        th.setBackground(black);
        tabla.setBounds(20, 580, 191, 73);
        tabla.setEnabled(false);
        tabla.setOpaque(false);
        tabla.setShowGrid(false);
        tabla.setShowHorizontalLines(false);
        tabla.setShowVerticalLines(false);
        tabla.setRowHeight(11);

        ((DefaultTableCellRenderer) tabla.getDefaultRenderer(Object.class)).setOpaque(false);
        ((DefaultTableCellRenderer) tabla.getDefaultRenderer(Object.class)).setSize(73,5);
        tabla.setFont(new Font("Lucida Fax", Font.PLAIN, 10));
        tabla.setForeground(yellow);
        fondito.add(tabla);
    }

    /**
     * Metodo que inicializa y personaliza los botones
     */
    public void iniciarBotones(){
        mover = new JButton();
        mover.setBounds(565,621,38,15);
        mover.setOpaque(false);
        mover.setContentAreaFilled(false);
        mover.setBackground(yellow);
        fondito.add(mover);

        disparar = new JButton();
        disparar.setBounds(565,600,38,15);
        disparar.setOpaque(false);
        disparar.setContentAreaFilled(false);
        fondito.add(disparar);

        dados = new JButton();
        dados.setBounds(565,641,38,15);
        dados.setContentAreaFilled(true);
        dados.setBackground(black);
        dados.setEnabled(false);
        fondito.add(dados);

        u = new JButton();
        u.setBounds(640,598,20,20);
        u.setContentAreaFilled(false);
        u.setBorderPainted(false);
        fondito.add(u);
        l = new JButton();
        l.setBounds(616,620,33,12);
        l.setContentAreaFilled(false);
        l.setBorderPainted(false);
        fondito.add(l);
        r = new JButton();
        r.setBounds(650,621,33,12);
        r.setContentAreaFilled(false);
        r.setBorderPainted(false);
        fondito.add(r);
        d = new JButton();
        d.setBounds(617,633,67,25);
        d.setContentAreaFilled(false);
        d.setBorderPainted(false);
        fondito.add(d);
        u.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarAciones("Elegiste hacia arriba");
                mostrarAciones("Pulsa dice para lanzar dados");
            }
        });

        l.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarAciones("Elegiste hacia la izquierda");
                mostrarAciones("Pulsa dice para lanzar dados");
            }
        });

        r.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarAciones("Elegiste hacia la derecha");
                mostrarAciones("Pulsa dice para lanzar dados");
            }
        });

        d.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarAciones("Elegiste hacia abajo");
                mostrarAciones("Pulsa dice para lanzar dados");
            }
        });

        JLabel escot = new JLabel();
        escot.setBounds(617,598,68,68);
        mover.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mover.setContentAreaFilled(true);
                mover.setBackground(black);
                mover.setEnabled(false);
                dados.setOpaque(false);
                dados.setContentAreaFilled(false);
                dados.setEnabled(true);
                disparar.setEnabled(true);
                disparar.setContentAreaFilled(false);
                /*Object[] nuevo = {"Seleccionaste Moverte"};
                getDtm().addRow(nuevo);
                setContador(1);
                if (getContador()>7){
                    getDtm().removeRow(0);
                }*/
                mostrarAciones("Seleccionaste moverte");
                mostrarAciones("¿Hacia donde te moveras?");
                quitarMarcas();
                pintarCaminos(false);
                if (escot.getIcon()==null){
                    ImageIcon escotilla = new ImageIcon("src/fts/escotilla.gif");
                    escot.setIcon(new ImageIcon(escotilla.getImage().getScaledInstance(68,68,Image.SCALE_REPLICATE)));
                }
            }
        });
        disparar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                disparar.setContentAreaFilled(true);
                disparar.setBackground(black);
                disparar.setEnabled(false);
                dados.setOpaque(false);
                dados.setContentAreaFilled(false);
                dados.setEnabled(true);
                mover.setEnabled(true);
                mover.setContentAreaFilled(false);
                /*Object[] nuevo = {"Seleccionaste Disparar"};
                getDtm().addRow(nuevo);
                setContador(1);
                if (getContador()>7){
                    getDtm().removeRow(0);
                }*/

                mostrarAciones("Seleccionaste disparar");
                mostrarAciones("¿Hacia donde dispararás?");
                quitarMarcas();
                pintarCaminos(true);
                if (escot.getIcon()==null){
                    ImageIcon escotilla = new ImageIcon("src/fts/escotilla.gif");
                    escot.setIcon(new ImageIcon(escotilla.getImage().getScaledInstance(68,68,Image.SCALE_REPLICATE)));
                }
            }
        });
        fondito.add(escot);
        dados.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Random alea = new Random(System.nanoTime());
                int alea1 = alea.nextInt(6)+1;
                /*Object[] nuevo = {"Dada tu suerte, te tocó "+alea1};
                getDtm().addRow(nuevo);
                setContador(1);
                if (getContador()>7){
                    getDtm().removeRow(0);
                }*/
                mostrarAciones("Dada tu suerte, te tocó "+alea1+".");
                CampoDeBatalla nuevo1 = new CampoDeBatalla();
                nuevo1.setVisible(true);
                setVisible(false);
            }
        });

        ant = new JButton();
        ant.setBounds(295,610,20,20);
        ant.setContentAreaFilled(false);
        ant.setOpaque(false);
        fondito.add(ant);

        sig = new JButton();
        sig.setBounds(450,610,20,20);
        sig.setContentAreaFilled(false);
        sig.setOpaque(false);
        fondito.add(sig);
        sig.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(escenario1[getPosiciony()][getPosicionx()].getAvion()!=null){
                    escenario1[getPosiciony()][getPosicionx()].setAvion(null);
                    escenario1[getPosiciony()][getPosicionx()].inicializarTanque();
                    mostrarAciones("Cambiaste a un tanque");
                } else {
                    escenario1[getPosiciony()][getPosicionx()].setTanque(null);
                    escenario1[getPosiciony()][getPosicionx()].inicializarAvion();
                    mostrarAciones("Cambiaste a un avion");
                }
                sig.setEnabled(false);
                ant.setEnabled(true);
                quitarMarcas();
            }
        });
        ant.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(escenario1[getPosiciony()][getPosicionx()].getAvion()!=null){
                    escenario1[getPosiciony()][getPosicionx()].setAvion(null);
                    escenario1[getPosiciony()][getPosicionx()].inicializarTanque();
                    mostrarAciones("Cambiaste a un tanque.");
                } else {
                    escenario1[getPosiciony()][getPosicionx()].setTanque(null);
                    escenario1[getPosiciony()][getPosicionx()].inicializarAvion();
                    mostrarAciones("Cambiaste a un avion.");
                }
                ant.setEnabled(false);
                sig.setEnabled(true);
                quitarMarcas();
            }
        });
    }

    /**
     * Metodo encargado de ver todo lo correspondiente a los tableros
     */
    public void iniciarEscenarios(){
        Random aleatorio = new Random(System.nanoTime());
        int escenarioElegido = aleatorio.nextInt(3);
        int ancho;
        int posicionInicialX;
        int posicionInicialY;
        int objetos, enemigos;
        boolean modalidad=true;
        switch (escenarioElegido){
            case 0:
            {
                objetos = 4;
                enemigos = 3;
                ancho = 130;
                setCuantoX(4);
                setCuantoY(4);
                posicionInicialX = 80;
                posicionInicialY = 22;
                escenario1 = new Casilla[getCuantoY()][getCuantoX()];
                break;
            }
            case 1:
            {
                ancho = 110;
                objetos = 6;
                enemigos = 4;
                setCuantoX(6);
                setCuantoY(4);
                posicionInicialX = 12;
                posicionInicialY = 80;
                escenario1 =new Casilla[getCuantoY()][getCuantoX()];
                break;
            }
            case 2:
            {
                ancho = 60;
                setCuantoX(8);
                setCuantoY(9);
                objetos = 24;
                enemigos = 6;
                posicionInicialX = 100;
                posicionInicialY = 10;
                escenario1 =new Casilla[getCuantoY()][getCuantoX()];
                break;
            }
            default:
            {
                ancho = 100;
                setCuantoX(4);
                setCuantoY(4);
                objetos = 4;
                enemigos = 3;
                posicionInicialX = 140;
                posicionInicialY = 140;
                break;
            }
        }
        ImageIcon enemigo1 = new ImageIcon("src/fts/enemigo5.gif");
        ImageIcon enemigo2 = new ImageIcon("src/fts/enemigo2.gif");
        ImageIcon enemigo3 = new ImageIcon("src/fts/enemigo4.gif");
        ImageIcon montania2 = new ImageIcon("src/fts/arbol.png");
        ImageIcon agua1 = new ImageIcon("src/fts/aguaredimension.gif");
        for (int u = 0; u<getCuantoY();u++)
            for (int p = 0; p<getCuantoX();p++){
                escenario1[u][p] = new TerrenoLibre((posicionInicialX+(ancho*p)),(posicionInicialY+(ancho*u)),ancho,p,u, this);
                escenario1[u][p].setContentAreaFilled(false);
                fondito.add(escenario1[u][p]);
            }
        Random queSera1 = new Random(System.currentTimeMillis());
        int intentos=0;
        while (intentos<objetos){
            int es = queSera1.nextInt(cuantoY);
            int es1 = queSera1.nextInt(cuantoX);
            if (escenario1[es][es1].getTieneAlgo()==0){
                int esmontana = queSera1.nextInt(2);
                if (esmontana==0){
                    escenario1[es][es1].setIcon(new ImageIcon(montania2.getImage().getScaledInstance((ancho-5),(ancho-5),Image.SCALE_REPLICATE)));
                    escenario1[es][es1].setTieneAlgo(1);
                    escenario1[es][es1].setQueSera(0);

                } else if (esmontana==1){
                    //escenario1[es][es1].setIcon(new ImageIcon(agua1.getImage().getScaledInstance((ancho-5),(ancho-5),Image.SCALE_REPLICATE)));
                    escenario1[es][es1].inicializarAgua();
                    System.out.println(escenario1[es][es1].getAgua().getPrueba());
                    escenario1[es][es1].getAgua().setPrueba(-50);
                    System.out.println(escenario1[es][es1].getAgua().getPrueba());
                }
                intentos++;
            }
        }
        modalidad = true;
        if (modalidad==true){
            intentos = 0;
            while (intentos<enemigos) {
                int fue = queSera1.nextInt(cuantoY);
                int fue1 = queSera1.nextInt(cuantoX) + 1 - 1;
                if (escenario1[fue][fue1].getTieneAlgo() == 0) {
                    int queEnemigo = queSera1.nextInt(3);
                    if (queEnemigo == 0) {
                        escenario1[fue][fue1].setIcon(new ImageIcon(enemigo1.getImage().getScaledInstance((ancho-5),(ancho-5),Image.SCALE_REPLICATE)));
                        escenario1[fue][fue1].setTieneAlgo(1);
                        escenario1[fue][fue1].setQueSera(2);
                    } else if (queEnemigo == 1) {
                        escenario1[fue][fue1].setIcon(new ImageIcon(enemigo2.getImage().getScaledInstance((ancho-5),(ancho-5),Image.SCALE_REPLICATE)));
                        escenario1[fue][fue1].setTieneAlgo(1);
                        escenario1[fue][fue1].setQueSera(2);
                    } else {
                        escenario1[fue][fue1].setIcon(new ImageIcon(enemigo3.getImage().getScaledInstance((ancho-5), (ancho-5), Image.SCALE_REPLICATE)));
                        escenario1[fue][fue1].setTieneAlgo(1);
                        escenario1[fue][fue1].setQueSera(2);
                    }
                    intentos++;
                }
            }
        }

        if (modalidad==true){
            intentos = 0;
            while(intentos==0){
                int fue1 = queSera1.nextInt(cuantoY);
                int fue2 = queSera1.nextInt(cuantoX) + 1 - 1;
                if (escenario1[fue1][fue2].getTieneAlgo() == 0) {
                    ImageIcon tanque = new ImageIcon("src/fts/tanquesuelo5.gif");
                    //escenario1[fue1][fue2].setIcon(new ImageIcon(tanque.getImage().getScaledInstance((ancho-5),(ancho-5),Image.SCALE_REPLICATE)));
                    escenario1[fue1][fue2].inicializarAvion();
                    escenario1[fue1][fue2].setContentAreaFilled(true);
                    escenario1[fue1][fue2].setBackground(yellow);
                    setPosicionx(fue2);
                    setPosiciony(fue1);
                    mostrarAciones("Entras a la guerra en ("+(fue2+1)+","+(fue1+1)+").");
                    intentos++;
                }
            }
        } else {
            int intentos2 = 0;
            while(intentos2==0){
                int fue2 = queSera1.nextInt(cuantoX) + 1 - 1;
                if (escenario1[0][fue2].getTieneAlgo() == 0) {
                    escenario1[0][fue2].inicializarAvion();
                    pintarCasilla(0,fue2,0);
                    setPosicionx(fue2);
                    setPosiciony(0);
                    mostrarAciones("Jugardor1 entras a la guerra en ("+(fue2+1)+",0).");
                    intentos2++;
                }
            }
            intentos2 = 0;
            while (intentos2==0){
                int fue = queSera1.nextInt(cuantoX);
                if (escenario1[cuantoY-1][fue].getTieneAlgo()==0){
                    escenario1[cuantoY-1][fue].inicializarTanque();
                    pintarCasilla(cuantoY-1,fue,0);
                    setPosicion2y(cuantoY-1);
                    setPosicion2x(fue);
                    mostrarAciones("Jugador2 entras a la guerra en ("+(fue+1)+","+(cuantoY+1)+").");
                    intentos2++;
                }
            }
        }
    }

    /**
     * Metodo encargado de retornar la posicion en X del Jugador1
     * @return posicionx
     */
    public int getPosicionx() {
        return posicionx;
    }

    /**
     * Metodo encargado de cambiar la posicion en X del Jugador1
     * @param posicionx Utilizada para conocer en que posicion se encuentra el jugador
     */
    public void setPosicionx(int posicionx) {
        this.posicionx = posicionx;
    }

    /**
     * Metodo encargado de retornar la posicion en Y del Jugador1
     * @return posiciony
     */
    public int getPosiciony() {
        return posiciony;
    }
    public void setPosiciony(int posiciony) {
        this.posiciony = posiciony;
    }
    public int getCuantoX() {
        return cuantoX;
    }
    public void setCuantoX(int cuantoX) {
        this.cuantoX = cuantoX;
    }
    public int getCuantoY() {
        return cuantoY;
    }
    public void setCuantoY(int cuantoY) {
        this.cuantoY = cuantoY;
    }
    public DefaultTableModel getDtm() {
        return dtm;
    }
    public void setDtm(DefaultTableModel dtm) {
        this.dtm = dtm;
    }
    public int getContador() {
        return contador;
    }
    public void setContador(int contador) {
        this.contador += contador;
    }
    public void pintarCaminos(boolean disparo){
        boolean sobreLasAguas;
        if (escenario1[getPosiciony()][getPosicionx()].getTanque()!=null){
            sobreLasAguas = false;
        } else {
            sobreLasAguas = true;
        }
        pintarCamino1(getPosiciony(),getPosicionx(), disparo, sobreLasAguas);
        pintarCamino2(getPosiciony(),getPosicionx(), disparo, sobreLasAguas);
        pintarCamino3(getPosiciony(),getPosicionx(), disparo, sobreLasAguas);
        pintarCamino4(getPosiciony(),getPosicionx(), disparo, sobreLasAguas);
    }
    public void pintarCamino1(int y,int x, boolean disparo, boolean sobreLasAguas){
        if (y>0){
            if(escenario1[y-1][x].getTieneAlgo()!=1){
                pintarCasilla(y-1,x,0);
                pintarCamino1(y-1,x, disparo, sobreLasAguas);
            } else {
                if (disparo){
                    if (escenario1[y-1][x].getAgua()!=null)
                    {
                        pintarCasilla(y-1,x,0);
                        pintarCamino1(y-1,x,disparo,sobreLasAguas);
                    } else
                        pintarCasilla(y-1,x,1);
                } else
                    if(sobreLasAguas && escenario1[y-1][x].getAgua()!=null)
                    {
                        pintarCasilla(y-1,x,0);
                        pintarCamino1(y-1,x,disparo,sobreLasAguas);
                    }
                    else
                        pintarCasilla(y-1,x,3);
            }
        }
    }
    public void pintarCamino2(int y,int x, boolean disparo, boolean sobreLasAguas) {
        if (y<cuantoY-1){
            if(escenario1[y+1][x].getTieneAlgo()!=1){
                pintarCasilla(y+1,x,0);
                pintarCamino2(y+1,x, disparo, sobreLasAguas);
            } else {
                if (disparo){
                    if(escenario1[y+1][x].getAgua()!=null)
                {
                    pintarCasilla(y+1,x,0);
                    pintarCamino2(y+1,x,disparo,sobreLasAguas);
                } else
                    pintarCasilla(y+1,x,1);
                } else
                    if(sobreLasAguas && escenario1[y+1][x].getAgua()!=null)
                    {
                        pintarCasilla(y+1,x,0);
                        pintarCamino2(y+1,x,disparo,sobreLasAguas);
                    }
                    else
                        pintarCasilla(y+1,x,3);
            }
        }
    }
    public void pintarCamino3(int y, int x, boolean disparo, boolean sobreLasAguas){
        if (x>0){
            if(escenario1[y][x-1].getTieneAlgo()!=1){
                pintarCasilla(y,x-1,0);
                pintarCamino3(y,x-1, disparo,sobreLasAguas);
            } else {
                if (disparo){
                    if (escenario1[y][x-1].getAgua()!=null)
                    {
                        pintarCasilla(y,x-1,0);
                        pintarCamino3(y,x-1,disparo,sobreLasAguas);
                    }
                    else
                        pintarCasilla(y,x-1,1);
                } else
                    if (sobreLasAguas && escenario1[y][x-1].getAgua()!=null)
                    {
                        pintarCasilla(y,x-1,0);
                        pintarCamino3(y,x-1,disparo,sobreLasAguas);
                    }
                    else
                        pintarCasilla(y,x-1,3);
            }
        }
    }
    public void pintarCamino4(int y, int x, boolean disparo, boolean sobreLasAguas){
        if (x<cuantoX-1){
            if(escenario1[y][x+1].getTieneAlgo()!=1){
                pintarCasilla(y, x+1,0);
                pintarCamino4(y,x+1, disparo,sobreLasAguas);
            } else {
                if (disparo){
                    if (escenario1[y][x+1].getAgua()!=null)
                    {
                        pintarCasilla(y,x+1,0);
                        pintarCamino4(y,x+1,disparo,sobreLasAguas);
                    }
                    else
                        pintarCasilla(y, x+1,1);
                } else
                    if (sobreLasAguas && escenario1[y][x+1].getAgua()!=null)
                    {
                        pintarCasilla(y,x+1,0);
                        pintarCamino4(y,x+1,disparo,sobreLasAguas);
                    }
                    else
                        pintarCasilla(y, x+1,3);
            }
        }
    }
    public void pintarCasilla(int y, int x, int es){
        escenario1[y][x].setContentAreaFilled(false);
        if (es==0){
            escenario1[y][x].setContentAreaFilled(true);
            escenario1[y][x].setBackground(yellow);
        } else if (es==1){
            escenario1[y][x].setContentAreaFilled(true);
            escenario1[y][x].setBackground(red);
        }
    }
    public void quitarMarcas(){
        for (int i = 0; i<cuantoY;i++)
            for (int u = 0; u<cuantoX; u++)
            {
                if (getPosicionx()==u && getPosiciony()==i){
                } else {
                    escenario1[i][u].setContentAreaFilled(false);
                }
            }

    }
    public void mostrarAciones(String mensaje){
        Object[] nuevo = {mensaje};
        getDtm().addRow(nuevo);
        setContador(1);
        if (getContador()>6){
            getDtm().removeRow(0);
        }
    }
    public void setPosicion2x(int posicion2x) {
        this.posicion2x = posicion2x;
    }
    public void setPosicion2y(int posicion2y) {
        this.posicion2y = posicion2y;
    }
}
