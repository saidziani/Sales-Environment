<?php
    require_once("../db_connexion.php");
    

        $req = $bdd->prepare('SELECT DISTINCT designation FROM produit ');

        $req->execute();
        
        $i=0;
        $produits = "";
        while($v = $req->fetch()){
            $produit=$v['designation'];
            if($i!=0)
                $produits.="-".$produit;
            else
                $produits.=$produit;
            $i++;
        }

        echo $produits;
?>