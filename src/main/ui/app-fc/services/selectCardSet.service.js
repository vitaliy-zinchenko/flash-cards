export default (localStorageService) => {
  /* @ngInject */
  const service = {};

  service.selectCard = (cardId) => {
    localStorageService.set("selectedCardId", cardId)
  };

  service.getSelectedCards = () => {
    return localStorageService.get("selectedCardId")
  };

  return service;
}