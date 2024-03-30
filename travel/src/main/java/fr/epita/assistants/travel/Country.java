package fr.epita.assistants.travel;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Country {

    protected String countryName;

    protected String countryZone;

    public Map<String, Integer> travelTimes;

    public Country(String countryName, String countryZone, String inputFilePath) throws IOException {
        this.countryName = countryName;
        this.countryZone = countryZone;

        this.travelTimes = initTravelTimes(inputFilePath);
    }

    public Map<String, Integer> initTravelTimes(String inputFilePath) throws IOException {
        Map<String, Integer> res = new HashMap<>();

        FileReader file = new FileReader(inputFilePath);
        CSVReader csvReader = new CSVReader(file);

        String[] nextRecord;
        while ((nextRecord = csvReader.readNext()) != null) {
            if (countryName.equals(nextRecord[0]))
                res.put(nextRecord[1], Integer.parseInt(nextRecord[2]));
            if (countryName.equals(nextRecord[1]))
                res.put(nextRecord[0], Integer.parseInt(nextRecord[2]));
        }

        return res;
    }
}
