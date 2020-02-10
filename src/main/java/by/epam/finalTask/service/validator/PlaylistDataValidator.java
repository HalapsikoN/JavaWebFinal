package by.epam.finalTask.service.validator;

import by.epam.finalTask.entity.Playlist;

import java.util.Calendar;

public class PlaylistDataValidator {

    private PlaylistDataValidator() {
    }

    public static boolean isValidName(String name) {
        return ((name != null) && (!name.isEmpty()));
    }

    public static boolean isValidDate(Calendar calendar) {
        return calendar != null;
    }

    public static boolean isValidPlaylist(Playlist playlist) {
        return playlist != null && isValidName(playlist.getName()) && isValidDate(playlist.getDate());
    }
}
