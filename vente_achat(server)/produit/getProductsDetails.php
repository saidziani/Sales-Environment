<?php
    require_once("../db_connexion.php");
    
        $prixP = (double)$_POST['prix'];
        $qualiteP = (double)$_POST['qualite'];
        $livraisonP = (double)$_POST['livraison'];
        $designP = $_POST['designation'];
        $id=$_POST['idV'];


        //Get max price product:
        $req = $bdd->prepare('SELECT MAX(prix) as max FROM produit WHERE designation = ?' );
        $req->execute(array($designP));
        $m = $req->fetch();
        $maxPrix = $m['max'];

        //Get max frais product:
        $req = $bdd->prepare('SELECT MAX(livraison) as max FROM produit WHERE designation = ?' );
        $req->execute(array($designP));
        $m = $req->fetch();
        $maxLivraison = $m['max'];

        //Devide values/max value
        $req = $bdd->prepare('SELECT * FROM produit WHERE designation = ? AND id_user = ? ');
        $req->execute(array($designP,$id));
        $v = $req->fetch();

        //Normalisation
        $prixNormal = $v['prix'] / $maxPrix; 
        $qualiteNormal =  $v['qualité'] / 4 ; 
        $livraisonNormal = $v['livraison'] / $maxLivraison;

        //Count values
        $valueCP = $prixNormal * $prixP + 
                       $qualiteNormal * $qualiteP + 
                       $livraisonNormal * $livraisonP;

        $valueToTable = $v['id_produit']."#".$valueCP."#".$v['id_user'] ;
           
        echo $valueToTable;


        //Get max price product:
        /*$req = $bdd->prepare('SELECT MAX(prix) as max FROM produit WHERE designation = ?' );
        $req->execute(array($design));
        $m = $req->fetch();
        $maxPrix = $m['max'];

        //Get max frais product:
        $req = $bdd->prepare('SELECT MAX(livraison) as max FROM produit WHERE designation = ?' );
        $req->execute(array($design));
        $m = $req->fetch();
        $maxLivraison = $m['max'];

        //Devide values/max value
        $req = $bdd->prepare('SELECT * FROM produit WHERE designation = ? ');
        $req->execute(array($design));
        $valueTable = array();
        $i=0;
        while($v = $req->fetch()){
            $prixDivMax = $v['prix'] / $maxPrix ;
            $livraisonDivMax = $v['livraison'] / $maxLivraison ;
            $qualiteDivMax = $v['qualité'] / 4 ;
            $valueToTable = $v['id_produit']."#".$prixDivMax."#".$livraisonDivMax."#".$qualiteDivMax ;
            $valueTable[$i] = $valueToTable;
            $i++;
        }*/

        //Begin work
        /*
        $req = $bdd->prepare('SELECT * FROM produit WHERE designation = ? AND qualite = ? AND prix <= ? AND livraison <= ?');

        $req->execute(array($design,$qualite,$prix,$livraison));
        
        $i=0;
        $produits = "";
        while($v = $req->fetch()){
            $produit=$v['id_produit'];
            if($i!=0)
                $produits.="-".$produit;
            else
                $produits.=$produit;
            $i++;
        }

        echo $produits;*/

        /*
        //Devide values/max value
        $req = $bdd->prepare('SELECT * FROM produit WHERE designation = ? ');
        $req->execute(array($designP));
        $valueTable = array();
        $i=0;
        while($v = $req->fetch()){
            
            $valueCP = $v['prix'] * $prix + 
                       $v['qualité'] * $qualite + 
                       $v['livraison'] * $livraison;

            $valueToTable = $v['id_produit']."#".$valueCP ;
            
            $valueTable[$i] = $valueToTable;

            if($i!=0)
                $valueTable.="-".$valueToTable;
            else
                $valueTable.=$valueToTable;
            $i++;
        }

        echo $valueTable;*/
?>