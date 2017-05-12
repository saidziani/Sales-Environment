<?php
    require_once("../db_connexion.php");
    
        $designP = $_POST['designation'];
        $req = $bdd->prepare('SELECT * FROM produit WHERE designation = ?');
        $req->execute(array($designP));
        
        $i=0;
        $vendeurs = "";
        while($v = $req->fetch()){
            $id_user = $v['id_user'];
            $req1 = $bdd->prepare('SELECT * FROM users WHERE id_user = ?');
            $req1->execute(array($id_user));
            $vend = $req1->fetch();

            $vendeur = $vend['prenom']."_".$id_user;
            if($i!=0)
                $vendeurs.="-".$vendeur;
            else
                $vendeurs.=$vendeur;
            $i++;
        }  

        echo $vendeurs;

?>