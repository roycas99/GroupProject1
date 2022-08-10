import java.util.ArrayList;

public class FiboDynamic {
    private int[] cache;

    public FiboDynamic(int[] cache) {
        this.cache = cache;
    }

    public int fibRec(int n) {
        if(cache[n] ==0){
            if (n < 2)
            cache[n] =n; 
            else cache[n] = fibRec(n - 1) + fibRec(n - 2);
        }
        
      

        return cache[n];
    }
    

}
