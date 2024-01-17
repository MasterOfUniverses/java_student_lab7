package mythreadsp2;

import java.util.ListIterator;
import java.util.concurrent.Callable;

public class RowMatrixMaxTask implements Callable<Integer> {
    private ListIterator<Integer> start;
    private String taskName;
    public RowMatrixMaxTask(String i_taskName,ListIterator<Integer> start){
        this.start=start;
        this.taskName=i_taskName;
    }
    @Override
    public Integer call(){
        Integer max=this.start.next();
        this.start.previous();
        while(this.start.hasNext()){
            Integer temp=this.start.next();
            if(max<temp){
                max=temp;
            }
        }
        return max;
    }
}
