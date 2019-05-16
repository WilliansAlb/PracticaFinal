import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Jugar {
    Vehiculos[] seleccionados;
    Arma[] seleccionadas;
    CampoDeBatalla base;
    int ataque = 45;
    private int turnos=1;
    private boolean inicio;

    public Jugar(Vehiculos[] seleccionados, Arma[] seleccionadas, CampoDeBatalla base) {
        this.seleccionados = seleccionados;
        this.seleccionadas = seleccionadas;
        this.base = base;
        iniciarTurnos();
    }
    public void iniciarTurnos(){
        base.mostrarAciones("Turno "+getTurnos());
        Random alea = new Random(System.nanoTime());

        if (turnos==1){
            if (alea.nextInt(2)==0){
                setInicio(true);
            } else {
                setInicio(false);
            }
        }

        if (isInicio())
        {
            if(turnos==1){
                base.mostrarAciones("Inicia "+base.jugador.getNombre());
            } else {
                base.mostrarAciones("Te toca "+base.jugador.getNombre());
            }
        }
        else
        {
            base.mover.setEnabled(false);
            base.disparar.setEnabled(false);
            base.dados.setEnabled(false);
            base.mostrarAciones("Inician los enemigos");
            try
            {
                Thread.sleep(6000);
            }
            catch(InterruptedException exception)
            {
                Thread.currentThread().interrupt();
            }
            disparoEnemigos();
            base.mover.setEnabled(true);
            base.disparar.setEnabled(true);
            base.dados.setEnabled(true);
        }
        turnos++;
    }

    public int getTurnos() {
        return turnos;
    }

    public void disparoEnemigos(){
        int fallos = 0;
        int disparosAire = 0;
        for (int i = 0; i < base.getCuantoY(); i++)
            for (int u = 0; u < base.getCuantoX(); u++)
            {
                if (base.escenario1[i][u].enemigos!=null)
                {
                    Random nuevoA = new Random(System.nanoTime());
                    if(nuevoA.nextInt(101)<75)
                    {
                        int aleatorio1 = nuevoA.nextInt(4);
                        if (i==0 && aleatorio1==0){
                            disparosAire++;
                        } else if (i==base.getCuantoY()-1 && aleatorio1==3)
                        {
                            disparosAire++;
                        } else if (i==base.getCuantoY()-1 && u==base.getCuantoX()){
                            if (aleatorio1==2 || aleatorio1==3){
                                disparosAire++;
                            } else {
                                disparar(i,u,aleatorio1,0);
                            }
                        } else if (i==0 && u==0){
                            if (aleatorio1==0 || aleatorio1==1){
                                disparosAire++;
                            } else {
                                disparar(i,u,aleatorio1,0);
                            }
                        } else
                            disparar(i,u,aleatorio1,0);
                    } else
                    {
                        fallos++;
                    }
                }
            }
        if(!isInicio()){
            base.mostrarAciones("Te toca "+base.jugador.getNombre());
        }
        if (fallos>0){
            base.mostrarAciones("Enemigos fallaron "+fallos+" veces");
        }
        if (disparosAire>0){
            base.mostrarAciones("Enemigos le dispararon al aire "+disparosAire+" veces");
        }
    }
    public void disparar(int y, int x, int direc, int a){

        switch (direc)
        {
            case 0:
            {
                dispararA(y,x,a);
                mostrarHP();
                break;
            }
            case 1:
            {
                dispararL(y,x,a);
                mostrarHP();
                break;
            }
            case 2:
            {
                dispararR(y,x,a);
                mostrarHP();
                break;
            }
            case 3:
            {
                dispararD(y,x,a);
                mostrarHP();
                break;
            }
            default:
                System.out.println("no funciona");
        }
    }
    public void dispararA(int y, int x, int a){
        try
        {
            Thread.sleep(100);
        }
        catch(InterruptedException exception)
        {
            Thread.currentThread().interrupt();
        }
        if (y>0)
        {
            if (a==0){
                if (base.escenario1[y-1][x].getTieneAlgo()==1)
                {
                    if (base.escenario1[y-1][x].montania!=null){
                        base.escenario1[y-1][x].montania.setPrueba(-(this.ataque));
                        base.escenario1[y-1][x].setToolTipText("HP: "+base.escenario1[y-1][x].montania.getPrueba());
                        if (base.escenario1[y-1][x].montania.getPrueba()<=0){
                            base.escenario1[y-1][x].eliminarLoQueTenga();
                            base.escenario1[y-1][x].setToolTipText("");
                            base.mostrarAciones("Enemigo elimino a una montaña");
                        } else
                            base.mostrarAciones("Enemigo le disparó a una montaña");
                    }
                    if (base.escenario1[y-1][x].getVehiculo()!=null)
                    {
                        base.escenario1[y-1][x].getVehiculo().setHp(-(this.ataque));
                        base.mostrarAciones("El enemigo te disparó");
                    }
                }else {
                    dispararA(y-1,x,a);
                }
            }
            else {
                if (base.escenario1[y-1][x].getTieneAlgo()==1)
                {
                    if (base.escenario1[y-1][x].montania!=null){
                        base.escenario1[y-1][x].montania.setPrueba(-base.seleccionados[base.getUtilizando()].getAtack());
                        base.escenario1[y-1][x].setToolTipText("HP: "+base.escenario1[y-1][x].montania.getPrueba());
                        if (base.escenario1[y-1][x].montania.getPrueba()<=0){
                            base.escenario1[y-1][x].eliminarLoQueTenga();
                            base.escenario1[y-1][x].setToolTipText("");
                            base.mostrarAciones("Eliminaste una montaña");
                        } else
                            base.mostrarAciones("Le disparaste a una montaña");
                    }
                    if (base.escenario1[y-1][x].enemigos!=null)
                    {
                        base.escenario1[y-1][x].enemigos.setHp(-base.seleccionados[base.getUtilizando()].getAtack());
                        base.escenario1[y-1][x].setToolTipText("HP: "+base.escenario1[y-1][x].enemigos.getHp());
                        if (base.escenario1[y-1][x].enemigos.getHp()<=0)
                        {
                            base.escenario1[y-1][x].eliminarLoQueTenga();
                            base.escenario1[y-1][x].setToolTipText("");
                            base.mostrarAciones("Eliminaste a un enemigo");
                            base.seleccionados[base.getUtilizando()].setEliminados(1);
                        } else
                            base.mostrarAciones("Le disparaste a un enemigo");
                    }
                }else {
                    dispararA(y-1,x,a);
                }
            }
        }
    }
    public void dispararL(int y, int x, int a) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
        }
        if (x > 0) {
            if (a == 0) {
                if (base.escenario1[y][x - 1].getTieneAlgo() == 1) {
                    if (base.escenario1[y][x - 1].montania != null) {
                        base.escenario1[y][x - 1].montania.setPrueba(-(this.ataque));
                        base.escenario1[y][x - 1].setToolTipText("HP: " + base.escenario1[y][x - 1].montania.getPrueba());
                        if (base.escenario1[y][x - 1].montania.getPrueba() <= 0) {
                            base.escenario1[y][x - 1].eliminarLoQueTenga();
                            base.escenario1[y][x - 1].setToolTipText("");
                            base.mostrarAciones("Enemigo elimino una montaña");
                        } else
                            base.mostrarAciones("Enemigo le disparó a una montaña");
                    }
                    if (base.escenario1[y][x - 1].getVehiculo() != null) {
                        base.escenario1[y][x - 1].getVehiculo().setHp(-(this.ataque));
                        base.hp.setText(base.escenario1[y][x - 1].getVehiculo().getHp() + "");
                        base.mostrarAciones("El enemigo te disparó");
                    }
                } else {
                    dispararL(y, x - 1, a);
                }
            } else {
                if (base.escenario1[y][x - 1].getTieneAlgo() == 1) {
                    if (base.escenario1[y][x - 1].montania != null) {
                        base.escenario1[y][x - 1].montania.setPrueba(-base.seleccionados[base.getUtilizando()].getAtack());
                        if (base.escenario1[y][x - 1].montania.getPrueba() <= 0) {
                            base.escenario1[y][x - 1].eliminarLoQueTenga();
                            base.escenario1[y][x - 1].setToolTipText("");
                            base.mostrarAciones("Eliminaste una montaña");
                        } else
                            base.mostrarAciones("Le disparaste a una montaña");
                    }
                    if (base.escenario1[y][x-1].enemigos != null) {
                        base.escenario1[y][x-1].enemigos.setHp(-base.seleccionados[base.getUtilizando()].getAtack());
                        base.escenario1[y][x-1].setToolTipText("HP: " + base.escenario1[y][x - 1].enemigos.getHp());
                        if (base.escenario1[y][x-1].enemigos.getHp() <= 0) {
                            base.escenario1[y][x-1].eliminarLoQueTenga();
                            base.escenario1[y][x-1].setToolTipText("");
                            base.mostrarAciones("Eliminaste a un enemigo");
                            base.seleccionados[base.getUtilizando()].setEliminados(1);
                        } else
                            base.mostrarAciones("Le disparaste a un enemigo");
                    }
                } else {
                    dispararL(y, x - 1, a);
                }
            }
        }
    }
    public void dispararR(int y, int x, int a){
        try
        {
            Thread.sleep(100);
        }
        catch(InterruptedException exception)
        {
            Thread.currentThread().interrupt();
        }
        if (x<base.getCuantoX()-1)
        {
            if (a==0){
                if (base.escenario1[y][x+1].getTieneAlgo()==1)
                {
                    if (base.escenario1[y][x+1].montania!=null){
                        base.escenario1[y][x+1].montania.setPrueba(-(this.ataque));
                        base.escenario1[y][x+1].setToolTipText("HP: "+base.escenario1[y][x+1].montania.getPrueba());
                        if (base.escenario1[y][x+1].montania.getPrueba()<=0){
                            base.escenario1[y][x+1].eliminarLoQueTenga();
                            base.escenario1[y][x+1].setToolTipText("");
                            base.mostrarAciones("Enemigo elimino una montaña");
                        } else
                            base.mostrarAciones("Enemigo le disparo a una montaña");
                    }
                    if (base.escenario1[y][x+1].getVehiculo()!=null)
                    {
                        base.escenario1[y][x+1].getVehiculo().setHp(-(this.ataque));
                        base.mostrarAciones("El enemigo te disparó");
                    }
                }else {
                    dispararR(y,x+1,a);
                }
            }
            else {
                if (base.escenario1[y][x+1].getTieneAlgo()==1)
                {
                    if (base.escenario1[y][x+1].montania!=null){
                        base.escenario1[y][x+1].montania.setPrueba(-base.seleccionados[base.getUtilizando()].getAtack());
                        base.escenario1[y][x+1].setToolTipText("HP: "+base.escenario1[y][x+1].montania.getPrueba());
                        if (base.escenario1[y][x+1].montania.getPrueba()<=0){
                            base.escenario1[y][x+1].eliminarLoQueTenga();
                            base.escenario1[y][x+1].setToolTipText("");
                            base.mostrarAciones("Eliminaste una montaña");
                        } else
                            base.mostrarAciones("Le disparaste a una montaña");
                    }
                    if (base.escenario1[y][x+1].enemigos!=null)
                    {
                        base.escenario1[y][x+1].enemigos.setHp(-base.seleccionados[base.getUtilizando()].getAtack());
                        base.escenario1[y][x+1].setToolTipText("HP: "+base.escenario1[y][x+1].enemigos.getHp());
                        if (base.escenario1[y][x+1].enemigos.getHp()<=0)
                        {
                        base.escenario1[y][x+1].eliminarLoQueTenga();
                        base.escenario1[y][x+1].setToolTipText("");
                        base.mostrarAciones("Eliminaste a un enemigo");
                        base.seleccionados[base.getUtilizando()].setEliminados(1);
                        } else
                            base.mostrarAciones("Le disparaste a un enemigo");
                    }
                }else {
                    dispararR(y,x+1,a);
                }
            }
        } else
            base.mostrarAciones("Alguien idiota le dio a la nada");
    }
    public void dispararD(int y, int x, int a){
        try
        {
            Thread.sleep(100);
        }
        catch(InterruptedException exception)
        {
            Thread.currentThread().interrupt();
        }
        if (y<base.getCuantoY()-1)
        {
            if (a==0){
                if (base.escenario1[y+1][x].getTieneAlgo()==1)
                {
                    if (base.escenario1[y+1][x].montania!=null){
                        base.escenario1[y+1][x].montania.setPrueba(-this.ataque);
                        if (base.escenario1[y+1][x].montania.getPrueba()<=0)
                        {
                            base.escenario1[y+1][x].eliminarLoQueTenga();
                            base.mostrarAciones("Enemigo elimino una montaña");
                        } else
                            base.mostrarAciones("Enemigo le disparo a una montaña");
                    }
                    if (base.escenario1[y+1][x].getVehiculo()!=null)
                    {
                        base.escenario1[y+1][x].getVehiculo().setHp(-this.ataque);
                        base.hp.setText("HP: "+base.escenario1[y+1][x].getVehiculo().getHp());
                        base.mostrarAciones("El enemigo te disparó");
                    }
                }else {
                    dispararD(y+1,x,a);
                }
            }
            else {
                if (base.escenario1[y+1][x].getTieneAlgo()==1)
                {
                    if (base.escenario1[y+1][x].montania!=null){
                        base.escenario1[y+1][x].montania.setPrueba(-base.seleccionados[base.getUtilizando()].getAtack());
                        base.escenario1[y+1][x].setToolTipText("HP: "+base.escenario1[y+1][x].montania.getPrueba());
                        if (base.escenario1[y+1][x].montania.getPrueba()<=0){
                            base.escenario1[y+1][x].eliminarLoQueTenga();
                            base.mostrarAciones("Eliminaste una montaña");
                        } else
                            base.mostrarAciones("Le disparaste a una montaña");
                    }
                    if (base.escenario1[y+1][x].enemigos!=null)
                    {
                        base.escenario1[y+1][x].enemigos.setHp(-base.seleccionados[base.getUtilizando()].getAtack());
                        if (base.escenario1[y+1][x].enemigos.getHp()<=0)
                        {
                            base.escenario1[y+1][x].eliminarLoQueTenga();
                            base.mostrarAciones("Eliminaste a un enemigo");
                            base.seleccionados[base.getUtilizando()].setEliminados(1);
                        } else
                            base.mostrarAciones("Le disparaste a un enemigo");
                    }
                }else {
                    dispararD(y+1,x,a);
                }
            }
        }
    }

    public boolean isInicio() {
        return inicio;
    }

    public void setInicio(boolean inicio) {
        this.inicio = inicio;
    }
    public void disparoBoot(){
        for (int i = 0; i < base.getCuantoY(); i++)
            for (int u = 0; u < base.getCuantoX(); u++)
            {
                if (base.escenario1[i][u].boot!=null)
                {
                    if (base.escenario1[i][u].boot.getNumRec()==0){
                        base.escenario1[i][u].eliminarLoQueTenga();
                        base.mostrarAciones("El boot explotó");
                    } else {
                        base.escenario1[i][u].setBackground(Color.blue);
                        Random nuevoA = new Random(System.nanoTime());
                        if(nuevoA.nextInt(101)<85)
                        {
                            disparoBoot1(i,u);
                        }
                        else
                        {
                            base.mostrarAciones("Boot falló");
                            base.escenario1[i][u].getBoot().setNumRec(-1);
                        }
                    }
                }
            }
    }
    public void disparoBoot1(int y, int x){
        int montaniasP = 0;
        int enemigosP = 0;
        for (int p=1; p<3; p++)
        {
            for (int q =1; q<3; q++){
                try{
                    if(q<base.getCuantoX() && p<base.getCuantoY()){
                        if(base.escenario1[y+p][x+q].tieneAlgo==1 && base.escenario1[y+p][x+q].agua==null && base.escenario1[y+p][x+q].getVehiculo()==null)
                        {
                            if (base.escenario1[y+p][x+q].montania!=null)
                            {
                                verificarMBoot(y+p,x+q);
                                montaniasP++;
                            }
                            if (base.escenario1[y+p][x+q].enemigos!=null){
                                verificarEBoot(y+p,x+q);
                                enemigosP++;
                            }
                        }
                    }
                } catch (Exception e){

                }
            }
        }
        for (int p=1; p<3; p++)
        {
            for (int q =1; q<3; q++){
                try{
                    if((x-q)>=0 && (y-p)>=0){
                        if(base.escenario1[y-p][x-q].tieneAlgo==1 && base.escenario1[y-p][x-q].agua==null && base.escenario1[y-p][x-q].getVehiculo()==null)
                        {
                            if (base.escenario1[y-p][x-q].montania!=null)
                            {
                                verificarMBoot(y-p,x-q);
                                montaniasP++;
                            }
                            if (base.escenario1[y-p][x-q].enemigos!=null){
                                verificarEBoot(y-p,x-q);
                                enemigosP++;
                            }
                        }
                    }
                } catch (Exception e){

                }
            }
        }
        for (int p=1; p<3; p++)
        {
            for (int q =1; q<3; q++){
                try{
                    if(q<base.getCuantoX() && (y-p)>=0){
                        if(base.escenario1[y-p][x+q].tieneAlgo==1 && base.escenario1[y-p][x+q].agua==null && base.escenario1[y-p][x+q].getVehiculo()==null)
                        {
                            if (base.escenario1[y-p][x+q].montania!=null)
                            {
                                verificarMBoot(y-p,x+q);
                                montaniasP++;
                            }
                            if (base.escenario1[y-p][x+q].enemigos!=null){
                                verificarEBoot(y-p,x+q);
                                enemigosP++;
                            }
                        }
                    }
                } catch (Exception e){

                }
            }
        }
        for (int p=1; p<3; p++)
        {
            for (int q =1; q<3; q++){
                try{
                    if((x-q)>=0 && p<base.getCuantoY()){
                        if(base.escenario1[y+p][x-q].tieneAlgo==1 && base.escenario1[y+p][x-q].agua==null && base.escenario1[y+p][x-q].getVehiculo()==null)
                        {
                            if (base.escenario1[y+p][x-q].montania!=null)
                            {
                                verificarMBoot(y+p,x-q);
                                montaniasP++;
                            }
                            if (base.escenario1[y+p][x-q].enemigos!=null){
                                verificarEBoot(y+p,x-q);
                                enemigosP++;
                            }
                        }
                    }
                } catch (Exception e){

                }
            }
        }
        for (int p=1; p<3; p++)
        {
            try{
                if((p+y)<base.getCuantoY()){
                    if(base.escenario1[y+p][x].tieneAlgo==1 && base.escenario1[y+p][x].agua==null && base.escenario1[y+p][x].getVehiculo()==null)
                    {
                        if (base.escenario1[y+p][x].montania!=null)
                        {
                            verificarMBoot(y+p,x);
                            montaniasP++;
                        }
                        if (base.escenario1[y+p][x].enemigos!=null){
                            verificarEBoot(y+p,x);
                            enemigosP++;
                        }
                    }
                }
            } catch (Exception e){

            }
        }
        for (int p=1; p<3; p++)
        {
            try{
                if((y-p)>=0){
                    if(base.escenario1[y-p][x].tieneAlgo==1 && base.escenario1[y-p][x].agua==null && base.escenario1[y-p][x].getVehiculo()==null)
                    {
                        if (base.escenario1[y-p][x].montania!=null)
                        {
                            verificarMBoot(y-p,x);
                            montaniasP++;
                        }
                        if (base.escenario1[y-p][x].enemigos!=null){
                            verificarEBoot(y-p,x);
                            enemigosP++;
                        }
                    }
                }
            } catch (Exception e){

            }
        }
        for (int p=1; p<3; p++)
        {
            try{
                if((x+p)<base.getCuantoX()){
                    if(base.escenario1[y][x+p].tieneAlgo==1 && base.escenario1[y][x+p].agua==null && base.escenario1[y][x+p].getVehiculo()==null)
                    {
                        if (base.escenario1[y][x+p].montania!=null)
                        {
                            verificarMBoot(y,x+p);
                            montaniasP++;
                        }
                        if (base.escenario1[y][x+p].enemigos!=null){
                            verificarEBoot(y,x+p);
                            enemigosP++;
                        }
                    }
                }
            } catch (Exception e){

            }
        }
        for (int p=1; p<3; p++)
        {
            try{
                if((x-p)>=0){
                    if(base.escenario1[y][x-p].tieneAlgo==1 && base.escenario1[y][x-p].agua==null && base.escenario1[y][x-p].getVehiculo()==null)
                    {
                        if (base.escenario1[y][x-p].montania!=null)
                        {
                            verificarMBoot(y,x-p);
                            montaniasP++;
                        }
                        if (base.escenario1[y][x-p].enemigos!=null){
                            verificarEBoot(y,x-p);
                            enemigosP++;
                        }
                    }
                }
            } catch (Exception e){

            }
        }
        mostrarHP();
        base.mostrarAciones("Boot le pegó a "+montaniasP+" montañas y");
        base.mostrarAciones("a "+enemigosP+" enemigos.");
        base.escenario1[y][x].boot.setNumRec(-1);
    }
    public void mostrarHP(){
        for (int i=0; i<base.getCuantoY(); i++)
            for (int u = 0; u < base.getCuantoX();u++)
            {
                if (base.escenario1[i][u].montania!=null){
                    base.escenario1[i][u].setToolTipText("HP: "+base.escenario1[i][u].montania.getPrueba());
                }
                if (base.escenario1[i][u].enemigos!=null){
                    base.escenario1[i][u].setToolTipText("HP: "+base.escenario1[i][u].enemigos.getHp());
                }
            }
    }
    public void moverJugadorA(int posX, int posY, int movimiento, boolean sobreAguas){
        if (movimiento!=0){
            if (posY!=0){
                if (base.escenario1[posY-1][posX].getTieneAlgo()==1){
                    if (base.escenario1[posY-1][posX].enemigos!=null || base.escenario1[posY-1][posX].montania!=null){
                        moverVeh(posY,posX);
                    } else if (base.escenario1[posY-1][posX].agua!=null){
                        if (sobreAguas){
                            moverJugadorA(posX, posY-1,movimiento-1,sobreAguas);
                        } else {
                            moverVeh(posY,posX);
                        }
                    }
                } else {
                    moverJugadorA(posX, posY-1,movimiento-1,sobreAguas);
                }
            } else {
                moverVeh(posY,posX);
            }
        } else {
            moverVeh(posY,posX);
        }
    }
    public void moverJugadorL(int posX, int posY, int movimiento, boolean sobreAguas){
        if (movimiento!=0){
            if (posX!=0){
                if (base.escenario1[posY][posX-1].getTieneAlgo()==1){
                    if (base.escenario1[posY][posX-1].enemigos!=null || base.escenario1[posY][posX-1].montania!=null){
                        moverVeh(posY,posX);
                    } else if (base.escenario1[posY][posX-1].agua!=null){
                        if (sobreAguas){
                            moverJugadorL(posX-1, posY,movimiento-1,sobreAguas);
                        } else {
                            moverVeh(posY,posX);
                        }
                    }
                } else {
                    moverJugadorL(posX-1,posY,movimiento-1,sobreAguas);
                }
            } else {
                moverVeh(posY,posX);
            }
        } else {
            moverVeh(posY,posX);
        }
    }
    public void moverJugadorR(int posX, int posY, int movimiento, boolean sobreAguas){
        if (movimiento!=0){
            if (posX<base.getCuantoX()-1){
                if (base.escenario1[posY][posX+1].getTieneAlgo()==1){
                    if (base.escenario1[posY][posX+1].enemigos!=null || base.escenario1[posY][posX+1].montania!=null){
                        moverVeh(posY,posX);
                    } else if (base.escenario1[posY][posX+1].agua!=null){
                        if (sobreAguas){
                            moverJugadorR(posX+1,posY,movimiento-1,sobreAguas);
                        } else {
                            moverVeh(posY,posX);
                        }
                    }
                } else {
                    moverJugadorR(posX+1,posY,movimiento-1,sobreAguas);
                }
            } else {
                moverVeh(posY,posX);
            }
        } else {
            moverVeh(posY,posX);
        }
    }
    public void moverJugadorD(int posX, int posY, int movimiento, boolean sobreAguas){
        if (movimiento!=0){
            if (posY<base.getCuantoY()-1){
                if (base.escenario1[posY+1][posX].getTieneAlgo()==1){
                    if (base.escenario1[posY+1][posX].enemigos!=null || base.escenario1[posY+1][posX].montania!=null){
                        moverVeh(posY,posX);
                    } else if (base.escenario1[posY+1][posX].agua!=null){
                        if (sobreAguas){
                            moverJugadorD(posX, posY+1,movimiento-1,sobreAguas);
                        } else {
                            moverVeh(posY,posX);
                        }
                    }
                } else {
                    moverJugadorD(posX, posY+1,movimiento-1,sobreAguas);
                }
            } else {
                moverVeh(posY,posX);
            }
        } else {
            moverVeh(posY,posX);
        }
    }

    public void verificarMBoot(int y, int x){
        base.escenario1[y][x].setContentAreaFilled(true);
        base.escenario1[y][x].setBackground(Color.blue);
        base.escenario1[y][x].montania.setPrueba(-15);
        if (base.escenario1[y][x].montania.getPrueba()<=0)
        {
            base.escenario1[y][x].eliminarLoQueTenga();
            base.escenario1[y][x].setContentAreaFilled(false);
        }
    }
    public void verificarEBoot(int y, int x){
        base.escenario1[y][x].setContentAreaFilled(true);
        base.escenario1[y][x].setBackground(Color.blue);
        base.escenario1[y][x].enemigos.setHp(-15);
        if (base.escenario1[y][x].enemigos.getHp()<=0)
        {
            base.escenario1[y][x].eliminarLoQueTenga();
            base.escenario1[y][x].setContentAreaFilled(false);
        }
    }
    public void moverVeh(int posY, int posX){
        ImageIcon imagenT = new ImageIcon(base.seleccionados[base.getUtilizando()].getTheImagen().getImage().getScaledInstance(base.getAncho()-5,base.getAncho()-5,Image.SCALE_REPLICATE));
        base.escenario1[posY][posX].setVehiculo(base.seleccionados[base.getUtilizando()]);
        base.escenario1[posY][posX].setIcon(imagenT);
        base.escenario1[base.getPosiciony()][base.getPosicionx()].eliminarLoQueTenga();
        base.setPosiciony(posY);
        base.setPosicionx(posX);
    }

}
