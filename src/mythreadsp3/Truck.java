package mythreadsp3;

import java.io.PrintWriter;
import java.util.ArrayList;

public class Truck {
    private int maxCapacity=0;
    private int currCapacity=0;
    private int reservedCapacity=0;
    private ArrayList<Integer> cargoes;
    public Truck(int maxCap){
        this.maxCapacity=maxCap;
        this.currCapacity=0;
        this.reservedCapacity=0;
        this.cargoes=new ArrayList<>();
    }
    public boolean checkCapacity(){
        return this.currCapacity<=this.reservedCapacity && this.reservedCapacity<=maxCapacity;
    }
    public void reserve(int cargoSize) throws Exception{
        this.reservedCapacity+=cargoSize;
        if(!this.checkCapacity()){
            throw new Exception("too much reserved");
        }
    }
    public void addCargo(int cargoSize) throws Exception{
        this.currCapacity+=cargoSize;
        this.cargoes.add(cargoSize);
        if(!this.checkCapacity()){
            throw new Exception("too much delivered");
        }
    }
    public int emptySize(){
        return this.maxCapacity-this.reservedCapacity;
    }
    public void stats(PrintWriter output){ //for debug
        output.println("--stats--");
        output.println(this.maxCapacity);
        output.println(this.reservedCapacity);
        output.println(this.currCapacity);
    }
    public void unload(PrintWriter output){
        output.println("--unload--");
        for(int i=this.cargoes.size()-1;i>-1;i--){
            output.println(this.cargoes.get(i));
        }
    }
}
