package src.NavigationAlgorithm;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ReadCSV {
    public static ArrayList<String[]> readCSV(String file) {
        ArrayList<String[]> csvContents = new ArrayList<String[]>();

        BufferedReader reader = null;
        String line = "";

        try {
            reader = new BufferedReader(new FileReader(file));
            while ((line = reader.readLine()) != null) {
                csvContents.add(line.split(","));
            }

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

    public static void makeContentsReadable(ArrayList<String[]> csvContents) {
        for (String[] row : csvContents) {
            for (String field : row) {
                System.out.print(field + " ");

            }
            System.out.println();
        }
    }

    public static Double getLongitude(ArrayList<String[]> csvContents, String location) {
        for (String[] row : csvContents) {
                if (row[0].toUpperCase().equals(location.toUpperCase())) {
                    return Double.parseDouble(row[1]);
                }
        }
        return Double.parseDouble("0");
    }

    public static Double getLatitude(ArrayList<String[]> csvContents, String location) {
        for (String[] row : csvContents) {
                if (row[0].toUpperCase().equals(location.toUpperCase())) {
                    return Double.parseDouble(row[2]);
                }
        }
        return Double.parseDouble("0");
    }

    public static String getLandmark(ArrayList<String[]> csvContents, String location){
        for (String[] row : csvContents) {
                if (row[0].toUpperCase().equals(location.toUpperCase())) {
                    return row[3];
                }
        }
        return "No landmark allocated";

    }

}

