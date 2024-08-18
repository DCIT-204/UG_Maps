package src.NavigationAlgorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Util {
    static ArrayList<String[]> csvContents = ReadCSV.readCSV("src/NavigationAlgorithm/UG-distances.csv");


    private static final double EARTH_RADIUS_KM = 6371.01;
    public static double haversineDistance(double lat1, double lon1, double lat2, double lon2) {
        // Convert latitude and longitude from degrees to radians
        double lat1Rad = Math.toRadians(lat1);
        double lon1Rad = Math.toRadians(lon1);
        double lat2Rad = Math.toRadians(lat2);
        double lon2Rad = Math.toRadians(lon2);

        // Haversine formula
        double dLat = lat2Rad - lat1Rad;
        double dLon = lon2Rad - lon1Rad;
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
        Math.cos(lat1Rad) * Math.cos(lat2Rad) *
        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        // Distance in kilometers
        return EARTH_RADIUS_KM * c;
    }


    public static void swapRoutes( ArrayList<List<Integer>> routes, int indexA, int indexB) {
        List<Integer> temp = routes.get(indexA);
        routes.set(indexA, routes.get(indexB));
        routes.set(indexB, temp);
    }

    public static void swapDistances(ArrayList<Double> A, int indexA, int indexB) {
        Double temp = A.get(indexA);
        A.set(indexA, A.get(indexB));
        A.set(indexB, temp);
    }

    public static void sortRoutes(ArrayList<List<Integer>> A, ArrayList<Double> B) {
        for (int i = 0; i < A.size(); i++) {
            for (int j = i + 1; j < A.size(); j++) {
                if (B.get(i) > B.get(j)) {
                    swapRoutes(A, i, j);
                    swapDistances(B, i, j);
                }
            }
        }
    }

    public static ArrayList<Integer> getNeighbours( int ID) {
        ArrayList<Integer> neighbourList = new ArrayList<>();
        String[] csvTitle = csvContents.get(0);
        for (String[] row : csvContents) {
            if (!Objects.equals(row[0], "ID")) {
                if (Integer.parseInt(row[0]) == ID) {
                    for (int i = 1; i < row.length; i++) {
                        if (Double.parseDouble(row[i]) <= 650.0) {
                            if (!(Integer.parseInt(csvTitle[i]) == ID)) {
                                neighbourList.add(Integer.parseInt(csvTitle[i]));
                            }
                        }
                    }
                }
            }
        }
        return neighbourList;
    }


    public static Double getDistance(int source, int target){
        String[] csvTitle = csvContents.get(0);
        for (String[] row : csvContents) {
            if (!Objects.equals(row[0], "ID")) {
                if (Integer.parseInt(row[0]) == source) {
                    return Double.parseDouble(row[target]);
                }
            }
            }
        return 0.0;
    }

}
