angular.module('minhasDiretivas').directive('headerStore', function(){
	var ddo = {};

	ddo.restrict = "AE";

	ddo.templateUrl = "../angular/directives/header-store.html";

	return ddo;
});