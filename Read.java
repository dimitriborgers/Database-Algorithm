import java.io.*;
import java.util.*;

public class Read {

    public static List<Double[]> qfetch() throws Exception {
        //Query.txt
        //Open file, create scanner, and list to hold arrays of line tokens
        File query = new File("query.txt");
        Scanner squery = new Scanner(query);
        List<Double[]> lineArray = new ArrayList<Double[]>();

        //Loop through each line, split by space, and create an array
        while (squery.hasNextLine()) {
            String[] stokens = squery.nextLine().split("\\s+");
            Double[] arrSelec = new Double[stokens.length];
            for (int i = 0; i < stokens.length; i++) {
                double selec = Double.parseDouble(stokens[i]);
                arrSelec[i] = selec;
            }
            //Insert array into global array list
            lineArray.add(arrSelec);
        }
        //Close file to prevent memory leak
        squery.close();
        return lineArray;
    }

    public static Map<String, Double> cfetch() throws Exception {
        //Config.txt
        //Open file, create scanner, and Map
        File config = new File("config.txt");
        Scanner sconfig = new Scanner(config);
        Map<String, Double> configMap = new HashMap<String, Double>();

        //Loop through each line, split by equal sign, and add to Map
        while (sconfig.hasNextLine()) {
            String[] ctokens = sconfig.nextLine().split(" = ");
            double cost = Double.parseDouble(ctokens[1]);
            configMap.put(ctokens[0],cost);
        }
        //Close file tp provent memory leak
        sconfig.close();
        return configMap;
    }
}
