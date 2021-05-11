angular.module('app', ['ngResource']).controller('EmojiController', function($scope, $resource) {
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
            vm.emojis = Emoji.query({'tag':tags}, success, error);
 		} else {
 		    refreshData();
        }
 	}

    copyToClipboard = function(el) {
        navigator.clipboard.writeText(el)
            .then(function() {

                    //snackbar
                    let s = document.getElementById("snackbar");
                    s.className = "show";
                    setTimeout(function(){ s.className = s.className.replace("show", ""); }, 2000);

                    //emoji bounce
                    element = document.getElementById(el);
                    element.classList.remove("bounce");
                    element.offsetWidth = element.offsetWidth;
                    element.classList.add("bounce");

                 }, function() {}
             );
    }


 	refreshData();

 });