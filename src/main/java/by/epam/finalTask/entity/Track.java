package by.epam.finalTask.entity;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Objects;

public class Track {

    private int id;
    private String name;
    private String artist;
    private Calendar date;
    private double price;

    public Track(String name, String artist, int date, double price) {
        this.name = name;
        this.artist = artist;
        this.date = new GregorianCalendar();
        this.date.set(Calendar.YEAR,date);
        this.price = price;
    }

    public Track(String name, String artist, Calendar date, double price) {
        this.name = name;
        this.artist = artist;
        this.date = date;
        this.price = price;
    }

    public Track(int id, String name, String artist, int date, double price) {
        this.id = id;
        this.name = name;
        this.artist = artist;
        this.date = new GregorianCalendar();
        this.date.set(Calendar.YEAR,date);
        this.price = price;
    }

    public Track(int id, String name, String artist, Calendar date, double price) {
        this.id = id;
        this.name = name;
        this.artist = artist;
        this.date = date;
        this.price = price;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Track track = (Track) o;

        if (id != track.id) return false;
        if (Double.compare(track.price, price) != 0) return false;
        if (!name.equals(track.name)) return false;
        if (!artist.equals(track.artist)) return false;
        return date.equals(track.date);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + artist.hashCode();
        result = 31 * result + date.hashCode();
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Track{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", artist='" + artist + '\'' +
                ", date=" + date.get(Calendar.YEAR) +
                ", price=" + price +
                '}';
    }
}
