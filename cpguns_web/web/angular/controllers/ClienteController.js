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

    $scope.alterar = function () {
        $http({
            method: 'POST',
            url: '/cpguns/costumer',
            params: {
                operacao: "ALTERAR",
                name: $scope.user.name,
                nascimento: $scope.user.dtBirth,
                genre: $scope.user.genre,
                cpf: $scope.user.cpf,
                rg: $scope.user.rg,
                phone: $scope.user.phoneNumber,
                email: $scope.user.user.email,
                password: $scope.user.user.password,
                id: $scope.user.id
            }
        }).then(function successCallback(response) {
            if (response.data.msg) {
                alert(response.data.msg);
            } else {
                window.sessionStorage.setItem("user", JSON.stringify(response.data.entities[0]));
                alert("Dados alterados com sucesso!");
            }
        }, function errorCallback(response) {
            alert("ERRO");
        });
    };

});