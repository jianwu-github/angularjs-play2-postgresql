var app = angular.module('AngularPlay2App', ['ui.router', 'ngResource', 'ngTable']);

app.factory("services",['$http', function($http) {
	var serviceBase = 'http://localhost:9000/companies'
    var obj = {}
	
	obj.createCompany = function (newCompany) {
		return $http.post(serviceBase, newCompany).then(function (results) {
		    return results;
		});
	};
	
	return obj;
}]);

app.config(function($stateProvider, $urlRouterProvider) {
    
    $urlRouterProvider.otherwise('/home');
    
    $stateProvider
        .state('home', {
            url: '/home',
            templateUrl: 'about'
        })        
        .state('previewcompanies', {
            url: '/previewcompanies',
            templateUrl:'assets/view-companies.html',
            controller:'GetCompanyList'
        }).state('viewCompanyComputers',{
            url:'/thecompany/:id/computerlist',
            templateUrl:'assets/view-computers.html',
            controller:'GetComputerList'
        })                
});

app.controller("GetCompanyList", function($scope, $stateParams, $http, services, ngTableParams) {
	var companyid = $stateParams.id
	
	$scope.newCompany = {id: 0, name: ''}
	$scope.createCompany = function(newCompany) {
		services.createCompany(newCompany);	 
    };
	
	$http.defaults.headers.common["X-Custom-Header"] = "Angular.js";
	$http.get('http://localhost:9000/companies').
	  success(function(data, status, headers, config) {
	  
	  $scope.tableParams = new ngTableParams({
	        page: 1,            // show first page
	        count: 10           // count per page
	    }, {
	        total: data.length, // length of data
	        getData: function($defer, params) {
	            $defer.resolve(data.slice((params.page() - 1) * params.count(), params.page() * params.count()));
	        }
	    });
	});
});

app.controller("GetComputerList", function($scope, $stateParams, $http, ngTableParams) {
	var companyid = $stateParams.id
	$http.defaults.headers.common["X-Custom-Header"] = "Angular.js";
	$http.get('http://localhost:9000/companies/' + companyid + '/computers').
	  success(function(data, status, headers, config) {
	  
	  $scope.tableParams = new ngTableParams({
	        page: 1,            // show first page
	        count: 10           // count per page
	    }, {
	        total: data.length, // length of data
	        getData: function($defer, params) {
	            $defer.resolve(data.slice((params.page() - 1) * params.count(), params.page() * params.count()));
	        }
	    });
	});
});