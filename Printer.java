import java.io.*;
import java.util.*;
import java.lang.String;

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

        if(logicalAnd) {
            // convert array to list of strings
            List<String> bestPathList = new ArrayList<String>();
            for (int i = 0; i < bestPath.length; i++) {
                int index = selectivities.indexOf(bestPath[i]);
                bestPathList.add(formatExp(index+1));
            }

            // iteratively group the terms together with & in between
            while (bestPathList.size() > 2) {
                String el1 = bestPathList.remove(0);
                String el2 = bestPathList.remove(0);
                String str = "(" + el1 + " & " + el2 + ")";
                bestPathList.add(0, str);
            }

            // the last two terms use a &&
            String el1 = bestPathList.remove(0);
            String el2 = bestPathList.remove(0);
            String ifStr = "if(" + el1 + " && " + el2 + ") {";
            System.out.println(ifStr);
        } else {
            // print out if and terms
            String[] terms = new String[bestPath.length];
            for (int i = 0; i < bestPath.length; i++) {
                int index = selectivities.indexOf(bestPath[i]);
                terms[i] = formatExp(index+1);
            }
            String ifStr = "if(" + String.join(" && ", terms) + ") {";
            System.out.println(ifStr);
        }

        System.out.println("    answer[j++] = i;");
        System.out.println("}");

        System.out.println("------------------------------------------------------------------");
        System.out.println("cost: " + cost);
    }
}
