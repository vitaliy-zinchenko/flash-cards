export default ($resource) => {
  const service = $resource('/card-set', { page:'@page', size:'@size' });

  service.getAll = (page, size) => {
    return service.query({ page: page , size: size });
  };

  service.createSet = (set) => {
    return service.save({ content: set });
  };

  return service;
}