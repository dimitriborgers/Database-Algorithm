import java.io.*;
import java.util.*;

public class Printer {

    private static String formatExp(int i) {
        return "t" + i + "[o" + i + "[i]]";
    }

    // , Pair<Double,Boolean> pair

    public static void print(List<Double> selectivities, Double[] bestPath ) throws Exception {


        // System.out.println("==================================================================");

        // // Print the selectivities
        // String[] selectivitiesArr = Arrays.stream(selectivities).map(s -> s.toString()).toArray(String[]::new);
        // String selectivitiesStr = String.join(" ", selectivitiesArr);
        // System.out.println(selectivitiesStr);

        // System.out.println("------------------------------------------------------------------");

        // String[] terms = new String[best.length];

        // for (int i = 0; i < best.length; i++) {
        //     // int index = selectivities.indexOf(best[i]);
        //     int index = java.util.Arrays.asList(selectivities).indexOf(best[i]);
        //     terms[i] = formatExp(index+1);
        // }
        // String ifStr = "if(" + String.join(" && ", terms) + ") {";
        // System.out.println(ifStr);

        // System.out.println("    answer[j++] = i;");
        // System.out.println("}");

        // System.out.println("------------------------------------------------------------------");
        // System.out.println("cost: " + cost);
    }
}
