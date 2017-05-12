
<?php
    try {
    $bdd = new PDO("mysql:host=localhost;dbname=venteachat;charset=utf8", 'username','password',
                    array(PDO::MYSQL_ATTR_INIT_COMMAND => "SET NAMES 'utf8'"));
    $bdd->setAttribute(PDO::ATTR_EMULATE_PREPARES, FALSE);} 
   
    catch(PDOException $e) {
        echo 'ERROR: ' . $e->getMessage();
        }
?>
