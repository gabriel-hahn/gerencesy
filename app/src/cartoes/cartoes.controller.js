(function () {

    'use strict'

    angular.module("Gerencesy.gerenciador.cartoes")
        .controller("CartoesCtrl", CartoesCtrl);

    CartoesCtrl.$inject = ['$scope', '$rootScope', 'CartoesService'];

    function CartoesCtrl($scope, $rootScope, CartoesService) {
        var vm = this;
        vm.dropCartao = _dropCartao;

        vm.listaDrag = [];

        //Estrutura inicial dos cartões.
        vm.cartoes = {
            selecionado: null,
            lista: [{
                descricao: "A fazer",
                listaAgrup: [],
                status: "A"
            },
            {
                descricao: "Em andamento",
                listaAgrup: [],
                status: "E"
            },
            {
                descricao: "Pausado",
                listaAgrup: [],
                status: "P"
            },
            {
                descricao: "Concluído",
                listaAgrup: [],
                status: "C"
            }]
        };

        //Recuperação dos cartões do servidor.
        _init();

        function _init() {
            CartoesService.findAll().then(function (response) {
                vm.listaCartoes = response;
                angular.forEach(vm.listaCartoes, function (card) {
                    switch (card.status) {
                        case "A":
                            vm.cartoes.lista[0].listaAgrup.push(card);
                            break;
                        case "E":
                            vm.cartoes.lista[1].listaAgrup.push(card);
                            break;
                        case "P":
                            vm.cartoes.lista[2].listaAgrup.push(card);
                            break;
                        case "C":
                            vm.cartoes.lista[3].listaAgrup.push(card);
                            break;
                    }
                });
            });
        }

        function _dropCartao(lista, index) {
            lista.splice(index, 1);
            var cartao = vm.listaDrag[0];
        }
    }

})();