import java.io.IOException;
import java.util.*;

public class Main {

    public static List<List<Double>> findSubsets(Double[] arr) throws Exception {
        int k = arr.length;
        int power = (int) Math.pow(2,k);
        List<List<Double>> dict = new ArrayList<List<Double>>();

        for (int i = 1; i < power; i++) {
            List<Double> tempArray = new ArrayList<Double>();

            for (int j = 0; j < k; j++) {
                if ((i & (1 << j)) > 0) {
                    tempArray.add(arr[j]);
                    System.out.print(arr[j]);
                }
            }
            System.out.println("");
            dict.add(tempArray);
        }
        CalcOne(dict);
        return dict;
    }

    public static void CalcOne(List<List<Double>> dict) throws Exception {
        Read config = new Read();
        Map<String, Double> configurations = config.cfetch();

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
            System.out.print(logicalAnd + " ");
            double noBranch = k*r + ((k - 1)*l) + (k*f) + a;
            System.out.print(noBranch + " ");

            best[count]=Math.min(logicalAnd, noBranch);
            System.out.println (best[count]);
            count++;
        }
    }

    public static void main(String[] args) throws Exception {
        Read query = new Read();
        List<Double[]> selectivities = query.qfetch();
        findSubsets(selectivities.get(0));
    }
}
