package org.example.Dao.custom;


import org.example.Dao.CrudDAO;
import org.example.entity.Reservation;

import java.io.IOException;
import java.util.List;

public interface ReservationDAO extends CrudDAO<Reservation> {
    List<String> loadStudentID() throws IOException;
    List<String> loadRoomID() throws IOException;
}