(function () {

    'use strict'

    angular.module("Gerencesy.gerenciador.boards")
        .controller("BoardsCtrl", BoardsCtrl);

    BoardsCtrl.$inject = ['$scope', '$rootScope', 'BoardService', 'Notification'];

    function BoardsCtrl($scope, $rootScope, BoardService, Notification) {
        var vm = this;

        _init();

        function _init() {

        }

    }

})();