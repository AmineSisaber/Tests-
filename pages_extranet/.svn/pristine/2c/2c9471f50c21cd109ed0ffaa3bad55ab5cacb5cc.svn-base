<?php
	include '../config.inc.php';
	header("content-type:application/json; charset=utf-8");
	
	$saisonN = isset($_GET["nextSeason"]) ? 'Saison_suivante' : 'Saison_N';
	$saisonHist = isset($_GET["nextSeason"]) ? 'Saison_precedente' : 'Saison_N-1';
	$sql = "SELECT TOP 1 Code_saison, [$saisonHist], Date_debut, Date_debut_numeric, Date_fin, Date_fin_numeric"
		. " FROM BPW_PROD_Datamarts.dbo.Saison INNER JOIN BPW_PROD_Datamarts.dbo.Saison_parametre ON Code_saison=$saisonN";
	
	

	$resultats = $connexion->query($sql);
	$resultats->setFetchMode(PDO::FETCH_ASSOC);
	$row = $resultats->fetch();
	$resultats->closeCursor();
	
	$dateStart = strtotime($row['Date_debut']) + 10000;
	$dateEnd = strtotime($row['Date_fin']) + 10000;
	$saisonHisto = $row[$saisonHist];
	$saison = $row['Code_saison'];
		
	$displayNextSeason = true;
	if(!isset($_GET["nextSeason"])) {
		$date = new DateTime();
		$date->add(new DateInterval("P3M"));
		$displayNextSeason = $date->getTimestamp() >= $dateEnd;
	}

	$months = array();
	
	$date = new DateTime();
	$date->setTimestamp($dateStart);
	while($date->getTimestamp()<$dateEnd) {
		$months[intval(date('m', $date->getTimestamp()))] = array('realized' => 0, 'planified' => 0, 'histo' => 0, 'count' => 0);
		$date->add(new DateInterval('P1M'));
	}
			
	$cuno = isset($_GET["cuno"]) ? $_GET["cuno"] : 0;
	$demons = array();
	
	$sql = "SELECT Code_demonstratrice, [Customer name] as Customer_name,"
    			. "(select count(Code_Reunion) from BPW_PROD_Datamarts.dbo.Ateliers a inner join BPW_PROD_Datamarts.dbo.Calendrier c on Date_commande_numeric=[Date YYYYMMDD] "
    			. " where c.Code_saison='$saisonHisto' and a.Code_demonstratrice=p.Code_demonstratrice) as Nb_atelier_realise_histo"
			. " FROM BPW_PROD_Datamarts.dbo.Hierarchie p"
			. " INNER JOIN BPW_PROD_Datamarts.dbo.Cliente ON Code_demonstratrice = Code_cliente"
			. " WHERE (p.Code_equipe IN ('". str_replace(",", "','", $cuno) . "') OR p.[Code anim junior] IN ('". str_replace(",", "','", $cuno) . "'))"
			. " AND p.Code_saison = '$saison'"
			. " ORDER BY Customer_name ASC";
									
// 	echo $sql;
	$resultats = $connexion->query($sql);
	$resultats->setFetchMode(PDO::FETCH_ASSOC);
	while ($row = $resultats->fetch()) {
	    $demons[$row['Code_demonstratrice']] = array('name' => utf8_encode($row['Customer_name']),
				'head' => ($row['Code_demonstratrice']==$cuno?1:0),
				'id' => $row['Code_demonstratrice'],
				'months' => $months,
				'planifiedSum' => 0,
				'realizedSum' => 0,
				'histoSum' => $row['Nb_atelier_realise_histo']
		);
	}
	$resultats->closeCursor();
	
	/**************************       **********************************/
	/* Récupération des ateliers réalisés par les conseilleres */
	$sql = "SELECT Code_Reunion as atelier, Date_commande as date, Code_demonstratrice as code_demons"
			. " FROM [BPW_PROD_Datamarts].[dbo].[Ateliers]"
			. " WHERE Code_demonstratrice IN ('". implode("','", array_keys($demons))."')"
			. " AND Code_saison = '$saison'";
	
	$resultats = $connexion->query($sql);
	$resultats->setFetchMode(PDO::FETCH_ASSOC);
	while ($row = $resultats->fetch()) {
		$demons[trim($row["code_demons"])]['months'][date('n', strtotime($row["date"]) + 10000)]['realized'] += 1;
		$demons[trim($row["code_demons"])]['realizedSum'] += 1;
		$months[date('n', strtotime($row["date"]) + 10000)]['realized'] += 1;
	}
	$resultats->closeCursor();
	
	/* Récupération des ateliers planifiés par les conseilleres */
	$sql = "SELECT Code_atelier as atelier, Date_atelier as date, Code_demonstratrice as code_demons"
			. " FROM [BPW_PROD_Datamarts].[dbo].[Planning_atelier]"
			. " WHERE Code_demonstratrice IN ('". implode("','", array_keys($demons))."')"
			. " AND Code_saison = '$saison'"
			. " AND Date_atelier >= Convert(date, getdate())"
			. " AND Statut <> 80";
									
	$resultats = $connexion->query($sql);
	$resultats->setFetchMode(PDO::FETCH_ASSOC);
	while ($row = $resultats->fetch()) {
		$demons[trim($row["code_demons"])]['months'][date('n', strtotime($row["date"]) + 10000)]['planified'] += 1;
		$demons[trim($row["code_demons"])]['planifiedSum'] += 1;
		$months[date('n', strtotime($row["date"]) + 10000)]['planified'] += 1;
	}
	$resultats->closeCursor();
	
	$sql = "select Numero_Mois,count(Code_Reunion) as Count_Reunion from BPW_PROD_Datamarts.dbo.Ateliers a"
			. " INNER JOIN BPW_PROD_Datamarts.dbo.Calendrier c ON Date_commande_numeric=[Date YYYYMMDD]"
			. " WHERE c.Code_saison='$saisonHisto'"
			. " and a.Code_demonstratrice in ('". implode("','", array_keys($demons))."')"
			. " group by Numero_Mois";
							
	$resultats = $connexion->query($sql);
	$resultats->setFetchMode(PDO::FETCH_ASSOC);
	while ($row = $resultats->fetch()) {
		$months[$row["Numero_Mois"]]['histo'] = $row["Count_Reunion"];
	}
	$resultats->closeCursor();
	
	$sql = "select Numero_Mois,count(Code_Reunion) as Count_Reunion from BPW_PROD_Datamarts.dbo.Ateliers a"
			. " INNER JOIN BPW_PROD_Datamarts.dbo.Calendrier c ON Date_commande_numeric=[Date YYYYMMDD]"
			. " WHERE c.Code_saison='$saisonHisto'"
			. " AND a.Code_demonstratrice in "
			. " 	(SELECT Code_demonstratrice FROM BPW_PROD_Datamarts.dbo.Hierarchie "
			. " 	WHERE Code_saison='$saisonHisto'"
			. "		AND (Code_equipe IN ('". str_replace(",", "','", $cuno) . "') OR [Code anim junior] IN ('". str_replace(",", "','", $cuno) . "')))"
			. " group by Numero_Mois";
	//     echo $sql;
	$resultats = $connexion->query($sql);
	$resultats->setFetchMode(PDO::FETCH_ASSOC);
	while ($row = $resultats->fetch()) {
		$months[$row["Numero_Mois"]]['histo2'] = $row["Count_Reunion"];
	}
	$resultats->closeCursor();
	
	$planifiedSum = 0;
	$realizedSum = 0;
	$histoSum = 0;
	foreach($demons as $demo) {
		$i=0;
		$histoSum += $demo['histoSum'];
		$realizedSum += $demo['realizedSum'];
		$planifiedSum += $demo['planifiedSum'];
		foreach($demo['months'] as $index => $demoMonth) {
			if(($demoMonth['realized'] + $demoMonth['planified'])>0)
				$months[$index]['count']++;
		}
	}
	
	foreach($demons as $ind => $demo) {
		$i=0;
		$demons[$ind]['month'] = array();
		foreach($demons[$ind]['months'] as $index => $demoMonth) {
			$demons[$ind]['month'][$i] = $demoMonth;
			$demons[$ind]['month'][$i]['month'] = $index;
			$i++;
		}
		$demons[$ind]['months']=NULL;
	}
	
	$monthArray = array();
	$currMonthIndex = 0;
	$i=0;
	foreach($months as $index => $monthTmp) {
		$monthArray[$i] = $monthTmp;
		$monthArray[$i]['month'] = $index;
		if($index==intval(date("m")))
			$currMonthIndex=$i;
		$i++;
	}
	
	$result = (object) array (
			"month" => $currMonthIndex,
			"year" => intval(date("Y")),
			"demons" => $demons,
			"months" => $monthArray,
			"monthCount" => count($months),
			"planifiedSum" => $planifiedSum,
			"realizedSum" => $realizedSum,
			"histoSum" => $histoSum,
			"saison" => $saison,
			"saisonHisto" => $saisonHisto,
			"displayNextSeason" => $displayNextSeason
	);
	
	echo json_encode($result);
?>