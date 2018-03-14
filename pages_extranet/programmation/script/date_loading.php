<?php
	header("content-type:application/json; charset=utf-8");
	
	date_default_timezone_set('Europe/Paris');
	
	$conn = mssql_connect("172.16.45.39", "isotools", "Jdf5fgD");
	/* Date d'actualisation des données. */
	$sql = "SELECT TOP 1 Date_Loading"
			. " FROM BPW_PROD_Datamarts.dbo.DWH_Loading WHERE Is_Ok = 1 AND Category = 'ALL' ORDER BY Date_Loading DESC";
	$query = mssql_query($sql, $conn);
	$row = mssql_fetch_array($query);
	$dateLoading = strtotime($row['Date_Loading']);
// 	$date = new DateTime($row['Date_Loading']);
// 	echo $date->format('d/m/Y H:i:s') . '<br/>';
// 	echo $row['Date_Loading'] . ' - ' . $date->getTimestamp();
	
	$result = (object) array (
			"dateLoading" => $dateLoading
	);
	
	echo json_encode($result);
	
	mssql_free_result($query);
?>