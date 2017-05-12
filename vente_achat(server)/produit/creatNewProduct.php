<?php

require_once("../db_connexion.php");

if (isset($_POST['id_u'])){

    $query = $bdd->prepare('INSERT INTO produit (designation,qualité,prix,livraison,id_user) VALUES (?,?,?,?,?)');

    $query->execute(array($_POST['design'],$_POST['qualite'],$_POST['prix'],$_POST['livraison'],$_POST['id_u'])); 

    $query->fetch();

    if ($query->rowCount()) {
        echo "sucess";
    
    }else{
        echo "failure";
    }
}

?>

