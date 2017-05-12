<?php

require_once("../db_connexion.php");

$name = $_POST['name'];
$query = $bdd->prepare('SELECT * FROM users WHERE prenom = ?');
$query->execute(array($name));

if (isset($_POST['pass'])){

    $pass = $_POST['pass'];
    $name = $_POST['name'];
    $fname = $_POST['fname'];
    
    $query = $bdd->prepare('INSERT INTO `users` (`nom`, `prenom`, `pass`, `type`) VALUES (?,?,?,1)');

    $query->execute(array($name, $fname, $pass));

    $query->fetch();

    if ($query->rowCount()) {
        echo "sucess";
    } else {
        echo "failure";
    }
}
else{

    if ($query->rowCount() > 0) {
        echo "true";
    }
    else{
        echo "false";
    }
}



?>