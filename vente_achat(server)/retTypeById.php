<?php

require_once("db_connexion.php");

$username = $_POST['id'];
$query = $bdd->prepare("SELECT * FROM users WHERE id_user = ? ");
$query->execute(array($username));

if ($row = $query->fetch()) {
    echo $row['type'];
}
else {
    echo "failure";
}


?>