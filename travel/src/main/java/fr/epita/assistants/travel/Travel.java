package fr.epita.assistants.travel;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Travel {
    public static void travelTo(Country source, Country destination) {

        Integer value = source.travelTimes.get(destination.countryName);
        if (value == null)
            return;

        ZoneId sourceZoneId = ZoneId.of(source.countryZone);
        ZonedDateTime sourceZonedDateTime = ZonedDateTime.now(sourceZoneId);

        System.out.println("Boarding in " + source.countryName + ", local date and time is: " + sourceZonedDateTime.format(DateTimeFormatter.RFC_1123_DATE_TIME));

        ZoneId destinationZoneId = ZoneId.of(destination.countryZone);
        ZonedDateTime destinationZonedDateTime = sourceZonedDateTime.withZoneSameInstant(destinationZoneId);

        destinationZonedDateTime = destinationZonedDateTime.plusHours(value);

        System.out.println("Landing in " + destination.countryName + ", local date and time on arrival will be: " + (destinationZonedDateTime.format(DateTimeFormatter.RFC_1123_DATE_TIME)));
    }
}
