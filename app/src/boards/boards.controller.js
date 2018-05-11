(function () {

    'use strict'

    angular.module("Gerencesy.gerenciador.boards")
        .controller("BoardsCtrl", BoardsCtrl);

    BoardsCtrl.$inject = ['$scope', '$rootScope', 'BoardService', 'Notification'];

    function BoardsCtrl($scope, $rootScope, BoardService, Notification) {
        var vm = this;

        vm.adicionarBoard = _adicionarBoard;

        _init();

        function _init() {
            BoardService.findAll().then(function (response) {
                vm.boards = response;
                angular.forEach(vm.boards, function (board) {
                    board.checked = board.status == "S" ? true : false;
                })
            });
        }

        function _adicionarBoard() {
            var board = {
                nome: vm.boardAdd,
                status: "N"
            }

            BoardService.post(board).then(function (response) {
                vm.boardAdd = null;
            });
        }

    }

})();