(function () {
    var app = angular.module('scheduleMaker');

    app.controller('Controller', ['$scope', 'departmentFactory',
        function ($scope, departmentFactory) {
    	
	    	$scope.currentSubjectCode = '';
	    	$scope.templateURL = 'html/departments.html';
	    	getDepartments();
	    	
	    	function getDepartments() {
	    		departmentFactory.getDepartments()
	    		.success(function(departments) {
	    			$scope.departments=departments;
	    		})
	    		.error(function(error) {
	    			$scope.status = "error";
	    			alert("Failure");
	    		});
	    	}
	    	
	    	function getCourses(Subject_Code) {
	    		departmentFactory.getCourses(Subject_Code)
	    		.success(function(courses) {
	    			$scope.courses=courses;
	    		})
	    		.error(function(error) {
	    			$scope.status = "error";
	    			alert("Failure");
	    		});
	    	}
	    	
	        $scope.selectDepartment = function(Subject_Code) {
	        	$scope.templateURL = "html/courses.html"
	        	$scope.currentSubjectCode = Subject_Code;
	        	getCourses(Subject_Code);
	        }
	        
	        $scope.showDepartments = function() {
	        	$scope.templateURL = "html/departments.html"
	        }
        
    }]);
    
})();