angular.module('minhasDiretivas').directive('becoinsHeader', function(){
	var ddo = {};

	ddo.restrict = "AE";

	ddo.templateUrl = "../angular/directives/header.html";

	return ddo;
});