angular.module('app', ['ngResource'])
 .controller('EmojiController', function($resource) {
 	let vm = this;
 	let Emoji = $resource('api/emojis');

    function setImages(element, index, array) {
        element.img = String.fromCodePoint(parseInt (element.hex, 16));
    }

 	function refreshData() {
 		vm.emojis = Emoji.query(
            function success(data, headers) {
                data.forEach(setImages)
            },
            function error(response) {
                console.log(response.status);
        });

 	}

 	refreshData();
 });