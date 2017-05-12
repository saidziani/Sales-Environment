<?php
    require_once("../db_connexion.php");
    

        $req = $bdd->prepare('SELECT * FROM produit ');

        $req->execute();
        
        $i=0;
        $produits = "";
        while($v = $req->fetch()){
            $produit=$v['designation']."#".$v['prix']."#".$v['qualité']."#".$v['id_produit']."#".$v['id_user'];
            if($i!=0)
                $produits.="-".$produit;
            else
                $produits.=$produit;
            $i++;
        }

        echo $produits;
?>