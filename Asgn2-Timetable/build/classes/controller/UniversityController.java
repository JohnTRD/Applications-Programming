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
public class UniversityController extends Controller<University> {

    
    @FXML private Button addBtn;
    @FXML private Button removeBtn;
    @FXML private Button loginBtn;
    @FXML private ListView<Student> studentsLv;
    private University university = new University();
    /**
     * Initializes the controller class.
     */
    @FXML public void initialize() {
        
       studentsLv.getSelectionModel().selectedItemProperty().addListener((observable, oldSubject, newSubject) -> removeBtn.setDisable(newSubject == null));
       studentsLv.getSelectionModel().selectedItemProperty().addListener((observable, oldSubject, newSubject) -> loginBtn.setDisable(newSubject == null));
    }   
    
    public final University getUniversity(){
        return university;
        //return model;
    }
    
    	private Student getSelectedStudent() {
		return studentsLv.getSelectionModel().getSelectedItem();
	}
    
    @FXML private void handleAddStudent(ActionEvent event) throws Exception {
        ViewLoader.showStage(getUniversity(), "/view/add_student.fxml", "Add Student", new Stage());
    }
    
    @FXML private void handleRemoveStudent(ActionEvent event) throws Exception {
        getUniversity().remove(getSelectedStudent());
    }
    
    @FXML private void handleLogin(ActionEvent event) throws Exception {
        ViewLoader.showStage(getSelectedStudent(), "/view/student.fxml", "Add Student", new Stage());
    }
}
