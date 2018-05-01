(function () {
    'use strict'

    angular.module('Gerencesy.gerenciador.cartoes')
        .factory('CartoesService', CartoesService);

    CartoesService.$inject = ['$http'];

    function CartoesService($http) {

        function findAll() {
            return $http.get('http://localhost:8080/api/cartao').then(function (response) {
                return response.data;
            });
        }

        return {
            findAll: findAll
        };
    }
})();