package AgentProject;

/**
 * Created by said on 07/05/17.
 */

import java.beans.EventHandler;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

public class productsListController extends MyScene {

    @FXML
    private Label myLabel;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Hyperlink link;

    @FXML
    private ListView<String> table;

    @FXML
    private Button deco;


    @FXML
    void onActionHandlerDeco(ActionEvent event) {
        welcomeController wlc = new welcomeController();
        Main.myWin.nextScene(wlc);
    }


    @FXML
    void initialize() {
        assert link != null : "fx:id=\"link\" was not injected: check your FXML file 'productsListView.fxml'.";
        assert table != null : "fx:id=\"table\" was not injected: check your FXML file 'productsListView.fxml'.";
        String type = AcheteurController.getTypeById(AcheteurController.id);

       // if(type.equalsIgnoreCase("failure")) {
            //type = VendeurController.getTypeById(VendeurController.id);
            showVProducts();
            System.out.print("i'm Vendeur");
/*
        }else if(type.equals("2")) {
            showAllProducts();
            System.out.print("i'm Acheteur");
        }*/
    }

    void showVProducts(){
        String[][] ret = VendeurController.getAllVendeursProducts(VendeurController.id);
        table.getItems().add("Produit\t\tPrix\t\tQualité");
        if(!ret[0][0].equalsIgnoreCase("NULLL1234")) {
            for (int i = 0; i < ret.length; i++) {
                table.getItems().add(ret[i][0] + "\t\t" + ret[i][1] + "\t\t" + ret[i][2]);
            }
        }
        link.setOnAction(event -> {
            Main.myWin.nextScene(new creatNewProduct());
        });
        myLabel.setText("Mes produits");
    }



    @Override
    Scene getScene() {
        Pane root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("productsListView.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene s = new Scene(root);
        setParent(root);
        return s;
    }
}
