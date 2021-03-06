import java.io.IOException;
import java.util.*;
import javafx.util.*;

public class Main {

    static Map<String, Double> configurations;
    static Double[] BestCombo;
    static boolean logicalAndBool;

    static void combinations(Double[] arr, List<Double[]> data, int k) {
        if (k == arr.length) {
            // create a temp array
            Double[] t = new Double[arr.length];

            for (int i = 0; i < arr.length; i++) {
                t[i] = arr[i];
            }
            // add combination to data arraylist
            data.add(t);
        } else {
            // loop through array from k to end
            // generate all combinations within the remaining subset
            for (int i = k; i < arr.length; i++) {
                // flip values arr[k] and arr[i] via temp value
                Double t = arr[k];
                arr[k] = arr[i];
                arr[i] = t;

                // recursively iterate through the array to find combinations
                combinations(arr, data, k + 1);

                // revert flip, via temp value again
                t = arr[k];
                arr[k] = arr[i];
                arr[i] = t;
            }
        }
    }

    // generate all combinations for a given Double array
    static List<Double[]> generateCombinations(List<Double> list) {
        // convert list<double> list to array type
        Double[] arr = list.toArray(new Double[list.size()]);
        List<Double[]> data = new ArrayList<Double[]>();
        // generate combinations and save to variable "data"
        combinations(arr, data, 0);
        return data;
    }

    //Recursive method. For any set of selectivities, it calculates from bottom up
    //ei. if [f1,f2,f3], it calculates [f3], then [f2,f3], then [f1,f2,f3]
    public static Double Recurse(List<Double> input) {
        double r = configurations.get("r"); double t = configurations.get("t");
        double l = configurations.get("l"); double m = configurations.get("m");
        double a = configurations.get("a"); double f = configurations.get("f");
        
        //checks to see if noBranching And has been used
        boolean noUsed = false;

        //creates a temporary array that contains all elements, except the first from input
        if (input.size() > 1) {
            List<Double> arr = new ArrayList<Double>();
            for (int i = 1; i < input.size(); i++) {
                Double temp = input.get(i);
                arr.add(temp);
            }
            
            Double tempCost = Recurse(arr);
            
            double p = 1;
            double k = input.size();
            
            for (double num : input) {
                p *= num;
            }
            double q;
            if (p <= .5) { q = p; } else { q = 1 - p; }

            Double logicalAnd2 = k*r + ((k - 1)*l) + (k*f) + t + m*q + (p*a);
            Double branchingAnd = fcost(input.get(0)) + (m*q) + p*(tempCost);
            
            if (branchingAnd < logicalAnd2) {
                noUsed = true;
            }
            //check to see if noBranch has been used. Once it's been used once, it can't be used again.
            if (noUsed == false) {
                Double noBranch = k*r + ((k - 1)*l) + (k*f) + a;
                Double bestOf1 = Math.min(logicalAnd2,branchingAnd);
                if (logicalAnd2 == bestOf1) {
                    logicalAndBool = true;
                }
                else {
                    logicalAndBool = false;
                }
                Double bestOf2 = Math.min(bestOf1,noBranch);
                return bestOf2;
            } else {
                Double bestOfTwo2 = Math.min(logicalAnd2,branchingAnd);
                return bestOfTwo2;
            }
        } else {
            double k = 1;
            double p = input.get(0);
            double q;
            if (p <= .5) { q = p; } else { q = 1 - p; }

            Double logicalAnd = k*r + ((k - 1)*l) + (k*f) + t + m*q + (p*a);
            Double noBranch = k*r + ((k - 1)*l) + (k*f) + a;
            Double bestOfTwo = Math.min(logicalAnd,noBranch);
            return bestOfTwo;
        }
    }

    public static Double fcost(Double arg) {
        double r = configurations.get("r"); double t = configurations.get("t");
        double l = configurations.get("l"); double m = configurations.get("m");
        double a = configurations.get("a"); double f = configurations.get("f");

        double k = 1;
        Double fCost = (k*r) + ((k - 1)*l) + (k*f) + t;
        return fCost;
    }

    public static Double determineBestPath(List<Double> input) {
        // generate all combinations for a set of selectives
        List<Double[]> combinations = generateCombinations(input);

        Double bestPath = Double.POSITIVE_INFINITY;
        // for each combination use recurse to find the min for a path.
        for (Double[] d : combinations) {
            List<Double> list = Arrays.asList(d);
            Double cost = Recurse(list);
            
            if(cost < bestPath) {
                bestPath = cost;
                BestCombo = d;
            }
        }
        return bestPath;
    }

    public static void main(String[] args) throws Exception {
        String queryFile = args[0];
        String configFile = args[1];

        Read query = new Read(queryFile, configFile);
        List<List<Double>> selectivities = query.qfetch();
        configurations = query.cfetch();

        // for each set of selectivities, print the details for the best path.
        for (int i = 0; i < selectivities.size(); i++) {
            List<Double> input = selectivities.get(i);
            Double bestCost = determineBestPath(input);
            Printer.print(input, BestCombo, bestCost, logicalAndBool);
        }
    }
}
