package mythreadsp1;

import java.util.ListIterator;

public class ThreadSum extends Thread{
    private String thrName="";
    private ListIterator<Integer> start;
    private ListIterator<Integer> end;
    private Integer sum;
    public ThreadSum(String name,ListIterator<Integer> i_start,ListIterator<Integer> i_end/*,Integer sum*/){
        this.thrName=name;
        this.start = i_start;
        this.end = i_end;
        this.sum=0;
        //this.sum = sum;
    }

    public synchronized void updateSum(Integer currSum){
        this.sum+=currSum;
    }
    public int getSum(){
        return this.sum;
    }
    @Override
    public void run(){
        Integer currSum=0;
        while((this.start.nextIndex()!=this.end.nextIndex())&&(this.start.hasNext())){
            currSum+=this.start.next();
            /*System.out.print(this.thrName+" ");
            System.out.println(currSum);*/
        }
        updateSum(currSum);
    }
}
