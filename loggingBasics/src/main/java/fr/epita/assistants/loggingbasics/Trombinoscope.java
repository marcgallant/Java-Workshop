package fr.epita.assistants.loggingbasics;

import java.util.*;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.Level;
import org.slf4j.LoggerFactory;

public class Trombinoscope {
    private final Logger LOGGER;
    private final HashMap<String, Long> map;

    public Trombinoscope() {
        LOGGER = (Logger) LoggerFactory.getLogger(Trombinoscope.class);
        this.LOGGER.setLevel(Level.TRACE);

        this.LOGGER.trace("Instantiating new Trombinoscope");

        map = new HashMap<>();
    }

    public Long putPerson(String name, long photoId) {

        this.LOGGER.debug("Putting person (\"{}\", {})", name, photoId);

        Long oldPhotoId = map.put(name, photoId);

        if (oldPhotoId == null)
            this.LOGGER.trace("Added entry for person \"{}\"", name);
        else
            this.LOGGER.trace("Updated entry for person \"{}\"", name);


        return oldPhotoId;
    }
}
