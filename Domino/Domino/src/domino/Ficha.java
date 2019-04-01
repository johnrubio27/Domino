
package domino;

import java.util.ArrayList;


public class Ficha {
    
    private int[] ficha;
    
    public Ficha(int i, int j){
        ficha = new int[2];
        ficha[0] = i;
        ficha[1] = j;
    }
    
    public int primer(){
        return ficha[0];
    }
    public int segundo(){
        return ficha[1];
    }
    
}
