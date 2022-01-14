package omnicellscheduler;


import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Callback;
import omnicellscheduler.Drugs;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author taylorhuffman
 */
public class SelectScene {
    
     private Callback<Class<?>, Object> controllerFactory ;

    public SelectScene(final List<Drugs> data) {
        controllerFactory = new Callback<Class<?>, Object>() {
            @Override
            public Object call(Class<?> type) {
                try {
                    Constructor<?> constructor = type.getDeclaredConstructor(List.class);
                    return constructor.newInstance(data);
                } catch (NoSuchMethodException exc) {
                    try {
                        return type.newInstance();
                    } catch (InstantiationException ex) {
                        Logger.getLogger(SelectScene.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IllegalAccessException ex) {
                        Logger.getLogger(SelectScene.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (Exception ex) {
                    // trouble...
                    ex.printStackTrace();
                    return null ;
                }
                return null;
            }
         };
    }

    public void setScene(String fxmlFileName, String title, ActionEvent event) throws IOException{
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFileName));
        loader.setControllerFactory(controllerFactory);
        Parent root = (Parent)loader.load();
        Scene scene = new Scene(root);
        stage.setTitle(title);
        stage.setScene(scene);
    }
}
