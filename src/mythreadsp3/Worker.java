package mythreadsp3;

import java.io.PrintWriter;

public class Worker extends Thread {
    private String name;
    private PrintWriter output;
    private int power;
    private Store store;
    private Truck truck;
    public Worker(String name,int power){
        this.name = name;
        this.power=power;
    }
    public void setStore(Store store){
        this.store=store;
    }
    public void setTruck(Truck truck){
        this.truck=truck;
    }
    public void setOutputWriter(PrintWriter output){
        this.output = output;
    }
    public String getWorkerName(){
        return this.name;
    }
    @Override
    public void run(){
        int maxCargoSize = this.truck.emptySize();
        do{
            int cargo = this.store.findCargo(maxCargoSize);
            if(cargo>-1){
                try {
                    this.truck.reserve(cargo);
                    sleep(1000*cargo/this.power);
                    this.truck.addCargo(cargo);
                    output.println("--"+this.name+"--");
                    this.truck.stats(output);
                }catch (Exception e){
                    output.println(e.getMessage());
                }
            }
            else{
                break;
            }
            maxCargoSize = this.truck.emptySize();
        }
        while(maxCargoSize>0);
        output.println("--"+this.name+": end--");
    }
}
