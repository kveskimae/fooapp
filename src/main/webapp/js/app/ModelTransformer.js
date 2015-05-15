// Service to tranform data received from backend in JSON notation into model object or array of model objects.
(function () {
angular.module('ExamApp').factory('modelTransformer', function modelTransformerFactory() {

    var transformObject = function (jsonResult, constructor) {
        var model = new constructor();
        angular.extend(model, jsonResult);
        return model;
    };

    // If provided JSON array, constructs array of model objects
    // 1st parameter is JSON and second is the constructor function for model.
    var transformResult = function (jsonResult, constructor) {
        if (angular.isArray(jsonResult)) {
            var models = [];
            angular.forEach(jsonResult, function (object) {
                models.push(transformObject(object, constructor));
            });
            return models;
        } else {
            return transformObject(jsonResult, constructor);
        }
    };

    var service = {
        transform: transformResult
    };

    return service;
});
})();