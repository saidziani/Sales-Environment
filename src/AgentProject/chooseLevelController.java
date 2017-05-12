package AgentProject;

/**
 * Created by said on 08/05/17.
 */

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;


public class chooseLevelController extends MyScene {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button valider;

    @FXML
    private ComboBox<String> prix;

    @FXML
    private ComboBox<String> qualite;

    @FXML
    void onActionHandler(ActionEvent event) {

        String p = prix.getSelectionModel().getSelectedItem();
        String q = qualite.getSelectionModel().getSelectedItem();
        float qualiteVal=0;
        switch (q){
            case "Très important": qualiteVal = 4 ; break;
            case "Important": qualiteVal = 3 ; break;
            case "Peu important": qualiteVal = 2 ; break;
            case "Négligeable": qualiteVal = 1 ; break;
        }

        float prixVal=0;
        switch (p){
            case "Très important": prixVal = 1 ; break;
            case "Important": prixVal = 2 ; break;
            case "Peu important": prixVal = 3 ; break;
            case "Négligeable": prixVal = 4 ; break;
        }


        /*if(s.equals("Acheteur"))
            AcheteurController.signUpAcheteur(name.getText(),fname.getText(),mdp.getText());*/
        System.out.print("prix="+p+"-"+prixVal+" /qualitè="+q+"-"+qualiteVal);
        productsListController plc = new productsListController();
        Main.myWin.nextScene(plc);
    }

    @FXML
    void initialize() {
        assert valider != null : "fx:id=\"valider\" was not injected: check your FXML file 'chooseLevelController.fxml'.";
        assert prix != null : "fx:id=\"prix\" was not injected: check your FXML file 'chooseLevelController.fxml'.";
        assert qualite != null : "fx:id=\"qualite\" was not injected: check your FXML file 'chooseLevelController.fxml'.";

        LinkedList<String> list = new LinkedList<>();
        list.add("Très important");
        list.add("Important");
        list.add("Peu important");
        list.add("Négligeable");
        if(prix != null)
            prix.getItems().addAll(list);

        if(qualite != null)
            qualite.getItems().addAll(list);


    }

    @Override
    Scene getScene() {
        Pane root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("chooseLevelView.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene s = new Scene(root);
        setParent(root);
        return s;
    }
}
