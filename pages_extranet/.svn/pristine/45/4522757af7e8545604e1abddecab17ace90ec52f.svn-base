<?php
// 	include 'Connexion_BD.php';
$mysqli = new mysqli("172.16.48.4", "root", "EgzJEvFB", "evenementdb");
if ($mysqli->connect_errno) {
    printf("Échec de la connexion a la BD evenementdb : %s\n", $mysqli->connect_error);
    exit();
}
$mysqli->set_charset("utf8");
$evenement = array();
$results = $mysqli->query("DELETE  FROM evenementdb.evenement where id= '". $_GET['id'] ."'");
$mysqli->close();
?>