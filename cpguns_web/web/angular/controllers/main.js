angular.module("cpguns", ['minhasDiretivas'])

        .controller("ClienteController", function ($scope, $http) {
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
        })

        .controller("StoreController", function ($scope, $http) {
            $scope.salvarStore = function (store) {
                $http({
                    method: 'POST',
                    url: '/cpguns/store',
                    params: {
                        operacao: "SALVAR",
                        name: store.name,
                        address: store.address,
                        number: store.number,
                        complement: store.complement,
                        zip: store.zip,
                        neighborhood: store.neighborhood,
                        city: store.city,
                        state: store.state
                    }
                }).then(function successCallback(response) {

                }, function errorCallback(response) {
                    // deu caquinha
                });
            };
        })

        .controller("CrudController", function ($scope, $http) {
            $scope.costumers = [];
            $scope.user;

            $scope.consultar = function () {
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

            $scope.cadastrar = function (user) {
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

            $scope.visualizar = function (costumer) {
                $scope.user = costumer;
            };
        })

        .controller("loginController", function ($scope, $http) {

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
                    if (response.data.entities.length === 0) {
                        alert("Nenhum usuário encontrado com essas credenciais!");
                    } else {
                        window.sessionStorage.setItem("user", JSON.stringify(response.data.entities[0]));
                        window.location.href = "http://localhost:8084/cpguns/pages/alterar_dados_cadastrais.html";
                    }
                }, function errorCallback(response) {
                    alert("ERRO");
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
                        // converte um objeto JavaScript para um objeto JSON
                        window.sessionStorage.setItem("user", JSON.stringify(response.data.entities[0]));
                        //var usuario = JSON.parse(window.sessionStorage.getItem("user"));
                        alert("Usuário cadastrado com sucesso!");
                        window.location.href = "http://localhost:8084/cpguns/pages/alterar_dados_cadastrais.html";
                    }
                }, function errorCallback(response) {
                    // deu caquinha
                });
            };
        })

        .controller("anuncioController", function ($scope) {
            $scope.produto = JSON.parse(window.sessionStorage.getItem("arm"));
            $scope.addCarrinho = function () {
                if (window.sessionStorage.getItem("carrinho")) { // EXISTE o carrinho
                    var carrinho = JSON.parse(window.sessionStorage.getItem("carrinho"));
                } else { // CARRINHO NÃO EXISTE
                    var carrinho = new Array();
                }
                carrinho.push($scope.produto);
                window.sessionStorage.setItem("carrinho", JSON.stringify(carrinho));

                window.location.href = "http://localhost:8084/cpguns/pages/carrinho.html";
            };
        })

        .controller("indexController", function ($scope, $http) {
            $scope.getArms = function () {
                $http({
                    method: 'GET',
                    url: '/cpguns/arms',
                    params: {
                        operacao: "CONSULTAR"
                    }
                }).then(function successCallback(response) {
                    $scope.arms = response.data;
                }, function errorCallback(response) {
                    alert("ERRO");
                });
            };

            $scope.verAnuncio = function (arm) {
                window.sessionStorage.setItem("arm", JSON.stringify(arm));
                window.location.href = "http://localhost:8084/cpguns/pages/anuncio.html";
            };
        })

        .controller("carrinhoController", function ($scope) {
            $scope.carrinho = {};
            $scope.valorTotal = 0;
            if (window.sessionStorage.getItem("carrinho")) {
                $scope.qtde = 1;
                $scope.carrinho = JSON.parse(window.sessionStorage.getItem("carrinho"));
                $scope.carrinho.forEach(function(produto){
                    produto.qtdeCarrinho = 1;
                    $scope.valorTotal += produto.price;
                });
            } else {
                alert("Nenhum item no carrinho!");
            }           

            $scope.removeItemCarrinho = function (produto) {
                var indice = $scope.carrinho.indexOf(produto);
                $scope.carrinho.splice(indice, 1);
                window.sessionStorage.setItem("carrinho", JSON.stringify($scope.carrinho));
            };
            
            $scope.mudarQtde = function(){
                $scope.valorTotal = 0;
                $scope.carrinho.forEach(function(produto){
                    $scope.valorTotal += produto.price * produto.qtdeCarrinho;
                });
            };
            
            $scope.limparCarrinho = function(){
                $scope.carrinho.splice(0, $scope.carrinho.length);
                window.sessionStorage.removeItem("carrinho");
            };
            
            $scope.continuar = function(){
                window.sessionStorage.setItem("carrinho", JSON.stringify($scope.carrinho));
                window.sessionStorage.setItem("valorTotal", $scope.valorTotal);
                window.location.href = "http://localhost:8084/cpguns/pages/escolher_endereco.html";
            };
        });


