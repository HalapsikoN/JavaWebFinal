package by.epam.finalTask;

import by.epam.finalTask.dao.AlbumDAO;
import by.epam.finalTask.dao.DAOFactory;
import by.epam.finalTask.dao.TrackDAO;
import by.epam.finalTask.dao.UserDAO;
import by.epam.finalTask.entity.Album;
import by.epam.finalTask.dao.DAOException;
import by.epam.finalTask.dao.pool.ConnectionPool;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Main {

    public static void main(String[] args) {

//        User user=new User("Kat", "DOP", Role.USER);
//        String password="ssss";

         Calendar calendar=new GregorianCalendar();
//        calendar.set(Calendar.YEAR, 1988);
//        System.out.println(calendar.get(Calendar.YEAR));

        //Track track=new Track("aal", "k", 44, 2);



        Album album=new Album("xxx", "Sia", calendar);

        DAOFactory daoFactory=DAOFactory.getInstance();
        UserDAO userDAO=daoFactory.getSqlUserDAO();
        TrackDAO trackDAO=daoFactory.getSqlTrackDAO();
        AlbumDAO albumDAO=daoFactory.getSqlAlbumDAO();
        try {
            //Track track=userDAO.getUserTracksById(1).get(2);
            //System.out.println(albumDAO.addAlbumWithOutTracks(album));
            System.out.println(albumDAO.getAlbumById(1));
            //System.out.println(albumDAO.addTrackToAlbum(album, track));
            //System.out.println(trackDAO.getTrackById(3));
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }
}
