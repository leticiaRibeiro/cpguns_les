angular.module("cpguns").controller("loginController", function ($scope, $http) {

    $scope.user = {};
    $scope.login = {};

    $scope.mostra = function () {
        alert("Nenhum usuário encontrado com o login e a senha informados!");
    };

    $scope.logar = function (login) {
        $http({
            method: 'GET',
            url: '/cpguns/login',
            params: {
                email: login.mail,
                password: login.pass,
                operacao: "CONSULTAR"
            }
        }).then(function successCallback(response) {
            window.sessionStorage.setItem("user", JSON.stringify(response.data[0]));
            window.location.href = "http://localhost:8084/cpguns/pages/alterar_dados_cadastrais.html";            
        }, function errorCallback(response) {
            alert("Nenhum usuário encontrado com essas credenciais!");
        });
    };

    $scope.cadastrar = function () {
        $http({
            method: 'POST',
            url: '/cpguns/costumer',
            params: {
                operacao: "SALVAR",
                name: $scope.user.name,
                nascimento: $scope.user.dtBirth,
                genre: $scope.user.genre,
                cpf: $scope.user.cpf,
                rg: $scope.user.rg,
                phone: $scope.user.phoneNumber,
                email: $scope.user.email,
                password: $scope.user.password
            }
        }).then(function successCallback(response) {
            if (response.data.msg) {
                alert(response.data.msg);
            } else {
                window.sessionStorage.setItem("user", JSON.stringify(response.data.entities[0]));
                //var usuario = JSON.parse(window.sessionStorage.getItem("user"));
                alert("Usuário cadastrado com sucesso!");
                window.location.href = "http://localhost:8084/cpguns/pages/alterar_dados_cadastrais.html";
            }
        }, function errorCallback(response) {
            // deu caquinha
        });
    };
});


