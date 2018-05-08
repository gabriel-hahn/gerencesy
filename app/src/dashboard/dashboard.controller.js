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
            DashboardService.findDashboard().then(function(response) {
                var totalCartoes = response["A"] + response["E"] + response["P"] + response["C"];

                vm.registro.fazer = response["A"] / totalCartoes * 100;
                vm.registro.andamento = response["E"] / totalCartoes * 100;
                vm.registro.pausadas = response["P"] / totalCartoes * 100;
                vm.registro.concluidas = response["C"] / totalCartoes * 100;
            });
        }
    }

})();