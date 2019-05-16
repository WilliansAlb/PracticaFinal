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

    private boolean disparo;
    /**
     * Variables para guardar la posición del Jugador 1
     */
    private int posicionx, posiciony;
    /**
     * Variables para guardar la posicion del Jugador 2
     */
    private int posicion2x, posicion2y, posicion1x, posicion1y;
    /**
     * Variables para modificar el tamaño del tablero de juego, y para contar cuantos mensajes se muestran en la pantalla
     */
    private int cuantoX, cuantoY, contador, escenario, ancho, direc;
    /**
     * Referencia, para colocarle un fondo al JFrame
     */
    FondoWWI fondito;
    /**
     * Botones para mover, disparar, tirar dados, seleccionar otro auto, anterior o siguiente
     */
    JButton mover, disparar, dados, sig, ant, u, d, l, r, boot;
    /**
     * Matriz para crear el tablero de juego
     */
    Casilla[][] escenario1;
    /**
     * Para guardar la referencia de la tabla
     */
    private DefaultTableModel dtm;
    private int utilizando = 0, utilizando1 = 0, utilizando2 = 0;
    Jugador jugador, jugador1, jugador2;

    Vehiculos[] seleccionados, seleccionados2, seleccionados3;

    Arma[] seleccionadas;

    JLabel vehS, hp, cuantosBoots;

    Jugar jugaremos;

    private boolean modalida, inicioJugador1;
    /**
     * Metodo constructor, inicia y posiciona el JFrame, así como manda a llamar el metodo iniciarComponentes()
     */
    public CampoDeBatalla(int escenario, Jugador jugador){
        this.jugador = jugador;
        this.escenario = escenario;
        setSize(700,700); //se establece el tamaño de la ventana
        setLocationRelativeTo(null);
        setTitle("WWI"); // le coloca titulo a la ventana
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        iniciarJugador();
        iniciarComponentes();
    }

    public CampoDeBatalla(int escenario,Jugador jugador,Jugador jugador1,Jugador jugador2, boolean modalida){
        this.modalida = modalida;
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.jugador = jugador;
        this.escenario = escenario;
        setSize(700,700); //se establece el tamaño de la ventana
        setLocationRelativeTo(null);
        setTitle("WWI"); // le coloca titulo a la ventana
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        iniciarJugadores();
        iniciarComponentes();

    }

    public void iniciarJugadores(){
        int conteo = 0;
        seleccionados2 = new Vehiculos[3];
        seleccionados3 = new Vehiculos[3];

        for (int i = 0; i < jugador1.getVehiculo().getLeght(); i++)
        {
            if (jugador1.getVehiculo().buscarNodo(i).isSelec()){
                seleccionados2[conteo] = jugador1.getVehiculo().buscarNodo(i);
                conteo++;
            }
        }
        conteo = 0;
        for (int i = 0; i < jugador2.getVehiculo().getLeght(); i++)
        {
            if (jugador2.getVehiculo().buscarNodo(i).isSelec()){
                seleccionados3[conteo] = jugador2.getVehiculo().buscarNodo(i);
                conteo++;
            }
        }
    }

    public void iniciarJugador(){
        int conteo = 0;
        seleccionados = new Vehiculos[3];
        seleccionadas = new Arma[3];
        for (int i = 0; i < jugador.getVehiculo().getLeght(); i++)
        {
            if (jugador.getVehiculo().buscarNodo(i).isSelec()){
                seleccionados[conteo] = jugador.getVehiculo().buscarNodo(i);
                conteo++;
            }
        }
        conteo = 0;
        for (int u = 0; u < jugador.getArmas().getLeghtA(); u++)
        {
            if (jugador.getArmas().buscarNodoA(u).isSelec())
            {
                seleccionadas[conteo] = jugador.getArmas().buscarNodoA(u);
                conteo++;
            }
        }
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
        iniciarBoot();
        jugaremos = new Jugar(seleccionados,seleccionadas,this);
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
        tabla.setRowHeight(10);

        ((DefaultTableCellRenderer) tabla.getDefaultRenderer(Object.class)).setOpaque(false);
        ((DefaultTableCellRenderer) tabla.getDefaultRenderer(Object.class)).setSize(73,5);
        tabla.setFont(new Font("Lucida Fax", Font.PLAIN, 9));
        tabla.setForeground(lightGray);
        fondito.add(tabla);
    }

    /**
     * Metodo que inicializa y personaliza los botones
     */
    public void iniciarBotones(){
        hp = new JLabel();
        JLabel pp = new JLabel();

        hp.setBounds(515,601,30,10);
        pp.setBounds(515,638,30,10);
        if(jugador2==null){
            hp.setText(seleccionados[0].getHp()+"");
            pp.setText(seleccionados[0].getAtack()+"");
        } else {
            hp.setText(seleccionados2[0].getHp()+"");
            pp.setText(seleccionados2[0].getAtack()+"");
        }

        hp.setForeground(yellow);
        pp.setForeground(yellow);

        fondito.add(hp);
        fondito.add(pp);

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

        ant = new JButton();
        //ant.setBounds(295,610,20,20);
        ant.setBounds(308,610,20,20);
        ant.setContentAreaFilled(false);
        ant.setOpaque(false);
        fondito.add(ant);

        sig = new JButton();
        sig.setBounds(450,610,20,20);
        sig.setContentAreaFilled(false);
        sig.setOpaque(false);
        fondito.add(sig);

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
                mostrarAciones("Elegiste hacia arriba, pulsa dice para continuar");
                quitarMarcas();
                if (isDisparo()){
                    pintarCaminoA(getPosiciony(),getPosicionx(),isDisparo(),true);
                } else {
                    boolean sobreLasAguas1;
                    if (escenario1[getPosiciony()][getPosicionx()].tanque!=null){
                        sobreLasAguas1 = false;
                    } else {
                        sobreLasAguas1 = true;
                    }
                    pintarCaminoA(getPosiciony(),getPosicionx(),isDisparo(),sobreLasAguas1);
                }
                setDirec(0);
            }
        });

        l.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarAciones("Elegiste hacia la izquierda, pulsa dice para continuar");
                setDirec(1);
                quitarMarcas();
                if (isDisparo()){
                    pintarCaminoL(getPosiciony(),getPosicionx(),isDisparo(),true);
                } else {
                    boolean sobreLasAguas1;
                    if (escenario1[getPosiciony()][getPosicionx()].tanque!=null){
                        sobreLasAguas1 = false;
                    } else {
                        sobreLasAguas1 = true;
                    }
                    pintarCaminoL(getPosiciony(),getPosicionx(),isDisparo(),sobreLasAguas1);
                }
            }
        });
        r.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarAciones("Elegiste hacia la derecha, pulsa dice para continuar");
                setDirec(2);
                quitarMarcas();
                if (isDisparo()){
                    pintarCaminoR(getPosiciony(),getPosicionx(),isDisparo(),true);
                } else {
                    boolean sobreLasAguas1;
                    if (escenario1[getPosiciony()][getPosicionx()].tanque!=null){
                        sobreLasAguas1 = false;
                    } else {
                        sobreLasAguas1 = true;
                    }
                    pintarCaminoR(getPosiciony(),getPosicionx(),isDisparo(),sobreLasAguas1);
                }
            }
        });
        d.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarAciones("Elegiste hacia abajo, pulsa dice para continuar");
                setDirec(3);
                quitarMarcas();
                if (isDisparo()){
                    pintarCaminoD(getPosiciony(),getPosicionx(),isDisparo(),true);
                } else {
                    boolean sobreLasAguas1;
                    if (escenario1[getPosiciony()][getPosicionx()].tanque!=null){
                        sobreLasAguas1 = false;
                    } else {
                        sobreLasAguas1 = true;
                    }
                    pintarCaminoD(getPosiciony(),getPosicionx(),isDisparo(),sobreLasAguas1);
                }
            }
        });

        vehS = new JLabel();
        vehS.setBounds(360,590,60,60);
        if (jugador2!=null){
            ImageIcon ima = new ImageIcon(seleccionados2[0].getTheImagen().getImage().getScaledInstance(60,60,Image.SCALE_REPLICATE));
            vehS.setIcon(ima);
            String lo = "<html><body>"+seleccionados2[0].getNombre()+"</body></html>";
            vehS.setToolTipText(lo);
        } else {
            ImageIcon ima = new ImageIcon(seleccionados[0].getTheImagen().getImage().getScaledInstance(60,60,Image.SCALE_REPLICATE));
            vehS.setIcon(ima);
            String lo = "<html><body>"+seleccionados[0].getNombre()+"</body></html>";
            vehS.setToolTipText(lo);
            fondito.add(vehS);
        }

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
                setDisparo(false);
                mostrarAciones("Elegiste moverte, pero ¿hacia donde?");
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
                setDisparo(true);
                mostrarAciones("Elegiste disparar, pero ¿hacia donde?");
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
                if (jugador2==null){
                    Random alea = new Random(System.nanoTime());
                    if (isDisparo()){
                        if (alea.nextInt(101)<70){
                            switch (getDirec())
                            {
                                case 0:
                                {
                                    jugaremos.dispararA(getPosiciony(), getPosicionx(),1);
                                    break;
                                }
                                case 1:
                                {
                                    jugaremos.dispararL(getPosiciony(), getPosicionx(),1);
                                    break;
                                }
                                case 2:
                                {
                                    jugaremos.dispararR(getPosiciony(), getPosicionx(),1);
                                    break;
                                }
                                case 3:
                                {
                                    jugaremos.dispararD(getPosiciony(), getPosicionx(),1);
                                    break;
                                }
                                default:
                                    System.out.println("no");
                            }
                            if (jugaremos.isInicio()){
                                jugaremos.disparoBoot();
                                jugaremos.disparoEnemigos();
                                verificarGanador();
                            } else {
                                jugaremos.disparoBoot();
                                jugaremos.disparoEnemigos();
                                mostrarAciones("Turno "+jugaremos.getTurnos());
                                verificarGanador();
                            }
                        } else
                        {
                            jugaremos.disparoBoot();
                            jugaremos.disparoEnemigos();
                            mostrarAciones("Has fallado el disparo");
                            verificarGanador();
                        }
                    } else
                    {
                        int movimiento = alea.nextInt(6)+1;
                        boolean sobreLasAguas2;
                        if (escenario1[getPosiciony()][getPosicionx()].tanque!=null){
                            sobreLasAguas2 = false;
                        } else {
                            sobreLasAguas2 = true;
                        }
                        mostrarAciones("Dada tu suerte, te mueves "+movimiento);
                        switch(getDirec()){
                            case 0:
                            {
                                jugaremos.moverJugadorA(getPosicionx(),getPosiciony(),movimiento,sobreLasAguas2);
                                break;
                            }
                            case 1:
                            {
                                jugaremos.moverJugadorL(getPosicionx(),getPosiciony(),movimiento,sobreLasAguas2);
                                break;
                            }
                            case 2:
                            {
                                jugaremos.moverJugadorR(getPosicionx()+1,getPosiciony(),movimiento,sobreLasAguas2);
                                break;
                            }
                            case 3:
                            {
                                jugaremos.moverJugadorD(getPosicionx(),getPosiciony()+1,movimiento,sobreLasAguas2);
                                break;
                            }
                        }
                        jugaremos.disparoBoot();
                        jugaremos.disparoEnemigos();
                        verificarGanador();
                        quitarMarcas();
                    }
                } else {
                    if (inicioJugador1) {
                        cambioDePosiciones(getPosicion1y(),getPosicion1x());
                        cambioDeArrays(seleccionados2);
                        Random alea = new Random(System.nanoTime());
                        if (isDisparo()) {
                            if (alea.nextInt(101) < 70) {
                                switch (getDirec()) {
                                    case 0: {
                                        jugaremos.dispararA(getPosiciony(), getPosicionx(), 1);
                                        break;
                                    }
                                    case 1: {
                                        jugaremos.dispararL(getPosiciony(), getPosicionx(), 1);
                                        break;
                                    }
                                    case 2: {
                                        jugaremos.dispararR(getPosiciony(), getPosicionx(), 1);
                                        break;
                                    }
                                    case 3: {
                                        jugaremos.dispararD(getPosiciony(), getPosicionx(), 1);
                                        break;
                                    }
                                    default:
                                        System.out.println("no");
                                }
                                if (jugaremos.isInicio()) {
                                    jugaremos.disparoBoot();
                                    jugaremos.disparoEnemigos();
                                    verificarGanador();
                                } else {
                                    jugaremos.disparoBoot();
                                    jugaremos.disparoEnemigos();
                                    mostrarAciones("Turno " + jugaremos.getTurnos());
                                    verificarGanador();
                                }
                            } else {
                                jugaremos.disparoBoot();
                                jugaremos.disparoEnemigos();
                                mostrarAciones("Has fallado el disparo");
                                verificarGanador();
                            }
                        } else {
                            int movimiento = alea.nextInt(6) + 1;
                            boolean sobreLasAguas2;
                            if (escenario1[getPosiciony()][getPosicionx()].tanque != null) {
                                sobreLasAguas2 = false;
                            } else {
                                sobreLasAguas2 = true;
                            }
                            mostrarAciones("Dada tu suerte, te mueves " + movimiento);
                            switch (getDirec()) {
                                case 0: {
                                    jugaremos.moverJugadorA(getPosicionx(), getPosiciony(), movimiento, sobreLasAguas2);
                                    break;
                                }
                                case 1: {
                                    jugaremos.moverJugadorL(getPosicionx(), getPosiciony(), movimiento, sobreLasAguas2);
                                    break;
                                }
                                case 2: {
                                    jugaremos.moverJugadorR(getPosicionx() + 1, getPosiciony(), movimiento, sobreLasAguas2);
                                    break;
                                }
                                case 3: {
                                    jugaremos.moverJugadorD(getPosicionx(), getPosiciony() + 1, movimiento, sobreLasAguas2);
                                    break;
                                }
                            }
                            jugaremos.disparoBoot();
                            jugaremos.disparoEnemigos();
                            verificarGanador();
                            quitarMarcas();
                        }
                    }
                }
            }
        });

        sig.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jugador2==null){
                    if(getUtilizando()<2){
                        ImageIcon imagen = seleccionados[getUtilizando()+1].getTheImagen();
                        escenario1[getPosiciony()][getPosicionx()].setIcon(new ImageIcon(imagen.getImage().getScaledInstance(getAncho()-5,getAncho()-5,Image.SCALE_REPLICATE)));
                        escenario1[getPosiciony()][getPosicionx()].setVehiculo(seleccionados[getUtilizando()+1]);
                        vehS.setIcon(new ImageIcon(imagen.getImage().getScaledInstance(60,60,Image.SCALE_REPLICATE)));
                        quitarMarcas();
                        String lo1 = "<html><body>"+seleccionados[getUtilizando()+1].getNombre()+"</body></html>";
                        vehS.setToolTipText(lo1);
                        mostrarAciones("Cambiaste a "+seleccionados[getUtilizando()+1].getNombre());
                        hp.setText(seleccionados[getUtilizando()+1].getHp()+"");
                        pp.setText(seleccionados[getUtilizando()+1].getAtack()+"");
                        setUtilizando(+1);
                        ant.setEnabled(true);
                        ant.setBorderPainted(true);
                        sig.setBorderPainted(true);
                        sig.setEnabled(true);
                        if(getUtilizando()==2)
                        {
                            sig.setEnabled(false);
                            sig.setBorderPainted(false);
                            ant.setEnabled(true);
                            ant.setBorderPainted(true);
                        }
                    } else
                    {
                        sig.setEnabled(false);
                        sig.setBorderPainted(false);
                        ant.setEnabled(true);
                        ant.setBorderPainted(true);
                    }
                } else {

                }
            }
        });
        ant.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(getUtilizando()>0){
                    ImageIcon imagen = seleccionados[getUtilizando()-1].getTheImagen();
                    escenario1[getPosiciony()][getPosicionx()].setIcon(new ImageIcon(imagen.getImage().getScaledInstance(getAncho()-5,getAncho()-5,Image.SCALE_REPLICATE)));
                    escenario1[getPosiciony()][getPosicionx()].setVehiculo(seleccionados[getUtilizando()-1]);
                    vehS.setIcon(new ImageIcon(imagen.getImage().getScaledInstance(60,60,Image.SCALE_REPLICATE)));
                    quitarMarcas();
                    String lo1 = "<html><body>"+seleccionados[getUtilizando()-1].getNombre()+"</body></html>";
                    vehS.setToolTipText(lo1);
                    mostrarAciones("Cambiaste a "+seleccionados[getUtilizando()-1].getNombre());
                    hp.setText(seleccionados[getUtilizando()-1].getHp()+"");
                    pp.setText(seleccionados[getUtilizando()-1].getAtack()+"");
                    setUtilizando(-1);
                    ant.setEnabled(true);
                    ant.setBorderPainted(true);
                    sig.setBorderPainted(true);
                    sig.setEnabled(true);
                    if(getUtilizando()==0)
                    {
                        ant.setEnabled(false);
                        ant.setBorderPainted(false);
                        sig.setBorderPainted(true);
                        sig.setEnabled(true);
                    }
                }
                else
                {
                    ant.setEnabled(false);
                    ant.setBorderPainted(false);
                    sig.setEnabled(true);
                    sig.setBorderPainted(true);
                }
            }
        });
    }

    public void iniciarBoot(){
        cuantosBoots = new JLabel();
        cuantosBoots.setText(jugador.getBootsComprados()+"");
        cuantosBoots.setBounds(252,600,32,20);
        cuantosBoots.setOpaque(false);
        cuantosBoots.setForeground(yellow);
        fondito.add(cuantosBoots);

        boot = new JButton();
        boot.setBounds(240,600,32,20);
        boot.setContentAreaFilled(false);
        fondito.add(boot);

        boot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jugador.getBootsComprados()==0){
                    ImageIcon vault = new ImageIcon("src/fts/desacuerdo.png");
                    JOptionPane.showMessageDialog(null,"No tienes ningún boot comprado, pobre","Falta de dinero",JOptionPane.INFORMATION_MESSAGE,vault);
                } else {
                    for (int i = 0; i<cuantoY; i++){
                        for (int u = 0; u<cuantoX; u++)
                        {
                            escenario1[i][u].setClick(true);
                        }
                    }
                }
            }
        });

    }

    /**
     * Metodo encargado de ver lo correspondiente a los tableros
     */
    public void iniciarEscenarios(){
        int posicionInicialX;
        int posicionInicialY;
        int objetos, enemigos;
        switch (escenario){
            case 1:
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
            case 2:
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
            case 3:
            {
                ancho = 60;
                setCuantoX(8);
                setCuantoY(9);
                objetos = 24;
                enemigos = 4;
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
                    escenario1[es][es1].inicializarObjetos(2);
                    escenario1[es][es1].setToolTipText("HP: "+escenario1[es][es1].montania.getPrueba());

                } else if (esmontana==1){
                    escenario1[es][es1].inicializarObjetos(1);
                }
                intentos++;
            }
        }
        if (!modalida){
            intentos = 0;
            while (intentos<enemigos) {
                int fue = queSera1.nextInt(cuantoY);
                int fue1 = queSera1.nextInt(cuantoX) + 1 - 1;
                if (escenario1[fue][fue1].getTieneAlgo() == 0) {
                    escenario1[fue][fue1].iniciarEnemigo();
                    escenario1[fue][fue1].setToolTipText("HP: "+escenario1[fue][fue1].enemigos.getHp());
                    intentos++;
                }
            }
        }

        if (jugador2==null){
            intentos = 0;
            while(intentos==0){
                int fue1 = queSera1.nextInt(cuantoY);
                int fue2 = queSera1.nextInt(cuantoX) + 1 - 1;
                if (escenario1[fue1][fue2].getTieneAlgo() == 0) {
                    ImageIcon imagen = seleccionados[getUtilizando()].getTheImagen();
                    escenario1[fue1][fue2].setVehiculo(seleccionados[getUtilizando()]);
                    escenario1[fue1][fue2].setIcon(new ImageIcon(imagen.getImage().getScaledInstance(ancho-5,ancho-5,Image.SCALE_REPLICATE)));
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
                    ImageIcon imagen = seleccionados2[0].getTheImagen();
                    escenario1[0][fue2].setVehiculo(seleccionados2[0]);
                    escenario1[0][fue2].setIcon(new ImageIcon(imagen.getImage().getScaledInstance(ancho-5,ancho-5,Image.SCALE_REPLICATE)));
                    escenario1[0][fue2].setContentAreaFilled(true);
                    escenario1[0][fue2].setBackground(yellow);
                    pintarCasilla(0,fue2,0);
                    setPosicion1x(fue2);
                    setPosicion1y(0);
                    mostrarAciones(jugador1.getNombre()+" entras a la guerra en ("+(fue2+1)+",0).");
                    intentos2++;
                }
            }
            intentos2 = 0;
            while (intentos2==0){
                int fue = queSera1.nextInt(cuantoX);
                if (escenario1[cuantoY-1][fue].getTieneAlgo()==0){
                    ImageIcon imagen = seleccionados3[getUtilizando()].getTheImagen();
                    escenario1[cuantoY-1][fue].setVehiculo(seleccionados3[0]);
                    escenario1[cuantoY-1][fue].setIcon(new ImageIcon(imagen.getImage().getScaledInstance(ancho-5,ancho-5,Image.SCALE_REPLICATE)));
                    escenario1[cuantoY-1][fue].setContentAreaFilled(true);
                    escenario1[cuantoY-1][fue].setBackground(yellow);
                    pintarCasilla(cuantoY-1,fue,0);
                    setPosicion2y(cuantoY-1);
                    setPosicion2x(fue);
                    mostrarAciones(jugador2.getNombre()+" entras a la guerra en ("+(fue+1)+","+(cuantoY+1)+").");
                    intentos2++;
                }
            }
        }
    }
    public void verificarGanador(){
        int enemigos=0;
        for (int i = 0; i<cuantoY;i++)
            for (int u = 0; u<cuantoX; u++)
            {
                if (escenario1[i][u].enemigos!=null){
                    enemigos++;
                }
            }
        if (enemigos==0){
            ImageIcon vault = new ImageIcon("src/fts/ganaste.png");
            JOptionPane.showMessageDialog(null,"¡Ganaste!","¡Ganaste!",JOptionPane.INFORMATION_MESSAGE,vault);
        }
    }
    public void pintarCaminos(boolean disparo){
        boolean sobreLasAguas;
        if (escenario1[getPosiciony()][getPosicionx()].tanque!=null){
            sobreLasAguas = false;
        } else {
            sobreLasAguas = true;
        }
        pintarCaminoA(getPosiciony(),getPosicionx(), disparo, sobreLasAguas);
        pintarCaminoL(getPosiciony(),getPosicionx(), disparo, sobreLasAguas);
        pintarCaminoD(getPosiciony(),getPosicionx(), disparo, sobreLasAguas);
        pintarCaminoR(getPosiciony(),getPosicionx(), disparo, sobreLasAguas);
    }
    public void pintarCaminoA(int y,int x, boolean disparo, boolean sobreLasAguas){
        if (y>0){
            if(escenario1[y-1][x].getTieneAlgo()==0){
                pintarCasilla(y-1,x,0);
                pintarCaminoA(y-1,x, disparo, sobreLasAguas);
            } else {
                if (disparo){
                    if (escenario1[y-1][x].getAgua()!=null)
                    {
                        pintarCasilla(y-1,x,0);
                        pintarCaminoA(y-1,x,disparo,sobreLasAguas);
                    } else
                        pintarCasilla(y-1,x,1);
                } else
                    if(sobreLasAguas && escenario1[y-1][x].getAgua()!=null)
                    {
                        pintarCasilla(y-1,x,0);
                        pintarCaminoA(y-1,x,disparo,sobreLasAguas);
                    }
                    else
                        pintarCasilla(y-1,x,3);
            }
        }
    }
    public void quitarMarcas(){
        for (int i = 0; i<cuantoY;i++)
            for (int u = 0; u<cuantoX; u++)
            {
                if (getPosicionx()==u && getPosiciony()==i){
                    escenario1[i][u].setBackground(yellow);
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
    public void cancelarBoot(){
        for (int i = 0; i<cuantoY; i++){
            for (int u = 0; u<cuantoX; u++)
            {
                escenario1[i][u].setClick(false);
            }
        }
    }
    public void pintarCaminoD(int y,int x, boolean disparo, boolean sobreLasAguas) {
        if (y<cuantoY-1){
            if(escenario1[y+1][x].getTieneAlgo()==0){
                pintarCasilla(y+1,x,0);
                pintarCaminoD(y+1,x, disparo, sobreLasAguas);
            } else {
                if (disparo){
                    if(escenario1[y+1][x].getAgua()!=null)
                {
                    pintarCasilla(y+1,x,0);
                    pintarCaminoD(y+1,x,disparo,sobreLasAguas);
                } else
                    pintarCasilla(y+1,x,1);
                } else
                    if(sobreLasAguas && escenario1[y+1][x].getAgua()!=null)
                    {
                        pintarCasilla(y+1,x,0);
                        pintarCaminoD(y+1,x,disparo,sobreLasAguas);
                    }
                    else
                        pintarCasilla(y+1,x,3);
            }
        }
    }
    public void pintarCaminoL(int y, int x, boolean disparo, boolean sobreLasAguas){
        if (x>0){
            if(escenario1[y][x-1].getTieneAlgo()==0){
                pintarCasilla(y,x-1,0);
                pintarCaminoL(y,x-1, disparo,sobreLasAguas);
            } else {
                if (disparo){
                    if (escenario1[y][x-1].getAgua()!=null)
                    {
                        pintarCasilla(y,x-1,0);
                        pintarCaminoL(y,x-1,disparo,sobreLasAguas);
                    }
                    else
                        pintarCasilla(y,x-1,1);
                } else
                    if (sobreLasAguas && escenario1[y][x-1].getAgua()!=null)
                    {
                        pintarCasilla(y,x-1,0);
                        pintarCaminoL(y,x-1,disparo,sobreLasAguas);
                    }
                    else
                        pintarCasilla(y,x-1,3);
            }
        }
    }
    public void pintarCaminoR(int y, int x, boolean disparo, boolean sobreLasAguas){
        if (x<cuantoX-1){
            if(escenario1[y][x+1].getTieneAlgo()==0){
                pintarCasilla(y, x+1,0);
                pintarCaminoR(y,x+1, disparo,sobreLasAguas);
            } else {
                if (disparo){
                    if (escenario1[y][x+1].montania!=null || escenario1[y][x+1].enemigos!=null)
                    {
                        pintarCasilla(y, x+1,1);
                    }
                    else{
                        pintarCasilla(y,x+1,0);
                        pintarCaminoR(y,x+1,disparo,sobreLasAguas);
                    }

                } else
                    if (sobreLasAguas && escenario1[y][x+1].getAgua()!=null)
                    {
                        pintarCasilla(y,x+1,0);
                        pintarCaminoR(y,x+1,disparo,sobreLasAguas);
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
    public void cambioDePosiciones(int y, int x){
        setPosicionx(x);
        setPosiciony(y);
    }
    public void cambioDeArrays(Vehiculos[] selec){
        seleccionados = selec;
    }
    public void setPosicion2x(int posicion2x) {
        this.posicion2x = posicion2x;
    }
    public void setPosicion2y(int posicion2y) {
        this.posicion2y = posicion2y;
    }
    public int getUtilizando() {
        return utilizando;
    }
    public void setUtilizando(int utilizando) {
        this.utilizando += utilizando;
    }
    public int getAncho() {
        return ancho;
    }
    public boolean isDisparo() {
        return disparo;
    }
    public void setDisparo(boolean disparo) {
        this.disparo = disparo;
    }
    public int getDirec() {
        return direc;
    }
    public void setDirec(int direc) {
        this.direc = direc;
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

    public int getUtilizando1() {
        return utilizando1;
    }

    public void setUtilizando1(int utilizando1) {
        this.utilizando1 = utilizando1;
    }

    public int getUtilizando2() {
        return utilizando2;
    }

    public void setUtilizando2(int utilizando2) {
        this.utilizando2 = utilizando2;
    }

    public int getPosicion2x() {
        return posicion2x;
    }

    public int getPosicion2y() {
        return posicion2y;
    }

    public int getPosicion1x() {
        return posicion1x;
    }

    public void setPosicion1x(int posicion1x) {
        this.posicion1x = posicion1x;
    }

    public int getPosicion1y() {
        return posicion1y;
    }

    public void setPosicion1y(int posicion1y) {
        this.posicion1y = posicion1y;
    }
}
