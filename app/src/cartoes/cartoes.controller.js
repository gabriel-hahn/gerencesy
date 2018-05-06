(function () {

    'use strict'

    angular.module("Gerencesy.gerenciador.cartoes")
        .controller("CartoesCtrl", CartoesCtrl);

    CartoesCtrl.$inject = ['$scope', '$rootScope', 'CartoesService'];

    function CartoesCtrl($scope, $rootScope, CartoesService) {
        var vm = this;
        vm.retornaCartaoDiferente = _retornaCartaoDiferente;
        vm.adicionarCartao = _adicionarCartao;
        vm.guardarStatusLista = _guardarStatusLista;
        vm.excluirCartao = _excluirCartao;

        vm.listaDrag = [];
        vm.diaAtual = null;

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
                cartao.id ? CartoesService.put(cartao) : CartoesService.post(cartao);
            }
        });

        //Em andamento
        $scope.$watchCollection('vm.cartoes.lista[1].listaAgrup', function (newVal, oldVal) {
            var cartao = _retornaCartaoDiferente(newVal, oldVal);
            if (cartao) {
                cartao.status = "E";
                cartao.id ? CartoesService.put(cartao) : CartoesService.post(cartao);
            }
        });

        //Pausado
        $scope.$watchCollection('vm.cartoes.lista[2].listaAgrup', function (newVal, oldVal) {
            var cartao = _retornaCartaoDiferente(newVal, oldVal);
            if (cartao) {
                cartao.status = "P";
                cartao.id ? CartoesService.put(cartao) : CartoesService.post(cartao);
            }
        });

        //Conluído
        $scope.$watchCollection('vm.cartoes.lista[3].listaAgrup', function (newVal, oldVal) {
            var cartao = _retornaCartaoDiferente(newVal, oldVal);
            if (cartao) {
                cartao.status = "C";
                cartao.id ? CartoesService.put(cartao) : CartoesService.post(cartao);
            }
        });

        function _init() {
            CartoesService.findAll().then(function (response) {
                vm.listaCartoes = response;
                angular.forEach(vm.listaCartoes, function (card) {
                    //Pega o dia da listagem atual
                    if (card.idDia) vm.diaAtual = card.idDia;
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

        function _adicionarCartao() {
            var cartao = {
                nome: vm.cartaoAdd,
                status: vm.statusListaAdd,
                idDia: vm.diaAtual
            }

            vm.cartaoAdd = null;

            angular.forEach(vm.cartoes.lista, function (cartoes) {
                if (cartoes.status == vm.statusListaAdd) {
                    cartoes.listaAgrup.push(cartao);
                }
            });
        }

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

        function _guardarStatusLista(status) {
            vm.statusListaAdd = status;
        }

        //Excluir cartao selecionado
        function _excluirCartao() {
            if (vm.cartoes.selecionado) {
                CartoesService.deleteCard(vm.cartoes.selecionado.id).then(function (response) {
                    angular.forEach(vm.cartoes.lista, function (cartoes) {
                        if (cartoes.status == vm.cartoes.selecionado.status) {
                            var cont = 0;
                            var listAux = angular.copy(cartoes.listaAgrup);
                            angular.forEach(listAux, function (cd) {
                                if (cd.id == vm.cartoes.selecionado.id) {
                                    cartoes.listaAgrup.splice(cont, 1);
                                }
                                cont++;
                            });
                        }
                    });
                });
            }
        }
    }

})();