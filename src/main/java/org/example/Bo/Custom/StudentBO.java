package org.example.Bo.Custom;


import org.example.Bo.SuperBO;
import org.example.dto.StudentDTO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface StudentBO extends SuperBO {
    public List<StudentDTO> getAllStudent() throws SQLException, ClassNotFoundException, IOException;
    public boolean addStudent(StudentDTO dto) throws SQLException, ClassNotFoundException, IOException;

    public boolean updateStudent(StudentDTO dto) throws SQLException, ClassNotFoundException, IOException;

    public boolean deleteStudent(String id) throws SQLException, ClassNotFoundException, IOException;

    public String generateNewStudentID() throws SQLException, ClassNotFoundException, IOException;
    public StudentDTO searchStudent(String id) throws SQLException, ClassNotFoundException, IOException;
}