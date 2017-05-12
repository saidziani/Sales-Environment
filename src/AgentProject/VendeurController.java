package AgentProject;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by said on 06/05/17.
 */
public class VendeurController extends Agent {

    static String id=null;

    public static String signInVendeur(String u,String p){
        String url="http://localhost/vente_achat/vendeur/loginVendeur.php";
        HashMap <String,String> data = new HashMap<>();
        data.put("user",u);
        data.put("pass", p);
        String s = QueryUtils.makeHttpPostRequest(url, data);
        if(s.matches("[0-9]*"))
            id = s;
        System.out.println("Je suis Vendeur: id="+s);
        return s;
    }

    public static String signUpVendeur(String name,String fname,String pass){
        String url="http://localhost/vente_achat/vendeur/signupVendeur.php";

        HashMap<String,String> data = new HashMap<>();
        data.put("name", name);
        data.put("fname", fname);
        data.put("pass", pass);
        String s = QueryUtils.makeHttpPostRequest(url, data);
        System.out.println(s);
        return s;
    }

    public static String setProduct(String design, String prix, String qualite, String livraison, String id){
        HashMap<String,String> data = new HashMap<>();
        data.put("id_u", id);
        data.put("design", design);
        data.put("qualite", qualite);
        data.put("prix", prix);
        data.put("livraison", livraison);
        String url="http://localhost/vente_achat/produit/creatNewProduct.php";
        String s = QueryUtils.makeHttpPostRequest(url, data);
        return s;
    }

    public static String[][] getAllVendeursProducts(String id) {
        String url = "http://localhost/vente_achat/vendeur/getAllVendeursProducts.php";
        HashMap<String, String> data = new HashMap<>();
        data.put("id", id);
        String products = QueryUtils.makeHttpPostRequest(url, data);
        System.out.print("I'mmm products"+products);
        if(products.equalsIgnoreCase("0")){
            String[][] prds = new String[1][1];
            prds[0][0] = "NULLL1234";
            return prds;
        }else {
            String[] prd = products.split("-");
            String[][] prds = new String[prd.length][];
            for (int i = 0; i < prd.length; i++) {
                prds[i] = prd[i].split("#");
            }
            return prds;
        }

    }

    public static String getTypeById(String id){
        HashMap<String,String> data = new HashMap<>();
        data.put("id", id);
        String url="http://localhost/vente_achat/retTypeById.php";
        String Type = QueryUtils.makeHttpPostRequest(url, data);
        return Type;
    }

    public String getProductInfo(String design, String q, String l, String p, String idV){
        String url="http://localhost/vente_achat/produit/getProductsDetails.php";

        String qualiteVal="NULL";
        switch (q){
            case "Très bonne": qualiteVal = "8" ; break;
            case "Bonne": qualiteVal = "6" ; break;
            case "Moyenne": qualiteVal = "4" ; break;
            case "Mauvaise": qualiteVal = "1" ; break;
        }

        String livraisonVal="NULL";
        switch (l){
            case "Très important": livraisonVal = "8" ; break;
            case "Important": livraisonVal = "6" ; break;
            case "Peu important": livraisonVal = "4" ; break;
            case "Négligeable": livraisonVal = "1" ; break;
        }

        String prixVal="NULL";
        switch (l){
            case "Très important": prixVal = "8" ; break;
            case "Important": prixVal = "6" ; break;
            case "Peu important": prixVal = "4" ; break;
            case "Négligeable": prixVal = "1" ; break;
        }

        HashMap<String,String> data = new HashMap<>();
        data.put("designation", design);
        data.put("qualite", qualiteVal);
        data.put("livraison", livraisonVal);
        data.put("prix", prixVal);
        data.put("idV", idV);
        String s = QueryUtils.makeHttpPostRequest(url, data);
        return s;
    }

    //Agent Part Begin:
    protected void setup() {
        addBehaviour(new CyclicBehaviour(this) {
            public void action() {
                ACLMessage demande;
                demande = receive();
                if (demande != null) {
                    String msg = demande.getContent();

                    String[] n = getLocalName().split("_");
                    String idV = n[1], nameV = n[0];

                    String[] choix = msg.split(";");
                    String design = choix[0], q = choix[1], l = choix[3], p = choix[2];
                    String idPlusVal = getProductInfo(design, q, l, p, idV);

                    System.out.println("I'm Agent= "+getLocalName());
                    System.out.println("idPlusVal="+idPlusVal);

                    ACLMessage reponse = demande.createReply();
                    reponse.setPerformative(ACLMessage.INFORM);
                    try {
                        reponse.setContentObject(idPlusVal);
                        send(reponse);
                        doWait(50);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                block();
            }
        });


    }

}


