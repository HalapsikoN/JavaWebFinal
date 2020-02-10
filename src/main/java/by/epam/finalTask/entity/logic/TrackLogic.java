package by.epam.finalTask.entity.logic;

import by.epam.finalTask.entity.Track;

import java.util.List;

public class TrackLogic {

    private TrackLogic() {
    }

    public static double getTrackListPrice(List<Track> trackList) {
        double result = 0;

        for (Track track : trackList) {
            result += track.getPrice();
        }

        return result;
    }
}
