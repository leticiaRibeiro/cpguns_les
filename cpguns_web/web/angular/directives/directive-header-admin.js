angular.module('minhasDiretivas').directive('headerAdmin', function(){
	var ddo = {};

	ddo.restrict = "AE";

	ddo.templateUrl = "../angular/directives/header-admin.html";

	return ddo;
});