angular.module('dbflute-intro').factory('ApiFactory',
        function ($rootScope, $http) {
    'use strict';

    return {
        manifest: function() {
            return $http({
                method : 'POST',
                url : 'api/intro/manifest'
            });
        },
        publicProperties: function() {
            return $http({
                method : 'POST',
                url : 'api/engine/publicProperties'
            });
        },
        engineVersions: function() {
            return $http({
                method : 'POST',
                url : 'api/engine/versions'
            });
        },
        classification: function() {
            return $http({
                method : 'POST',
                url : 'api/client/classification'
            });
        },
        clientBeanList: function() {
            return $http({
                method : 'POST',
                url : 'api/client/list'
            });
        },
        clientCreate: function(clientBean, testConnection) {
            return $http({
                method : 'POST',
                url : 'api/client/create',
                data : {clientBean: clientBean, testConnection: testConnection}
            });
        },
        clientUpdate: function(clientBean, testConnection) {
            return $http({
                method : 'POST',
                url : 'api/client/update',
                data : {clientBean: clientBean, testConnection: testConnection}
            });
        },
        clientRemove: function(params) {
            return $http({
                method : 'POST',
                url : 'api/client/delete/' + params.project
            });
        },
        downloadEngine: function(params) {
            return $http({
                method : 'POST',
                url : 'api/engine/download/' + params.version
            });
        },
        removeEngine: function(params) {
            return $http({
                method : 'POST',
                url : 'api/engine/remove/' + params.version
            });
        }
    };
});
