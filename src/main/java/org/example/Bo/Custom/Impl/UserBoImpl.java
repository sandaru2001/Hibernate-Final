package org.example.Bo.Custom.Impl;



import org.example.Bo.Custom.UserBo;
import org.example.Dao.DaoFactory;
import org.example.Dao.custom.UserDao;
import org.example.dto.UserDTO;
import org.example.entity.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserBoImpl implements UserBo {
    UserDao userDao = (UserDao) DaoFactory.getDaoFactory().getDAO(DaoFactory.DAOTypes.USER);

    @Override
    public List<UserDTO> getAllUser() throws SQLException, ClassNotFoundException, IOException {
        List<UserDTO> allUsers= new ArrayList<>();
        List<User> all = userDao.getAll();
        for (User user : all) {
            allUsers.add(new UserDTO(user.getUserId(),user.getUserName(),user.getPassword(),user.getRe_enter()));
        }
        return allUsers;
    }

    @Override
    public boolean addUser(UserDTO dto) throws SQLException, ClassNotFoundException, IOException {
        return userDao.add(new User(dto.getUserId(),dto.getUserName(),dto.getPassword(), dto.getRe_enter()));
    }

    @Override
    public boolean updateUser(UserDTO dto) throws SQLException, ClassNotFoundException, IOException {
           return userDao.update(new User(dto.getUserId(), dto.getUserName(),dto.getPassword(),dto.getRe_enter()));
    }

    @Override
    public boolean deleteUser(String id) throws SQLException, ClassNotFoundException, IOException {
        return userDao.delete(id);
    }


    @Override
    public String generateNewUserId() throws SQLException, ClassNotFoundException, IOException {
       return userDao.generateNewID();
    }
}
