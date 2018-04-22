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