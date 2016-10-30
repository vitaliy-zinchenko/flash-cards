export default ($resource) => {
  /* @ngInject */
  const mainUrl = '/api/card-set'
  const getById = '/api/card-set/:id'
  const params = { page:'@page', size:'@size' };

 const actions = {
    'getById': {
      url: getById,
    }
  };

  const service = $resource(mainUrl, params, actions);

  service.getAll = (page, size) => {
    return service.query({ page: page , size: size }).$promise;
  };

  service.createSet = (set) => {
    return service.save(set).$promise;
  };

  service.get = (id) => {
    return service.getById({ id: id }).$promise;
  };

  return service;
}