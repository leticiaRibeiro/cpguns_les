angular.module('minhasDiretivas').directive('headerLogado', function(){
	var ddo = {};

	ddo.restrict = "AE";

	ddo.templateUrl = "../angular/directives/header-logado.html";

	return ddo;
});