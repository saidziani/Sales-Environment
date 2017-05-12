<?php
    require_once("../db_connexion.php");
    
    if(isset($_POST['id'])){

        $req = $bdd->prepare('SELECT * FROM users WHERE id_user = ?');

        $req->execute(array($_POST['id']));
        
        $v = $req->fetch();

        $vendeur=$v[0]."#".$v[1];

        echo $vendeur;
    }
?>