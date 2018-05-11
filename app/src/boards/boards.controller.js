(function () {

    'use strict'

    angular.module("Gerencesy.gerenciador.boards")
        .controller("BoardsCtrl", BoardsCtrl);

    BoardsCtrl.$inject = ['$scope', '$rootScope', 'BoardService', 'Notification'];

    function BoardsCtrl($scope, $rootScope, BoardService, Notification) {
        var vm = this;
        vm.idBoardAtivoAtual = null;

        vm.adicionarBoard = _adicionarBoard;
        vm.trocarBoardAtivo = _trocarBoardAtivo;

        _init();

        function _init() {
            BoardService.findAll().then(function (response) {
                vm.boards = response;
                angular.forEach(vm.boards, function (board) {
                    if (board.status == "S") {
                        vm.idBoardAtivoAtual = board.id;
                        board.checked = true;
                    }
                    else {
                        board.checked = false;
                    }
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

        //Troca o board ativo atual pelo que foi selecionado.
        function _trocarBoardAtivo(board) {
            if (board.id != vm.idBoardAtivoAtual) {
                BoardService.changeBoardActive(board).then(function (response) {
                    _init();
                    //Notification.success("Board ativo atualizado com sucesso!");
                });
            }
        }
    }

})();