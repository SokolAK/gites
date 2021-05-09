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



    copyToClipboard = function(el) {
        navigator.clipboard.writeText(el)
            .then(function() {
                      let s = document.getElementById("snackbar");
                      s.className = "show";
                      setTimeout(function(){ s.className = s.className.replace("show", ""); }, 2000);



                            element = document.getElementById(el);

                            element.classList.remove("bounce");

                            // -> triggering reflow /* The actual magic */
                            // without this it wouldn't work. Try uncommenting the line and the transition won't be retriggered.
                            element.offsetWidth = element.offsetWidth;

                            // -> and re-adding the class
                            element.classList.add("bounce");





                 }, function() {}
             );
    }


 	refreshData();

 });