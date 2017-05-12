<?php
    require_once("../db_connexion.php");
    
        $prixP = (double)$_POST['prix'];
        $qualiteP = (double)$_POST['qualite'];
        $livraisonP = (double)$_POST['livraison'];
        $designP = $_POST['designation'];
        $idVendeur = $_POST['id_v'];

        $req = $bdd->prepare('SELECT * FROM produit WHERE designation = ? AND id_user = ? ');
        $req->execute(array($designP,$idVendeur));
        $valueTable = array();
        $i=0;
        $v = $req->fetch();
        $valueCP = $v['prix'] * $prix + 
                   $v['qualité'] * $qualite + 
                   $v['livraison'] * $livraison;

        $valueToTable = $v['id_produit']."#".$valueCP ;
            
        echo $valueToTable;

?>