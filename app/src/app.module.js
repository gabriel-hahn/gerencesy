(function () {
    'use strict';

    angular.module('Gerencesy.gerenciador', [
        'dndLists',
        'auth0.auth0',
        'ui-notification',
        'Gerencesy.gerenciador.cartoes',
        'Gerencesy.gerenciador.boards',
        'Gerencesy.gerenciador.dashboard'
    ])

        .config(function ($routeProvider, angularAuth0Provider, $locationProvider) {
            $routeProvider
                .when("/dashboard", {
                    templateUrl: './src/dashboard/dashboard.html',
                    controller: 'DashboardCtrl',
                    controllerAs: 'vm'
                })
                .when("/cartoes", {
                    templateUrl: './src/cartoes/cartoes.html',
                    controller: 'CartoesCtrl',
                    controllerAs: 'vm'
                })
                .when("/boards", {
                    templateUrl: './src/boards/boards.html',
                    controller: 'BoardsCtrl',
                    controllerAs: 'vm'
                })

                .otherwise({ redirectTo: '/dashboard' });
        });
})();
