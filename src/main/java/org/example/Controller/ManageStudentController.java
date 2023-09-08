package org.example.Controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.example.Bo.BOFactory;
import org.example.Bo.Custom.StudentBO;
import org.example.dto.StudentDTO;
import org.example.entity.Student;
import org.example.util.Regex;
import org.example.util.TextFields;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

public class ManageStudentController implements Initializable {

    public TextField txtStudentAddress;
    public TextField txtStudentName;
    public TextField txtContactNo;
    public TextField txtStudentId;
    public TextField txtSearch;
    public RadioButton radiobtnMale;
    public Button btnAdd;
    public RadioButton radioobtnFemale;
    public Button btnUpdate;
    public Button btnDelete;
    public TableView tblStudent;
    public TableColumn colStudentId;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colContactNo;
    public TableColumn colDob;
    public TableColumn colGender;
    public AnchorPane ManageStudent;
    public DatePicker cmbDob;

    StudentBO studentBO = (StudentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.STUDENT);
    ObservableList<Student> observableList;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> list = FXCollections.observableArrayList("Male","Female");
        colGender.setText(String.valueOf(list));

        try {
            getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        setCellValueFactory();
        clearAll();
    }

    private void setCellValueFactory() {
        colStudentId.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContactNo.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colDob.setCellValueFactory(new PropertyValueFactory<>("dbo"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
    }



    private void clearAll() {
        txtStudentName.setText(null);
        txtStudentAddress.setText(null);
        txtContactNo.setText(null);
        cmbDob.setValue(null);
        radiobtnMale.setText(null);
    }


    private void getAll() throws SQLException, IOException, ClassNotFoundException {
        observableList = FXCollections.observableArrayList();
        List<StudentDTO> allStudent = studentBO.getAllStudent();

        for (StudentDTO studentDTO : allStudent){
            observableList.add(new Student(studentDTO.getStudentID(),studentDTO.getName(),studentDTO.getAddress(),studentDTO.getContact(),studentDTO.getDbo(),studentDTO.getGender()));
        }
        tblStudent.setItems(observableList);
    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        if (!isValid()){
            new Alert(Alert.AlertType.ERROR,"Check Fields").show();
            return;
        }
        String date=cmbDob.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        StudentDTO studentDTO = new StudentDTO(txtStudentId.getText(), txtStudentName.getText(), txtStudentAddress.getText(), txtContactNo.getText(),date,radiobtnMale.getText());

        try {
            if (studentBO.addStudent(studentDTO)){
                new Alert(Alert.AlertType.CONFIRMATION,"Saved", ButtonType.OK).show();
            }else {
                new Alert(Alert.AlertType.WARNING,"Try again",ButtonType.OK).show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        clearAll();
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
    }

    public boolean isValid() {
        if(!Regex.setTextColor(TextFields.NAME, (JFXTextField) txtStudentName))return false;
        if(!Regex.setTextColor(TextFields.ADDRESS, (JFXTextField) txtStudentAddress))return false;
        if(!Regex.setTextColor(TextFields.PHONE, (JFXTextField) txtContactNo))return false;
        return true;
    }
}
