angular.module('app', ['ngResource']).controller('EmojiController', function($scope, $resource) {
 	let vm = this;
 	let Emoji = $resource('api/emojis');

    success = function(data, headers) {
        //data.forEach(setImages)
    }
    error = function(response) {
        //console.log(response.status);
    }

 	getAll = function() {
 		vm.emojis = Emoji.query(success, error);
 	}

 	$scope.searchByTag = function() {
        let input = $scope.inputText;
        if(input) {
            let tags = input.split(" ");
            search(tags);
        } else {
            getAll();
        }
 	}

 	search = function(values) {
 	    vm.emojis = Emoji.query({'tag':values}, success, error);
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

 	getAll();

 });