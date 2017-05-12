package AgentProject;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;

import java.io.IOException;
import java.util.*;

/**
 * Created by said on 06/05/17.
 */

public class AcheteurController extends Agent {

    static String id=null;

    public static String signInAcheteur(String u,String p){
        String url="http://localhost/vente_achat/acheteur/loginAcheteur.php";
        HashMap <String,String> data = new HashMap<>();
        data.put("user",u);
        data.put("pass", p);
        String s = QueryUtils.makeHttpPostRequest(url, data);
        if(s.matches("[0-9]*"))
            id = s;
        System.out.println("Je suis Acheteur: id="+s);
        return s;

    }

    public static String signUpAcheteur(String name,String fname,String pass)
    {
        String url="http://localhost/vente_achat/acheteur/signupAcheteur.php";
        HashMap<String,String> data = new HashMap<>();
        data.put("name", name);
        data.put("fname", fname);
        data.put("pass", pass);
        String s = QueryUtils.makeHttpPostRequest(url, data);
        System.out.println(s);
        return s;
    }

    public static String getAcheteur(){
        HashMap<String,String> data = new HashMap<>();
        data.put("id", id);
        String url="http://localhost/vente_achat/acheteur/getAcheteur.php";
        String acheteur=QueryUtils.makeHttpPostRequest(url, data);
        return acheteur;
    }

    public static String[][] getAllAcheteurProducts() {
        String url = "http://localhost/vente_achat/produit/getAllProducts.php";
        HashMap<String, String> data = new HashMap<>();
        String products = QueryUtils.makeHttpPostRequest(url, data);
        String[] prd = products.split("-");
        String[][] prds = new String[prd.length][];
        for (int i = 0; i < prd.length; i++) {
            prds[i] = prd[i].split("#");
        }
        return prds;
    }

    public static String[] getAllAcheteurProductsName() {
        String url = "http://localhost/vente_achat/produit/getAllProductsName.php";
        HashMap<String, String> data = new HashMap<>();
        String products = QueryUtils.makeHttpPostRequest(url, data);
        String[] prd = products.split("-");

        return prd;
    }

    public static String getType(String user){
        HashMap<String,String> data = new HashMap<>();
        data.put("user", user);
        String url="http://localhost/vente_achat/retType.php";
        String Type = QueryUtils.makeHttpPostRequest(url, data);
        return Type;
    }

    public static String getTypeById(String id){
        HashMap<String,String> data = new HashMap<>();
        data.put("id", id);
        String url="http://localhost/vente_achat/retTypeById.php";
        String Type = QueryUtils.makeHttpPostRequest(url, data);
        return Type;
    }


    //Agent Part Begin:
    protected void setup(){
        ACLMessage demande;
        demande = new ACLMessage(ACLMessage.INFORM);
        Object[] args = getArguments();
        String v = (String) args[1];
        String choice = (String) args[0];
        //Send to all vendeurs client choice
        String[] vendeurs = v.split("-");
        for(int i=0;i<vendeurs.length;i++){
            demande.addReceiver(
                    new AID(vendeurs[i], AID.ISLOCALNAME)
            );
        }
        demande.setContent(choice);
        send(demande);
        //Waiting for Vendeurs reply
        long debut = System.currentTimeMillis();
        long fin = System.currentTimeMillis();
        ACLMessage reply;
        ArrayList<String> offres = new ArrayList<>();
        while((fin-debut) < 1000) {
            reply = receive();

            while(reply == null && (fin-debut) < 1000){
                reply=receive();
                fin = System.currentTimeMillis();
            }
            if(reply != null){
                debut = System.currentTimeMillis();
                try {
                    System.out.println("I received: "+reply.getContentObject()+" From "
                            +reply.getSender().getLocalName());
                } catch (UnreadableException e) {
                    e.printStackTrace();
                }

                //Collecting info from Vendeurs
                try {
                    String  rep = (String) reply.getContentObject();
                    offres.add(rep) ;

                } catch (UnreadableException e) {
                    e.printStackTrace();
                }
            }
        }
        double a = getBestOffer(offres);
        String value = String.valueOf(a);





    }

    public double getBestOffer(ArrayList<String> offer){
        ArrayList<Double> offerSorted = new ArrayList<>();
        for(String element :offer){
            System.out.println("Element:"+element);
            String[] ele = element.split("#");
            double val = Double.parseDouble(ele[1]);
            offerSorted.add(val);
        }
        Collections.sort(offerSorted);

        double best = offerSorted.get(0);

        /*===========*/
        String idP = null;
        for(String element :offer){
            String[] ele = element.split("#");
            double val = Double.parseDouble(ele[1]);
            if(val == best){
                idP = ele[0];
            }
        }

        System.out.println("The Best choice ever is:"+best+" ID="+idP);
        HashMap<String,String> data = new HashMap<>();
        data.put("idP", idP);
        String url="http://localhost/vente_achat/produit/updateProduct.php";
        QueryUtils.makeHttpPostRequest(url, data);
        /*==========*/

        return best;
    }


}
