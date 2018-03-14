<!DOCTYPE html>
<?php
	header("Cache-Control: no-cache, must-revalidate");
	header("Expires: Sat, 26 Jul 1997 05:00:00 GMT");
?>
<html>
    <head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9"/>
        <script src="../js/jquery/dist/jquery.min.js" type="text/javascript"></script>
		<script src="../js/angular/angular.min.js" type="text/javascript"></script>
        <script src="../js/angular/angular-animate.min.js" type="text/javascript"></script>
        <script src="../js/angular/angular-aria.min.js" type="text/javascript"></script>
        <script src="../js/angular/angular-messages.min.js" type="text/javascript"></script>
        <script src="../js/angular/angucomplete-alt.min.js" type="text/javascript"></script>
        <script src="../js/angular/angular-sanitize.min.js" type="text/javascript"></script>
        <script src="../js/ngDialog/js/ngDialog.min.js" type="text/javascript"></script>
        <script src="../js/chart.js/dist/Chart.min.js" type="text/javascript"></script>
        <script src="../js/angular-chart.js/dist/angular-chart.min.js" type="text/javascript"></script>
        <script src="../js/Chart.PieceLabel.js/build/Chart.PieceLabel.min.js" type="text/javascript"></script>
        <script src="../js/angular-chart.js/chart-plugins.js" type="text/javascript"></script>
        <link rel="stylesheet" href="../js/ngDialog/css/ngDialog.min.css" type="text/css" media="screen" />
		<link rel="stylesheet" href="../js/ngDialog/css/ngDialog-theme-default.min.css" type="text/css" media="screen" />
		
        <script src="./js/app.js" type="text/javascript"></script>
        <script src="./js/app_perso.js" type="text/javascript"></script>
        <script src="./js/app_equipe.js" type="text/javascript"></script>
        <link rel="stylesheet" href="./css/style.css" type="text/css" media="screen,print" />
        <link rel="stylesheet" href="./css/style_perso.css" type="text/css" media="screen,print" />
        <link rel="stylesheet" href="./css/style_equipe.css" type="text/css" media="screen,print" />
        <?php
        	include 'config.inc.php';
            $cuno = $_GET["cuno"];
            $lang = $_GET["lang"];
            $decalage = isset($_GET["decalage"])?$_GET["decalage"]:-1;
            $nbweek = isset($_GET["nbweek"])?$_GET["nbweek"]:6;
            $type = "perso";
            $role = "perso";
            switch($cuno) {
				case "0400001": $cuno="0400000"; $type = "equipe"; $role="secteur"; break;
				case "0400374": $cuno="0400000,0400374"; $type = "equipe"; $role="secteur"; break;
                case "0100003": $cuno="0400000,0400374,2223431,0300011"; $type = "equipe"; $role="secteur"; break;
                case "0100001": $cuno="0105056,0105740,2797032,2767426"; $type = "equipe"; $role="secteur"; break;
                default:
                	$sql = "SELECT (SELECT COUNT(Code_demonstratrice) FROM BPW_PROD_Datamarts.dbo.W_Hierarchie WHERE Code_equipe = '$cuno' OR Code_anim_junior='$cuno') as nb_demo,"
                	. "(SELECT COUNT(Code_demonstratrice) FROM BPW_PROD_Datamarts.dbo.W_Hierarchie"
	        		. " WHERE Code_secteur = '$cuno'"
	        		. " OR [Code equipe detachee] = '$cuno'"
	        		. " OR [Code equipe detachee2] = '$cuno'"
	        		. " OR [Code equipe detachee3] = '$cuno'"
	        		. " OR [Code equipe detachee4] = '$cuno'"
	        		. " OR [Code equipe detachee5] = '$cuno'"
	        		. " OR [Code equipe detachee6] = '$cuno'"
	        		. " OR Code_groupe = '$cuno'"
	        		. " OR Code_zone = '$cuno'"
	        		. " ) as nb_anim";
	        		$resultats = $connexion->query($sql);
	        		$resultats->setFetchMode(PDO::FETCH_BOTH);
	        		$row = $resultats->fetch();
	        		$resultats->closeCursor();
	        		$type = ($row[0]>0 || $row[1]>0)?'equipe':'perso';
	        		$role = ($row[1]>0?'secteur':($row[0]>0?'equipe':'perso'));
			}
        ?>
        <script src="../js/angular/angular-locale_<?php echo "$lang-$lang" ?>.js" type="text/javascript"></script>
        <script src="./js/language_<?php echo $lang ?>.js" type="text/javascript"></script>
        
        <script type="text/javascript"><?php echo "var CUNO = '$cuno', LANG = '$lang', ROLE = '$role', DECALAGE=$decalage, NBWEEK=$nbweek;" ?></script>
    </head>
    <body ng-app="app">
        <div id="container" ng-controller="controller_<?php echo $type ?>">
<!--         	<div class="titleAgenda">Mon agenda</div> -->
<!--         	<div>&nbsp;</div> -->
            <div ng-bind-html="language.SUBTITLE" class="subtitle"></div>
			<div ng-show="isAnim" class="subtitle">{{ language.SUBTITLE_ANIM }}</div>
            <div ng-include="'atelier_<?php echo $type ?>.html'"></div>
        </div>
    </body>
</html>