package by.epam.finalTask.entity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Album {

    private int id;
    private String name;
    private String artist;
    private Calendar date;
    private List<Track> trackList;

    public Album() {
    }

    public Album(String name, String artist, Calendar date) {
        this.name = name;
        this.artist = artist;
        this.date = date;
        this.trackList=new ArrayList<>();
    }

    public Album(String name, String artist, Calendar date, List<Track> trackList) {
        this.name = name;
        this.artist = artist;
        this.date = date;
        this.trackList = trackList;
    }

    public Album(int id, String name, String artist, Calendar date, List<Track> trackList) {
        this.id = id;
        this.name = name;
        this.artist = artist;
        this.date = date;
        this.trackList = trackList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public List<Track> getTrackList() {
        return trackList;
    }

    public void setTrackList(List<Track> trackList) {
        this.trackList = trackList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Album album = (Album) o;

        if (id != album.id) return false;
        if (!name.equals(album.name)) return false;
        if (!artist.equals(album.artist)) return false;
        if (!date.equals(album.date)) return false;
        return trackList.equals(album.trackList);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + artist.hashCode();
        result = 31 * result + date.hashCode();
        result = 31 * result + trackList.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", artist='" + artist + '\'' +
                ", date=" + date.get(Calendar.YEAR)+
                ", trackList=" + trackList +
                '}';
    }
}
