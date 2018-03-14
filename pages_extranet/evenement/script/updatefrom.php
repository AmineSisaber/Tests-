<?php
header("content-type:application/json; charset=utf-8");

include 'connexionbd.php';

if ($mysqli->query(" UPDATE evenement SET titre_evenement = '".$_GET['titre_evenement']."',nom_lieu = '".$_GET['nom_lieu']."',adresse ='".$_GET['adresse']."',codepostal = '".$_GET['codepostal']."',ville = '".$_GET['ville']."',latitude ='".$_GET['latitude']."',longitude ='".$_GET['longitude']."',date ='2017-06-29 07:37:25',nom = '".$_GET['nom']."',prenom ='".$_GET['prenom']."',tel ='".$_GET['tel']."',email ='".$_GET['email']."',code_conseillere = '".$_GET['code_conseillere']."',date_creation = '2017-06-29 07:37:25',valide = '".$_GET['valide']."',categorie = '".$_GET['categorie']."'   WHERE id = '".$_GET['id']."'")

) {
    header("HTTP/1.1 200 OK");
} else {
    header(':', true, 500);
    header('X-PHP-Response-Code: 500', true, 500);
}
$mysqli->close();

?>