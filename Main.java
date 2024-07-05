import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args){
        ArrayList<String[]> csvContents = ReadCSV.readCSV("UG-locations.csv");
        ReadCSV.makeContentsReadable(csvContents);
        System.out.println();
        System.out.println(ReadCSV.getLongitude(csvContents,"Commonwealth Hall"));
//        System.out.println();
        System.out.println(ReadCSV.getLatitude(csvContents,"Commonwealth Hall"));
        System.out.println(ReadCSV.getLandmark(csvContents,"Commonwealth Hall"));
//        System.out.println();
        System.out.println(ReadCSV.getLongitude(csvContents,"Commonwealth"));
//        System.out.println();
        System.out.println(ReadCSV.getLatitude(csvContents,"Commonwealth"));
        System.out.println(ReadCSV.getLandmark(csvContents,"Commonwealth"));
    }
}