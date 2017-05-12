<?php
    require_once("../db_connexion.php");
    
    if(isset($_POST['id_u'])){

        $req = $bdd->prepare('SELECT prenom FROM users WHERE id_user = ?');

        $req->execute(array($_POST['id_u']));
        
        $v = $req->fetch();

        echo $v['prenom'];
    }
?>