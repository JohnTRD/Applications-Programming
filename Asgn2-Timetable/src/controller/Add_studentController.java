/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javafx.collections.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.text.*;
import javafx.scene.control.*;
import javafx.stage.*;
import javafx.beans.binding.*;
import javafx.beans.property.*;
import java.io.*;
import java.text.*;
import model.*;
import au.edu.uts.ap.javafx.*;

/**
 * FXML Controller class
 *
 * @author johna_000
 */
public class Add_studentController extends Controller<University> {
public final University getUniversity() { return model; }
@FXML private TextField studentNoTf;
    @FXML private TextField studentNameTf;
    //@FXML private RadioButton fullRb;
    //@FXML private RadioButton halfRb;
    @FXML private ToggleGroup attendanceGrp;
    @FXML private CheckBox scholarCb;
    @FXML private Button cancelBtn;
    @FXML private Button addconfirmBtn;
    @FXML private Label errorMssg;

    /**
     * Initializes the controller class.
     */
    
    private String getStuNum() { return studentNoTf.getText(); }
    private String getStuName() { return studentNameTf.getText(); }
    private String getAttend() { return attendanceGrp.getSelectedToggle().getUserData().toString();}
    private boolean getScholar() { 
        return scholarCb.isSelected();
    }
    
    @FXML public void initialize() {
    studentNoTf.textProperty().addListener((observable, oldSubject, newSubject) -> handleIsEmpty());
    studentNameTf.textProperty().addListener((observable, oldSubject, newSubject) -> handleIsEmpty());
            }
    
    @FXML private void handleIsEmpty() {
    if(!getStuNum().equals("") && !getStuName().equals("") && !getAttend().equals(""))
    addconfirmBtn.setDisable(false);
    else{addconfirmBtn.setDisable(true);}
    }
    
//    @FXML private void handleIsChosenOne(AcitonEvent event) throws Exception {
//    handleIsEmpty();
//    }
    
    @FXML private void handleCancel(ActionEvent event) throws Exception {
        stage.close();
    }
    
    @FXML private void handleReallyAddStudent(ActionEvent event) throws Exception {
        //getUniversity().addStudent("99141145","ayyyy lmao","Full Time",true);
        try {
        getUniversity().addStudent(getStuNum(),getStuName(),getAttend(),getScholar());
        stage.close();
        }
        catch(Exception e){
            errorMssg.setText("Student already exists");
        }
        
    }
}
