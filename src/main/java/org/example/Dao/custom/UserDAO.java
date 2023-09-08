package org.example.Dao.custom;


import org.example.Dao.CrudDAO;
import org.example.entity.User;

import java.io.IOException;

public interface UserDAO extends CrudDAO<User> {
    boolean checkPassword(String username, String password) throws IOException;
}
