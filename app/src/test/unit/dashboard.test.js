describe('Testing AngularJS Test Suite', function () {

    beforeEach(module('Gerencesy.gerenciador.dashboard'));

    describe('Testing AngularJS Controller', function () {
        var scope, ctrl;

        beforeEach(inject(function ($controller, $rootScope) {
            scope = $rootScope.$new();
            ctrl = $controller('DashboardCtrl', { $scope: scope });
        }));

        afterEach(function () {
            //Cleanup code
        });

        it('Should inicialize the registro', function () {
            expect(scope.registro).toBeDefined();
            expect(scope.title).toBe();
        });

        /*it('Should add a card', function () {
            expect($scope.cartoes).toBeDefined();
            expect($scope.cartoes.lista.length).toBe(4);
        });*/
    });
});