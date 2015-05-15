// Base controller other controllers inherit from. Provides shared functionality for other controllers.
(function () {
angular.module('ExamApp')
    .controller('BaseCtrl', function ($scope, $http, $state, appData) {
        $scope.appData = appData;
        $scope.resetMsg = function () {
            $scope.appData.msg = "";
        };
        $scope.resetMsg();
    });
})();