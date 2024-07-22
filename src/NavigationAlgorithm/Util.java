package src.NavigationAlgorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Util {
    static ArrayList<String[]> csvContents = ReadCSV.readCSV("src/NavigationAlgorithm/UG-locations.csv");


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

    public static double getDistance(int node1, int node2){


        double lat1 = ReadCSV.getLatitude(csvContents,node1);
        double long1 = ReadCSV.getLongitude(csvContents,node1);
        double lat2 = ReadCSV.getLatitude(csvContents,node2);
        double long2 = ReadCSV.getLongitude(csvContents,node2);
//        System.out.println(node1);
//        System.out.println(node2);
//        System.out.println(haversineDistance(lat1,long1,lat2,long2));
        return haversineDistance(lat1,long1,lat2,long2);

        }

        public static List<Integer> getNeighbours(int node){
            List<Integer> neighbours = new ArrayList<>();
            for (String[] row : csvContents) {
                if(!Objects.equals(row[0], "ID")){
                    if(Integer.parseInt(row[0]) != node) {
                        if (Math.floor(getDistance(node, Integer.parseInt(row[0]))) < 0.61 && (getDistance(node, Integer.parseInt(row[0])) != 0.0)) {
                            neighbours.add(Integer.parseInt(row[0]));
                        }
                    }
                }
            }
        return neighbours;

        }
}
