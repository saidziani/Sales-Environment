package AgentProject;

/**
 * Created by said on 06/05/17.
 */
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import java.io.IOException;


public class A1 extends Agent {
    //String nom, prenom;
    ACLMessage demande;
    //Envoie la demande seulement:
    protected void setup(){
        addBehaviour(new CyclicBehaviour(this) {
            public void action() {
                Produit p = new Produit();
                p.designation = "SOURIS";
                demande = new ACLMessage(ACLMessage.INFORM);
                demande.addReceiver(new AID("Vendeur", AID.ISLOCALNAME));
                try {
                    demande.setContentObject(p);
                    send(demande);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                block();
                //Here Response
                ACLMessage reponse;
                Produit E;
                reponse = receive();
                System.out.println("Haja lewla");
                if (reponse != null) {
                    try {
                        E = (Produit) reponse.getContentObject();
                        String design = E.designation;
                        System.out.println("Reciveddddd => je suis " + getLocalName() + " j'ai reçu le produit "
                                + E.designation + " " +
                                "avec le prix " + E.prix);
                    } catch (UnreadableException e) {
                        System.err.println(getLocalName() + "catched exception " + e.getMessage());
                    }
                    doDelete();
                }
                block();
            }
        });
    }

}
