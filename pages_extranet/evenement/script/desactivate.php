<?php
header("content-type:application/json; charset=utf-8");

include 'connexionbd.php';

if ($mysqli->query("UPDATE evenementdb.categorie SET etat='0' WHERE code='".$_GET['code']."' "))
{
    header("HTTP/1.1 200 OK");
} else {
    header(':', true, 500);
    header('X-PHP-Response-Code: 500', true, 500);
}
$mysqli->close();

?>