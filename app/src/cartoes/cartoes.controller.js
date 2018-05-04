(function () {

    'use strict'

    angular.module("Gerencesy.gerenciador.cartoes")
        .controller("CartoesCtrl", CartoesCtrl);

    CartoesCtrl.$inject = ['$scope', '$rootScope', 'CartoesService'];

    function CartoesCtrl($scope, $rootScope, CartoesService) {
        var vm = this;
        vm.retornaCartaoDiferente = _retornaCartaoDiferente;

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

        //Watch dos agrupamentos
        //A fazer
        $scope.$watchCollection('vm.cartoes.lista[0].listaAgrup', function (newVal, oldVal) {
            var cartao = _retornaCartaoDiferente(newVal, oldVal);
            if (cartao) {
                cartao.status = "A";
                CartoesService.put(cartao);
            }
        });

        //Em andamento
        $scope.$watchCollection('vm.cartoes.lista[1].listaAgrup', function (newVal, oldVal) {
            var cartao = _retornaCartaoDiferente(newVal, oldVal);
            if (cartao) {
                cartao.status = "E";
                CartoesService.put(cartao);
            }
        });

        //Pausado
        $scope.$watchCollection('vm.cartoes.lista[2].listaAgrup', function (newVal, oldVal) {
            var cartao = _retornaCartaoDiferente(newVal, oldVal);
            if (cartao) {
                cartao.status = "P";
                CartoesService.put(cartao);
            }
        });

        //Conluído
        $scope.$watchCollection('vm.cartoes.lista[3].listaAgrup', function (newVal, oldVal) {
            var cartao = _retornaCartaoDiferente(newVal, oldVal);
            if (cartao) {
                cartao.status = "C";
                CartoesService.put(cartao);
            }
        });

        //Retorna o cartao que precisa ter seu status atualizado
        function _retornaCartaoDiferente(newVal, oldVal) {
            if (newVal.length > 0 || oldVal.length > 0) {
                
                //Verifica a alteração da posição do cartão
                if (newVal.length > oldVal.length) {
                    var retorno = undefined;
                    angular.forEach(newVal, function (cartao) {
                        if (oldVal.indexOf(cartao) == -1) {
                            retorno = cartao;
                        }
                    });

                    return retorno;
                }
            }
        }

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
    }

})();