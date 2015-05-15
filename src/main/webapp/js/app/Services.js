(function () {
angular.module('ExamApp').factory('PersonResource', function ($resource) {
    return $resource('rest/users/:id', {}, {
        update: { method: 'PUT', params: { id: '@id' } }
    });
});

angular.module('ExamApp').factory('PersonService', function PersonServiceFactory($rootScope, PersonResource, events, Person, modelTransformer, appData) {
    return {
        fetchAllPeople: function () {
            PersonResource.query(
                {},
                function (data, status, headers, config) {
                    if (data.length < 1) {
                        $rootScope.$broadcast(events.message._FETCH_ALL_PEOPLE_FAILED_, ["No people were found"]);
                    } else {
                        var transformed = modelTransformer.transform(data, Person);
                        var log = [];
                        angular.forEach(transformed, function (value, key) {
                            if (!!value.birthTime) {
                                value.birthTime = new Date(value.birthTime);
                                delete value['$promise'];
                                delete value['$resolved'];
                            }
                        }, log);
                        appData.people = transformed;
                        $rootScope.$broadcast(events.message._FETCH_ALL_PEOPLE_COMPLETE_, []);
                    }
                },
            function (data) {
                $rootScope.$broadcast(events.message._FETCH_ALL_PEOPLE_FAILED_, [data.statusText]);
            });
        },
        savePerson: function (person) {
            PersonResource.save(
                person
                ,
                function (data, status, headers, config) {
                    if (data.id < 1) {
                        $rootScope.$broadcast(events.message._SAVE_PERSON_FAILED_, ["No id was returned"]);
                    } else {
                        var transformed = modelTransformer.transform(data, Person);
                        if (!!transformed.birthTime) {
                            transformed.birthTime = new Date(transformed.birthTime);
                        }
                        $rootScope.$broadcast(events.message._SAVE_PERSON_COMPLETE_, [transformed]);
                    }
                },
            function (data) {
                $rootScope.$broadcast(events.message._SAVE_PERSON_FAILED_, [data.statusText]);
            });
        },
        deletePerson: function (id) {
            PersonResource.delete(
                {
                    id: id
                }
                ,
                function (data, status, headers, config) {
                    $rootScope.$broadcast(events.message._DELETE_PERSON_COMPLETE_, [id]);
                },
            function (data) {
                $rootScope.$broadcast(events.message._DELETE_PERSON_FAILED_, [data.statusText]);
            });
        },
        saveEditedPerson: function (person) {
        	console.log("person id=" + person.id);
            PersonResource.update(
                person
                ,
                function (data, status, headers, config) {
                	console.log("-1-");
                    if (data.id < 1) {
                        $rootScope.$broadcast(events.message._SAVE_EDITED_PERSON_FAILED_, ["No id was returned"]);
                    } else {
                        var transformed = new Person();
                        transformed.active = data.active;
                        transformed.firstName = data.firstName;
                        transformed.lastName = data.lastName;
                        transformed.id = data.id;
                        transformed.birthTime = data.birthTime;
                        transformed.lastChangedTime = data.lastChangedTime;
                        $rootScope.$broadcast(events.message._SAVE_EDITED_PERSON_COMPLETE_, [transformed]);
                    }
                },
            function (data) {
                	console.log("-2-");
                $rootScope.$broadcast(events.message._SAVE_EDITED_PERSON_FAILED_, [data.statusText]);
            });
        },
        // Search
        performSearchByFirstName: function (firstName) {
            PersonResource.query(
                {
                    searchByFirstName: firstName
                }
                ,
                function (data, status, headers, config) {
                    if (data.length < 1) {
                        $rootScope.$broadcast(events.message._SEARCH_PERSON_BY_FIRST_NAME_FAILED_, ["No matches were found"]);
                    } else {
                        var transformed = modelTransformer.transform(data, Person);
                        var log = [];
                        angular.forEach(transformed, function (value, key) {
                            if (!!value.birthTime) {
                                value.birthTime = new Date(value.birthTime);
                            }
                        }, log);
                        appData.people = transformed;
                        $rootScope.$broadcast(events.message._SEARCH_PERSON_BY_FIRST_NAME_COMPLETE_, []);
                    }
                },
            function (data) {
                $rootScope.$broadcast(events.message._SEARCH_PERSON_BY_FIRST_NAME_FAILED_, [data.statusText]);
            });
        }
    };
});

})();