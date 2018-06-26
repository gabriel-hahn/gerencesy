describe('Testing AngularJS Test Suite - Boards', function () {

    beforeEach(module('Gerencesy.gerenciador.boards'));

    describe('Testing AngularJS Controller', function () {
        var scope, ctrl, httpBackend;
        var URL_BACK_BOARD = 'http://localhost:8080/api/board';

        beforeEach(inject(function ($controller, $rootScope, $httpBackend) {
            scope = $rootScope.$new();
            ctrl = $controller('BoardsCtrl', { $scope: scope });
            httpBackend = $httpBackend;
        }));

        afterEach(function () {
            httpBackend.verifyNoOutstandingRequest();
        });

        /*it('Should inicialize the registro', function () {

        });*/
    });
});