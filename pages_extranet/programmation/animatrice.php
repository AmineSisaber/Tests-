<?php
	include 'config.inc.php';
    header("content-type:application/json; charset=utf-8");

	$cuno = $_GET["cuno"];
    $query = $_GET["query"];
    
    $sql="SELECT Code_demonstratrice, [Customer name] as Customer_name"
			. " FROM BPW_PROD_Datamarts.dbo.W_Hierarchie p"
            . " INNER JOIN BPW_PROD_Datamarts.dbo.Cliente ON Code_demonstratrice = Code_cliente"
            . " WHERE Code_secteur IN ('". str_replace(",", "','", $cuno) . "') "
            . " AND (UPPER([Customer name]) LIKE UPPER('$query%') OR UPPER(Prenom_cliente + ' ' + Nom_cliente) LIKE UPPER('$query%'))"
            . " AND Code_demonstratrice IN (SELECT Code_equipe FROM BPW_PROD_Datamarts.dbo.W_Hierarchie)"
            . " ORDER BY Customer_name ASC";
	$animatrice = array();
	
	$resultats = $connexion->query($sql);
	$resultats->setFetchMode(PDO::FETCH_ASSOC);
	while($row = $resultats->fetch()) {
		if($row['Code_demonstratrice'] != $cuno) {
			$animatrice[] = array('name' => trim(utf8_encode($row['Customer_name'])), 'cuno' => $row['Code_demonstratrice']);
		}
	}
	$resultats->closeCursor();
	
	$result = (object) array (
		"animatrice" => $animatrice
	);
	
	echo json_encode($result);
?>