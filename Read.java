import java.io.File;
import java.util.Scanner;
import java.io.IOException;
import java.util.*;

public class Read {
    //Should I return the list and Map?
    public static void main(String [] args) throws Exception {
        //Query.txt
        //Open file, create scanner, and ArrayList
        File query = new File("query.txt");
        Scanner squery = new Scanner(query);
        ArrayList<Float[]> lineArray = new ArrayList<Float[]>();

        //Loop through each line, split by space, and create an array
        while (squery.hasNextLine()) {
            String[] stokens = squery.nextLine().split("\\s+");
            Float[] arrSelec = new Float[stokens.length];
            for (int i = 0; i < stokens.length; i++) {
                float selec = Float.parseFloat(stokens[i]);
                arrSelec[i] = selec;
            }
            //Insert array into global array list
            lineArray.add(arrSelec);
        }
        //Close file to prevent memory leak
        squery.close();

        //Config.txt
        //Open file, create scanner, and Map
        File config = new File("config.txt");
        Scanner sconfig = new Scanner(config);
        Map<String, Integer> configMap = new HashMap<String, Integer>();

        //Loop through each line, split by equal sign, and add to Map
        while (sconfig.hasNextLine()) {
            String[] ctokens = sconfig.nextLine().split(" = ");
            int cost = Integer.parseInt(ctokens[1]);
            configMap.put(ctokens[0],cost);
        }
        //Close file tp provent memory leak
        sconfig.close();
    }
}
