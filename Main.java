import java.io.IOException;
import java.util.*;

public class Main {

    // public static List<List<Double>> findSubsets(Double[] arr) throws Exception {
    //     int k = arr.length;
    //     int power = (int) Math.pow(2,k);
    //     List<List<Double>> dict = new ArrayList<List<Double>>();

    //     for (int i = 1; i < power; i++) {
    //         List<Double> tempArray = new ArrayList<Double>();

    //         for (int j = 0; j < k; j++) {
    //             if ((i & (1 << j)) > 0) {
    //                 tempArray.add(arr[j]);
    //             }
    //         }
    //         dict.add(tempArray);
    //     }
    //     arrange(dict);
    //     System.out.println(dict);
    //     return dict;
    // }

    //Recursive method. For any set of selectivities, it calculates from bottom up
    //ei. if [f1,f2,f3], it calculates [f3], then [f2,f3], then [f1,f2,f3]

    public static Double Recurse(Map<String, Double> configurations, List<Double> input) {
        double r = configurations.get("r"); double t = configurations.get("t");
        double l = configurations.get("l"); double m = configurations.get("m");
        double a = configurations.get("a"); double f = configurations.get("f");
        System.out.println("start: " + input);

        //checks to see if noBranching And has been used
        boolean noUsed = false;

        //creates a temporary array that contains all elements, except the first from input
        if (input.size() > 1) {
            List<Double> arr = new ArrayList<Double>();
            for (int i = 1; i < input.size(); i++) {
                Double temp = input.get(i);
                arr.add(temp);
            }
            System.out.println("input: " + input + "   remainder " + arr);
            //find the best cost of the sub-temporary array
            Double tempCost = Recurse(configurations, arr);
            System.out.println("temporary costs: " + tempCost);

            double p = 1;
            double k = input.size();
            System.out.println("input size:   " + k);
            for (double num : input) {
                p *= num;
            }
            double q;
            if (p <= .5) { q = p; } else { q = 1 - p; }

            Double logicalAnd2 = k*r + ((k - 1)*l) + (k*f) + t + m*q + (p*a);
            System.out.println("logicalAnd:  " + logicalAnd2);
            Double branchingAnd = fcost(configurations, input.get(0)) + (m*q) + p*(tempCost);
            System.out.println("branchingAnd:    " + branchingAnd);
            if (branchingAnd < logicalAnd2) {
                noUsed = true;
            }
            //check to see if noBranch has been used. Once it's been used once, it can't be used again.
            if (noUsed == false) {
                Double noBranch = k*r + ((k - 1)*l) + (k*f) + a;
                System.out.println("no branch:   " + noBranch);
                Double bestOf1 = Math.min(logicalAnd2,branchingAnd);
                Double bestOf2 = Math.min(bestOf1,noBranch);
                return bestOf2;
            } else {
                Double bestOfTwo2 = Math.min(logicalAnd2,branchingAnd);
                System.out.println("BestCost:   " + bestOfTwo2);
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
            System.out.println("Else costs: " + bestOfTwo);
            return bestOfTwo;
        }
    }

    public static Double fcost(Map<String, Double> configurations, Double arg) {
        double r = configurations.get("r"); double t = configurations.get("t");
        double l = configurations.get("l"); double m = configurations.get("m");
        double a = configurations.get("a"); double f = configurations.get("f");

        double k = 1;
        Double fCost = (k*r) + ((k - 1)*l) + (k*f) + t;
        return fCost;
    }

    // public static List<List<Double>> arrange(List<List<Double>> dict) {
    //     for (int i = 1; i < dict.size() - 1; i++) {
    //         if (dict.get(i).size() <= dict.get(i+1).size()) {
    //             continue;
    //         } else {
    //             List<Double> temp = dict.get(i+1);
    //             dict.set(i+1, dict.get(i));
    //             dict.set(i, temp);
    //             arrange(dict);
    //         }
    //     }
    //     return dict;
    // }

    //public static void CalcOne(Map<String, Double> configurations, List<List<Double>> dict) throws Exception {
    //     List<Double[]> best = new ArrayList<Double[]>();
    //     int count = 0;

    //     double r = configurations.get("r"); double t = configurations.get("t");
    //     double l = configurations.get("l"); double m = configurations.get("m");
    //     double a = configurations.get("a"); double f = configurations.get("f");

    //     for (List<Double> temp : dict) {
    //         double p = 1;
    //         int k = temp.size();

    //         for (double num : temp) {
    //             p *= num;
    //         }
    //         double q;
    //         if (p <= .5) { q = p; } else { q = 1 - p; }

    //         double logicalAnd = k*r + ((k - 1)*l) + (k*f) + t + m*q + (p*a);
    //         double noBranch = k*r + ((k - 1)*l) + (k*f) + a;

    //         double bestCost = Math.min(logicalAnd, noBranch);
    //         if (bestCost == logicalAnd) {
    //             Double[] insertion = {bestCost, 0.0};
    //             best.add(insertion);
    //         } else {
    //             Double[] insertion = {noBranch, 1.0};
    //             best.add(insertion);
    //         }
    //         count++;
    //     }
    // }

    public static void main(String[] args) throws Exception {
        String queryFile = args[0];
        String configFile = args[1];

        Read query = new Read(queryFile, configFile);
        List<List<Double>> selectivities = query.qfetch();
        Map<String, Double> configurations = query.cfetch();

        List<Double> input = selectivities.get(0);
        //List<Double> remainder = new ArrayList<Double>();

       // Recurse(configurations, input);

        //List<List<Double>> subsets = findSubsets(selectivities.get(0));

        //List<List<Double>> subsets = findSubsets(selectivities.get(0));
        //CalcOne(configurations, subsets);
    }
}
