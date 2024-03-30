package fr.epita.assistants.fgen;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static org.codehaus.plexus.util.FileUtils.deleteDirectory;

public class FGen {

    public Stream<String> stream;

    public Path path;

    public FGen(final String inputPath) {
        InputStream inputStream = FGen.class.getClassLoader().getResourceAsStream(inputPath);
        if (inputStream == null)
            throw new RuntimeException();

        stream = new BufferedReader(new InputStreamReader(inputStream)).lines();

        path = Paths.get("").toAbsolutePath();
        readInputFile();
    }

    private void createFile(String filename) {
        File file = new File(path + File.separator + filename);
        file.getParentFile().mkdirs();

        if (filename.endsWith("/"))
            file.mkdirs();
        else {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void deleteFile(String filename) {
        File file = new File(path + File.separator + filename);

        //Delete directory
        File[] contents = file.listFiles();
        if (contents != null) {
            for (File f : contents) {
                deleteFile(filename + File.separator + f.getName());
            }
        }

        file.delete();
    }


    private void changeDirectory(String directory) {
        path = Paths.get(path + File.separator + directory);

        if (!path.toFile().exists()) {
            throw new RuntimeException();
        }
    }

    private void readInputFile() {

        stream.forEach(elt ->
        {
            Pattern pattern = Pattern.compile(". (.*)");
            Matcher matcher = pattern.matcher(elt);
            if (!matcher.find())
                throw new RuntimeException();

            if (elt.startsWith("+")) {
                //Create file
                createFile(matcher.group(1));
            }
            else if (elt.startsWith(">")) {
                //Move in file
                changeDirectory(matcher.group(1));
            }
            else if (elt.startsWith("-")) {
                //Delete File
                deleteFile(matcher.group(1));
            }
            else {
                throw new RuntimeException();
            }
        });
    }
}
