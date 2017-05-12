package AgentProject;

/**
 * Created by said on 08/05/17.
 */
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.ResourceBundle;

import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;

import java.net.URL;

public class clientProductListController extends MyScene {

        static String id=null;

        @FXML
        private ResourceBundle resources;

        @FXML
        private URL location;

        @FXML
        private Button valider;

        @FXML
        private ComboBox<String> combo;

        @FXML
        private ComboBox<String> comboPrix;

        @FXML
        private ComboBox<String> comboLiv;

        @FXML
        private ListView<String> list;

        @FXML
        private Button deco;


        @FXML
        void onActionHandlerDeco(ActionEvent event) {
            welcomeController wlc = new welcomeController();
            Main.myWin.nextScene(wlc);
        }

        public void agentLanceur(String v, String choice){
                try {
                    Runtime rt = Runtime.instance();

                    ProfileImpl p = new ProfileImpl("localhost", 1099, "RSHP");
                    ContainerController mc = rt.createMainContainer(p);

                    Object[] arg = new Object[2];
                    arg[0] = choice;
                    arg[1] = v;

                    AgentController acheteur = mc.createNewAgent(AcheteurController.getAcheteur(),
                                    AcheteurController.class.getName(), arg);
                    acheteur.start();

                    String[] vendeurs = v.split("-");
                    for(int i=0;i<vendeurs.length;i++){
                        AgentController vendeur = mc.createNewAgent(vendeurs[i],
                                                  VendeurController.class.getName(), null);
                        vendeur.start();
                    }


                    rt.setCloseVM(true);

                }catch (Exception e) { e.printStackTrace();}
        }

        @FXML
        void initialize() {
            assert valider != null : "fx:id=\"valider\" was not injected: check your FXML file 'clientProductListView.fxml'.";
            assert combo != null : "fx:id=\"combo\" was not injected: check your FXML file 'clientProductListView.fxml'.";
            assert list != null : "fx:id=\"list\" was not injected: check your FXML file 'clientProductListView.fxml'.";
            assert comboPrix != null : "fx:id=\"comboPrix\" was not injected: check your FXML file 'clientProductListView.fxml'.";
            assert comboLiv != null : "fx:id=\"comboLiv\" was not injected: check your FXML file 'clientProductListView.fxml'.";


            LinkedList<String> list = new LinkedList<>();
            list.add("Très bonne");
            list.add("Bonne");
            list.add("Moyenne");
            list.add("Mauvaise");
            if(combo != null)
                combo.getItems().addAll(list);

            list.clear();
            list.add("Très important");
            list.add("Important");
            list.add("Peu important");
            list.add("Négligeable");
            if(comboPrix != null)
                comboPrix.getItems().addAll(list);

            list.clear();
            list.add("Très important");
            list.add("Important");
            list.add("Peu important");
            list.add("Négligeable");
            if(comboLiv != null)
                comboLiv.getItems().addAll(list);

            showAllProducts();
        }

        void showAllProducts(){
            String[] ret = AcheteurController.getAllAcheteurProductsName();
            for(int i=0; i<ret.length;i++) {
                list.getItems().add(ret[i]);
            }
            valider.setOnAction(event ->
            {
                String s = list.getSelectionModel().getSelectedItem();
                String s1 = combo.getSelectionModel().getSelectedItem();
                String s2 = comboPrix.getSelectionModel().getSelectedItem();
                String s3 = comboLiv.getSelectionModel().getSelectedItem();
                String choice = s+";"+s1+";"+s2+";"+s3;

                String vendeurs = null;
                vendeurs = getAllProductsVendeurs(s);
                agentLanceur(vendeurs,choice);
                bestProductController bp = new bestProductController();
                Main.myWin.nextScene(bp);

            });
        }

        String getAllProductsVendeurs(String design){
            String url="http://localhost/vente_achat/produit/getAllProductsVendeurs.php";
            HashMap<String,String> data = new HashMap<>();
            data.put("designation", design);
            String s = QueryUtils.makeHttpPostRequest(url, data);
            System.out.println(s);
            return s;
        }

        @Override
        Scene getScene() {
            Pane root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("clientProductListView.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Scene s = new Scene(root);
            setParent(root);
            return s;
        }
}
