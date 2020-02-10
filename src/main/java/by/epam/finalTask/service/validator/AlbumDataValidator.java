package by.epam.finalTask.service.validator;

import by.epam.finalTask.entity.Album;

import java.util.Calendar;

public class AlbumDataValidator {

    private AlbumDataValidator() {
    }

    public static boolean isValidName(String name) {
        return ((name != null) && (!name.isEmpty()));
    }

    public static boolean isValidArtist(String artist) {
        return ((artist != null) && (!artist.isEmpty()));
    }

    public static boolean isValidDate(Calendar calendar) {
        return calendar != null;
    }

    public static boolean isValidAlbum(Album album) {
        return album != null && isValidName(album.getName()) && isValidArtist(album.getArtist()) && isValidDate(album.getDate());
    }
}
