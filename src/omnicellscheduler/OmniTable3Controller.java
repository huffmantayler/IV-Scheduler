/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package omnicellscheduler;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author taylorhuffman
 */
public class OmniTable3Controller implements Initializable {

    @FXML
    private TableView<Drugs> mondayTable;
    @FXML
    private TableView<Drugs> tuesdayTable;
    @FXML
    private TableView<Drugs> thursdayTable;
    @FXML
    private TableView<Drugs> fridayTable;
    @FXML
    private TableColumn<Drugs, String> drugMonday;
    @FXML
    private TableColumn<Drugs, Double> numMonday;
    @FXML
    private TableColumn<Drugs, String> drugTuesday;
    @FXML
    private TableColumn<Drugs, Double> numTuesday;
    @FXML
    private TableColumn<Drugs, String> drugThursday;
    @FXML
    private TableColumn<Drugs, Double> numThursday;
    @FXML
    private TableColumn<Drugs, String> drugFriday;
    @FXML
    private TableColumn<Drugs, Double> numFriday;
    @FXML
    private Button export;

    private final ObservableList<Drugs> numsMonday = FXCollections.observableArrayList();
    private Data data = Data.getInstance();
    private ObservableList<Drugs> monday = calculateDays(data, "Monday");
    private ObservableList<Drugs> tuesday = calculateDays(data, "Tuesday");
    private ObservableList<Drugs> thursday = calculateDays(data, "Thursday");
    private ObservableList<Drugs> friday = calculateDays(data, "Friday");

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setData();
        export.setOnAction(ActionEvent ->{
            ExcelExport excel = new ExcelExport();
            try {
                excel.export(mondayTable);
            } catch (IOException ex) {
                Logger.getLogger(OmniTable3Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }); 
    }

    @FXML
    public void setData() {
        drugMonday.setCellValueFactory(new PropertyValueFactory<>("name"));
        numMonday.setCellValueFactory(new PropertyValueFactory<>("num"));
        drugTuesday.setCellValueFactory(new PropertyValueFactory<>("name"));
        numTuesday.setCellValueFactory(new PropertyValueFactory<>("num"));
        drugThursday.setCellValueFactory(new PropertyValueFactory<>("name"));
        numThursday.setCellValueFactory(new PropertyValueFactory<>("num"));
        drugFriday.setCellValueFactory(new PropertyValueFactory<>("name"));
        numFriday.setCellValueFactory(new PropertyValueFactory<>("num"));
        mondayTable.setItems(monday);
        tuesdayTable.setItems(tuesday);
        thursdayTable.setItems(thursday);
        fridayTable.setItems(friday);

    }

    public double roundTotals(double num) {

        double roundDown = Math.floor(num / 21);
        double remainder = num % 21;
        
        if(remainder <= 10){
            num = roundDown * 21;
        } else if(remainder > 10){
            num = (roundDown + 1) * 21;
        }
//        if (num == 0) {
//            num = 0;
//        } else if (num < 10){
//            num = 0;
//        } else if (num <= 21) {
//            num = 21;
//        } else if (num <= 30){
//            num = 21;
//        } else if (num <= 42) {
//            num = 42;
//        } else if (num <= 50){
//            num = 42;
//        } else if (num <= 63) {
//            num = 63;
//        } else if (num <= 70){
//            num = 63;
//        } else if (num <= 84) {
//            num = 84;
//        } else {
//            num = 84;
//        }

        return num;
    }

    public ObservableList<Drugs> calculateDays(Data data, String day) {
        ObservableList<Drugs> days = FXCollections.observableArrayList();
        ArrayList<Drugs> newList = new ArrayList<Drugs>();
        for (int i = 0; i < data.getDrugs().size(); i++) {
            if (day.equals("Monday")) {
                newList.add(new Drugs(data.getDrugs().get(i).getName(), roundTotals(data.getDrugs().get(i).getNum() * 7 * 0.3)));
            } else if (day.equals("Tuesday") || day.equals("Thursday")) {
                newList.add(new Drugs(data.getDrugs().get(i).getName(), roundTotals(data.getDrugs().get(i).getNum() * 7 * 0.25)));
            } else if (day.equals("Friday")) {
                newList.add(new Drugs(data.getDrugs().get(i).getName(), roundTotals(data.getDrugs().get(i).getNum() * 7 * 0.2)));
            }
        }
        days.addAll(newList);
        return days;
    }

}
