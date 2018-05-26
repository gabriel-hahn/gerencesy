(function () {
    'use strict'

    angular.module('Gerencesy.gerenciador.boards')
        .factory('BoardService', BoardService);

    BoardService.$inject = ['$http'];

    function BoardService($http) {

        const URL_LOCAL = 'http://localhost:8080/api/board';

        function findAll() {
            return $http.get(URL_LOCAL).then(function (response) {
                return response.data;
            });
        }

        function getIdBoardCheck() {
            return $http.get(URL_LOCAL + '/getIdBoardCheck/').then(function (response) {
                return response.data;
            });
        }

        function changeBoardActive(board) {
            return $http.put(URL_LOCAL + '/changeBoardActive/' + board.id).then(function (response) {
                return response.data;
            });
        }

        function post(board) {
            return $http.post(URL_LOCAL, board).then(function (response) {
                return response.data;
            });
        }

        function deleteBoard(id) {
            return $http.delete(URL_LOCAL + '/' + id).then(function (response) {
                return response.data;
            });
        }

        function put(board) {
            return $http.put(URL_LOCAL + '/' + board.id, board).then(function (response) {
                return response.data;
            });
        }

        return {
            findAll: findAll,
            post: post,
            deleteBoard: deleteBoard,
            put: put,
            getIdBoardCheck: getIdBoardCheck,
            changeBoardActive: changeBoardActive
        };
    }
})();