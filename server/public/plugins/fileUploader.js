angular.module('uploader', []).directive('appFilereader', function ($q, $parse, ImageFactory) {
    return {
        restrict: 'A',
        require: '?ngModel',
        link: function (scope, element, attrs) {

            var model = $parse(attrs.appFilereader);
            var modelSetter = model.assign;
            element.bind('change', function () {
                scope.$apply(function () {
                    ImageFactory.postImage(element[0].files[0]).then(function(success) {
                        modelSetter(scope, success);
                    })
                });
            });

        } //link
    }; //return
}).factory("ImageFactory", function($q, $http) {
    return {
        postImage: function (image) {
            var fd = new FormData();
            var deferred = $q.defer();
            fd.append('picture', image);
            $http.post('/upload', fd, {
                transformRequest: angular.identity,
                headers: {'Content-Type': undefined}
            }).success(function (success) {
                deferred.resolve(success)
            }).error(function (error) {
                console.log(error)
            });
            return deferred.promise
        }
    }
}).config(['$compileProvider', function ($compileProvider) {
    $compileProvider.aHrefSanitizationWhitelist(/^\s*(https?|ftp|mailto|tel|file|blob):/);
}]).controller("fileDownloader", function($scope, $http) {
    $http.get("/reservationsString").success(function(data) {

        var a = JSON.stringify(data);//angular.fromJson(data);

        var b = a.substring(1, a.length - 1);
        b = b.replace(/\\n/g, "\n").replace(/\\t/g, "\t");
        console.log("b= " + b)

        //var content = data.map(function(e) { return e.toString });
        var blob = new Blob([ b ], { type : 'text/plain' });
        $scope.url = (window.URL || window.webkitURL).createObjectURL( blob );
    });

});