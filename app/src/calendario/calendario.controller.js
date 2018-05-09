(function () {

    'use strict'

    angular.module("Gerencesy.gerenciador.calendario")
        .controller("CalendarioCtrl", CalendarioCtrl);

    CalendarioCtrl.$inject = ['$scope', '$rootScope', 'CartoesService', 'Notification'];

    function CalendarioCtrl($scope, $rootScope, CartoesService, Notification) {
        var vm = this;

    }

})();