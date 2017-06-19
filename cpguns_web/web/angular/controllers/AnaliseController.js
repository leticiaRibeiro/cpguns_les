angular.module("analise", ['chart.js', 'minhasDiretivas']).controller("AnaliseController", function ($scope, $http) {
    var label = new Array();
    var produtos = new Array();
    var produtosNome = new Array();
    var valores = new Array();
    var valoresFinal = new Array();
    var seriesEstado = new Array();
    var dataEstado = new Array();
    var labelVendas = new Array();
    var seriesVendas = new Array();
    var dadosVendas = new Array();
    $scope.mostraAcessos = false;
    $scope.mostraVendas = false;
    $scope.mostraEstados = false;

    $scope.gerar = function () {
        var label = new Array();
        var produtos = new Array();
        var produtosNome = new Array();
        var valores = new Array();
        var valoresFinal = new Array();
        var seriesEstado = new Array();
        var dataEstado = new Array();
        var labelVendas = new Array();
        var seriesVendas = new Array();
        var dadosVendas = new Array();
        $scope.mostraAcessos = false;
        $scope.mostraVendas = false;
        $scope.mostraEstados = false;
        $http({
            method: "GET",
            url: '/cpguns/analise',
            params: {
                tipo: $scope.analise.tipo,
                operacao: "CONSULTAR",
                inicio: $scope.analise.inicio,
                fim: $scope.analise.fim
            }
        }).then(function successCallback(response) {
            if ($scope.analise.tipo === "acessos") {
                $scope.mostraAcessos = true;
                var valores = response.data[0].acessos;
                // Para saber todos os produtos e dias que vamos mostrar
                for (var i = 0; i < valores.length; i++) {
                    label.push(valores[i].dtAcesso);
                    for (var j = 0; j < valores[i].acessos.length; j++) {
                        if (produtosNome.indexOf(valores[i].acessos[j].product.name) < 0) {
                            produtosNome.push(valores[i].acessos[j].product.name);
                            produtos.push(valores[i].acessos[j].product);
                        }
                    }
                }

                // colocar o vetor de produtos em ordem crescente
                var troca = true;
                var aux;
                var auxNome;
                while (troca) {
                    troca = false;
                    for (var i = 0; i < produtos.length - 1; i++) {
                        if (produtos[i].id > produtos[i + 1].id) {
                            troca = true;
                            aux = produtos[i];
                            produtos[i] = produtos[i + 1];
                            produtos[i + 1] = aux;

                            auxNome = produtosNome[i];
                            produtosNome[i] = produtosNome[i + 1];
                            produtosNome[i] = auxNome;
                            break;
                        }
                    }
                }

                // popular os valores de cada produto
                var achei = false;
                for (var i = 0; i < produtos.length; i++) {
                    var valorAux = new Array();
                    for (var j = 0; j < valores.length; j++) {
                        achei = false;
                        for (var k = 0; k < valores[j].acessos.length; k++) {
                            if (valores[j].acessos[k].product.id === produtos[i].id) {
                                valorAux.push(valores[j].acessos[k].numeroAcessos);
                                achei = true;
                                break;
                            }
                        }
                        if (!achei) {
                            valorAux.push(0);
                        }
                    }
                    valoresFinal.push(valorAux);
                }
            } else if ($scope.analise.tipo === "vendas") {
                $scope.mostraVendas = true;
                var resultado = response.data[0].vendas;
                var policiais = new Array();
                var civis = new Array();
                seriesVendas.push("POLICIAL");
                seriesVendas.push("CIVIL");
                for (var i = 0; i < resultado.length; i++) {
                    labelVendas.push(resultado[i].dtVenda);
                    policiais.push(resultado[i].policial);
                    civis.push(resultado[i].civil);
                }
                dadosVendas.push(policiais);
                dadosVendas.push(civis);
            } else if ($scope.analise.tipo === "estados") {
                $scope.mostraEstados = true;
                var resultado = response.data[0].estados;

                for (var i = 0; i < resultado.length; i++) {
                    seriesEstado.push(resultado[i].estado);
                    dataEstado.push(resultado[i].quantidade);
                }
            }
            $scope.labels_acessos = label;
            $scope.series_acessos = produtosNome;
            $scope.data_acessos = valoresFinal;

            $scope.labels_estados = seriesEstado;
            $scope.series_estados = seriesEstado;
            $scope.data_estados = dataEstado;

            $scope.labels_vendas = labelVendas;
            $scope.series_vendas = seriesVendas;
            $scope.data_vendas = dadosVendas;

            $scope.labels = ["January", "February", "March", "April", "May", "June", "July"];
            $scope.series = ['Series A', 'Series B'];
            $scope.data = [
                [65, 59, 80, 81, 56, 55, 40],
                [28, 48, 40, 19, 86, 27, 90]
            ];
            $scope.onClick = function (points, evt) {
                console.log(points, evt);
            };
            $scope.datasetOverride = [{yAxisID: 'y-axis-1'}, {yAxisID: 'y-axis-2'}];
            $scope.options = {
                scales: {
                    yAxes: [
                        {
                            id: 'y-axis-1',
                            type: 'linear',
                            display: true,
                            position: 'left'
                        },
                        {
                            id: 'y-axis-2',
                            type: 'linear',
                            display: true,
                            position: 'right'
                        }
                    ]
                }
            };
        }, function errorCallback(response) {
            alert("Erro");
        });
    };
});