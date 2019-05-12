import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Jugar {
    Vehiculos[] seleccionados;
    Arma[] seleccionadas;
    CampoDeBatalla base;
    int ataque = 45;
    private int turnos=1;

    public Jugar(Vehiculos[] seleccionados, Arma[] seleccionadas, CampoDeBatalla base) {
        this.seleccionados = seleccionados;
        this.seleccionadas = seleccionadas;
        this.base = base;
        iniciarTurnos();
    }
    public void iniciarTurnos(){
        base.mostrarAciones("Turno "+getTurnos());
        Random alea = new Random(System.nanoTime());

        if (alea.nextInt(2)==0)
        {
            base.mostrarAciones("Inicia "+base.jugador.getNombre());

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

    public void setTurnos(int turnos) {
        this.turnos += turnos;
    }

    public void disparoEnemigos(){
        for (int i = 0; i < base.getCuantoY(); i++)
            for (int u = 0; u < base.getCuantoX(); u++)
            {
                if (base.escenario1[i][u].enemigos!=null)
                {
                    Random nuevoA = new Random(System.nanoTime());
                    if(nuevoA.nextInt(101)<75)
                    {
                        disparar(i,u,nuevoA.nextInt(4),0);
                    } else
                    {
                        System.out.println("disparo "+i+u);
                        base.mostrarAciones(base.escenario1[i][u].enemigos.getNombre()+" fallo");
                    }
                }
            }
    }
    public void disparar(int y, int x, int direc, int a){

        switch (direc)
        {
            case 0:
            {
                dispararA(y,x,a);
                break;
            }
            case 1:
            {
                dispararL(y,x,a);
                break;
            }
            case 2:
            {
                dispararR(y,x,a);
                break;
            }
            case 3:
            {
                dispararD(y,x,a);
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
                        } else
                            base.mostrarAciones("Le disparaste a un enemigo");
                    }
                }else {
                    dispararA(y-1,x,a);
                }
            }
        } else {
            base.mostrarAciones("Alguien idiota le dio a la nada");
        }
    }
    public void dispararL(int y, int x, int a){
        try
        {
            Thread.sleep(100);
        }
        catch(InterruptedException exception)
        {
            Thread.currentThread().interrupt();
        }
        if (x>0)
        {
            if (a==0){
                if (base.escenario1[y][x-1].getTieneAlgo()==1)
                {
                    if (base.escenario1[y][x-1].montania!=null){
                        base.escenario1[y][x-1].montania.setPrueba(-(this.ataque));
                        base.escenario1[y][x-1].setToolTipText("HP: "+base.escenario1[y][x-1].montania.getPrueba());
                        if (base.escenario1[y][x-1].montania.getPrueba()<=0){
                            base.escenario1[y][x-1].eliminarLoQueTenga();
                            base.escenario1[y][x-1].setToolTipText("");
                            base.mostrarAciones("Enemigo elimino una montaña");
                        } else
                            base.mostrarAciones("Enemigo le disparó a una montaña");
                    }
                    if (base.escenario1[y][x-1].getVehiculo()!=null)
                    {
                        base.escenario1[y][x-1].getVehiculo().setHp(-(this.ataque));
                        base.hp.setText(base.escenario1[y][x-1].getVehiculo().getHp()+"");
                        base.mostrarAciones("El enemigo te disparó");
                    }
                }else {
                    dispararL(y,x-1,a);
                }
            }
            else {
                if (base.escenario1[y][x-1].getTieneAlgo()==1)
                {
                    if (base.escenario1[y][x-1].montania!=null){
                        base.escenario1[y][x-1].montania.setPrueba(-base.seleccionados[base.getUtilizando()].getAtack());
                        base.escenario1[y][x-1].setToolTipText("HP: "+base.escenario1[y][x-1].montania.getPrueba());
                        if (base.escenario1[y][x-1].montania.getPrueba()<=0){
                            base.escenario1[y][x-1].eliminarLoQueTenga();
                            base.escenario1[y][x-1].setToolTipText("");
                            base.mostrarAciones("Eliminaste una montaña");
                        } else
                            base.mostrarAciones("Le disparaste a una montaña");
                    }
                    if (base.escenario1[y][x-1].enemigos!=null)
                    {
                        base.escenario1[y][x-1].enemigos.setHp(-base.seleccionados[base.getUtilizando()].getAtack());
                        base.escenario1[y][x-1].setToolTipText("HP: "+base.escenario1[y][x-1].enemigos.getHp());
                        if (base.escenario1[y][x-1].enemigos.getHp()<=0)
                        {
                            base.escenario1[y][x-1].eliminarLoQueTenga();
                            base.escenario1[y][x-1].setToolTipText("");
                            base.mostrarAciones("Eliminaste a un enemigo");
                        } else
                            base.mostrarAciones("Le disparaste a un enemigo");
                    }
                }else {
                    dispararL(y,x-1,a);
                }
            }
        } else
            base.mostrarAciones("Alguien idiota le dio a la nada");
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
                        base.escenario1[y+1][x].montania.setPrueba(-(this.ataque));
                        base.escenario1[y+1][x].setToolTipText("HP: "+base.escenario1[y+1][x].montania.getPrueba());
                        if (base.escenario1[y+1][x].montania.getPrueba()<=0)
                        {
                            base.escenario1[y+1][x].eliminarLoQueTenga();
                            base.escenario1[y+1][x].setToolTipText("");
                            base.mostrarAciones("Enemigo elimino una montaña");
                        } else
                            base.mostrarAciones("Enemigo le disparo a una montaña");
                    }
                    if (base.escenario1[y+1][x].getVehiculo()!=null)
                    {
                        base.escenario1[y+1][x].getVehiculo().setHp(-(this.ataque));
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
                            base.escenario1[y+1][x].setToolTipText("");
                            base.mostrarAciones("Eliminaste una montaña");
                        } else
                            base.mostrarAciones("Le disparaste a una montaña");
                    }
                    if (base.escenario1[y+1][x].enemigos!=null)
                    {
                        base.escenario1[y+1][x].enemigos.setHp(-base.seleccionados[base.getUtilizando()].getAtack());
                        base.escenario1[y+1][x].setToolTipText("HP: "+base.escenario1[y+1][x].enemigos.getHp());
                        if (base.escenario1[y+1][x].enemigos.getHp()<=0)
                        {
                            base.escenario1[y+1][x].eliminarLoQueTenga();
                            base.escenario1[y+1][x].setToolTipText("");
                            base.mostrarAciones("Eliminaste a un enemigo");
                        } else
                            base.mostrarAciones("Le disparaste a un enemigo");
                    }
                }else {
                    dispararD(y+1,x,a);
                }
            }
        }
        else
            base.mostrarAciones("Alguien idiota le dio a la nada");
    }
}
