angular.module('minhasDiretivas').directive('menuLoja', function(){
	var ddo = {};

	ddo.restrict = "AE";

	ddo.templateUrl = "../angular/directives/menu_loja.html";

	return ddo;
});