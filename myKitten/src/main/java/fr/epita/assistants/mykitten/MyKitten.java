package fr.epita.assistants.mykitten;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.AtomicMoveNotSupportedException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MyKitten {
    /**
     * Initializer.
     *
     * @param srcPath Source file path.
     */
    public MyKitten(String srcPath) {
        // Read file into stream, try-with-resources
        try {
            streamContent = Files.lines(Paths.get(srcPath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Use the streamContent to replace `wordToReplace` with "miaou". Don't forget
     * to add the line number beforehand for each line. Store the new
     * result directly in the streamContent field.
     *
     * @param wordToReplace The word to replace
     */
    public void replaceByMiaou(String wordToReplace) {
        AtomicInteger i = new AtomicInteger(0);

        streamContent = streamContent.map(elt -> i.incrementAndGet() + " " + elt.replaceAll(wordToReplace, "miaou"));
    }

    /**
     * Use the streamContent to write the content into the destination file.
     *
     * @param destPath Destination file path.
     */
    public void toFile(String destPath) {

        try {
            BufferedWriter finalBufferedWriter = new BufferedWriter(new FileWriter(destPath));
            streamContent.forEach(elt -> {
                try {
                    finalBufferedWriter.write(elt);
                    finalBufferedWriter.newLine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            finalBufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Creates an instance of MyKitten and calls the above methods to do it
     * straightforwardly.
     *
     * @param srcPath       Source file path
     * @param destPath      Destination file path
     * @param wordToReplace Word to replace
     */
    public static void miaou(String srcPath, String destPath,
                             String wordToReplace) {
        MyKitten mykitten = new MyKitten(srcPath);
        mykitten.replaceByMiaou(wordToReplace);
        mykitten.toFile(destPath);
    }

    public Stream<String> streamContent;
}
