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

            $scope.buscarLojas = function () {
                $http({
                    method: 'GET',
                    url: '/cpguns/store',
                    params: {
                        operacao: "CONSULTAR"
                    }
                }).then(function successCallback(response) {
                    $(document).ready(function () {
                        $('select').material_select();
                    });
                    $scope.stores = response.data;
                    $scope.slcestado = null;
                    $scope.slccidade = null;
                    $scope.slcloja = null;
                    $scope.estados = new Array();
                    $scope.cidades = new Array();
                    $scope.lojas = new Array();
                    $scope.stores.forEach(function (store) {
                        if ($scope.estados.indexOf(store.address.city.state.name) < 0) {
                            $scope.estados.push(store.address.city.state.name);
                        }
                    });
                }, function errorCallback(response) {
                    alert("ERRO");
                });
            };

            $scope.carregarCidade = function () {
                try {
                    $scope.cidades.splice(0, $scope.cidades.length);
                    $scope.stores.forEach(function (store) {
                        if (store.address.city.state.name === $scope.slcestado) {
                            if ($scope.cidades.indexOf(store.address.city.name) < 0) {
                                $scope.cidades.push(store.address.city.name);
                            }
                        }
                    });
                } finally {
                    $(document).ready(function () {
                        $('select').material_select();
                    });
                }
            };

            $scope.carregarLoja = function () {
                try {
                    $scope.lojas.splice(0, $scope.lojas.length);
                    $scope.stores.forEach(function (store) {
                        if (store.address.city.name === $scope.slccidade) {
                            if ($scope.lojas.indexOf(store.name) < 0) {
                                $scope.lojas.push(store.name);
                            }
                        }
                    });
                } finally {
                    $(document).ready(function () {
                        $('select').material_select();
                    });
                }
            };

            $scope.continuar = function () {
                $scope.stores.forEach(function (store) {
                    if (store.name === $scope.slcloja && store.address.city.name === $scope.slccidade && store.address.city.state.name === $scope.slcestado) {
                        window.sessionStorage.setItem("store", JSON.stringify(store));
                        window.location.href = "http://localhost:8084/cpguns/pages/pagamento.html";
                    }
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
            
            $scope.addCarrinho = function (produto) {
                if (window.sessionStorage.getItem("carrinho")) { // EXISTE o carrinho
                    var carrinho = JSON.parse(window.sessionStorage.getItem("carrinho"));
                } else { // CARRINHO NÃO EXISTE
                    var carrinho = new Array();
                }
                carrinho.push(produto);
                window.sessionStorage.setItem("carrinho", JSON.stringify(carrinho));

                window.location.href = "http://localhost:8084/cpguns/pages/carrinho.html";
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
                $scope.carrinho.forEach(function (produto) {
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

            $scope.mudarQtde = function () {
                $scope.valorTotal = 0;
                $scope.carrinho.forEach(function (produto) {
                    $scope.valorTotal += produto.price * produto.qtdeCarrinho;
                });
            };

            $scope.limparCarrinho = function () {
                $scope.carrinho.splice(0, $scope.carrinho.length);
                window.sessionStorage.removeItem("carrinho");
            };

            $scope.continuar = function () {
                var autorizacao = prompt("Insira o número da autorização concedida pela polícia");
                if (autorizacao !== null) {
                    if (window.sessionStorage.getItem("user")) {
                        window.sessionStorage.setItem("carrinho", JSON.stringify($scope.carrinho));
                        window.sessionStorage.setItem("valorTotal", $scope.valorTotal);
                        window.sessionStorage.setItem("autorizacao", autorizacao);
                        window.location.href = "http://localhost:8084/cpguns/pages/escolher_endereco.html";
                    } else {
                        alert("Você deve estar logado para efetuar a compra!");
                        window.sessionStorage.setItem("carrinho", JSON.stringify($scope.carrinho));
                        window.sessionStorage.setItem("valorTotal", $scope.valorTotal);
                        window.location.href = "http://localhost:8084/cpguns/pages/login.html";
                    }
                } else {
                    alert("Favor inserir o número da autorização!");
                }


            };
        })

        .controller("finalizarController", function ($scope, $http) {
            $scope.carrinho = JSON.parse(window.sessionStorage.getItem("carrinho"));
            $scope.valorTotal = JSON.parse(window.sessionStorage.getItem("valorTotal"));

            $scope.buscarCEP = function () {
                $http({
                    method: 'GET',
                    url: 'https://viacep.com.br/ws/' + $scope.endereco.zip + '/json',
                }).then(function success(response) {
                    $scope.endereco.logradouro = response.data.logradouro;
                    $scope.endereco.cidade = response.data.localidade;
                    $scope.endereco.estado = response.data.uf;
                    $scope.endereco.bairro = response.data.bairro;
                }, function erro(response) {
                    console.log(response);
                });
            };

            $scope.finalizar = function () {
                $scope.cartao;
                $scope.endereco;
                $scope.store = JSON.parse(window.sessionStorage.getItem("store"));
                $scope.user = JSON.parse(window.sessionStorage.getItem("user"));
                var valor = window.sessionStorage.getItem("valorTotal");
                var valorFinal = (parseFloat(valor) + parseFloat(300) + ((parseFloat(valor) * parseFloat(10)) / parseFloat(100)));

                $http({
                    method: 'POST',
                    url: '/cpguns/order',
                    params: {
                        operacao: "SALVAR",
                        user: $scope.user,
                        carrinho: window.sessionStorage.getItem("carrinho"),
                        valorTotal: valorFinal,
                        store: window.sessionStorage.getItem("store"),
                        autorizacao: window.sessionStorage.getItem("autorizacao"),
                        endereco: $scope.endereco,
                        card: $scope.card
                    }
                }).then(function successCallback(response) {

                    window.sessionStorage.removeItem("store");
                    window.sessionStorage.removeItem("carrinho");
                    window.sessionStorage.removeItem("autorizacao");
                    window.sessionStorage.removeItem("valorTotal");
                    window.sessionStorage.setItem("order", JSON.stringify(response.data.entities[0]));
                    window.location.href = "http://localhost:8084/cpguns/pages/confirmacao.html";
                }, function errorCallback(response) {
                    // deu caquinha
                });

                // chamar o Java ($http)
            };
        })

        .controller("pedidoController", function ($scope, $http) {
            $scope.buscarPedidos = function () {
                $http({
                    method: 'GET',
                    url: '/cpguns/order',
                    params: {
                        operacao: "CONSULTAR"
                    }
                }).then(function successCallback(response) {
                    $scope.pedidos = response.data;
                }, function errorCallback(response) {
                    // deu caquinha
                });
            };

            $scope.getStatus = function (status) {
                if (status === "AGUARDANDO_APROVACAO") {
                    return "Aguardando Aprovação";
                } else if (status === "CANCELADO") {
                    return "Cancelado";
                } else if (status === "RETIRADO") {
                    return "Retirado";
                } else if (status === "EM_TRANSPORTE") {
                    return "Em transporte";
                } else if (status === "EM_NEGOCIACAO") {
                    return "Em negociação";
                } else if (status === "DEVOLVIDO") {
                    return "Devolvido";
                }
            };

            $scope.verDetalhes = function (pedido) {
                window.sessionStorage.setItem("pedido", JSON.stringify(pedido));
                window.location.href = "http://localhost:8084/cpguns/pages/pedido_especifico.html";
            };

            $scope.getPedido = function () {
                $scope.pedido = JSON.parse(window.sessionStorage.getItem("pedido"));
                $scope.valorCarrinho = 0;
                for (var i = 0; i < $scope.pedido.carrinho.products.length; i++) {
                    $scope.valorCarrinho += $scope.pedido.carrinho.products[i].price * $scope.pedido.carrinho.products[i].qtdeCarrinho;
                }
            };

            $scope.getMensagem = function (quantidade) {
                return quantidade > 1 ? quantidade + " itens" : quantidade + "item";
            };

            $scope.alterar = function (pedido) {
                var status;
                if (pedido.status === "AGUARDANDO_APROVACAO") {
                    status = "Em negociação";
                } else if (pedido.status === "RETIRADO") {
                    status = "Devolvido";
                } else if (pedido.status === "EM_TRANSPORTE") {
                    status = "Retirado";
                } else if (pedido.status === "EM_NEGOCIACAO") {
                    status = "Em transporte";
                }

                $http({
                    method: 'POST',
                    url: '/cpguns/order',
                    params: {
                        operacao: "ALTERAR",
                        id: pedido.id,
                        status: status
                    }
                }).then(function successCallback(response) {
                    $scope.buscarPedidos();
                }, function errorCallback(response) {
                    // deu caquinha
                });
            };

            $scope.cancelar = function (pedido) {
                var status = "Cancelado";
                $http({
                    method: 'POST',
                    url: '/cpguns/order',
                    params: {
                        operacao: "ALTERAR",
                        id: pedido.id,
                        status: status
                    }
                }).then(function successCallback(response) {
                    $scope.buscarPedidos();
                }, function errorCallback(response) {
                    // deu caquinha
                });
            };
        })
        
        .controller("productController", function($scope, $http){
            $scope.salvar = function () {
                $http({
                    method: 'POST',
                    url: '/cpguns/arms',
                    params: {
                        operacao: "SALVAR",
                        origem: $scope.produto.origem,
                        manufacturer: $scope.produto.manufacturer,
                        uri: $scope.produto.uri,
                        nome: $scope.produto.nome,
                        preco: $scope.produto.preco,
                        qtde: $scope.produto.qtde,
                        calibre: $scope.produto.calibre,
                        peso: $scope.produto.peso,
                        capacidade: $scope.produto.capacidade,
                        modelo: $scope.produto.modelo,
                        action: $scope.produto.action,
                        descricao: $scope.produto.descricao
                    }
                }).then(function successCallback(response) {
                    alert("Produto salvo com sucesso!");
                }, function errorCallback(response) {
                    alert("ERRO");
                });
            };                        
        })

        .controller("confirmacaoController", function ($scope) {
            $scope.order = JSON.parse(window.sessionStorage.getItem("order"));
        });
