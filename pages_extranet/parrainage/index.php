<!DOCTYPE html>
<?php
	header("Cache-Control: no-cache, must-revalidate");
	header("Expires: Sat, 26 Jul 1997 05:00:00 GMT");
?>
<html>
    <head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9"/>
        <script src="/js/jquery/dist/jquery.min.js" type="text/javascript"></script>
		<script src="/js/angular/angular.min.js" type="text/javascript"></script>
        <script src="/js/angular/angular-animate.min.js" type="text/javascript"></script>
        <script src="/js/angular/angular-aria.min.js" type="text/javascript"></script>
        <script src="/js/angular/angular-messages.min.js" type="text/javascript"></script>
        <script src="/js/angular/angucomplete-alt.min.js" type="text/javascript"></script>
        <script src="/js/angular/angular-sanitize.min.js" type="text/javascript"></script>
        <script src="/js/ngDialog/js/ngDialog.min.js" type="text/javascript"></script>
        <script src="./js/app.js" type="text/javascript"></script>
        <link rel="stylesheet" href="/js/ngDialog/css/ngDialog.min.css" type="text/css" media="screen" />
		<link rel="stylesheet" href="/js/ngDialog/css/ngDialog-theme-default.min.css" type="text/css" media="screen" />
        <link rel="stylesheet" href="./css/style.css" type="text/css" media="screen,print" />
        <?php
            $cuno = $_GET["cuno"];
            $lang = $_GET["lang"];
        ?>
        <script src="/js/angular/angular-locale_<?php echo "$lang-$lang" ?>.js" type="text/javascript"></script>
        <script src="./js/language_<?php echo $lang ?>.js" type="text/javascript"></script>
        
        <script type="text/javascript"><?php echo "var CUNO = '$cuno', LANG = '$lang';" ?></script>
    </head>
    <body ng-app="app">
        <div id="container" ng-controller="controller_<?php echo $type ?>">
        	<div class="coord">{{ language.COORD }}</div>
            <div class="form">
            	<table width="100%">
            		<tr>
            			<th>{{ language.NAME }} *</th>
            			<td><input ng-model="candidate.name" name="name"/></td>
            		</tr>
            		<tr>
            			<th>{{ language.FIRSTNAME }} *</th>
            			<td><input ng-model="candidate.firstname" name="firstname"/></td>
            		</tr>
            		<tr>
            			<th>{{ language.NAME }} *</th>
            			<td><input ng-model="candidate.name" name="name"/></td>
            		</tr>
            		<tr>
            			<th>{{ language.NAME }} *</th>
            			<td><input ng-model="candidate.name" name="name"/></td>
            		</tr>
            		<tr>
            			<th>{{ language.NAME }} *</th>
            			<td><input ng-model="candidate.name" name="name"/></td>
            		</tr>
            		<tr>
            			<th>{{ language.NAME }} *</th>
            			<td><input ng-model="candidate.name" name="name"/></td>
            		</tr>
            		<tr>
            			<th>{{ language.NAME }} *</th>
            			<td><input ng-model="candidate.name" name="name"/></td>
            		</tr>
            		<tr>
            			<th>{{ language.NAME }} *</th>
            			<td><input ng-model="candidate.name" name="name"/></td>
            		</tr>
            		<tr>
            			<th>{{ language.NAME }} *</th>
            			<td><input ng-model="candidate.name" name="name"/></td>
            		</tr>
            		<tr>
            			<th>{{ language.NAME }} *</th>
            			<td><input ng-model="candidate.name" name="name"/></td>
            		</tr>
            	</table>
            	<div class="libelle">{{ language.NAME }}</div>
            	<div class=""></div>
            </div>
        </div>
    </body>
</html>