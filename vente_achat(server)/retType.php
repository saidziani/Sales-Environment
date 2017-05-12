<?php

require_once("db_connexion.php");

$username = $_POST['user'];
$query = $bdd->prepare("SELECT * FROM users WHERE prenom = ? ");
$query->execute(array($username));

if ($row = $query->fetch()) {
    echo $row['type'];
}
else {
    echo "failure";
}


?>