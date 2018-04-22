import java.io.*;
import java.util.*;

public class Printer {

    private static String formatExp(int i) {
        return "t" + i + "[o" + i + "[i]]";
    }

    public static void print(List<Double> selectivities, Double[] bestPath, Double cost, Boolean logicalAnd) throws Exception {
        System.out.println("==================================================================");

        // Print the selectivities
        String selectivitiesStr = "";
        for (Double s : selectivities) {
            selectivitiesStr += (s + " ");
        }
        System.out.println(selectivitiesStr);

        System.out.println("------------------------------------------------------------------");

        // print out if and terms 
        String[] terms = new String[bestPath.length];
        for (int i = 0; i < bestPath.length; i++) {
            int index = selectivities.indexOf(bestPath[i]);
            terms[i] = formatExp(index+1);
        }
        String ifStr = "if(" + String.join(" && ", terms) + ") {";
        System.out.println(ifStr);

        System.out.println("    answer[j++] = i;");
        System.out.println("}");

        System.out.println("------------------------------------------------------------------");
        System.out.println("cost: " + cost);
    }
}
