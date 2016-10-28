export default (localStorageService) => {
  /* @ngInject */
  const service = {};

  const selectedCardSetIdsKey = "selectedCardSetIds";

  service.selectCard = (cardId) => {
    localStorageService.set("selectedCardId", cardId)
  };

  service.getSelectedCards = () => {
    return localStorageService.get("selectedCardId")
  };

  service.selectCardSet = (cardSetId) => {
    var selectedCardSetIds = localStorageService.get(selectedCardSetIdsKey);
    if(!selectedCardSetIds) {
      selectedCardSetIds = [];
      localStorageService.set(selectedCardSetIdsKey, selectedCardSetIds);
    }
    selectedCardSetIds.push(cardSetId);
    localStorageService.set(selectedCardSetIdsKey, selectedCardSetIds);
  };

  service.getSelectCardSet = () => {
    return localStorageService.get(selectedCardSetIdsKey);
  }

  return service;
}