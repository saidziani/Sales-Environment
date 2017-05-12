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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class creatAccountController extends MyScene {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button valider;

    @FXML
    private TextField fname;

    @FXML
    private TextField name;

    @FXML
    private PasswordField mdp;

    @FXML
    private ComboBox<String> role;

    @FXML
    void onActionHandler(ActionEvent event)
    {
        String s = role.getSelectionModel().getSelectedItem();
        if(s.equals("Acheteur"))
            AcheteurController.signUpAcheteur(name.getText(),fname.getText(),mdp.getText());
        else if(s.equals("Vendeur"))
            VendeurController.signUpVendeur(name.getText(),fname.getText(),mdp.getText());
        else
            System.out.print("failure");
        Main.myWin.popScene();
    }

    @FXML
    void initialize() {
        assert valider != null : "fx:id=\"valider\" was not injected: check your FXML file 'creatAccountView.fxml'.";
        assert fname != null : "fx:id=\"fname\" was not injected: check your FXML file 'creatAccountView.fxml'.";
        assert name != null : "fx:id=\"name\" was not injected: check your FXML file 'creatAccountView.fxml'.";
        assert mdp != null : "fx:id=\"mdp\" was not injected: check your FXML file 'creatAccountView.fxml'.";

        LinkedList<String> list = new LinkedList<>();
        list.add("Acheteur");
        list.add("Vendeur");
        if(role != null)
            role.getItems().addAll(list);
    }

    @FXML
    private Button deco;


    @FXML
    void onActionHandlerDeco(ActionEvent event) {
        welcomeController wlc = new welcomeController();
        Main.myWin.nextScene(wlc);
    }

    @Override
    Scene getScene() {
        Pane root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("creatAccountView.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene s = new Scene(root);
        setParent(root);
        return s;
    }
}
