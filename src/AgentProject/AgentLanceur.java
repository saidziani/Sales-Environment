package AgentProject;

/**
 * Created by said on 12/04/17.
 */
import jade.core.Runtime;
import jade.core.ProfileImpl;
import jade.wrapper.*;

public class AgentLanceur {
    public static void main(String[]args){
        try {
            Runtime rt = Runtime.instance();

            ProfileImpl p = new ProfileImpl("localhost", 1099, "RSHP");
            ContainerController mc = rt.createMainContainer(p);

          /*  Object[] arg = new Object[1];
            arg[0] = "clavier";*/

            AgentController acheteur = mc.createNewAgent("Acheteur", A1.class.getName(), null);
            acheteur.start();

            AgentController vendeur = mc.createNewAgent("Vendeur", V1.class.getName(), null);
            vendeur.start();

            rt.setCloseVM(true);

        }catch (Exception e) { e.printStackTrace();}
    }

}
