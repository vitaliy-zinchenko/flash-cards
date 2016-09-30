export default ($resource) => {
  /* @ngInject */
  const service = $resource('/api/card-set', { page:'@page', size:'@size' });

  service.getAll = (page, size) => {
    return service.query({ page: page , size: size }).$promise;
  };

  service.createSet = (set) => {
    return service.save(set).$promise;
  };

  return service;
}