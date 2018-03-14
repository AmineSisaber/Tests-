<?php
	include '../config.inc.php';
//     header("content-type:application/json; charset=utf-8");

    $sql = "SELECT TOP 1 Code_saison, [Saison_N-1], Date_debut, Date_debut_numeric, Date_fin, Date_fin_numeric"
				. " FROM BPW_PROD_Datamarts.dbo.Saison INNER JOIN BPW_PROD_Datamarts.dbo.Saison_parametre ON Code_saison=Saison_N";   
	$resultats = $connexion->query($sql);
	$resultats->setFetchMode(PDO::FETCH_ASSOC);
	$row = $resultats->fetch();
	$resultats->closeCursor();

    $dateStart = strtotime($row['Date_debut']) + 10000;
    $dateEnd = strtotime($row['Date_fin']) + 10000;
    $saisonHisto = $row['Saison_N-1'];
    $weeks = array();
    $date = new DateTime();
    $date->setTimestamp($dateStart);
    while($date->getTimestamp()<$dateEnd) {
        $weeks[intval(date('W', $date->getTimestamp()))] = array('realized' => 0, 'planified' => 0, 'histo' => 0);
        $date->add(new DateInterval('P7D'));
    }
        
    $months = array();
    $date = new DateTime();
    $date->setTimestamp($dateStart);
    while($date->getTimestamp()<$dateEnd) {
        $months[intval(date('m', $date->getTimestamp()))] = array('realized' => 0, 'planified' => 0, 'histo' => 0);
        $date->add(new DateInterval('P1M'));
    }

    $sql = "SELECT TOP 1 Date_Loading"
				. " FROM BPW_PROD_Datamarts.dbo.DWH_Loading WHERE Is_Ok = 1 AND Category = 'ALL' ORDER BY Date_Loading DESC";
    
	$resultats = $connexion->query($sql);
	$resultats->setFetchMode(PDO::FETCH_ASSOC);
	$row = $resultats->fetch();
    $dateLoading = strtotime($row['Date_Loading']);
	$resultats->closeCursor();

    /**************************       **********************************/

    $cuno = $_GET["cuno"];

    $sql = "SELECT *, " 
                       . "(select count(Code_Reunion) from BPW_PROD_Datamarts.dbo.Ateliers a inner join BPW_PROD_Datamarts.dbo.Calendrier c on Date_commande_numeric=[Date YYYYMMDD] "
                       . " where c.Code_saison='$saisonHisto' and p.Semaine=a.Semaine and a.Code_demonstratrice=p.Code_demonstratrice) as Nb_atelier_realise_week_histo,"
                       . "(select count(Code_Reunion) from BPW_PROD_Datamarts.dbo.Ateliers a inner join BPW_PROD_Datamarts.dbo.Calendrier c on Date_commande_numeric=[Date YYYYMMDD] "
                       . " where c.Code_saison='$saisonHisto' and p.Month=Numero_Mois and a.Code_demonstratrice=p.Code_demonstratrice) as Nb_atelier_realise_month_histo"
                       . " FROM BPW_PROD_Catalog.dbo.BC_Suivi_planning_conseillere_cumul_saison_0 p where Code_demonstratrice IN ('". str_replace(",", "','", $cuno) . "') order by AnneeSemaine";
    $rows = array();

    $planifiedSum = 0;
    $realizedSum = 0;
    $histoSum = 0;
	
    $date = new DateTime();
    $date->setTime(00,00);
    
    $resultats = $connexion->query($sql);
    $resultats->setFetchMode(PDO::FETCH_ASSOC);
    while ($row = $resultats->fetch()) {
        if(strtotime($row["Date"]) < $date->getTimestamp()) {
            $weeks[$row["Semaine"]]['realized'] += $row["Nb_atelier_realise"];
            $months[$row["Month"]]['realized'] += $row["Nb_atelier_realise"];
            $realizedSum += $row["Nb_atelier_realise"];
        } else {
            $weeks[$row["Semaine"]]["planified"] += $row["Sum_Nb_atelier_programme"] - $row["Sum_Nb_atelier_annule"] + $row["Sum_Nb_atelier_nouveau"];
            $months[$row["Month"]]["planified"] += $row["Sum_Nb_atelier_programme"] - $row["Sum_Nb_atelier_annule"] + $row["Sum_Nb_atelier_nouveau"];
            $planifiedSum += $row["Sum_Nb_atelier_programme"] - $row["Sum_Nb_atelier_annule"] + $row["Sum_Nb_atelier_nouveau"];
        }
        $weeks[$row["Semaine"]]['histo'] = $row["Nb_atelier_realise_week_histo"];
        $months[$row["Month"]]['histo'] = $row["Nb_atelier_realise_month_histo"];
    }
    $resultats->closeCursor();

    foreach($months as $month) {
        $histoSum += $month['histo'];
    }
    
    $i=0;
    $weeksWithInd = array();
    foreach($weeks as $index => $week) {
        $weeksWithInd[$i] = $week;
        $weeksWithInd[$i]['week'] = $index;
        if($index==intval(date("W")))
            $currWeekIndex=$i;
        $i++;
    }
    
    $i=0;
    $monthsWithInd = array();
    foreach($months as $index => $month) {
        $monthsWithInd[$i] = $month;
        $monthsWithInd[$i]['month'] = $index;
        if($index==intval(date("m")))
            $currMonthIndex=$i;
        $i++;
    }
   
    $result = (object) array (
        "week" => $currWeekIndex,
        "month" => $currMonthIndex,
        "year" => intval(date("Y")),
        "weeks" => $weeksWithInd,
        "months" => $monthsWithInd,
        "weekCount" => count($weeks),
        "monthCount" => count($months),
        "planifiedSum" => $planifiedSum,
        "realizedSum" => $realizedSum,
        "histoSum" => $histoSum,
        "dateLoading" => $dateLoading
    );
    echo json_encode($result);
?>