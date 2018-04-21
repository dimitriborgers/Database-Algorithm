import java.io.*;
import java.util.*;

public class Printer {

    public void print(Double[] selectivities, Double cost) throws Exception {
        System.out.println("==================================================================");
        
        // Print the selectivities
        String[] selectivitiesArr = Arrays.stream(selectivities).map(s -> s.toString()).toArray(String[]::new);
        String selectivitiesStr = String.join(" ", selectivitiesArr);
        System.out.println(selectivitiesStr);

        System.out.println("------------------------------------------------------------------");

        // how do we generate this stuff: 
        // what would the params be passed into my print function 
        // and what processing might i need to do

        // if((t1[o1[i]] & t2[o2[i]]) && t3[o3[i]]) {
        //     answer[j] = i;
        //     j += (t4[o4[i]] & t5[o5[i]]);
        // }

        System.out.println("------------------------------------------------------------------");
        System.out.println("cost: " + cost);
    }
}