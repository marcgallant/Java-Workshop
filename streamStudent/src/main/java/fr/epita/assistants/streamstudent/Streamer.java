package fr.epita.assistants.streamstudent;

import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Stream;

public class Streamer {
    public Stream<Pair<Integer, String>> validator(Stream<Pair<Integer, String>> stream) {
        return stream.filter(i -> i.getKey() >= 0 && i.getKey() <= 100 &&
                        (i.getValue().replaceAll("[^_.]","").length() == 1));
    }

    public Stream<Pair<Integer, String>> orderGrade(Stream<Pair<Integer, String>> stream) {
        return stream.sorted(Comparator.comparing(Pair::getValue)).sorted(Comparator.comparing(Pair::getKey));
    }

    public Stream<Pair<Integer, String>> lowercase(Stream<Pair<Integer, String>> stream) {
        return stream.map(i ->
        {
            String tmp = i.getValue().replaceAll("[^A-Z]", "");
            if (tmp.length() >= 1) {
                return new Pair<>(i.getKey() / 2, i.getValue().toLowerCase());
            } else {
                return new Pair<>(i.getKey(), i.getValue().toLowerCase());
            }
        });
    }

    public Optional<Pair<Integer, String>> headOfTheClass(Stream<Pair<Integer, String>> stream) {
        return stream.sorted(Comparator.comparing(Pair::getValue)).max(Comparator.comparing(Pair::getKey));
    }

    public Stream<Pair<Integer, String>> quickFix(Stream<Pair<Integer, String>> stream) {
        return stream.map(i ->
        {
            if (i.getValue().toLowerCase().matches("^ma.*$") || i.getValue().toLowerCase().matches("^l.*?x$")) {
                return new Pair<>(Math.min(i.getKey() * 2, 100), i.getValue());
            }
            return i;
        });
    }

    public Stream<Pair<Integer, String>> encryption(Stream<Pair<Integer, String>> stream) {
        return stream.map(i ->
        {
            int mid = i.getValue().length() / 2; //get the middle of the String
            String[] parts = {i.getValue().substring(0, mid),i.getValue().substring(mid)};
            String res = parts[1] + parts[0];
            return new Pair<>(i.getKey(), res);
        });
    }
}
