<?php
include 'connexionbd.php';
$today = date("Y-m-d H:i:s");
$cuno = isset($_GET['cuno']);
$address = $_GET['codep'] . " " . $_GET['ville'];
$address = '33000 bordeaux';
function getCoordsFromAdress($address)
{
    $cuno = isset($_GET['cuno']);
    $coords = array();
    $base_url = "http://maps.googleapis.com/maps/api/geocode/xml?";
    $request_url = $base_url . "address=" . urlencode($address) . '&sensor=false';
    $xml = simplexml_load_file($request_url) or die("url not loading");
    $json = file_get_contents($request_url) or die("url not loading");
    $obj = json_decode($json);
    $coords['lat'] = $coords['lon'] = '';
    $coords['status'] = $xml->status;
    if ($coords['status'] == 'OK') {
        $coords['lat'] = $xml->result->geometry->location->lat;
        $coords['lon'] = $xml->result->geometry->location->lng;
    }
    return $coords;
}
$dateEvt=$_GET['date'];
$coords = getCoordsFromAdress($address);
$lat = $coords['lat'];
$lon = $coords['lon'];
if (
$mysqli->query("INSERT INTO  evenementdb.evenement (`titre_evenement`, `nom_lieu`, `adresse`, `codepostal`,`ville`,`latitude`,`longitude`,`date`, `nom`, `prenom`, `tel`, `email`, `code_conseillere`,`date_creation`,`valide`,`categorie`)"
    . " VALUES ('" . $_GET['titre'] . "','" . $_GET['nomlieu'] . "','" . $_GET['adre'] . "','" . $_GET['codep'] . "','" . $_GET['ville'] . "','$lat','$lon','$dateEvt','nom','prenom','" . $_GET['tel'] . "','" . $_GET['email'] . "','".$_GET['cuno']."','$today','0','".$_GET['categorie']."')")
) {
    header("HTTP/1.1 200 OK");
} else {
    header(':', true, 500);
    header('X-PHP-Response-Code: 500', true, 500);
}
$mysqli->close();
?>