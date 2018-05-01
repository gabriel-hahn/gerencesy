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
                vm.registro.fazer = response["A"];
                vm.registro.andamento = response["E"];
                vm.registro.pausadas = response["P"];
                vm.registro.concluidas = response["C"];
            });
        }
    }

})();