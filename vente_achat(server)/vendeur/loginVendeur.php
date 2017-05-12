<?php

require_once("../db_connexion.php");

$username = $_POST['user'];
$password = $_POST['pass'];
$query = $bdd->prepare("SELECT * FROM users WHERE prenom = ? ");
$query->execute(array($username));
$correct = 0;


if ($row = $query->fetch()) {
    if ($password == $row['pass']) {
        $correct = 1;
    }
}
if ($correct == 1) {
    echo $row['id_user'];
} else {
    echo "failure";
}


?>