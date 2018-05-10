(function () {
    'use strict'

    angular.module('Gerencesy.gerenciador.boards')
        .factory('BoardService', BoardService);

    BoardService.$inject = ['$http'];

    function BoardService($http) {

        function findBoards() {
            return $http.get('http://localhost:8080/api/board/').then(function (response) {
                return response.data;
            });
        }

        return {
            findBoards: findBoards
        };
    }
})();