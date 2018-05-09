(function () {
    'use strict';

    angular.module('Gerencesy.gerenciador', [
        'dndLists',
        'ui-notification',
        'Gerencesy.gerenciador.cartoes',
        'Gerencesy.gerenciador.calendario',
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
                .when("/calendario", {
                    templateUrl: './src/calendario/calendario.html',
                    controller: 'CalendarioCtrl',
                    controllerAs: 'vm'
                })
                .otherwise({ redirectTo: '/' });
        });
})();
