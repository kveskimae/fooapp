// Controller for presenting all  items of museum
(function () {
angular.module('ExamApp')
    .controller('PeopleCtrl', function ($rootScope, $scope, $http, $state, $routeParams, $stateParams, $controller, events, appData) {
        angular.extend(this, $controller('BaseCtrl', { $scope: $scope }));

        // Get all people from backend to present them
        $rootScope.$broadcast(events.message._FETCH_ALL_PEOPLE_, null);

        // Edit exisint person
        $scope.personToEdit = null;
        $scope.personToEditBirthTime = {
            value: null // Hack to get Angular 2-way binding working in version 1.3
        };
        $scope.editPerson = function (id) {
            $scope.personToEdit = id;
            $scope.personToEditBirthTime.value = $scope.appData.findPersonByID(id).birthTime;
        };
        $scope.saveEditedPerson = function (id) {
        	console.log("id=" + id);
            $scope.appData.findPersonByID(id).birthTime = $scope.personToEditBirthTime.value;
            // $scope.personToEdit.birthTime = $scope.personToEditBirthTime.value;
            $rootScope.$broadcast(events.message._SAVE_EDITED_PERSON_, [id]);
        };
        $rootScope.$on(events.message._SAVE_EDITED_PERSON_COMPLETE_, function (event, data) {
            $scope.personToEdit = null;
            $scope.resetMsg();
        });

        // Create a new person
        $scope.newPerson = {
            // identityCode: null,
            firstName: null,
            // lastName: null,
            active: true
        };
        $scope.saveNewPerson = function () {
            $rootScope.$broadcast(events.message._SAVE_PERSON_, [$scope.newPerson]);
        };
        $rootScope.$on(events.message._SAVE_PERSON_COMPLETE_, function (event, data) {
            $scope.newPerson = {
                identityCode: null,
                firstName: null,
                lastName: null,
                active: true
            };
        });

        // Search
        $scope.searchByFirstName = null;
        $scope.doSearchByFirstName = function () {
            $rootScope.$broadcast(events.message._SEARCH_PERSON_BY_FIRST_NAME_, [$scope.searchByFirstName]);
        };
        $rootScope.$on(events.message._SEARCH_PERSON_BY_FIRST_NAME_COMPLETE_, function (event, data) {
            $scope.resetMsg();
        });

        // Hide person
        $scope.hidePerson = function (id) {
            $rootScope.$broadcast(events.message._HIDE_PERSON_, [id]);
        };
        $rootScope.$on(events.message._HIDE_PERSON_COMPLETE_, function (event, data) {
            $scope.person = null;
        });

        // Delete person
        $scope.deletePerson = function (id) {
            $rootScope.$broadcast(events.message._DELETE_PERSON_, [id]);
        };
        $rootScope.$on(events.message._DELETE_PERSON_COMPLETE_, function (event, data) {
            $scope.personToEdit = null;
        });

        // Add test data - HACK TO BE DELETED LATER
        $scope.addTestData = function () {
            $http.get("rest/addtestdata").success(function (data, status, headers, config) {
                $scope.appData.msg = "Success: " + data;
                $rootScope.$broadcast(events.message._FETCH_ALL_PEOPLE_, null);
            }).error(function (data, status, headers, config) {
                $scope.appData.msg = "Adding test data failed (" + status + ")";
            });
        };

    });
})();