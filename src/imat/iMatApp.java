package imat;


import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.event.Event;
import se.chalmers.cse.dat216.project.IMatDataHandler;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
public class iMatApp extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        
        ResourceBundle bundle = java.util.ResourceBundle.getBundle("imat/resources/iMat");
        
        Parent root = FXMLLoader.load(getClass().getResource("FXML/MainView.fxml"), bundle);
        
        Scene scene = new Scene(root, 1000, 700);
        
        stage.setTitle(bundle.getString("application.name"));
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.setFullScreenExitHint("");
        stage.setFullScreen(true);
        stage.setOnCloseRequest(new CloseHandler());
        stage.setAlwaysOnTop(true);
        stage.show();





    }

    @Override
    public void stop(){
        IMatDataHandler.getInstance().shutDown();
        // Save file
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    private class CloseHandler implements EventHandler{
        public CloseHandler()
        {

        }

        @Override
        public void handle(Event event) {
            System.out.println("Here");
            IMatDataHandler.getInstance().shutDown();
        }
    }
    
}
