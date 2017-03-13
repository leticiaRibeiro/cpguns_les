angular.module('listagemDiretiva', []).directive('minhaListagem', function(){
   
    var ddo = {};
    ddo.restrict = "AE";
    
    ddo.scope = {
        nome: "@",
        telefone: "@"
    };
    
    ddo.templateUrl = "js/directives/minha-listagem.html";
    return ddo;
    
});

