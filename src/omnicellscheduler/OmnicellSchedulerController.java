/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package omnicellscheduler;



import java.awt.TextField;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author taylorhuffman
 */
public class OmnicellSchedulerController implements Initializable {
    
    @FXML
    private javafx.scene.control.TextField azith;
    @FXML 
    private javafx.scene.control.TextField cefaz;
    @FXML
    private javafx.scene.control.TextField clin6;
    @FXML
    private javafx.scene.control.TextField clin9;
    @FXML
    private javafx.scene.control.TextField insulin;
    @FXML 
    private javafx.scene.control.TextField v750;
    @FXML
    private javafx.scene.control.TextField v1gm;
    @FXML
    private javafx.scene.control.TextField v1250;
    @FXML
    private javafx.scene.control.TextField v1500;
    @FXML
    private javafx.scene.control.TextField v2gm;
    @FXML
    private Button submit;
    @FXML
    private Button clear;
    
    private ArrayList<Drugs> nums = new ArrayList();
    
    private Data data = Data.getInstance();
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)  {
    }    
    
    @FXML
    public void getData() throws IOException{
       nums.add(new Drugs("Azithromycin" ,Integer.parseInt(azith.getText())));
       nums.add(new Drugs("Cefazolin", Integer.parseInt(cefaz.getText())));
       nums.add(new Drugs("Clindamycin 600mg", Integer.parseInt(clin6.getText())));
       nums.add(new Drugs("Clindamycin 900mg", Integer.parseInt(clin9.getText())));
       nums.add(new Drugs("Insulin", Integer.parseInt(insulin.getText())));
       nums.add(new Drugs("Vancomycin 750mg", Integer.parseInt(v750.getText())));
       nums.add(new Drugs("Vancomycin 1gm", Integer.parseInt(v1gm.getText())));
       nums.add(new Drugs("Vancomycin 1250mg", Integer.parseInt(v1250.getText())));
       nums.add(new Drugs("Vancomycin 1500mg", Integer.parseInt(v1500.getText())));
       nums.add(new Drugs("Vancomycin 2gm", Integer.parseInt(v2gm.getText())));
//       for(int j = 0; j < 3 ; j++){
//          for(int i = 0; i < 10; i++){
//               nums.add(nums.get(i));
//          }
//       }
       System.out.println(nums.size());
        data.setDrugs(nums);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("OmniTable3.fxml"));
        Parent root;
        try{
            
            
        root = fxmlLoader.load();
        Scene scene1 = submit.getScene();
        Window window = scene1.getWindow();
        Stage stage1 = (Stage) window;
        Scene scene = new Scene(root);
        stage1.setScene(scene);
        stage1.centerOnScreen();
        } catch (IOException e){
            e.printStackTrace();
        }
    
    }
    
}
