package fr.epita.assistants.cinematheque;

import java.io.PrintStream;
import java.time.Period;
import java.util.ArrayList;
import java.util.Comparator;
import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class Cinematheque {

    public Stock<Movie> movieStock;

    PrintStream output;

    public Cinematheque() {
        this.output = System.out;
        movieStock = new ListStock<>();
        movieStock.property.addPropertyChangeListener(new Logger(output));
    }

    public Cinematheque(PrintStream output) {
        this.output = output;
        movieStock = new ListStock<>();
        movieStock.property.addPropertyChangeListener(new Logger(output));
    }

    public boolean add(Movie movie) {
        return movieStock.add(movie);
    }

    public boolean remove(Movie movie) {
        return movieStock.remove(movie);
    }

    public boolean contains(Movie movie) {
        return movieStock.contains(movie);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (Movie movie : movieStock.list()) {
            stringBuilder.append(movie.toString()).append("\n"); //FIXME
        }

        return stringBuilder.toString();
    }

    public boolean sortByTitle() {
        return movieStock.sort(Comparator.comparing((Movie i) -> i.title)
                .thenComparing(i -> i.director)
                .thenComparing(i -> i.release)
                .thenComparing(i -> i.duration));
    }

    public void banDirector(String director) {

        if (director != null) {
            List<Movie> tmp = new ArrayList<>(movieStock.list());

            for (Movie movie : tmp) {
                if (movie.director.equals(director)) {
                    movieStock.remove(movie);
                }
            }
        }
    }

    public Period datesAmplitude() {
        Optional<Movie> tmp = (Optional<Movie>) movieStock.list().stream().max(Comparator.comparing(i -> i.release));
        if (tmp.isEmpty())
            return null;
        Movie max = tmp.get();

        Optional<Movie> tmp2 = (Optional<Movie>) movieStock.list().stream().min(Comparator.comparing(i -> i.release));
        if (tmp2.isEmpty())
            return null;
        Movie min = tmp2.get();

        return Period.between(min.release, max.release);
    }

    public Duration averageDuration() {
        List<Movie> list = (List<Movie>) movieStock.list();
        int len = list.size();
        if (len == 0)
            return Duration.ZERO;

        Stream<Movie> stream = list.stream();
        return stream.map(i -> i.duration).reduce(Duration.ZERO, Duration::plus).dividedBy(len);
    }
}
