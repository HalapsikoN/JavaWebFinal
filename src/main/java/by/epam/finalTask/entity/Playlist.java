package by.epam.finalTask.entity;

import java.util.Calendar;
import java.util.List;

public class Playlist {

    private int id;
    private String name;
    private Calendar date;
    private List<Track> trackList;

    public Playlist(String name, Calendar date) {
        this.name = name;
        this.date = date;
    }

    public Playlist(String name, Calendar date, List<Track> trackList) {
        this.name = name;
        this.date = date;
        this.trackList = trackList;
    }

    public Playlist(int id, String name, Calendar date, List<Track> trackList) {
        this.id = id;
        this.name = name;
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

        Playlist playlist = (Playlist) o;

        if (id != playlist.id) return false;
        if (!name.equals(playlist.name)) return false;
        if (!date.equals(playlist.date)) return false;
        return trackList.equals(playlist.trackList);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + date.hashCode();
        result = 31 * result + trackList.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Playlist{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", trackList=" + trackList +
                '}';
    }
}
