package fr.epita.assistants.cinematheque;

import java.time.Duration;
import java.time.LocalDate;

public class Movie {
    public final String title;

    public final String director;

    public final LocalDate release;

    public final Duration duration;

    public Movie(String title, String director, LocalDate release, Duration duration) {
        if (title == null || director == null || release == null || duration == null)
            throw new NullPointerException();

        this.title = title;
        this.director = director;
        this.release = release;
        this.duration = duration;
    }

    public String getTitle() {
        return title;
    }

    public String getDirector() {
        return director;
    }

    public LocalDate getRelease() {
        return release;
    }

    public Duration getDuration() {
        return duration;
    }

    @Override
    public boolean equals(Object anObject) {
        if (anObject == null || anObject.getClass() != Movie.class)
            return false;

        Movie movie = (Movie) anObject;

        return title.equals(movie.title) &&
                director.equals(movie.director) &&
                release.equals(movie.release) &&
                duration.equals(movie.duration);
    }

    @Override
    public String toString() {
        return "Movie(title=" + title + ", director=" + director + ", release=" + release + ", duration="+ duration +")";
    }
}
