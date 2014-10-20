var app = angular.module("AngularPlay2App", []);

app.service("greeter", function() { 
    this.name = "";
	this.greeting = function() {
	    return (this.name) ? ("Hello, " + this.name + "!") : "";
	};
});

app.controller("AppController", function($scope, greeter) { 
	$scope.greeter = greeter;
});