angular.module("cpguns").controller("ClienteController", function ($scope, $http) {

    $scope.user = {};

    $scope.recuperarDados = function () {
        $scope.user = JSON.parse(window.sessionStorage.getItem("user"));
    };

    $scope.excluir = function () {
        if (confirm("Você realmente deseja excluir sua conta?")) {
            $http({
                method: 'GET',
                url: '/cpguns/costumer',
                params: {
                    id: $scope.user.id,
                    operacao: "EXCLUIR"
                }
            }).then(function successCallback(response) {
                alert("Sua conta foi excluida!");
                window.sessionStorage.removeItem("user");
                window.location.href = "http://localhost:8084/cpguns/pages/index.html";
            }, function errorCallback(response) {
                // called asynchronously if an error occurs
                // or server returns response with an error status.
            });
        }
    };

});