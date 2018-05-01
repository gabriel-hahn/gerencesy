(function () {
    'use strict'

    angular.module('Gerencesy.gerenciador.dashboard')
        .factory('DashboardService', DashboardService);

    DashboardService.$inject = ['$http'];

    function DashboardService($http) {

        function findDashboard() {
            return $http.get('http://localhost:8080/api/cartao/dashboard').then(function (response) {
                return response.data;
            });
        }

        return {
            findDashboard: findDashboard
        };
    }
})();