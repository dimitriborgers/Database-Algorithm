import java.io.IOException;
import java.util.*;

public class Main {

    public static List<List<Double>> findSubsets(Double[] arr) throws Exception {
        int k = arr.length;
        int power = (int) Math.pow(2,k);
        List<List<Double>> dict = new ArrayList<List<Double>>();

        System.out.print("Possible plans: \n");

        for (int i = 1; i < power; i++) {
            List<Double> tempArray = new ArrayList<Double>();

            for (int j = 0; j < k; j++) {
                if ((i & (1 << j)) > 0) {
                    tempArray.add(arr[j]);
                    // System.out.print(arr[j] + " ");
                }
            }
            dict.add(tempArray);
            arrange(dict);
        }
        System.out.println(dict + "\n");
        // CalcOne(dict);
        return dict;
    }

    public static void CalcTwo(List<List<Double>> dict) throws Exception {
        // Read config = new Read();
        // Map<String, Double> configurations = config.cfetch();

        // double r = configurations.get("r"); double t = configurations.get("t");
        // double l = configurations.get("l"); double m = configurations.get("m");
        // double a = configurations.get("a"); double f = configurations.get("f");
    }

    public static List<List<Double>> arrange(List<List<Double>> dict) {
        for (int i = 1; i < dict.size() - 1; i++) {
            if (dict.get(i).size() <= dict.get(i+1).size()) {
                continue;
            } else {
                List<Double> temp = dict.get(i+1);
                dict.set(i+1, dict.get(i));
                dict.set(i, temp);
                arrange(dict);
            }
        }
        return dict;
    }

    public static void CalcOne(Map<String, Double> configurations, List<List<Double>> dict) throws Exception {
        double r = configurations.get("r"); double t = configurations.get("t");
        double l = configurations.get("l"); double m = configurations.get("m");
        double a = configurations.get("a"); double f = configurations.get("f");

        for (List<Double> temp : dict) {
            double p = 1;
            int k = temp.size();
            int power = (int) Math.pow(2,k);
            double[] best = new double[power];
            int count = 0;

            for (double num : temp) {
                p *= num;
            }
            double q;
            if (p <= .5) { q = p; } else { q = 1 - p; }

            double logicalAnd = k*r + ((k - 1)*l) + (k*f) + t + m*q + (p*a);
            System.out.print(temp + " => p: " + p + "\n");
            System.out.print(" - logicalAnd: " + logicalAnd + " \n");
            double noBranch = k*r + ((k - 1)*l) + (k*f) + a;
            System.out.print(" - nobranch: " + noBranch + " \n");

            best[count]=Math.min(logicalAnd, noBranch);
            System.out.println (" - best: " + best[count]);
            count++;
        }
    }

    public static void main(String[] args) throws Exception {
        String queryFile = args[0];
        String configFile = args[1];
        
        Read query = new Read(queryFile, configFile);
        List<Double[]> selectivities = query.qfetch();
        Map<String, Double> configurations = query.cfetch();

        // List<List<Double>> subsets = findSubsets(selectivities.get(0));
        // CalcOne(configurations, subsets);
        Printer p = new Printer();
        p.print(selectivities.get(0), selectivities.get(0), 10.5);       
    }
}
