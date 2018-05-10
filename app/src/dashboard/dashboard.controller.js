(function () {

    'use strict'

    angular.module("Gerencesy.gerenciador.dashboard")
        .controller("DashboardCtrl", DashboardCtrl);

    DashboardCtrl.$inject = ['$scope', '$rootScope', 'DashboardService'];

    function DashboardCtrl($scope, $rootScope, DashboardService) {
        var vm = this;
        vm.registro = {}

        _init();

        //Busca os valores para preenchimento do Dashboard.
        function _init() {
            DashboardService.findDashboard().then(function (response) {

                //Soma a quantidade de cartões existentes para todos os boards.
                var totalCartoes = response["A"] + response["E"] + response["P"] + response["C"];

                if (totalCartoes > 0) {
                    vm.registro.fazer = response["A"] / totalCartoes * 100;
                    vm.registro.andamento = response["E"] / totalCartoes * 100;
                    vm.registro.pausadas = response["P"] / totalCartoes * 100;
                    vm.registro.concluidas = response["C"] / totalCartoes * 100;
                }
                else {
                    vm.registro.fazer = 0;
                    vm.registro.andamento = 0;
                    vm.registro.pausadas = 0;
                    vm.registro.concluidas = 0;
                }
            });
        }
    }

})();