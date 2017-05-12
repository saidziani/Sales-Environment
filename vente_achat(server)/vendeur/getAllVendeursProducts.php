<?php
    require_once("../db_connexion.php");
    
    if(isset($_POST['id'])){

        $req = $bdd->prepare('SELECT * FROM produit WHERE id_user = ?');

        $req->execute(array($_POST['id']));
        
        $i=0;
        $produits = "";
        while($v = $req->fetch()){
            $produit=$v['designation']."#".$v['prix']."#".$v['qualité'];
            if($i!=0)
                $produits.="-".$produit;
            else
                $produits.=$produit;
            $i++;
        }
        if($i==0){
            echo "0";
        }else{
            echo $produits;
        }
    }
?>