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
	    <script src="../js/smart-table/smart-table.min.js" type="text/javascript"></script>
	    <link rel="stylesheet" href="../js/ngDialog/css/ngDialog.min.css" type="text/css" media="screen" />
		<link rel="stylesheet" href="../js/ngDialog/css/ngDialog-theme-default.min.css" type="text/css" media="screen" />
		<link rel="stylesheet" href="../js/bootstrap/css/bootstrap.min.css" type="text/css" media="screen" />
		<link rel="stylesheet" href="../js/bootstrap/css/bootstrap-theme.min.css" type="text/css" media="screen" />
			
	    <script src="./js/app.js" type="text/javascript"></script>
	    <link rel="stylesheet" href="./css/style.css" type="text/css" media="screen,print" />
	    
	    <?php
            $cuno = $_GET["cuno"];
            $lang = $_GET["lang"];
        ?>
        <script src="../js/angular/angular-locale_<?php echo "$lang-$lang" ?>.js" type="text/javascript"></script>
        <script src="./js/language_<?php echo $lang ?>.js" type="text/javascript"></script>
        
        <script type="text/javascript"><?php echo "var CUNO = '$cuno', LANG = '$lang';" ?></script>
	</head>
<body ng-app="app">
	<div class="" ng-controller="controller">
<!--     <div ng-show="loading"><h3>Chargement des hotesses...</h3></div> -->
    <table st-table="display_records" st-safe-src="hotesses" class="">
        <thead>
	        <tr>
	            <th colspan="9">
	            	<div class="search">
	                <input st-search placeholder="Recherche (nom, prénom, ... ) " type="text" />
	                </div>
	            </th>
	        </tr>
	        <tr class="head">
	            <th st-sort="nom">Nom</th>
	            <th st-sort="prenom">Prénom</th>
<!-- 	            <th st-sort="code_postal">CP</th> -->
	            <th st-sort="ville">Ville</th>
	            <th st-sort="tel">Téléphone</th>
	            <th st-sort="datesoumission">Date de création</th>
<!-- 	            <th st-sort="canal">Canal d'entrée</th> -->
	            <th st-sort="statu">Statut</th>
	            <th></th>
	        </tr>
        </thead>
        <tbody>
        <tr st-select-row="row" st-select-mode="multiple" ng-repeat="row in display_records">
            <td class="aleft">{{row.nom}}</td>
            <td class="aleft">{{row.prenom}}</td>
<!--             <td>{{row.code_postal}}</td> -->
            <td class="aleft">{{row.ville}}</td>
            <td>{{row.tel}}</td>
            <td>{{row.datesoumission}}</td>
<!--             <td>{{row.canal}}</td> -->
            <td>{{row.statu}}</td>
            <td>
                        <a href="#" ng-click="viewRecord(row)">
                        	<img src="../image/picto_view.png" />
                        </a>
	                    <a href="#" ng-click="editRecord(row)">
	                    	<img src="../image/picto_edit.png" />
	            		</a>
			</td>
		</tr>
		</tbody>
		<tfoot>
			<tr>
			    <td colspan="9" class="text-center">
			        <div st-pagination="" st-items-by-page="itemsByPage"></div>
			    </td>
			</tr>
		</tfoot>
			</table>
</div>
</body>
</html>
