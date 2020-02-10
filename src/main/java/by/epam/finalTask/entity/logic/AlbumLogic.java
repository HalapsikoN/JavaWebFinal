package by.epam.finalTask.entity.logic;

import by.epam.finalTask.entity.Album;
import by.epam.finalTask.entity.Track;

import java.util.List;

public class AlbumLogic {

    private AlbumLogic() {
    }

    public static double getAlbumPrice(Album album) {
        List<Track> trackList = album.getTrackList();
        double result = 0;
        for (Track track : trackList) {
            result += track.getPrice();
        }
        return result;
    }
}
