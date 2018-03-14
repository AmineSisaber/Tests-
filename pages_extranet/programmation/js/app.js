angular.module("app", ['angucomplete-alt', 'ngDialog', 'ngSanitize', 'chart.js']).config(['ChartJsProvider', function (ChartJsProvider) {
	Chart.defaults.global.colors = [
		{
		    backgroundColor: '#48bac2',
		    pointBackgroundColor: '#48bac2',
		    pointHoverBackgroundColor: '#48bac2',
		    borderColor: 'rgba(0,0,0,0)',
		    pointBorderColor: '#fff',
		    pointHoverBorderColor: 'rgba(151,187,205,1)'
		}, {
		    backgroundColor: '#004855',
		    pointBackgroundColor: '#004855',
		    pointHoverBackgroundColor: '#004855',
		    borderColor: 'rgba(0,0,5f5e5e,0',
		    pointBorderColor: '#fff',
		    pointHoverBorderColor: 'rgba(151,187,205,1)'
		 }];
}]).filter('month', function($filter) {
    return function(month) {
        var d = new Date();
        return $filter('date')(new Date(d.getFullYear(), month-1), 'MMMM');
    };
}).filter('language', function($filter, language) {
    return function(key) {
        return eval('language.' + key);
    };
}).filter('orderObjectBy', function() {
    return function(items, field1, field2, reverse) {
        var filtered = [];
        angular.forEach(items, function(item) {
            filtered.push(item);
        });
        filtered.sort(function (a, b) {
            if(a[field1] != b[field1])
                return (a[field1] < b[field1] ? 1 : -1);
            return (a[field2] > b[field2] ? 1 : -1);
        });
        if(reverse) filtered.reverse();
            return filtered;
    };
}).filter("trust", function($sce) {
    return function(htmlCode){
        return $sce.trustAsHtml(htmlCode);
    }
}).directive("rowWidth", function () {
    return {
        scope:{
            rowWidth: '='
        },
        link: function (scope, element, attrs) {
            scope.$watch('rowWidth', function (width) {
                element.css('width', width + '%');
            });
        }
    }
}).directive("rowSize", function () {
    return {
        scope:{
            rowSizeLeft: '=',
            rowSizeWidth: '='
        },
        link: function (scope, element, attrs) {
            scope.$watch('rowSizeLeft', function (left) {
                element.css('left', left + '%');
            });
            scope.$watch('rowSizeWidth', function (width) {
                element.css('width', width + '%');
            });
        }
    }
});