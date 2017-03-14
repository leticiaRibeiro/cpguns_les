angular.module('minhasDiretivas').directive('meuFooter', function(){
	var ddo = {};

	ddo.restrict = "AE";

	ddo.templateUrl = "../angular/directives/footer.html";

	return ddo;
});