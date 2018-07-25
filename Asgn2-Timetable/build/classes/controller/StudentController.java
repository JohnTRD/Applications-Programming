/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

//import au.edu.uts.ap.javafx.*;
//import model.*;
//import java.net.URL;
//import java.util.ResourceBundle;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
//import javafx.scene.control.Button;
//import javafx.scene.control.ComboBox;
//import javafx.scene.text.Text;

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
public class StudentController extends Controller<Student> {

    public final Student getStudent() { return model; }
    @FXML private Text studentHeading;
    @FXML private Text stuNumDisp;
    @FXML private Text stuAttndDisp;
    @FXML private Text stuScholarDisp;
    @FXML private Button withdrawBtn;
    @FXML private Button enrolBtn;
    @FXML private ComboBox<Subject> subjectCb;
    @FXML private TableView<Activity> activityTable;
    @FXML private TableView<Activity> enrolledTable;

    /**
     * Initializes the controller class.
     */
    @FXML public void initialize() {
        // TODO
        //ObservableList<Activity> actlist = FXCollections.observableList(getStudent().getUniversity().getSubjects().getActivities());
        //subjectTable.setItems(actlist);
               activityTable.getSelectionModel().selectedItemProperty().addListener((observable, oldSubject, newSubject) -> shouldEnrol());//enrolBtn.setDisable(newSubject == null));
               enrolledTable.getSelectionModel().selectedItemProperty().addListener((observable, oldSubject, newSubject) -> withdrawBtn.setDisable(newSubject == null));
               ObservableList<Activity> enrolledlist = FXCollections.observableList(getStudent().getActivities());
               enrolledTable.setItems(enrolledlist);
    }   
    
    private void shouldEnrol(){
        int enrolled = activityTable.getSelectionModel().getSelectedItem().getEnrolled();
        int capacity = activityTable.getSelectionModel().getSelectedItem().getCapacity();
        int number = activityTable.getSelectionModel().getSelectedItem().getNumber();
        int subjectNumber = activityTable.getSelectionModel().getSelectedItem().getSubjectNumber();
        String group = activityTable.getSelectionModel().getSelectedItem().getGroup();
        if(capacity<=enrolled || getStudent().isThereActivity(subjectNumber,group,number) ){
            enrolBtn.setDisable(true);
        }
        else{enrolBtn.setDisable(false);}
    }

    @FXML private void handleWithdraw(ActionEvent event) {
        Activity activity = enrolledTable.getSelectionModel().getSelectedItem();
        getStudent().withdraw(activity);
        ObservableList<Activity> enrolledlist = FXCollections.observableList(getStudent().getActivities());
        enrolledTable.setItems(enrolledlist);
        shouldEnrol();
        activityTable.getColumns().get(8).setVisible(false);
        activityTable.getColumns().get(8).setVisible(true);
        enrolledTable.getColumns().get(8).setVisible(false);
        enrolledTable.getColumns().get(8).setVisible(true);
    }

    @FXML private void handleEnrol(ActionEvent event) {
        Activity activity = activityTable.getSelectionModel().getSelectedItem();
        getStudent().enrol(activity);
        ObservableList<Activity> enrolledlist = FXCollections.observableList(getStudent().getActivities());
        enrolledTable.setItems(enrolledlist);
        shouldEnrol();
        activityTable.getColumns().get(8).setVisible(false);
        activityTable.getColumns().get(8).setVisible(true);
        enrolledTable.getColumns().get(8).setVisible(false);
        enrolledTable.getColumns().get(8).setVisible(true);
        //test
//        int subjectnumber = activity.getSubjectNumber();
//        refresh(subjectnumber);
    }
    
//    private void refresh(int subjectNum) {
//        Subject subject = subject(subjectNum);
//        ObservableList<Activity> actlist = FXCollections.observableList(subject.getActivities());
//        activityTable.setItems(actlist);
//
//    }
    
    @FXML private void handleView(ActionEvent event) {
        int subjectNo = subjectCb.getSelectionModel().getSelectedItem().getNumber();
        Subject subject = subject(subjectNo);
        ObservableList<Activity> actlist = FXCollections.observableList(subject.getActivities());
        activityTable.setItems(actlist);
    }
    
    private Subject subject(int number) {
        ObservableList<Subject> subjlist = getStudent().getUniversity().getSubjects();
        for (Subject subject : subjlist)
            if (subject.matches(number))
                return subject;
        return null;
    }
    
    @FXML private void handleLogout(ActionEvent event) {
        stage.close();
    }
    
}
