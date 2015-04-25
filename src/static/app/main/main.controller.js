'use strict';

angular.module('dbflute-intro')
  .controller('MainCtrl', function ($scope, $window, $modal, ApiFactory) {

      $scope.manifest = {};
      $scope.publicProperties = [];
      $scope.versions = [];
      $scope.classificationMap = {};
      $scope.clientBeanList = [];
      $scope.clientBean = null;
      $scope.editFlg = false;

      $scope.manifest = function() {
          ApiFactory.manifest().then(function(response) {
              $scope.manifest = response.data;
          });
      };

      $scope.engineVersions = function(version) {
          ApiFactory.engineVersions().then(function(response) {
              $scope.versions = response.data;
          });
      };

      $scope.classification = function() {
          ApiFactory.classification().then(function(response) {
              $scope.classificationMap = response.data;
          });
      };

      $scope.clientBeanList = function() {
          ApiFactory.clientBeanList().then(function(response) {
              $scope.clientBeanList = response.data;
          });
      };

      $scope.add = function() {
          $scope.editFlg = true;
          $scope.clientBean = {};
      };

      $scope.edit = function() {
          $scope.editFlg = true;
      };

      $scope.cancelEdit = function() {
          $scope.editFlg = false;
      };

      $scope.create = function(clientBean) {
          ApiFactory.clientCreate(clientBean).then(function(response) {
              $scope.clientBeanList();
          });
      };

      $scope.update = function(clientBean) {
          ApiFactory.clientUpdate(clientBean).then(function(response) {
              $scope.clientBeanList();
          });
      };

      $scope.remove = function(clientBean) {
          ApiFactory.clientRemove(clientBean).then(function(response) {
              $scope.clientBeanList();
          });
      };

      $scope.openSchemaHTML = function(clientBean) {
          $window.open('api/client/schemahtml/' + clientBean.project);
      };

      $scope.openHistoryHTML = function(clientBean) {
          $window.open('api/client/historyhtml/' + clientBean.project);
      };

      $scope.task = function(clientBean, task) {
          $window.open('api/client/task/' + clientBean.project + '/' + task);
      };


      $scope.downloadModal = function() {
          var downloadInstance = $modal.open({
              templateUrl: 'app/main/download.html',
              controller: 'DownloadInstanceController',
              resolve: {
                publicProperties: function() {
                    return ApiFactory.publicProperties();
                }
            }
          });

          downloadInstance.result.then(function(versions) {
              $scope.versions = versions;
          });
      };

      $scope.setCurrentProject = function(clientBean) {
          $scope.clientBean = clientBean.clientBean;
      };

      $scope.manifest();
      $scope.engineVersions();
      $scope.classification();
      $scope.clientBeanList();
});

angular.module('dbflute-intro').controller('DownloadInstanceController',
        function($scope, $modalInstance, publicProperties, ApiFactory) {
    'use strict';

    $scope.downloading = false;

    $scope.publicProperties = {};
    $scope.publicProperties.compatible10x = [];
    $scope.publicProperties.compatible11x = [];

    $scope.currentBranch = 'compatible11x';

    var publicPropertiesData = publicProperties.data;
    var compatible10xRelease = publicPropertiesData['compatible10x.dbflute.latest.release.version'];
    var compatible10xSnapshot = publicPropertiesData['compatible10x.dbflute.latest.snapshot.version'];
    var compatible11xRelease = publicPropertiesData['dbflute.latest.release.version'];
    var compatible11xSnapshot = publicPropertiesData['dbflute.latest.snapshot.version'];

    $scope.publicProperties.compatible10x[0] = compatible10xRelease;
    $scope.publicProperties.compatible11x[0] = compatible11xRelease;

    if (compatible10xRelease !== compatible10xSnapshot) {
        $scope.publicProperties.compatible10x[1] = compatible10xSnapshot;
    }

    if (compatible11xRelease !== compatible11xSnapshot) {
        $scope.publicProperties.compatible11x[1] = compatible11xSnapshot;
        $scope.dbfluteEngine.version = compatible11xSnapshot;
    }

    $scope.dbfluteEngine = {
        version: $scope.publicProperties.compatible11x[0]
    };

    $scope.selectVersion = function(version) {
        $scope.dbfluteEngine.version = version;
    };

    $scope.version = null;

    $scope.downloadEngine = function() {
        $scope.downloading = true;

        if ($scope.version !== null) {
            $scope.dbfluteEngine.version = $scope.version;
        }
        ApiFactory.downloadEngine($scope.dbfluteEngine).then(function(response) {
            $scope.downloading = false;

            ApiFactory.engineVersions().then(function(response) {
                $modalInstance.close(response.data);
            });
        });
    };
});
