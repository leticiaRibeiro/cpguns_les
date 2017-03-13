/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
angular.module('cpguns').controller('FormController', function($scope){
    $scope.clientes = [
        
            {"nome": "Leticia","telefone": "123456789"},
            {"nome": "Gustavo","telefone": "987654321"},
            {"nome": "Elza","telefone": "123123123"},
            {"nome": "Bene","telefone": "987987987"}
    ];   
});

