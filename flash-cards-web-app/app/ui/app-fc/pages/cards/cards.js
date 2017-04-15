module.exports = function($scope, $state, Cards, Sets) {

    $scope.cards = [];
    $scope.currentSet = null;
    $scope.id = $state.params.setId;

    if( $scope.id ) {
      Sets.get($scope.id)
        .then(function(set) {
          $scope.currentSet = set;
        });
      Cards.getAll($scope.id, 0, 99999)
        .then(function(cards) {
          $scope.cards = cards;
          _newCards(2)
        });
    } else {
        _newCards(2)
    }


    // ****************************
    //         UI handlers
    // ****************************

    $scope.saveSet = function(set) {
      if($scope.id) {
        Sets.update(set).then(function(data) {
          console.log("updated")
        });
      } else {
        Sets.createSet(set).then(function(data) {
          $state.go('cards', {setId: data.id});
        });
      }
    };

    $scope.saveCard = function(card) {
      if(!$scope.isFilledWordAndTranslation(card)) {
        return
      }
      if(card.id) {
        Cards.update($scope.id, card).then(function(response) {
          console.log("updated")
        })
      } else {
        Cards.create($scope.id, card).then(function(response) {
          card.id = response.id
        })
      }
    };

    $scope.addLineIfNeeded = function(card, index) {
      //   0  1  2  3  4  5
      // [ O, O, O, O, O, O]     length = 6
      //               |<->|     <- last empty lines
      if($scope.isFilledWordOrTranslation(card) && $scope.cards.length <= index + 2) {
          _newCard()
      }
    }

    $scope.isFilledWordOrTranslation = function(card) {
      return card.word || card.translation;
    }

    $scope.isFilledWordAndTranslation = function(card) {
      return card.word && card.translation;
    }

    $scope.deleteCard = function(card) {
      if(card.id) {
          Cards.remove($state.id, card.id).then(function(response) {
            $scope.cards.splice($scope.cards.indexOf(card), 1);
          })
      } else {
          $scope.cards.splice($scope.cards.indexOf(card), 1);
      }
    };

    $scope.goToCards = function() {
      $state.go('cards-training');
    }

    $scope.goToTranslate = function() {
      $state.go('training-translate');
    }

    $scope.goToMatching = function() {
      $state.go('matching', {setId: $scope.id});
    }

    // ****************************
    //         Methods
    // ****************************

    function _newCards(count) {
      for(var i = 0; i < count; i++) {
          $scope.cards.push({});
      }
    };

    function _newCard() {
      $scope.cards.push({});
    };

}
