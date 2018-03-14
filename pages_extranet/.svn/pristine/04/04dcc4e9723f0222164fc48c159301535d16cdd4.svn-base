<?php
    header("content-type:application/json; charset=utf-8");
    
    $conn = mssql_connect("172.16.45.39", "isotools", "Jdf5fgD");
    $sql = "SELECT TOP 1 Code_saison, [Saison_N-1], Date_debut, Date_debut_numeric, Date_fin, Date_fin_numeric"
                       . " FROM BPW_PROD_Datamarts.dbo.Saison INNER JOIN BPW_PROD_Datamarts.dbo.Saison_parametre ON Code_saison=Saison_N";
    $query = mssql_query($sql, $conn);
    $row = mssql_fetch_array($query);

    $dateStart = strtotime($row['Date_debut']) + 10000;
    $dateEnd = strtotime($row['Date_fin']) + 10000;
    $saisonHisto = $row['Saison_N-1'];
    $weeks = array();
    for($week = intval(date('W', $dateStart)); $week <= intval(date('W', $dateEnd)) ; $week++)
        $weeks[$week] = array('realized' => 0, 'planified' => 0);
    
    $months = array();
    for($month = intval(date('m', $dateStart)); $month <= intval(date('m', $dateEnd)) ; $month++)
        $months[$month] = array('realized' => 0, 'planified' => 0, 'histo' => 0);

    $cuno = isset($_GET["cuno"]) ? $_GET["cuno"] : 0;
    $demons = array();

    $sql = "SELECT Code_demonstratrice, [Customer name] as Customer_name,"
                       . "(select count(Code_Reunion) from BPW_PROD_Datamarts.dbo.Ateliers a inner join BPW_PROD_Datamarts.dbo.Calendrier c on Date_commande_numeric=[Date YYYYMMDD] "
                       . " where c.Code_saison='$saisonHisto' and a.Code_demonstratrice=p.Code_demonstratrice) as Nb_atelier_realise_histo"
                       . " FROM BPW_PROD_Datamarts.dbo.W_Hierarchie p"
                       . " INNER JOIN BPW_PROD_Datamarts.dbo.Cliente ON Code_demonstratrice = Code_cliente"
                       . " WHERE Code_equipe IN ('". str_replace(",", "','", $cuno) . "') OR Code_anim_junior IN ('". str_replace(",", "','", $cuno) . "')"
                       . " ORDER BY Customer_name ASC";
    $query = mssql_query($sql, $conn);

    while ($row = mssql_fetch_array($query)) {
        $demons[$row['Code_demonstratrice']] = array('name' => utf8_encode($row['Customer_name']),
                                                                   'head' => ($row['Code_demonstratrice']==$cuno?1:0),
                                                                   'id' => $row['Code_demonstratrice'],
                                                                   'weeks' => $weeks, 
                                                                   'months' => $months,
                                                                   'planifiedSum' => 0,
                                                                   'realizedSum' => 0,
                                                                   'histoSum' => $row['Nb_atelier_realise_histo']
                                                                  );
    }

    $sql = "SELECT TOP 1 Date_Loading"
                       . " FROM BPW_PROD_Datamarts.dbo.DWH_Loading WHERE Is_Ok = 1 AND Category = 'ALL' ORDER BY Date_Loading DESC";
	$query = mssql_query($sql, $conn);
	$row = mssql_fetch_array($query);
    $dateLoading = strtotime($row['Date_Loading']);

    /**************************       **********************************/
	
    $sql = "SELECT * FROM BPW_PROD_Catalog.dbo.BC_Suivi_planning_conseillere_cumul_saison_0 where Code_demonstratrice IN ('". implode("','", array_keys($demons))."') order by AnneeSemaine";
    $query = mssql_query($sql, $conn);
    $date = new DateTime();
    $date->setTime(00,00);
    while ($row = mssql_fetch_array($query)) {
        if(strtotime($row["Date"]) < $date->getTimestamp()) {
            $demons[trim($row["Code_demonstratrice"])]['weeks'][$row["Semaine"]]['realized'] += $row["Nb_atelier_realise"];
            $demons[trim($row["Code_demonstratrice"])]['months'][$row["Month"]]['realized'] += $row["Nb_atelier_realise"];
            $demons[trim($row["Code_demonstratrice"])]['realizedSum'] += $row["Nb_atelier_realise"];
            $months[$row["Month"]]['realized'] += $row["Nb_atelier_realise"];
        } else {
            $demons[trim($row["Code_demonstratrice"])]['weeks'][$row["Semaine"]]["planified"] += 
                $row["Sum_Nb_atelier_programme"] - $row["Sum_Nb_atelier_annule"] + $row["Sum_Nb_atelier_nouveau"];
            $demons[trim($row["Code_demonstratrice"])]['months'][$row["Month"]]["planified"] += 
                $row["Sum_Nb_atelier_programme"] - $row["Sum_Nb_atelier_annule"] + $row["Sum_Nb_atelier_nouveau"];
            $demons[trim($row["Code_demonstratrice"])]['planifiedSum'] += 
                $row["Sum_Nb_atelier_programme"] - $row["Sum_Nb_atelier_annule"] + $row["Sum_Nb_atelier_nouveau"];
            $months[$row["Month"]]['planified'] += $row["Sum_Nb_atelier_programme"] - $row["Sum_Nb_atelier_annule"] + $row["Sum_Nb_atelier_nouveau"];
        }
    }

    $sql = "select Numero_Mois,count(Code_Reunion) as Count_Reunion from BPW_PROD_Datamarts.dbo.Ateliers a"
                . " INNER JOIN BPW_PROD_Datamarts.dbo.Calendrier c ON Date_commande_numeric=[Date YYYYMMDD]"
                . " WHERE c.Code_saison='$saisonHisto'"
                . " and a.Code_demonstratrice in ('". implode("','", array_keys($demons))."')"
                . " group by Numero_Mois";
    $query = mssql_query($sql, $conn);

    while ($row = mssql_fetch_array($query)) {
        $months[$row["Numero_Mois"]]['histo'] = $row["Count_Reunion"];
    }

    $planifiedSum = 0;
    $realizedSum = 0;
    $histoSum = 0;
    foreach($demons as $demo) {
        $histoSum += $demo['histoSum'];
        $realizedSum += $demo['realizedSum'];
        $planifiedSum += $demo['planifiedSum'];
    }

    $result = (object) array (
        "week" => intval(date('W')),
        "month" => intval(date("m")),
        "year" => intval(date("Y")),
        "demons" => $demons,
        "weeks" => $weeks,
        "months" => $months,
        "weekCount" => count($weeks),
        "monthCount" => count($months),
        "planifiedSum" => $planifiedSum,
        "realizedSum" => $realizedSum,
        "histoSum" => $histoSum,
        "dateLoading" => $dateLoading
    );

    echo json_encode($result, JSON_PRETTY_PRINT | JSON_UNESCAPED_UNICODE | JSON_UNESCAPED_SLASHES);
	
	mssql_free_result($query);
?>