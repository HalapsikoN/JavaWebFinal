package by.epam.finalTask.service.validator;

import by.epam.finalTask.entity.Track;

import java.util.Calendar;

public class TrackDataValidator {

    private TrackDataValidator(){
    }

    public static boolean isValidName(String name){
        return ((name!=null) && (!name.isEmpty()));
    }

    public static boolean isValidArtist(String artist){
        return ((artist!=null) && (!artist.isEmpty()));
    }

    public static boolean isValidDate(Calendar calendar){
        return calendar!=null;
    }

    public static boolean isValidPrice(double price){
        return price>=0;
    }

    public static boolean isValidTrack(Track track){
        return track!=null && isValidName(track.getName()) && isValidArtist(track.getArtist()) && isValidDate(track.getDate())&&isValidPrice(track.getPrice());
    }

}
