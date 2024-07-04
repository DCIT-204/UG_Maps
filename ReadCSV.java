import java.io.*;
import java.util.ArrayList;

public class ReadCSV {
    public static ArrayList<String[]> readCSV(String file) {
        ArrayList<String[]> csvContents = new ArrayList<String[]>();
        System.out.println("hello world");
        BufferedReader reader = null;
        String line = "";

        try {
            reader = new BufferedReader(new FileReader(file));
            while ((line = reader.readLine()) != null) {
                csvContents.add(line.split(","));
            }
            System.out.println("done");
        } catch (Exception e) {
            System.out.println("Could not read contents of file");
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (Exception e) {
                System.out.println("Something went horribly wrong:");
                e.printStackTrace();
            }
        }
        return csvContents;
    }
    public static void makeContentsReadable(ArrayList<String[]> csvContents){
        for (String[] row : csvContents) {
            for (String field : row) {
                System.out.print(field + " ");
            }
            System.out.println();
        }
    }
    public static void getLongitude(ArrayList<String[]> csvContents){
        for (String[] row : csvContents) {
            for (String field : row) {
                System.out.print(field + " ");
            }
            System.out.println();
        }
    }
}
