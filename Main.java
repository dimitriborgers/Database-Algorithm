import java.io.IOException;
import java.util.*;

public class Main {

    static Map<String, Double> configurations;

    public static Double Recurse(List<Double> input) {
        double r = configurations.get("r"); double t = configurations.get("t");
        double l = configurations.get("l"); double m = configurations.get("m");
        double a = configurations.get("a"); double f = configurations.get("f");
        System.out.println("start: " + input);
        boolean noUsed = false;

        if (input.size() > 1) {
            List<Double> arr = new ArrayList<Double>();
            for (int i = 1; i < input.size(); i++) {
                Double temp = input.get(i);
                arr.add(temp);
            }
            System.out.println("input: " + input + "   remainder " + arr);
            Double tempCost = Recurse(arr);
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
            Double branchingAnd = fcost(input.get(0)) + (m*q) + p*(tempCost);
            System.out.println("branchingAnd:    " + branchingAnd);
            if (branchingAnd < logicalAnd2) {
                noUsed = true;
            }
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

    public static Double fcost(Double arg) {
        double r = configurations.get("r"); double t = configurations.get("t");
        double l = configurations.get("l"); double m = configurations.get("m");
        double a = configurations.get("a"); double f = configurations.get("f");

        double k = 1;
        Double fCost = (k*r) + ((k - 1)*l) + (k*f) + t;
        return fCost;
    }

    public static void main(String[] args) throws Exception {
        String queryFile = args[0];
        String configFile = args[1];

        Read query = new Read(queryFile, configFile);
        List<List<Double>> selectivities = query.qfetch();
        configurations = query.cfetch();

        List<Double> input = selectivities.get(0);
        //List<Double> remainder = new ArrayList<Double>();

       // Recurse(configurations, input);

        //List<List<Double>> subsets = findSubsets(selectivities.get(0));

        //List<List<Double>> subsets = findSubsets(selectivities.get(0));
        //CalcOne(configurations, subsets);
    }
}
