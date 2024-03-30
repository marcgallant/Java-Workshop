package fr.epita.assistants.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.*;
import java.nio.file.Paths;

public class Json {

    public static void addToJson(String key, String value, String filename) {


        try {
            File file = new File(filename);
            file.createNewFile();

            //Open file as Json node
            Reader reader = new FileReader(filename);
            ObjectMapper mapper = new ObjectMapper();

            ObjectNode node = (ObjectNode) mapper.readTree(reader);

            if (node == null) {
                node = mapper.createObjectNode();
            }

            //Add value
            node.put(key, value);


            //Write Json node in file
            String jsonString = mapper.writeValueAsString(node);
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            writer.write(jsonString);

            reader.close();
            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
