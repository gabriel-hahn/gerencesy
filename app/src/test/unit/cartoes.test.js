describe('Testing AngularJS Test Suite - Cartões', function () {

    beforeEach(module('Gerencesy.gerenciador.cartoes'));

    describe('Testing AngularJS Controller', function () {
        var scope, ctrl, httpBackend;
        var URL_BACK_CARTOES = 'http://localhost:8080/api/cartao';

        beforeEach(inject(function ($controller, $rootScope, $httpBackend) {
            scope = $rootScope.$new();
            ctrl = $controller('CartoesCtrl', { $scope: scope });
            httpBackend = $httpBackend;
        }));

        afterEach(function () {
            httpBackend.verifyNoOutstandingRequest();
        });

        /*it('Should inicialize the registro', function () {
            httpBackend.flush();
            httpBackend.expectGET(URL_BACK_DASHBOARD).response({

            });
        });*/

        /*it('Should add a card', function () {
            expect($scope.cartoes).toBeDefined();
            expect($scope.cartoes.lista.length).toBe(4);
        });*/
    });
});

//expect(scope.registro).toBeDefined();
//expect(scope.title).toBe();