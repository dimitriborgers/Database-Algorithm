import java.io.File;
import java.util.Scanner;

public class Read {

    public static void main(String [] args) throws Exception {
        File query = new File("query.txt");
        Scanner sc = new Scanner(query);

        while (sc.hasNextLine()) {
            System.out.println(sc.nextLine());
        }
    }
}
