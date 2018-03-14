<?php

header("content-type:application/json; charset=utf-8");
$mysqli= new mysqli("172.16.48.4", "root", "EgzJEvFB", "evenementdb");
if ($mysqli->connect_errno) {
    printf("Échec de la connexion a la BD evenementdb : %s\n", $mysqli->connect_error);
    exit();
}
$mysqli->set_charset("utf8");
$evenement = array();
$results = $mysqli->query("SELECT * FROM evenementdb.evenement WHERE valide= '1' ");
while($row = $results->fetch_array(MYSQL_ASSOC)) {
    $evenement[] = $row;
}
$result = (object) array (
    "evenement" => $evenement
);
echo json_encode($result);
$results->close();
$mysqli->close();
?>