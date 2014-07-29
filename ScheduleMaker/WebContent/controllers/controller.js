/*
 * The base file for the angular js application
 * TODO: Add routing for multiple pages
 */

(function () {
    var app = angular.module('scheduleMaker');

    var departmentURL = 'http://localhost:8080/ScheduleMaker/rest/departments/';
    app.factory('departmentFactory', ['$resource',
        function ($resource) {
            console.log($resource(departmentURL, {}, {
                query: { method: 'GET', isArray: true }
            }));
            return $resource(departmentURL, {});
        }
    ]);

    app.controller('Controller', ['$scope', 'departmentFactory',
        function ($scope, departmentFactory) {

        $scope.departments = departmentFactory.query();

    }]);
})();