 <?php 
// 	header("content-type:application/json; charset=utf-8");
	
    $conn = mssql_connect("172.16.45.39", "isotools", "Jdf5fgD");
    $cuno = isset($_GET["cuno"]) ? $_GET["cuno"] : 0;
    $nbweek = isset($_GET["nbweek"]) ? $_GET["nbweek"] : 10;
    
    /* récupération plage date et numéros de semaine */
    $date = new DateTime();
    $date->sub(new DateInterval('P' . (- (1 - date('N'))) . 'D'));
    $date->sub(new DateInterval('P28D'));
    $weeks = array();
    $lastDayMonth = 0;
    $month = intval(date('m', $date->getTimestamp()));
    $dateStart = $date->format('Y-m-d');
    $dateStartI = $date->format('Ymd');
    $dateChartEnd = null;
    for($i = 0; $i < 5 + $nbweek; $i++) {
    	if($i>0)
    		$date->add(new DateInterval('P7D'));
    	$weeks[intval(date('W', $date->getTimestamp()))] = array('realized' => 0, 'planified' => 0, "date" => $date->getTimestamp());
    	if($i==3) {
    		$lastDayMonth = intval($date->format('t')) - intval($date->format('d')) + 1;
    	} else if($i==7) {
    		$dateTmp = clone $date;
    		$dateTmp->add(new DateInterval('P6D'));
    		$dateChartEnd = $dateTmp->getTimestamp() - 1000000;
    	}
    }
    $date->add(new DateInterval('P6D'));
    $dateEnd = $date->format('Y-m-d');
    $timestampEnd = $date->getTimestamp();
    $dateEndI = $date->format('Ymd');
    
    $demons = array();
    	
    /* récupération des démonstratrices */
    $sql = "SELECT distinct p.Code_demonstratrice, c.[Customer name] as Customer_name, p.[Date debut] as date_start, p.[Date fin] as date_end"
    		. " FROM BPW_PROD_Datamarts.dbo.Hierarchie p"
    		. " INNER JOIN BPW_PROD_Datamarts.dbo.Cliente c ON p.Code_demonstratrice = c.Code_cliente"
    		. " WHERE (p.Code_equipe IN ('". str_replace(",", "','", $cuno) . "') OR p.[Code anim junior] IN ('". str_replace(",", "','", $cuno) . "'))"
    		. " AND (c.Date_sortie IS NULL OR c.Date_sortie=0 OR c.Date_sortie > '$dateStart')"
    		. " AND c.Date_entree <= '$dateEnd'"
    		. " AND p.[Date debut] <= $dateEndI" 
    		. " AND p.[Date fin]  >= $dateStartI"
    		. " ORDER BY Customer_name ASC";
//     		echo $sql;
    $query = mssql_query($sql, $conn);
    											
    while ($row = mssql_fetch_array($query)) {
    	$demons[$row['Code_demonstratrice']] = array(
    		'name' => utf8_encode($row['Customer_name']),
			'head' => ($row['Code_demonstratrice']==$cuno?1:0),
    		'id' => $row['Code_demonstratrice'],
    		'weeks' => $weeks,
    	    'dateStart' => DateTime::createFromFormat('Ymd', $row['date_start'] . "")->getTimestamp() ,
    	    'dateEnd' => DateTime::createFromFormat('Ymd', $row['date_end'] . "")->getTimestamp()
    	);
    }
    
    /* Récupération des ateliers réalisés par les conseilleres */
    $sql = "SELECT Code_Reunion as atelier, Date_commande as date, Code_demonstratrice as code_demons"
  			. " FROM [BPW_PROD_Datamarts].[dbo].[Ateliers]"
  			. " WHERE Code_demonstratrice IN ('". implode("','", array_keys($demons))."')" 
			. " AND Date_commande >= '$dateStart'"
			. " AND Date_commande <= '$dateEnd'";
	
	$query = mssql_query($sql, $conn);
	while ($row = mssql_fetch_array($query)) {
		$demons[trim($row["code_demons"])]['weeks'][date('W', strtotime($row["date"]) + 10000)]['realized'] += 1;
	}
	
	/* Récupération des ateliers plannifiés par les conseilleres */
	$sql = "SELECT Code_atelier as atelier, Date_atelier as date, Code_demonstratrice as code_demons"
			. " FROM [BPW_PROD_Datamarts].[dbo].[Planning_atelier]"
			. " WHERE Code_demonstratrice IN ('". implode("','", array_keys($demons))."')" 
			. " AND Date_atelier >= '$dateStart'"
			. " AND Date_atelier >= Convert(date, getdate())"
			. " AND Date_atelier <= '$dateEnd'"
			. " AND Statut <> 80";
	
	$query = mssql_query($sql, $conn);
	while ($row = mssql_fetch_array($query)) {
		$demons[trim($row["code_demons"])]['weeks'][date('W', strtotime($row["date"]) + 10000)]['planified'] += 1;
	}
	
	foreach ($weeks as $key => $weekTmp) {
		$weeks[$key]['active']=0;
		$weeks[$key]['inactive']=0;
		$weeks[$key]['avg']=0;
		$weeks[$key]['avgTop']=0;
	}
	foreach($demons as $demo) {
		foreach($demo['weeks'] as $key => $weekTmp) {
			if(($weekTmp['realized'] + $weekTmp['planified']) > 0)
				$weeks[$key]['active']++;
				else if($demo['dateStart'] <= $weekTmp['date'] && (!$demo['dateEnd'] || $demo['dateEnd'] >= $weekTmp['date']))
				$weeks[$key]['inactive']++;
		}
	}
	
	/** Calcul des moyennes par semaine */
	
	// Récupération du pays
	$sql = "SELECT TOP 1 Code_pays FROM [BPW_PROD_Datamarts].[dbo].[Cliente]"
			. " WHERE Code_cliente in ('". str_replace(",", "','", $cuno) . "')";
	$query = mssql_query($sql, $conn);
	$row = mssql_fetch_array($query);
	$codePays = $row['Code_pays'];
	
	$query = mssql_query("set datefirst 1", $conn);
	
	$sql = "SELECT weeknb, SUM(active) as active FROM "
	        . "((SELECT DATEPART( week, Date_atelier) as weeknb, COUNT(distinct p.Code_demonstratrice) as active"
            . " FROM [BPW_PROD_Datamarts].[dbo].[Planning_atelier] p"
            . " INNER JOIN [BPW_PROD_Datamarts].[dbo].Cliente cl ON cl.Code_cliente=p.Code_demonstratrice"
            . " WHERE Date_atelier >= '$dateStart'"
            . " AND Date_atelier >= Convert(date, getdate())"
            . " AND Date_atelier <= '$dateEnd'"
            . " AND p.Statut <> 80"
            . " AND cl.Code_pays " . (trim($codePays) == "FR" ? " = 'FR'" :  " <> 'FR'")
            . " GROUP BY DATEPART( week, Date_atelier))"
            . " UNION "
            . " (SELECT DATEPART( week, Date_commande) as weeknb, COUNT(distinct p.Code_demonstratrice) as active"
            . " FROM [BPW_PROD_Datamarts].[dbo].[Ateliers] p"
            . " INNER JOIN [BPW_PROD_Datamarts].[dbo].Cliente cl ON cl.Code_cliente=p.Code_demonstratrice"
            . " WHERE Date_commande >= '$dateStart'"
            . " AND Date_commande <= '$dateEnd'"
            . " AND cl.Code_pays " . (trim($codePays) == "FR" ? " = 'FR'" :  " <> 'FR'")
            . " GROUP BY DATEPART( week, Date_commande))) as t_active"
            . " GROUP BY weeknb";
// 	echo $sql . "<br/>";
    $query = mssql_query($sql, $conn);
    while ($row = mssql_fetch_array($query)) {
        $sqlForWeek = "SELECT COUNT(DISTINCT c.Code_cliente) as effectif"
                    . " FROM Cliente c"
                    . " INNER JOIN Hierarchie h ON h.Code_demonstratrice=Code_cliente"
                    . " WHERE (c.Date_sortie IS NULL OR c.Date_sortie=0 OR c.Date_sortie > '".  date('Y-m-d', $weeks[$row['weeknb']-1]['date']) ."')"
                    . " AND c.Date_entree <= '".  date('Y-m-d', $weeks[$row['weeknb']-1]['date']) ."'"
                    . " AND c.Code_pays " . (trim($codePays) == "FR" ? " = 'FR'" :  " <> 'FR'")
                    . " AND c.Statut <> '10' AND c.Type_cliente = 'Demons'";
//         echo $sqlForWeek . "<br/>";
        $queryForWeek = mssql_query($sqlForWeek, $conn);
        $rowForWeek = mssql_fetch_array($queryForWeek);
        $weeks[$row['weeknb']-1]['avg'] = round($row['active']*100 / $rowForWeek['effectif']);
//         echo $row['active'] . "*100 / " . $rowForWeek['effectif'] . " = " . $weeks[$row['weeknb']-1]['avg'] . "%<br/>";
    }
    mssql_free_result($queryForWeek);

	//Récupération des moyennes du pays
// 	$sql = "SELECT weeknb, CAST(AVG(taux) as decimal (4,0)) as moyenne"
// 			. " FROM ("
// 				. " SELECT w.Code_saison, w.Code_equipe, DATEPART( week, Date_atelier) as weeknb,"
// 				. " CAST (COUNT(distinct p.Code_demonstratrice)*100 / CAST ((SELECT COUNT(Code_demonstratrice)"
// 					. " FROM [BPW_PROD_Datamarts].[dbo].Hierarchie wh"
// 					. " INNER JOIN Cliente c ON c.Code_cliente = wh.Code_demonstratrice"
// 					. " where wh.Code_equipe=w.Code_equipe and wh.Code_saison=w.Code_saison" 
// 					. " AND (c.Date_sortie IS NULL OR c.Date_sortie=0 OR c.Date_sortie > '$dateStart')"
//                     . " AND c.Date_entree <= '$dateEnd') AS decimal(4,2)) as decimal(10,0)) as taux"
// 				. " FROM [BPW_PROD_Datamarts].[dbo].[Planning_atelier] p"
// 				. " INNER JOIN [BPW_PROD_Datamarts].[dbo].Hierarchie w ON w.Code_demonstratrice = p.Code_demonstratrice AND w.Code_saison=p.Code_saison"
// 				. " INNER JOIN [BPW_PROD_Datamarts].[dbo].Cliente cl ON cl.Code_cliente=w.Code_demonstratrice"
// 				. " WHERE Date_atelier >= '$dateStart'"
// 				. " AND Date_atelier <= '$dateEnd'"
// 				. " AND p.Statut <> 80"
// 				. " AND cl.Code_pays" . (trim($codePays) == "FR" ? " = 'FR'" :  " <> 'FR'")
// 				. " GROUP BY w.Code_saison, w.Code_equipe, DATEPART( week, Date_atelier)) as tmp"
// 			. " group by weeknb";
// 	echo $sql;
// 	$query = mssql_query($sql, $conn);
// 	while ($row = mssql_fetch_array($query)) {
// 		$weeks[$row['weeknb']-1]['avg'] = $row['moyenne'];
// 	}
	
	//Récupération des moyennes du top 10 du pays
	$sql = "WITH results as ("
			. " SELECT weeknb, taux,RANK() OVER (PARTITION BY weeknb ORDER BY taux desc) AS Rank"
			. " FROM ("
			    . " SELECT tt.*, CAST (active *100 / CAST(effectif AS decimal(4,2)) as decimal(10,0)) as taux"
			    . " FROM ("
			        . " SELECT Code_saison, Code_equipe, weeknb, SUM(active) as active,"
    				. " (SELECT COUNT(Code_demonstratrice)" 
				    . " FROM [BPW_PROD_Datamarts].[dbo].Hierarchie wh"
				    . " INNER JOIN Cliente c ON c.Code_cliente = wh.Code_demonstratrice"
				    . " where wh.Code_equipe=act.Code_equipe and wh.Code_saison=act.Code_saison"
				    . " AND (c.Date_sortie IS NULL OR c.Date_sortie=0 OR c.Date_sortie > '$dateStart')"
				    . " AND c.Date_entree <= '$dateEnd') as effectif"
				    . " FROM ("
    				. " (SELECT w.Code_saison, w.Code_equipe, DATEPART( week, Date_atelier) as weeknb, COUNT(distinct p.Code_demonstratrice) as active"
    				. " FROM [BPW_PROD_Datamarts].[dbo].[Planning_atelier] p"
    				. " INNER JOIN [BPW_PROD_Datamarts].[dbo].Hierarchie w ON w.Code_demonstratrice = p.Code_demonstratrice AND w.Code_saison=p.Code_saison"
    				. " INNER JOIN [BPW_PROD_Datamarts].[dbo].Cliente cl ON cl.Code_cliente=w.Code_demonstratrice"
    				. " WHERE Date_atelier >= '$dateStart'"
    				. " AND Date_atelier >= Convert(date, getdate())"
    				. " AND Date_atelier <= '$dateEnd'"
    				. " AND p.Statut <> 80"
    				. " AND cl.Code_pays " . (trim($codePays) == "FR" ? " = 'FR'" :  " <> 'FR'")
    				. " GROUP BY w.Code_saison, w.Code_equipe, DATEPART( week, Date_atelier))"
    				. " UNION"
    				. " (SELECT w.Code_saison, w.Code_equipe, DATEPART( week, Date_commande) as weeknb, COUNT(distinct a.Code_demonstratrice) as active"
    				. " FROM [BPW_PROD_Datamarts].[dbo].[Ateliers] a"
    				. " INNER JOIN [BPW_PROD_Datamarts].[dbo].Hierarchie w ON w.Code_demonstratrice = a.Code_demonstratrice AND w.Code_saison=a.Code_saison"
    				. " INNER JOIN [BPW_PROD_Datamarts].[dbo].Cliente cl ON cl.Code_cliente=w.Code_demonstratrice"
    				. " WHERE Date_commande >= '$dateStart'"
    				. " AND Date_commande <= '$dateEnd'"
    				. " AND cl.Code_pays " . (trim($codePays) == "FR" ? " = 'FR'" :  " <> 'FR'")
    				. " GROUP BY w.Code_saison, w.Code_equipe, DATEPART( week, Date_commande))"
                    . " ) as act"
                    . " GROUP BY Code_saison, Code_equipe, weeknb) as tt"
				. " WHERE effectif > " . (trim($codePays) == "FR" ? "10" : "5") . ") as tmp"
			. ")"
			. " SELECT weeknb, CAST(AVG(taux) as DECIMAL(4,0)) as moyenne from results where Rank<" . (trim($codePays) == "FR" ? "10" : "5")
			. " GROUP BY weeknb";
// 	echo $sql;
	$query = mssql_query($sql, $conn);
	while ($row = mssql_fetch_array($query)) {
		$weeks[$row['weeknb']-1]['avgTop'] = $row['moyenne'];
	}
	
	foreach($demons as $ind => $demo) {
		$i=0;
		$demons[$ind]['week'] = array();
		foreach($demons[$ind]['weeks'] as $index => $demoweek) {
			$demons[$ind]['week'][$i] = $demoweek;
			$demons[$ind]['week'][$i]['week'] = $index;
			$i++;
		}
		$demons[$ind]['weeks']=NULL;
	}
	
	$weekArray = array();
	$currweekIndex = 0;
	$i=0;
	foreach($weeks as $index => $weekTmp) {
		$weekArray[$i] = $weekTmp;
		$weekArray[$i]['week'] = $index;
		if($index==intval(date("W")))
			$currweekIndex=$i;
		$i++;
	}
	
    $result = (object) array (
//     		"week" => intval(date('W')),
    		"week" => $currweekIndex,
    		"country" => $codePays,
    		"demons" => $demons,
    		"weeks" => $weekArray,
    		"weekCount" => count($weekArray),
    		"lastDayMonth" => $lastDayMonth,
    		"dateEnd" => $dateChartEnd
    );
    
//     var_dump($result);
    
    echo json_encode($result);
    
    mssql_free_result($query);
?>