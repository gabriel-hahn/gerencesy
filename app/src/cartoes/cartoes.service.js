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

        function getByBoardActive() {
            return $http.get(URL_LOCAL + '/getByBoardActive').then(function (response) {
                return response.data;
            });
        }

        function post(cartao) {
            return $http.post(URL_LOCAL, cartao).then(function (response) {
                return response.data;
            });
        }

        function deleteCard(id) {
            return $http.delete(URL_LOCAL + '/' + id).then(function (response) {
                return response.data;
            });
        }

        function put(cartao) {
            return $http.put(URL_LOCAL + '/' + cartao.id, cartao).then(function (response) {
                return response.data;
            });
        }

        return {
            findAll: findAll,
            post: post,
            deleteCard: deleteCard,
            put: put,
            getByBoardActive: getByBoardActive
        };
    }
})();