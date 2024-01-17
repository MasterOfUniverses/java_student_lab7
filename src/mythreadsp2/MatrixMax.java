package mythreadsp2;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.*;

public class MatrixMax {
    public static void main(String[] args) {
        String inputFileName = "input2.txt";
        /*try{
            PrintWriter output = new PrintWriter(inputFileName);
            output.println(8);
            output.println("1 2 3 4 5 6 7 8");
            output.close();
        }catch(Exception e){
            System.out.println("trouble to create test file");
        }*/
        if (args.length>0){
            inputFileName=args[0];
        }
        Scanner input;
        try{
            File fInput = new File(inputFileName);
            input = new Scanner(fInput);
        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            return;
        }
        int ThreadPoolSize = 1;
        int matrixRowCount = 0;
        int matrixColumnCount = 0;
        if(input.hasNextInt()){
            ThreadPoolSize = input.nextInt();
        }else{
            System.out.println("empty or no-valid input file");
            return;
        }
        if(input.hasNextInt()){
            matrixRowCount = input.nextInt();
        }else{
            System.out.println("empty or no-valid input file");
            return;
        }
        if(input.hasNextInt()){
            matrixColumnCount = input.nextInt();
        }else{
            System.out.println("empty or no-valid input file");
            return;
        }
        ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();
        for(int i=0;i<matrixRowCount;i++){
            matrix.add(new ArrayList<>());
        }
        for(int i=0;i<matrixRowCount;i++){
            for(int j=0;j<matrixColumnCount;j++){
                if(!input.hasNextInt()){
                    System.out.println("no-valid input file: not found array's member");
                    return;
                }else{
                    matrix.get(i).add(input.nextInt());
                }
            }
        }
        ArrayList<RowMatrixMaxTask> tasks = new ArrayList<>();
        for(int i=0;i<matrixRowCount;i++){
            tasks.add(new RowMatrixMaxTask("task "+String.valueOf(i),matrix.get(i).listIterator(0)));
        }
        ExecutorService service = Executors.newFixedThreadPool(ThreadPoolSize);
        ArrayList<Future<Integer>> futures=new ArrayList<>();
        for(int i=0;i< tasks.size();i++){
            futures.add(service.submit(tasks.get(i)));
        }
        service.shutdown();
        Integer max=0;
        try {
            max = futures.get(0).get();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        for(int i=0;i<futures.size();i++){
            try{
                Integer temp=futures.get(i).get();
                if(temp>max){
                    max=temp;
                }
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
        System.out.println(max);
    }
}
