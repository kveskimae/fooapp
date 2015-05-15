// Model for the whole application data, .
(function () {
var AppModel = function () {
    var self = this;
    self.msg = "";
    self.people = [];
};

// Enrich with methods.
AppModel.prototype = {
    findPersonByID: function (id) {
        var log = [];
        angular.forEach(this.people, function (value) {
            if (value.id == id) {
                this.push(value);
            }
        }, log);
        return log[0];
    },
    changeEditedPerson: function (person) {
        var log = [];
        var idx = null;
        angular.forEach(this.people, function (value, key) {
            if (value.id == person.id) {
                idx = key;
            }
        }, log);
        this.people[idx] = person;
    },
    removePerson: function (id) {
        var log = [];
        var idx = null;
        angular.forEach(this.people, function (value, key) {
            if (value.id == id) {
                idx = key;
            }
        }, log);
        this.people.splice(idx, 1);
    }
};

// Singleton instance created once and only one instance per running in client browser program.
// Shares data between different controllers and caches data.
var appDataInstance = new AppModel();

angular.module('ExamApp')
    .value('appData', appDataInstance);

angular.module('ExamApp').factory('Person', function () {
    var Person = function () {
        var self = this;
        self.id = null;
        self.firstName = null;
        self.lastName = null;
        self.birthTime = null;
        self.lastChangedTime = null;
        self.active = null;
    };
    Person.prototype = {
        // Add any convience methods here
    };
    return Person;
});
})();