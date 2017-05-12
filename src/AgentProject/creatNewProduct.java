package AgentProject;

/**
 * Created by said on 07/05/17.
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
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class creatNewProduct extends MyScene {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button valider;

    @FXML
    private TextField prix;

    @FXML
    private TextField design;

    @FXML
    private TextField livraison;

    @FXML
    private ComboBox<String> qualite;

    @FXML
    void onActionHandler(ActionEvent event) {
        String q = qualite.getSelectionModel().getSelectedItem();

        String qualiteVal= null;
        switch (q){
            case "Très bonne": qualiteVal = "4" ; break;
            case "Bonne": qualiteVal = "3" ; break;
            case "Moyenne": qualiteVal = "2" ; break;
            case "Mauvaise": qualiteVal = "1" ; break;
        }

        VendeurController.setProduct(design.getText(),prix.getText(),qualiteVal,livraison.getText(),VendeurController.id);
        System.out.print(design.getText()+","+prix.getText()+","+qualiteVal+","+livraison.getText()+","+VendeurController.id);
        Main.myWin.popScene();
    }

    @FXML
    void initialize() {
        assert valider != null : "fx:id=\"valider\" was not injected: check your FXML file 'creatNewProduct.fxml'.";
        assert prix != null : "fx:id=\"prix\" was not injected: check your FXML file 'creatNewProduct.fxml'.";
        assert design != null : "fx:id=\"design\" was not injected: check your FXML file 'creatNewProduct.fxml'.";
        assert qualite != null : "fx:id=\"qualite\" was not injected: check your FXML file 'creatNewProduct.fxml'.";

        LinkedList<String> list = new LinkedList<>();
        list.add("Très bonne");
        list.add("Bonne");
        list.add("Moyenne");
        list.add("Mauvaise");
        if(qualite != null)
            qualite.getItems().addAll(list);
    }

    @Override
    Scene getScene() {
        Pane root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("creatNewProduct.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene s = new Scene(root);
        setParent(root);
        return s;
    }
}
