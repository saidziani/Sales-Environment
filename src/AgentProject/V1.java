package AgentProject;

/**
 * Created by said on 06/05/17.
 */
import java.io.IOException;
import java.util.ArrayList;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;

public class V1 extends Agent {

    String nom, prenom;
    ArrayList<Produit> myProducts = new ArrayList<Produit>();


    public void creatProduct(String designation, double prix){
        Produit p = new Produit(prix,designation);
        this.myProducts.add(p);
    }


    protected void setup() {
        Produit p1 = new Produit(1000,"ecran");
        Produit p2 = new Produit(1000,"souris");
        Produit p3 = new Produit(1000,"clavier");

        myProducts.add(p1);
        myProducts.add(p2);
        myProducts.add(p3);

        addBehaviour(new CyclicBehaviour(this) {
            public void action() {
                ACLMessage demande;
                Produit E;
                demande = receive();
                if (demande != null) {
                    try {
                        E = (Produit) demande.getContentObject();
                        String design = E.designation;
                        System.out.println("Before");
                        int i = 0;
                        while (i <= myProducts.size() - 1) {
                            System.out.println("so Clause");
                            if (myProducts.get(i).designation.equalsIgnoreCase(design)) {
                                System.out.println("222222222222222=> je suis " + getLocalName() + " j'ai reçu le produit "
                                        + myProducts.get(i).designation + " " +
                                        "avec le prix " + myProducts.get(i).prix);

                                ACLMessage reponse;
                                reponse = new ACLMessage(ACLMessage.INFORM);
                                reponse.addReceiver(new AID("Acheteur", AID.ISLOCALNAME));
                                try {
                                    reponse.setContentObject(myProducts.get(i));
                                    send(reponse);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                            }
                            i++;
                        }
                        doDelete();
                        Runtime.getRuntime().exit(A1.AP_DELETED);
                    } catch (UnreadableException e) {
                        System.err.println(getLocalName() + "catched exception " + e.getMessage());
                    }
                }
                block();
            }
        });


    }
}

