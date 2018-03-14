var services = angular.module('servicesVue1', ['ngResource']);

services.factory('Logger', function($resource) {

    var logger = {};
    logger.usr
    var usr = $resource('http://localhost:1024/api/questions/list');
    return logger;
});