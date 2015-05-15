(function () {
angular.module('ExamApp', ['ngResource', 'ui.router', 'ngRoute'])
    .config(function ($stateProvider, $urlRouterProvider, $routeProvider) {
        // Define states
        $stateProvider
            .state('people', {
                templateUrl: 'js/app/templates/people.html',
                controller: 'PeopleCtrl',
                url: '/people'
            })
        ;
    })
    .run(function ($rootScope, $state, events, appData, PersonService) {
        // Get all people
        $rootScope.$on(events.message._FETCH_ALL_PEOPLE_, function (event, data) {
            PersonService.fetchAllPeople();
        });
        $rootScope.$on(events.message._FETCH_ALL_PEOPLE_COMPLETE_, function (event, data) {
        });
        $rootScope.$on(events.message._FETCH_ALL_PEOPLE_FAILED_, function (event, data) {
            appData.msg = "Fetching people failed: " + data[0];
        });
        // Save a person
        $rootScope.$on(events.message._SAVE_PERSON_, function (event, data) {
            PersonService.savePerson(data[0]);
        });
        $rootScope.$on(events.message._SAVE_PERSON_COMPLETE_, function (event, data) {
            appData.people.push(data[0]);
        });
        $rootScope.$on(events.message._SAVE_PERSON_FAILED_, function (event, data) {
            appData.msg = "Saving person failed: " + data[0];
        });
        // Edit existing person
        $rootScope.$on(events.message._SAVE_EDITED_PERSON_, function (event, data) {
        	console.log("[0]=" + data[0]);
            var editedPerson = appData.findPersonByID(data[0]);
            console.log("editedPerson=");
            console.log(editedPerson);
            PersonService.saveEditedPerson(editedPerson);
        });
        $rootScope.$on(events.message._SAVE_EDITED_PERSON_COMPLETE_, function (event, data) {
            appData.changeEditedPerson(data[0]);
        });
        $rootScope.$on(events.message._SAVE_EDITED_PERSON_FAILED_, function (event, data) {
            appData.msg = "Saving edited person failed: " + data[0];
        });
        // Logically hide a person
        $rootScope.$on(events.message._HIDE_PERSON_, function (event, data) {
            var person = appData.findPersonByID(data[0]);
            person.active = false;
            PersonService.saveEditedPerson(person);
        });
        $rootScope.$on(events.message._HIDE_PERSON_COMPLETE_, function (event, data) {
        });
        $rootScope.$on(events.message._HIDE_PERSON_FAILED_, function (event, data) {
            appData.msg = "Hiding person failed: " + data[0];
        });
        // Delete person
        $rootScope.$on(events.message._DELETE_PERSON_, function (event, data) {
            PersonService.deletePerson(data[0]);
        });
        $rootScope.$on(events.message._DELETE_PERSON_COMPLETE_, function (event, data) {
            appData.removePerson(data[0]);
        });
        $rootScope.$on(events.message._DELETE_PERSON_FAILED_, function (event, data) {
            appData.msg = "Deleting person failed: " + data[0];
        });
        // Search person 
        $rootScope.$on(events.message._SEARCH_PERSON_BY_FIRST_NAME_, function (event, data) {
            PersonService.performSearchByFirstName(data[0]);
        });
        $rootScope.$on(events.message._SEARCH_PERSON_BY_FIRST_NAME_COMPLETE_, function (event, data) {
        });
        $rootScope.$on(events.message._SEARCH_PERSON_BY_FIRST_NAME_FAILED_, function (event, data) {
            appData.msg = "Searching person failed: " + data[0];
        });
        $state.go('people');
    });
})();
