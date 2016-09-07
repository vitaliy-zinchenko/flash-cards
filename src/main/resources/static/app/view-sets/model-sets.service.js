export default ($resource) => {
  const service = $resource('/api/card-set', { page:'@page', size:'@size' });

  service.getAll = (page, size) => {
    return service.query({ page: page , size: size });
  };

  service.createSet = (set) => {
    return service.save(set);
  };

  return service;
}