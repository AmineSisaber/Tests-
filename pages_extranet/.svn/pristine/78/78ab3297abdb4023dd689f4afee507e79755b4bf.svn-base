angular.module("app")
    .controller("controller_equipe", ['$http', '$scope', '$location', 'language', 'ngDialog', 'equipe', function($http, $scope, $location, language, ngDialog, equipe) {
        $scope.language = language;
        $scope.currSeason=true;
        
        $scope.effectif = [0, 0, 0, 0, 0];
        var cuno = CUNO;
        var decalage = DECALAGE;
        var nbweek = NBWEEK;
        var role = ROLE;
		$scope.isAnim = role=='equipe' || role=='secteur';
        $scope.isSecteur = role=='secteur';
        $scope.currentCuno = CUNO;
        equipe.loadMonth($scope);
        equipe.loadWeek($scope);
        equipe.dateLoading($scope);
        
        $scope.seasonNext = function(bool) {
        	$scope.nextSeason=bool;
        	equipe.loadMonth($scope);
        }
        
        $scope.openDemo = function(cuno, name) {
            ngDialog.open({ template: './atelier_perso.html', controller: 'controller_perso', data: { 'cuno' : cuno, 'name' : name }});
        }
        
        $scope.dataFormatFn = function(str) { return {query: str, cuno: cuno}; }
        
        $scope.formatResult = function(results) {
            return results;
        }
        
        $scope.animSelected = function(anim) { 
            if(anim && anim.originalObject && anim.originalObject.cuno) {
            	$scope.loadAnim(anim.originalObject.cuno, $scope);
            }
        };
        
        $scope.inputChanged = function(str) {
            if(!str) {
            	$scope.currentCuno = CUNO;
                equipe.loadMonth($scope);
                equipe.loadWeek($scope);
                $scope.seasonNext(false);
            }
        };
        
        $scope.loadAnim = function(cuno) {
        	$scope.currentCuno = cuno;
            equipe.loadMonth($scope);
            equipe.loadWeek($scope);
            $scope.seasonNext(false);
        };
        
        $scope.weekRealised = function(week) {
            return week < $scope.currWeek;  
        };
        
        $scope.monthRealised = function(month) {
            return month < $scope.currMonth;  
        };
       
        $scope.chartModel = {
        		labels: [language.ACTIVES, language.INACTIVES],
        		options: {
                	legend : false,
                	cutoutPercentage: 64,
                	 pieceLabel: {
                		 mode: 'value',
                		 fontSize: 13,
                		 fontStyle: 'bold'
                	 },
                	 elements: {
                		 center: {
        	        		 sidePadding: 30, //Default 20 (as a percentage)
        	        		 colorTop: '#48bac2',
//        	        		 colorBottom: '#e04580',
        	        		 fontStyle: 'Arial'
                		 }
                	 },
                	 legendCallback: function(chart) {
        				var text = [];
        				text.push('<div class="chart-legend">');
        				for (var i = chart.data.datasets[0].data.length - 1; i >= 0 ; i--) {
        					text.push('<span style="color:' + chart.data.datasets[0].backgroundColor[i] + '">');
        					if (chart.data.labels[i]) {
        						text.push(chart.data.datasets[0].data[i] + ' ');
        						text.push(chart.data.labels[i]);
        					}
        					text.push('</span>');
        					if(i > 0)
        						text.push(' - ');
        				}
        				text.push('</div>');
        				return text.join("");
                	 }
        		}
        };
        
        $scope.charts = [];
        
        $scope.$on('chart-create', function (evt, chart) {
        	var index = chart.id % 5;
        	$("#chartjs-legend-" + index).html(chart.generateLegend());
        	var count = 0;
        	for (var i = chart.data.datasets[0].data.length - 1; i >= 0 ; i--) {
				count += chart.data.datasets[0].data[i];
			}
        	$scope.effectif[index] = count;
    	});

}]).service('equipe', ['$http', function($http) {
    return {
		'loadMonth': function($scope){
            $http({
                'method': 'GET',
                'url': './script/team_month.php' + ($scope.nextSeason?'?nextSeason':''),
                'params': {cuno: $scope.currentCuno}
            }).then(function (response) {
                $scope.demons = response.data.demons;
                $scope.months = response.data.months;
                $scope.currYear = response.data.year;
                $scope.currMonth = response.data.month;
                $scope.monthCount = response.data.monthCount;
                $scope.planifiedSum = response.data.planifiedSum;
                $scope.realizedSum = response.data.realizedSum;
                $scope.histoSum = response.data.histoSum;
                $scope.currSeason=!$scope.nextSeason;
                $scope.saison = response.data.saison;
                $scope.saisonHisto = response.data.saisonHisto;
                $scope.displayNextSeason = response.data.displayNextSeason;
            });
        },
		'loadWeek': function($scope){
            $http({
                'method': 'GET',
                'url': './script/team_week.php',
                'params': {cuno: $scope.currentCuno, nbweek: NBWEEK}
            }).then(function (response) {
                $scope.demons2 = response.data.demons;
                $scope.weeks = response.data.weeks;
                $scope.currWeek = response.data.week;
                
                $scope.weekCount = response.data.weekCount;
//                $scope.weekCount=11;
                
                $scope.country = response.data.country;
                $scope.lastDayMonth = response.data.lastDayMonth;
                $scope.dateEnd = response.data.dateEnd;
                var weeks = response.data.weeks;
                $scope.charts = [];
                for(var i=$scope.currWeek + DECALAGE; i < $scope.currWeek + 5 + DECALAGE; i++) {
                	var chart = angular.copy($scope.chartModel);
                	chart.data = [ weeks[i].active, weeks[i].inactive];
                	var taux = Math.round((weeks[i].active/(weeks[i].active + weeks[i].inactive))*100);
                	chart.options.elements.center.textTop = (taux>9?taux:(' ' + taux)) + '%';
//                	chart.options.elements.center.textTop = 'La semaine';
//                	chart.options.elements.center.text = (i==$scope.currWeek ? 'en cours' : ('S' + weeks[i].week)) ;
                	chart.options.elements.center.textBottom = (weeks[i].active + weeks[i].inactive);
                	chart.week = weeks[i].week;
                	chart.date = weeks[i].date;
                	chart.avg = weeks[i].avg;
                	chart.avgTop = weeks[i].avgTop;
                	$scope.charts.push(chart);
                }
            });
        },
        'dateLoading': function($scope){
            $http({
                'method': 'GET',
                'url': './script/date_loading.php'
            }).then(function (response) {
                $scope.dateLoading = response.data.dateLoading;
            });
        }
    }
}]);