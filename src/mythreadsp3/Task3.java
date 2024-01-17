package mythreadsp3;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Task3 {
    private static int MAXCAP = 50;
    public static void main(String[] args) {
        String cargoDataFileName = "input3cargo.txt";
        /*try{
            PrintWriter output = new PrintWriter(cargoDataFileName);
            output.println("1 2 3 4 5 6 7 8");
            output.close();
        }catch(Exception e){
            System.out.println("trouble to create test file");
        }*/
        if (args.length>0){
            cargoDataFileName=args[0];
        }
        Scanner inputCargo;
        try{
            File fInput = new File(cargoDataFileName);
            inputCargo = new Scanner(fInput);
        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            return;
        }
        String workersDataFileName = "input3workers.txt";
        /*try{
            PrintWriter output = new PrintWriter(workersDataFileName);
            output.println("Steve 1 Peter 2 Foma 4");
            output.close();
        }catch(Exception e){
            System.out.println("trouble to create test file");
        }*/
        if (args.length>1){
            workersDataFileName=args[1];
        }
        Scanner inputWorkers;
        try{
            File fInput = new File(workersDataFileName);
            inputWorkers = new Scanner(fInput);
        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            return;
        }
        ArrayList<Integer> cargoes=new ArrayList<>();
        while(inputCargo.hasNextInt()){
            cargoes.add(inputCargo.nextInt());
        }
        inputCargo.close();
        ArrayList<Worker> workers=new ArrayList<>();
        while(inputWorkers.hasNext()){
            String name = inputWorkers.next();
            Integer power = inputWorkers.nextInt();
            Worker man = new Worker(name,power);
            workers.add(man);
        }
        Store store = new Store(cargoes);
        PrintWriter output = new PrintWriter(System.out);
        try{
            output = new PrintWriter("output.txt");
        }catch (Exception e){
            e.printStackTrace();
        }
        store.startWork(output,MAXCAP,workers);
        output.close();
    }
}
