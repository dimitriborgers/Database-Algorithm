import java.io.IOException;
import java.util.*;
import javafx.util.*;

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
                }
            }
            dict.add(tempArray);
        }
        arrange(dict);

        System.out.println(dict);
        List<Double> lastSet = dict.get(power-2);
        CalcTwo(lastSet);
        return dict;
    }

    public static void CalcTwo(List<Double> lastSet) {
        Double temporary = lastSet.remove(0);
        System.out.println(temporary);
        System.out.println(lastSet);
        if (lastSet.size() = 1) {
            double cost = CalcOne.List<Double>best[matching index];
        } else {
            fcost(temporary) + mq + CalcTwo(lastSet);
        }
        // Eq 1:       fcost(What's on left) + mq + p*(Eq 1 on what's on right)
        // fcost(E):   kr + (k - 1)l + f1 + ... + fk + t
    }


    public static Pair<ArrayList<String>, Double>> Calculate(Double cost, List<List<Double>> dict, boolean first, ArrayList<String> arr) {
        List<Double> singleAmbersign = new ArrayList<Double>();
        Pair<Double,Double> bestPair = new Pair <Double,Double>();

        if(dict.isEmpty()) {
            return bestPair;

        for(int i = 0; i < list.length; i++) {
            if((first == true) && (i == list.length - 1)) {
                continue;
            } else {

            List<Double> f_not_i = dict;
            f_not_i.remove(0);
            ArrayList<String> arrCopy = arr;
            arrCopy.add("hi");
            Pair<ArrayList<String>, Double>> returnPair = Calculate(cost(i,cost), f_not_i, false, arrCopy);
            if(returnPair.cost < bestPair.cost) {
//                 bestPair = returnPair
//         }
//       }
//       return bestPair;
// }



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
        List<Double[]> best = new ArrayList<Double[]>();
        int count = 0;

        double r = configurations.get("r"); double t = configurations.get("t");
        double l = configurations.get("l"); double m = configurations.get("m");
        double a = configurations.get("a"); double f = configurations.get("f");

        for (List<Double> temp : dict) {
            double p = 1;
            int k = temp.size();

            for (double num : temp) {
                p *= num;
            }
            double q;
            if (p <= .5) { q = p; } else { q = 1 - p; }

            double logicalAnd = k*r + ((k - 1)*l) + (k*f) + t + m*q + (p*a);
            double noBranch = k*r + ((k - 1)*l) + (k*f) + a;

            double bestCost = Math.min(logicalAnd, noBranch);
            if (bestCost == logicalAnd) {
                Double[] insertion = {bestCost, 0.0};
                best.add(insertion);
            } else {
                Double[] insertion = {noBranch, 1.0};
                best.add(insertion);
            }
            count++;
        }
    }

    public static void main(String[] args) throws Exception {
        String queryFile = args[0];
        String configFile = args[1];

        Read query = new Read(queryFile, configFile);
        List<Double[]> selectivities = query.qfetch();
        Map<String, Double> configurations = query.cfetch();

        List<List<Double>> subsets = findSubsets(selectivities.get(0));
        CalcOne(configurations, subsets);
    }
}
