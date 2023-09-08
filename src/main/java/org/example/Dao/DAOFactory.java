package org.example.Dao;


import org.example.Dao.Custom.Impl.UserDAOImpl;
import org.example.Dao.custom.Impl.ReservationDAOImpl;
import org.example.Dao.custom.Impl.RoomDAOImpl;
import org.example.Dao.custom.Impl.StudentDAOImpl;


public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getDaoFactory() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes {
        USER,STUDENT,ROOM,RESERVATION
    }

    public SuperDAO getDAO(DAOTypes types){
        switch (types) {
            case USER:
                return new UserDAOImpl();
            case STUDENT:
                return new StudentDAOImpl();
            case ROOM:
                return new RoomDAOImpl();
            case RESERVATION:
                return new ReservationDAOImpl();
            default:
                return null;
        }
    }
}