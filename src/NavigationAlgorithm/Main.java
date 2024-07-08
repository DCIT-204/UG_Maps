package src.NavigationAlgorithm;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        ArrayList<String[]> csvContents = ReadCSV.readCSV("src/NavigationAlgorithm/UG-locations.csv");
        ReadCSV.makeContentsReadable(csvContents);
        System.out.println(56);
        System.out.println(ReadCSV.getLongitude(csvContents,"Commonwealth Hall"));
       System.out.println(44);
        System.out.println(ReadCSV.getLatitude(csvContents,"Commonwealth Hall"));
        System.out.println(ReadCSV.getLandmark(csvContents,"Commonwealth Hall"));
        System.out.println(68);
        System.out.println(ReadCSV.getLongitude(csvContents,"Commonwealth"));
        System.out.println(99);
        System.out.println(ReadCSV.getLatitude(csvContents,"Commonwealth"));
        System.out.println(ReadCSV.getLandmark(csvContents,"Commonwealth"));
    }
}