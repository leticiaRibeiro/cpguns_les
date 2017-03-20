angular.module("cpguns").controller("CrudController", function ($scope, $http) {
    $scope.costumers = [];
    $scope.user;
    
    $scope.consultar = function(){
        $http({
            method: 'GET',
            url: '/cpguns/costumer',
            params: {
                operacao: "CONSULTAR"
            }
        }).then(function successCallback(response) {
            $scope.costumers = response.data;
        }, function errorCallback(response) {
            // called asynchronously if an error occurs
            // or server returns response with an error status.
        });        
    };
    
    $scope.cadastrar = function(user){
        $http({
            method: 'POST',
            url: '/cpguns/costumer',
            params: {
                operacao: "SALVAR",
                name: user.name,
                nascimento: user.dtBirth,
                genre: user.genre,
                cpf: user.cpf,
                rg: user.rg,
                phone: user.phoneNumber,
                email: user.user.email,
                password: user.user.password
            }
        }).then(function successCallback(response) {
            alert(response.data);
            $scope.consultar();
        }, function errorCallback(response) {
            // called asynchronously if an error occurs
            // or server returns response with an error status.
        });                 
    };
    
    $scope.visualizar = function(costumer){
        $scope.user = costumer;
    };
});


