<?php
header("content-type:application/json; charset=utf-8");

include 'connexionbd.php';

if ($mysqli->query("UPDATE evenementdb.evenement SET valide='0' WHERE id='".$_GET['id']."' "))
{
    header("HTTP/1.1 200 OK");
} else {
    header(':', true, 500);
    header('X-PHP-Response-Code: 500', true, 500);
}
$mysqli->close();

?>