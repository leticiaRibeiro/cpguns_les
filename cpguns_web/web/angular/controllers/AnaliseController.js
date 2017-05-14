angular.module("analise", ['chart.js', 'minhasDiretivas']).controller("AnaliseController", function ($scope, $http) {
    $http({
        method: 'GET',
        url: '/cpguns/analise',
        params: {
            tipo: "acessos",
            operacao: "CONSULTAR"
        }
    }).then(function successCallback(response) {
        var valores = response.data[0].acessos;
        var label = new Array();
        for (var i = 0; i < valores.length; i++) {
            label.push(valores[i].dtAcesso);
        }
        console.log("eba");
    }, function errorCallback(response) {
        // called asynchronously if an error occurs
        // or server returns response with an error status.
    });

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
});