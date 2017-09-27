(function() {
	'use strict';
	angular.module('translatorss', ['ngRoute']).config(['$routeProvider',
			function($routeProvider) {
				$routeProvider.when('/about', {
							templateUrl : 'templates/about.html'
						});
				$routeProvider.when('/help', {
							templateUrl : 'templates/help.html'
						});
				$routeProvider.when('/contact', {
							templateUrl : 'templates/contact.html'
						});
			}]);
}).call(this);