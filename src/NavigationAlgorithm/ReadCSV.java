package src.NavigationAlgorithm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Objects;

public class ReadCSV {
    public static ArrayList<String[]> readCSV(String file) {
        ArrayList<String[]> csvContents = new ArrayList<>();

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
                if (reader != null) {
                    reader.close();
                }
            } catch (Exception e) {
                System.out.println("Something went horribly wrong: ");
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
            if (row[1].equalsIgnoreCase(location)) {
                return Double.parseDouble(row[3]);
            }
        }
        return 0.0;
    }

    public static Double getLongitude(ArrayList<String[]> csvContents, int id) {
        for (String[] row : csvContents) {
            if (Objects.equals(row[0], String.valueOf(id))) {
                return Double.parseDouble(row[3]);
            }
        }
        return 0.0;
    }


    public static Double getLatitude(ArrayList<String[]> csvContents, String location) {
        for (String[] row : csvContents) {
            if (row[1].equalsIgnoreCase(location)) {
                return Double.parseDouble(row[2]);
            }
        }
        return 0.0;
    }


    public static Double getLatitude(ArrayList<String[]> csvContents, int id) {
        for (String[] row : csvContents) {
            if (Objects.equals(row[0], String.valueOf(id))) {
                return Double.parseDouble(row[2]);
            }
        }
        return 0.0;
    }

    public static String getLandmark(ArrayList<String[]> csvContents, String location) {
        for (String[] row : csvContents) {
            if (row[1].equalsIgnoreCase(location)) {
                return row[1]; 
                
            }
        }
        return "No landmark allocated";
    }

    public static String getType(ArrayList<String[]> csvContents, String location) {
        for (String[] row : csvContents) {
            if (row[1].equalsIgnoreCase(location)) {
                return row[4];
            }
        }
        return "No type allocated";
    }

    public static int getIdByName(ArrayList<String[]> csvContents, String name) {
        for (String[] row : csvContents) {
            if (row[1].equalsIgnoreCase(name)) {
                return Integer.parseInt(row[0]);
            }
        }
        return -1; // Return -1 if the name is not found
    }

    public static String getNameById(ArrayList<String[]> csvContents, int id) {
        for (String[] row : csvContents) {
            if(!Objects.equals(row[0], "ID")){
            if (Integer.parseInt(row[0]) == id) {
                return row[1];
            }
            }
        }
        return null; // Return null if the id is not found
    }

    public static int getID(ArrayList<String[]> csvContents, String name) {
        return getIdByName(csvContents, name);
    }

    public static int getCSVLength(ArrayList<String[]> csvContents){
        return csvContents.size();
    }
}
