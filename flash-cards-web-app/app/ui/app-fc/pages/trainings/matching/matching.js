module.exports = function(Cards, $scope, $state, $window) {

  var MATCHING_SIZE = 7;

  var setId = $state.params.setId;
  Cards.listToLearn(setId).$promise
      .then(function(cards) {
          cards.randomize();
          $scope.allCards = cards;
          $scope.inProgressCards = _getInProgressCards($scope.allCards);
      })

  $scope.learnAnyway = function() {
    Cards.getAll(setId, 0, 9999) //TODO remove 0 and 9999
        .then(function(cards) {
            cards.randomize();
            $scope.allCards = cards;
            $scope.inProgressCards = _getInProgressCards($scope.allCards);
        })
  }

  $scope.selectWord = function(card) {
    _clearWords()
    card.selectedWord = true
    _check()
  }

  $scope.selectTranslation = function(card) {
    _clearTranslations()
    card.selectedTranslation = true
    _check()
  }

  $scope.goBack = function() {
    $window.history.back();
  }

  function _check() {
    var selectedWord = $scope.inProgressCards.find(function(card){return card.selectedWord})
    var selectedTranslation = $scope.inProgressCards.find(function(card) {return card.selectedTranslation})
    if(!selectedWord || !selectedTranslation) {
        // wasn't selected both word and translation
        return;
    }
    if(selectedWord == selectedTranslation) {
      selectedWord.guessed = true
      selectedWord.rightCount ? selectedWord.rightCount++ : selectedWord.rightCount = 1;
      _clearWords()
      _clearTranslations()
      if(_isFinishedRound()) {
        $scope.inProgressCards = _getInProgressCards($scope.allCards);
        if($scope.inProgressCards.length == 0) {
          $scope.finished = true;
          _sendProgress();
        }
      }
    } else {
      selectedWord.wrongCount ? selectedWord.wrongCount++ : selectedWord.wrongCount = 1;
      selectedWord.reTry = true;
      _clearWords()
      _clearTranslations()
    }
  }

  function _sendProgress() {
    var cardProgresses = $scope.allCards.map(function(card) {
      return {
        cardId: card.id,
        right: card.rightCount,
        wrong: card.wrongCount
      }
    });
    Cards.sendProgress(cardProgresses);
  }

  function _clearWords() {
    $scope.inProgressCards.forEach(function(card) {return card.selectedWord = false})
  }

  function _clearTranslations() {
    $scope.inProgressCards.forEach(function(card) {return card.selectedTranslation = false})
  }

  function _isFinishedRound() {
    return $scope.inProgressCards.filter(function(card) {return !card.guessed}).length == 0;
  }

  function _getInProgressCards(allCards) {
    var isFirstRound = function(card) {return !card.rightCount && !card.wrongCount};
    var isReTryRound = function(card) {return card.reTry == true};
    var inProgress = [];
    for(var i = 0; inProgress.length < MATCHING_SIZE && allCards[i]; i++) {
        var card = allCards[i];
        if(isFirstRound(card) || isReTryRound(card)) {
            card.guessed = card.reTry = false;
            card.wordPosition = Math.random();
            card.translationPosition = Math.random();
            inProgress.push(card);
        }
    }
    return inProgress;
  }

}
