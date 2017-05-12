package AgentProject;

/**
 * Created by said on 06/05/17.
 */
import java.io.*;

class Produit implements Serializable{
    double prix;
    String designation;

    public Produit(){

    }

    public Produit(double prix, String designation){
        this.designation = designation;
        this.prix = prix;
    }
}