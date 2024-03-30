package fr.epita.assistants.ministreamsmusic.dataaccess;

import fr.epita.assistants.ministreamsmusic.data.Song;

import java.util.List;
import java.util.stream.Stream;

public class StreamsSongs {
    public static List<String> getOlderArtists(List<Song> songs)
    {
        Stream<Song> stream = songs.stream();
        return stream
                .filter(song -> song.getArtist().getAge() >= 30)
                .map(song -> song.getArtist().getSurname())
                .distinct()
                .limit(10).toList();
    }

    public static Integer getSumAges(List<Song> songs) {
        Stream<Song> stream = songs.stream();
        return stream
                .filter(song -> song.getArtist().getAge() >= 20)
                .map(song -> song.getArtist().getAge())
                .reduce(Integer::sum).orElse(0);
    }

    public static List<String> getMusics(List<Song> songs) {
        Stream<Song> stream = songs.stream();
        return stream
                .filter(song -> song.getArtist().getName().toLowerCase().contains("an"))
                .map(song -> song.getName())
                .limit(10).toList();
    }
}
