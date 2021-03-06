﻿// Message types that can be changed with observer pattern through Angular $rootScope mediary.
(function () {
angular.module('ExamApp').constant('events', {
    message: {

        _FETCH_ALL_PEOPLE_: '_FETCH_ALL_PEOPLE_',
        _FETCH_ALL_PEOPLE_COMPLETE_: '_FETCH_ALL_PEOPLE_COMPLETE_',
        _FETCH_ALL_PEOPLE_FAILED_: '_FETCH_ALL_PEOPLE_FAILED_',

        _SAVE_PERSON_: '_SAVE_PERSON_',
        _SAVE_PERSON_COMPLETE_: '_SAVE_PERSON_COMPLETE_',
        _SAVE_PERSON_FAILED_: '_SAVE_PERSON_FAILED_',

        _SAVE_EDITED_PERSON_: '_SAVE_EDITED_PERSON_',
        _SAVE_EDITED_PERSON_COMPLETE_: '_SAVE_EDITED_PERSON_COMPLETE_',
        _SAVE_EDITED_PERSON_FAILED_: '_SAVE_EDITED_PERSON_FAILED_',

        _HIDE_PERSON_: '_HIDE_PERSON_',
        _HIDE_PERSON_COMPLETE_: '_HIDE_PERSON_COMPLETE_',
        _HIDE_PERSON_FAILED_: '_HIDE_PERSON_FAILED_',

        _DELETE_PERSON_: '_DELETE_PERSON_',
        _DELETE_PERSON_COMPLETE_: '_DELETE_PERSON_COMPLETE_',
        _DELETE_PERSON_FAILED_: '_DELETE_PERSON_FAILED_',

        _SEARCH_PERSON_BY_FIRST_NAME_: '_SEARCH_PERSON_BY_FIRST_NAME_',
        _SEARCH_PERSON_BY_FIRST_NAME_COMPLETE_: '_SEARCH_PERSON_BY_FIRST_NAME_COMPLETE_',
        _SEARCH_PERSON_BY_FIRST_NAME_FAILED_: '_SEARCH_PERSON_BY_FIRST_NAME_FAILED_',

    }

});

})();