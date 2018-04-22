import java.io.IOException;
import java.util.*;

public class Main {

    static Map<String, Double> configurations;

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
        // System.out.println("start: " + input);

        //checks to see if noBranching And has been used
        boolean noUsed = false;

        //creates a temporary array that contains all elements, except the first from input
        if (input.size() > 1) {
            List<Double> arr = new ArrayList<Double>();
            for (int i = 1; i < input.size(); i++) {
                Double temp = input.get(i);
                arr.add(temp);
            }
            // System.out.println("input: " + input + "   remainder " + arr);

            Double tempCost = Recurse(arr);
            // System.out.println("temporary costs: " + tempCost);

            double p = 1;
            double k = input.size();
            // System.out.println("input size:   " + k);
            for (double num : input) {
                p *= num;
            }
            double q;
            if (p <= .5) { q = p; } else { q = 1 - p; }

            Double logicalAnd2 = k*r + ((k - 1)*l) + (k*f) + t + m*q + (p*a);
            // System.out.println("logicalAnd:  " + logicalAnd2);
            Double branchingAnd = fcost(input.get(0)) + (m*q) + p*(tempCost);
            // System.out.println("branchingAnd:    " + branchingAnd);
            if (branchingAnd < logicalAnd2) {
                noUsed = true;
            }
            //check to see if noBranch has been used. Once it's been used once, it can't be used again.
            if (noUsed == false) {
                Double noBranch = k*r + ((k - 1)*l) + (k*f) + a;
                // System.out.println("no branch:   " + noBranch);
                Double bestOf1 = Math.min(logicalAnd2,branchingAnd);
                Double bestOf2 = Math.min(bestOf1,noBranch);
                return bestOf2;
            } else {
                Double bestOfTwo2 = Math.min(logicalAnd2,branchingAnd);
                // System.out.println("BestCost:   " + bestOfTwo2);
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
            // System.out.println("Else costs: " + bestOfTwo);
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
            // test print the combinations 
            for (Double t : d) {
                System.out.print(t + ", ");
            }

            List<Double> list = Arrays.asList(d);
            Double cost = Recurse(list);
            System.out.print(" => " + cost);
            System.out.println();

            if(cost < bestPath) {
                bestPath = cost;
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

        List<Double> input = selectivities.get(0);
        
        Double bestCost = determineBestPath(input);
        System.out.println("best path cost: " + bestCost);

        Double[] bestPath = {0.5, 0.8, 0.2, 0.3};
        Double cost = 10.5;
        Boolean logicalAnd = true;
        Printer.print(input, bestPath, cost, logicalAnd);

        //List<Double> remainder = new ArrayList<Double>();

        //List<List<Double>> subsets = findSubsets(selectivities.get(0));

        //List<List<Double>> subsets = findSubsets(selectivities.get(0));
        //CalcOne(configurations, subsets);
    }
}
