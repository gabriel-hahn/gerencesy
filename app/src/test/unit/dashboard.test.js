describe('Testing AngularJS Test Suite - Dashboard', function () {

    beforeEach(module('Gerencesy.gerenciador.dashboard'));

    describe('Testing AngularJS Controller', function () {
        var scope, ctrl, httpBackend;
        var URL_BACK_DASHBOARD = 'http://localhost:8080/api/cartao/dashboard';

        beforeEach(inject(function ($controller, $rootScope, $httpBackend) {
            scope = $rootScope.$new();
            ctrl = $controller('DashboardCtrl', { $scope: scope });
            httpBackend = $httpBackend;
        }));

        afterEach(function () {
            httpBackend.verifyNoOutstandingRequest();
        });

        it('Should inicialize the registro', function () {
            /*httpBackend.flush();
            httpBackend.expectGET(URL_BACK_DASHBOARD).response({

            });*/
        });
    });
});