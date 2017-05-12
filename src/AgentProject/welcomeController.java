package AgentProject;

/**
 * Created by said on 07/05/17.
 */
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class welcomeController extends MyScene
{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Hyperlink link;

    @FXML
    private PasswordField mdp;

    @FXML
    private TextField user;

    @FXML
    private Button connect;

    @FXML
    void linkHandler(ActionEvent event){
        Main.myWin.nextScene(new creatAccountController());
    }

    @FXML
    void onActionHandler(ActionEvent event)
    {
        String type = AcheteurController.getType(user.getText());

        if(type.equals("1")){
            String ret = VendeurController.signInVendeur(user.getText(),mdp.getText());
            productsListController plc = new productsListController();
            Main.myWin.nextScene(plc);
        }
        else if(type.equals("2")){
            String ret = AcheteurController.signInAcheteur(user.getText(),mdp.getText());
            //productsListController plc = new productsListController();
            clientProductListController cplc = new clientProductListController();
            Main.myWin.nextScene(cplc);
        }

    }

    @FXML
    void initialize() {
        assert link != null : "fx:id=\"link\" was not injected: check your FXML file 'welcome.fxml'.";
        assert mdp != null : "fx:id=\"mdp\" was not injected: check your FXML file 'welcome.fxml'.";
        assert user != null : "fx:id=\"user\" was not injected: check your FXML file 'welcome.fxml'.";
        assert connect != null : "fx:id=\"connect\" was not injected: check your FXML file 'welcome.fxml'.";
    }

    @Override
    Scene getScene() {
        Pane root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("welcome.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene s = new Scene(root);
        setParent(root);
        return s;
    }
}
