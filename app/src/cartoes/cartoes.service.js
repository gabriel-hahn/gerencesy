(function () {
    'use strict'

    angular.module('Gerencesy.gerenciador.cartoes')
        .factory('CartoesService', CartoesService);

    CartoesService.$inject = ['$http'];

    function CartoesService($http) {

        const URL_LOCAL = 'http://localhost:8080/api/cartao';

        function findAll() {
            return $http.get(URL_LOCAL).then(function (response) {
                return response.data;
            });
        }

        function put(cartao) {
            return $http.put(URL_LOCAL, cartao);
        }

        function options() {
            return $http.options(URL_LOCAL);
        }

        return {
            findAll: findAll,
            put: put,
            options: options
        };
    }
})();