package org.example.Dao.custom.Impl;

import org.example.Dao.custom.ReservationDAO;
import org.example.entity.Reservation;
import org.example.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ReservationDAOImpl implements ReservationDAO {
    @Override
    public List<Reservation> getAll() throws SQLException, ClassNotFoundException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        NativeQuery nativeQuery = session.createNativeQuery("SELECT * FROM reservation");
        nativeQuery.addEntity(Reservation.class);
        List<Reservation> reservations = nativeQuery.getResultList();
        transaction.commit();
        session.close();
        return reservations;
    }

    @Override
    public boolean add(Reservation entity) throws SQLException, ClassNotFoundException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.persist(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Reservation entity) throws SQLException, ClassNotFoundException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.update(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        String sql = "DELETE FROM reservation WHERE reservationId = :reservationId";
        NativeQuery<Reservation> nativeQuery = session.createNativeQuery(sql);
        nativeQuery.setParameter("reservationId",id);
        nativeQuery.executeUpdate();

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        NativeQuery<String> nativeQuery = session.createNativeQuery("SELECT reservationId FROM Reservation ORDER BY reservationId DESC LIMIT 1");
        String id = nativeQuery.uniqueResult();
        transaction.commit();
        session.close();
        if(id != null){
            String[] strings = id.split("R0");
            int newID = Integer.parseInt(strings[1]);
            newID++;
            String ID = String.valueOf(newID);
            int length = ID.length();
            if (length < 2){
                return "R00"+newID;
            }else {
                if (length < 3){
                    return "R0"+newID;
                }else {
                    return "R"+newID;
                }
            }
        }else {
            return "R001";
        }
    }

    @Override
    public Reservation search(String id) throws SQLException, ClassNotFoundException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        NativeQuery nativeQuery = session.createNativeQuery("SELECT * FROM reservation WHERE reservationId = :reservationId");
        nativeQuery.setParameter("reservationId",id);

        nativeQuery.addEntity(Reservation.class);
        Reservation reservation = (Reservation) nativeQuery.uniqueResult();
        transaction.commit();
        session.close();
        return reservation;
    }

    @Override
    public List<String> loadStudentID() throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        NativeQuery nativeQuery = session.createNativeQuery("SELECT studentID FROM student");
        List<String> studentIds = nativeQuery.getResultList();
        transaction.commit();
        session.close();
        return studentIds;
    }

    @Override
    public List<String> loadRoomID() throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        NativeQuery nativeQuery = session.createNativeQuery("SELECT roomId FROM room");
        List<String> roomIds = nativeQuery.getResultList();
        transaction.commit();
        session.close();
        return roomIds;
    }
}