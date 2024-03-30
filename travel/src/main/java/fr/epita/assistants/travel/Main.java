package fr.epita.assistants.travel;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Country fr = new Country("France", "Europe/Paris", "src/main/resources/travel_times.csv");
        System.out.println(fr.travelTimes);
        Country it = new Country("Italy", "Europe/Paris", "src/main/resources/travel_times.csv");
        System.out.println(it.travelTimes);

        Country usa = new Country("Chicago", "America/Chicago", "src/main/resources/travel_times.csv");
        Travel.travelTo(fr, usa);
    }
}
