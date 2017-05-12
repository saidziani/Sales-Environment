<?php
    require_once("../db_connexion.php");
    
        $idP = $_POST['idP'];

        $req = $bdd->prepare('UPDATE produit SET vendu = 1 WHERE id_produit = ?');
        $req->execute(array($idP));
        $req->fetch();

?>