angular.module('app', ['ngResource'])
 .controller('EmojiController', function($scope, $resource) {
 	let vm = this;
 	let Emoji = $resource('api/emojis');


    success = function(data, headers) {
        //data.forEach(setImages)
    }
    error = function(response) {
        console.log(response.status);
    }

 	refreshData = function() {
 		vm.emojis = Emoji.query(success, error);
 	}

 	$scope.search = function() {
 	    if($scope.inputText) {
            let tags = $scope.inputText.split(" ");
            vm.emojis = Emoji.query({'tags':tags}, success, error);
 		} else {
 		    refreshData();
        }
 	}

 	refreshData();

 });