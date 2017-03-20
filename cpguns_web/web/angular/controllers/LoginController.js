angular.module("cpguns").controller("loginController", function ($scope, $http) {
    $scope.mostra = function () {
        alert("Nenhum usuário encontrado com o login e a senha informados!");
    };

    $scope.logar = function (user) {
        $http({
            method: 'GET',
            url: '/cpguns/login',
            params: {
                email : user.email,
                password: user.password,
                operacao: "CONSULTAR"
            }
        }).then(function successCallback(response) {
            // this callback will be called asynchronously
            // when the response is available
        }, function errorCallback(response) {
            // called asynchronously if an error occurs
            // or server returns response with an error status.
        });
    };
});


