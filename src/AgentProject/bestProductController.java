package AgentProject;

/**
 * Created by said on 10/05/17.
 */


import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;

public class bestProductController extends MyScene{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea tArea;


    @FXML
    private Button deco;


    @FXML
    void onActionHandlerDeco(ActionEvent event) {
        welcomeController wlc = new welcomeController();
        Main.myWin.nextScene(wlc);
    }

    @FXML
    void initialize() {
        assert tArea != null : "fx:id=\"tArea\" was not injected: check your FXML file 'bestProductController.fxml'.";

        HashMap<String,String> data = new HashMap<>();
        data.put("idP", "idP");
        String url="http://localhost/vente_achat/produit/getTokenProduct.php";
        String product = QueryUtils.makeHttpPostRequest(url, data);
        String[] p = product.split("#");
        System.out.print("Produit: "+p[0]+"\n"+"Prix: "+p[1]+"\n"+"Frais liv. :"+p[2]+"\n"+"Vendeur: "+p[3]);
        tArea.setText("Produit: "+p[0]+"\n"+"Prix: "+p[1]+"\n"+"Frais liv. :"+p[2]+"\n"+"Vendeur: "+p[3]);
    }

    @Override
    Scene getScene() {
        Pane root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("bestProductView.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene s = new Scene(root);
        setParent(root);
        return s;

    }


}
