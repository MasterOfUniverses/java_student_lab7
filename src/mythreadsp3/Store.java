package mythreadsp3;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Store {
    private LinkedList<Integer> cargoes;
    public Store(){
        this.cargoes=new LinkedList<>();
    }
    public void genNewCargoes(int minLim,int maxLim,int number){
        Random rnd = new Random();
        for(int i=0;i<number;i++){
            cargoes.add(rnd.nextInt(maxLim-minLim)+minLim);
        }
    }
    public Store(List<Integer> data){
        this.cargoes=new LinkedList<>();
        for(int i=0;i< data.size();i++){
            this.cargoes.add(data.get(i));
        }
    }
    public void startWork(PrintWriter output,int truckCap, ArrayList<Worker> workers){
        Truck truck = new Truck(truckCap);
        for(Worker man:workers){
            man.setStore(this);
            man.setTruck(truck);
            man.setOutputWriter(output);
            /*try {
                man.setOutputWriter(new PrintWriter(man.getWorkerName() + ".txt"));
            }catch (Exception e){
                System.out.println("print stats err");
            }
            */
        }
        System.out.println("start work");
        for(Worker man:workers){
            man.start();
            System.out.println(man.getWorkerName()+" start");
        }
        for(Worker man:workers){
            try{
                if(man.isAlive()){
                    man.join();
                }
            }catch(Exception e){
                System.out.println("print join err");
            }
        }
        truck.unload(output);
    }
    public synchronized int findCargo(int maxCap){
        for(int i=0;i<this.cargoes.size();i++){
            if(this.cargoes.get(i)<=maxCap){
                int cargo = this.cargoes.get(i);
                this.cargoes.remove(i);
                return cargo;
            }
        }
        return -1;
    }
}
