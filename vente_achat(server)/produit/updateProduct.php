<?php
    require_once("../db_connexion.php");
    
        $idP = $_POST['idP'];

        $req = $bdd->prepare('UPDATE produit SET take = 0 ');
        $req->execute();
        $req->fetch();

        $req = $bdd->prepare('UPDATE produit SET take = 1 WHERE id_produit = ?');
        $req->execute(array($idP));
        $req->fetch();

?>