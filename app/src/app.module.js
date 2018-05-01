(function () {
    'use strict';

    angular.module('Gerencesy.gerenciador', [
        'dndLists',
        'Gerencesy.gerenciador.cartoes',
        'Gerencesy.gerenciador.dashboard'
    ])

        .config(function ($routeProvider) {
            $routeProvider
                .when("/", {
                    templateUrl: './src/dashboard/dashboard.html',
                    controller: 'DashboardCtrl',
                    controllerAs: 'vm'
                })
                .when("/cartoes", {
                    templateUrl: './src/cartoes/cartoes.html',
                    controller: 'CartoesCtrl',
                    controllerAs: 'vm'
                })
                .otherwise({ redirectTo: '/' });
        });
})();
