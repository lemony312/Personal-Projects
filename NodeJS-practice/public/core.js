var app = angular.module('scotchTodo', []);

app.controller("myCtrl", function($scope, $http) {
    $scope.formData = {};

    //when landing on the page, get all todos and show them
    $http.get('/api/todos')
        .success(function(data) {
            $scope.todos = data;
            console.log(data);
        })
        .error(function(data) {
            console.log('Error: ' + data);
        });

    $http.get('/api/ranks')
        .success(function(data) {
            $scope.items = data;
            console.log(data);
            $scope.createRank();
            // $scope.createItem();
        })
        .error(function(data) {
            console.log('Error: ' + data);
        });

    // when submitting the add form, send the text to the node API
    $scope.createTodo = function() {
        $http.post('/api/todos', $scope.formData)
            .success(function(data) {
                $scope.formData = {}; // clear the form so our user is ready to enter another
                $scope.todos = data;
                // console.log(data);
            })
            .error(function(data) {
                console.log('Error: ' + data);
            });
    };

    // delete a todo after checking it
    $scope.deleteTodo = function(id) {
        $http.delete('/api/todos/' + id)
            .success(function(data) {
                $scope.todos = data;
                // console.log(data);
            })
            .error(function(data) {
                console.log('Error: ' + data);
            });
    };

    // when submitting the add form, send the text to the node API
    $scope.createRank = function() {
        var obj = {
            ranking: 5,
            todoId: $scope.todos[0]._id,
            itemId: '5931ae8f6c7dbd851a0cc464594adf684d40f620c236fdd9'
        }
        $http.post('/api/ranks', obj)
            .success(function(data) {
                $scope.ranks = data;
                console.log(data);
            })
            .error(function(data) {
                console.log('Error: ' + data);
            });
    };

    $scope.createItem = function() {
        var obj = {
            val: 5,
            todoId: $scope.todos[0]._id,
            rankId: $scope.ranks[0]._id
        }
        $http.post('/api/items', obj)
            .success(function(data) {
                $scope.items = data;
                console.log(data);
            })
            .error(function(data) {
                console.log('Error: ' + data);
            });
    };

    $scope.getItem = function(id) {
        $http.post('/api/items/' + id)
            .success(function(data) {
                $scope.items = data;
                console.log(data);
            })
            .error(function(data) {
                console.log('Error: ' + data);
            });
    };
});