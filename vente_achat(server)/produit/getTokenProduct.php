<?php
    require_once("../db_connexion.php");
    
        $req = $bdd->prepare('SELECT * FROM produit WHERE take = 1 ');
        $req->execute();
        $p = $req->fetch();

        $req = $bdd->prepare('SELECT * FROM users WHERE id_user = ? ');
        $req->execute(array($p['id_user']));
        $prd = $req->fetch();
        

        //$produit = $p['designation']."#".$p['prix']."DA#".$p['livraison']."DA#".prd['prenom']."#".p['id_produit'];

        $produit = $p['designation']."#".$p['prix']."DA#".$p['livraison']."DA#".$prd['prenom']."#".$p['id_produit'];
            
        echo $produit;

?>