package org.example.Bo;


import org.example.Bo.Custom.Impl.ReservationBOImpl;
import org.example.Bo.Custom.Impl.RoomBOImpl;
import org.example.Bo.Custom.Impl.StudentBOImpl;
import org.example.Bo.Custom.Impl.UserBOImpl;

public class BOFactory{
    private static BOFactory boFactory;
    private BOFactory(){
    }
    public static BOFactory getBoFactory(){
        return (boFactory==null)? boFactory=new BOFactory() : boFactory;
    }

    public enum BOTypes{
        USER,ROOM,STUDENT,RESERVATION
    }

    //Object creation logic for BO objects
    public SuperBO getBO(BOTypes types){
        switch (types){
            case USER:
                return new UserBOImpl();
            case ROOM:
                return new RoomBOImpl();
            case STUDENT:
                return new StudentBOImpl();
            case RESERVATION:
                return new ReservationBOImpl();
            default:
                return null;
        }
    }
}