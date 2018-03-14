<?php

	header("content-type:application/json; charset=utf-8");
// 	include 'Connexion_BD.php';
	$mysqli= new mysqli("172.16.48.4", "root", "EgzJEvFB", "affectation");
	if ($mysqli->connect_errno) {
		printf("Échec de la connexion a la BD geolocbd : %s\n", $mysqli->connect_error);
		exit();
	}
	$mysqli->set_charset("utf8");
	
	$cuno = isset($_GET["cuno"]) ? $_GET["cuno"] : 0;
	$hotesses = array();

	$results = $mysqli->query("SELECT ho.*, ah.date as date_affectation FROM hotesse ho"
			. " INNER JOIN affectation_hotesse ah ON ho.sid=ah.hotesse where ah.conseillere = '$cuno'"
			. " AND ah.date IS NOT NULL AND ho.status='AFFECTE'");
	

	while($row = $results->fetch_array(MYSQL_ASSOC)) {
		$row['nom'] = $row['nom'];
        $hotesses[] = $row;
    }
    
	$result = (object) array (
        "hotesses" => $hotesses
    );

	echo json_encode($result);
    $results->close();
	$mysqli->close();
?>