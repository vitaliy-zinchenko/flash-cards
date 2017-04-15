module.exports = function($resource) {

  var service = $resource('/api/card-set',
    { id: '@id', page:'@page', size:'@size' },
    {
        'getById': {
          url: '/api/card-set/:id',
          method: 'GET'
        },
        'delete': {
          url: '/api/card-set/:id',
          method: 'DELETE'
        },
        '_update': {
          url: '/api/card-set/:id',
          method: 'PUT'
        }
    });

  this.getAll = function(page, size) {
    return service.query({ page: page , size: size }).$promise;
  };

  this.createSet = function(set) {
    return service.save(set).$promise;
  };

  this.update = function(set) {
    return service._update(set).$promise;
  };

  this.get = function(id) {
    return service.getById({ id: id }).$promise;
  };

  this.remove = function(id) {
    return service.delete({ id: id }).$promise;
  };

}


/*
export default ($resource) => {



  const service = $resource('/api/card-set',
                            { id: '@id', page:'@page', size:'@size' },
                            {
                                'getById': {
                                  url: '/api/card-set/:id',
                                  method: 'GET'
                                },
                                'delete': {
                                  url: '/api/card-set/:id',
                                  method: 'DELETE'
                                },
                                '_update': {
                                  url: '/api/card-set/:id',
                                  method: 'PUT'
                                }
                            });

  service.getAll = (page, size) => {
    return service.query({ page: page , size: size }).$promise;
  };

  service.createSet = (set) => {
    return service.save(set).$promise;
  };

  service.update = (set) => {
    return service._update(set).$promise;
  };

  service.get = (id) => {
    return service.getById({ id: id }).$promise;
  };

  service.remove = (id) => {
    return service.delete({ id: id }).$promise;
  };

  return service;
}*/
