export default ($resource) => {
  /* @ngInject */

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
                                }
                            });

  service.getAll = (page, size) => {
    return service.query({ page: page , size: size }).$promise;
  };

  service.createSet = (set) => {
    return service.save(set).$promise;
  };

  service.get = (id) => {
    return service.getById({ id: id }).$promise;
  };

  service.remove = (id) => {
    return service.delete({ id: id }).$promise;
  };

  return service;
}