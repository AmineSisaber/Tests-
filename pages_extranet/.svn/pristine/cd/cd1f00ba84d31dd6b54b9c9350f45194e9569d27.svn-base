<?php
	$dev = false;
	$serverName = "172.16.45.39"; //serverName\instanceName
	$user = "isotools";
	$pass = "Jdf5fgD";
	$database="BPW_PROD_Datamarts";
	
	// $dbh = new PDO("", $user, $pass);
	$dsn = "dblib:host=$serverName;dbname=$database";
	if($dev)
		$dsn = "sqlsrv:Server=$serverName;Database=$database;";
	
	$connexion = new PDO( $dsn , $user, $pass);
?>