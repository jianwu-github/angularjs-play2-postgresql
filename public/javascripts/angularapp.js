var app = angular.module('AngularPlay2App', ['ui.router', 'ngResource']);

app.config(function($stateProvider, $urlRouterProvider) {
    
    $urlRouterProvider.otherwise('/home');
    
    $stateProvider
        .state('home', {
            url: '/home',
            templateUrl: 'about'
        })        
        .state('previewcompanies', {
            url: '/previewcompanies',
            templateUrl: 'previewcompanies'
        }).state('viewCompanyComputers',{
            url:'/thecompany/:id/computerlist',
            templateUrl:'assets/view-computers.html',
            controller:'GetComputerList'
        })                
});

app.controller("GetComputerList", function($scope, $stateParams, $http) {
	var companyid = $stateParams.id
	$http.defaults.headers.common["X-Custom-Header"] = "Angular.js";
	$http.get('http://localhost:9000/companies/' + companyid + '/computers').
	  success(function(data, status, headers, config) {
	  $scope.companyComputerList = data;
	});
});