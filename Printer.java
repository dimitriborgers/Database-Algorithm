import java.io.*;
import java.util.*;

public class Printer {

    private String formatExp(int i) {
        return "t" + i + "[o" + i + "[i]]";
    }

    public void print(Double[] selectivities, Double[] best, Double cost) throws Exception {
        System.out.println("==================================================================");
        
        // Print the selectivities
        String[] selectivitiesArr = Arrays.stream(selectivities).map(s -> s.toString()).toArray(String[]::new);
        String selectivitiesStr = String.join(" ", selectivitiesArr);
        System.out.println(selectivitiesStr);

        System.out.println("------------------------------------------------------------------");

        String[] terms = new String[best.length];

        for (int i = 0; i < best.length; i++) {
            terms[i] = formatExp(i+1);
        }
        String ifStr = "if(" + String.join(" && ", terms) + ") {";
        System.out.println(ifStr);

        System.out.println("    answer[j++] = i;");                
        System.out.println("}");

        System.out.println("------------------------------------------------------------------");
        System.out.println("cost: " + cost);
    }
}