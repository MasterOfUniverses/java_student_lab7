package mythreadsp1;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class ArraySum {
    public static void main(String[] args){
        String inputFileName = "input1.txt";
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
        int arrayLength = 0;
        if(input.hasNextInt()){
            arrayLength = input.nextInt();
        }else{
            System.out.println("empty or no-valid input file");
            return;
        }
        ArrayList<Integer> data = new ArrayList<>();
        data.ensureCapacity(arrayLength);
        for(int i=0;i<arrayLength;i++){
            if(!input.hasNextInt()){
                System.out.println("no-valid input file: not found array's member");
                return;
            }else{
                data.add(input.nextInt());
            }
        }
        input.close();
        Integer res=0;
        ThreadSum thr1 = new ThreadSum("thr1",data.listIterator(0), data.listIterator(data.size()/2)/*,res */);
        ThreadSum thr2 = new ThreadSum("thr2",data.listIterator(data.size()/2), data.listIterator(0)/*,res */);
        thr1.start();
        thr2.start();
        while(thr1.isAlive()) {
            try {
                thr1.join();
            } catch (InterruptedException e) {
            }
        }while(thr2.isAlive()) {
            try {
                thr2.join();
            } catch (InterruptedException e) {
            }
        }
        res+= thr1.getSum();
        res+= thr2.getSum();
        System.out.println(res);
    }
}
