package by.epam.finalTask.entity.logic;

import by.epam.finalTask.entity.Playlist;
import by.epam.finalTask.entity.Track;

import java.util.List;

public class PlaylistLogic {

    private PlaylistLogic(){
    }

    public static double getAlbumPrice(Playlist playlist){
        List<Track> trackList=playlist.getTrackList();
        double result=0;
        for(Track track:trackList){
            result+=track.getPrice();
        }
        return result;
    }
}
