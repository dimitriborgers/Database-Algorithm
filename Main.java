import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class Main {
    //I created an arraylist of arraylists because Array can't hold objects?
    //Why can I use just a List object?
    //How do you check what Java version your computer has?
    public static void printSubsets(String arr[]) {
        int k = arr.length;
        int power = (int) Math.pow(2,k);
        //ArrayList<String[]> subArray = new ArrayList<String[]>();
        List<List<String>> dict = new ArrayList<List<String>>();

        for (int i = 1; i < power; i++) {
            List<String> tempArray = new ArrayList<String>();
            //String[] arrTemp = new String[k];

            for (int j = 0; j < k; j++) {
                if ((i & (1 << j)) > 0) {
                    //arrTemp[j] = arr[j];
                    tempArray.add(arr[j]);
                    //System.out.print("hi");
                }
            }
            //System.out.println("");
            //subArray.add(tempArray);
            dict.add(tempArray);
            //System.out.println(tempArray.get(0));
        }
        //System.out.println(tempArray.get(0));
        //System.out.println(dict.get(0).get(0));
        //System.out.println(dict.get(0));
    }




        //temporary Code
        // double[] best = new double[power];

        // double r = 1;
        // double t = 2;
        // double l = 1;
        // double m = 16;
        // double a = 2;
        // double f = 4;
        // double[] selec = {0.2, 0.1, 0.9};
        // double p = 1;
        // for (double num : selec) {
        //     p *= num;
        // }
        // System.out.println(p);
        // double q;
        // if (p <= .5) {
        //     q = p;
        // } else {
        //     q = 1 - p;
        // }
        // double logicalAnd = k*r + ((k - 1)*l) + (k*f) + t + m*q + (p*a);
        // System.out.println(logicalAnd);
        // double noBranch = k*r + ((k - 1)*l) + (k*f) + a;
        // System.out.println(noBranch);

        // for (int i = 1; i < power; i++) {
        //     best[i]=Math.min(logicalAnd, noBranch);
        // }
        //kr + (k - 1)l + f1 + ... + fk + a
        //kr + (k - 1)l + f1 + ... + fk + t + mq + p1...pka
        //q = p if p < 0.5 or q = 1 - p




        //return subArray;



    public static void main(String[] args) {
        String arr[] = {"a", "b", "c"};
        printSubsets(arr);
    }
}
