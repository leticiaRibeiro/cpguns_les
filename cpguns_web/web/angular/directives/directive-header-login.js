angular.module('minhasDiretivas').directive('headerLogin', function(){
	var ddo = {};

	ddo.restrict = "AE";

	ddo.templateUrl = "../angular/directives/header-login.html";

	return ddo;
});