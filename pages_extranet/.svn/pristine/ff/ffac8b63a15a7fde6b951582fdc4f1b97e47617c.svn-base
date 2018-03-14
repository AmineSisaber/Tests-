<?php

/**
 * Created by PhpStorm.
 * User: sisaber
 * Date: 07/06/2017
 * Time: 15:15
 */

// 	header("content-type:application/json; charset=utf-8");
	include 'Connexion_BD.php';
	
	$sid = $_GET['sid'];
	

	if($mysqli->query("UPDATE hotesse SET"
			. " civilite = '" . $_GET['civilite']. "',"
			. " nom = '" . $_GET['nom']. "',"
			. " adresse = '" . $_GET['adresse']. "',"
			. " ville = '" . $_GET['ville']. "',"
			. " email = '" . $_GET['email']. "',"
			. " tel = '" . $_GET['tel']. "',"
			. " statu = '" . $_GET['statu']. "',"
			. " commentaire = '" . (isset($_GET['commentaire']) ? $_GET['commentaire'] : '') . "'"
			. " WHERE sid=$sid")) {
	} else {
		echo $mysqli->error;		
	}

	$mysqli->close();
?>