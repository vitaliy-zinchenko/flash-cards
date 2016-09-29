export default class setTitleController {
  /* @ngInject */
  constructor(setService, $state) {
    this._setService = setService;
    this.$state = $state;

    this.initialize();
    };


//create new set
  saveSet(set) {
    this._setService.createSet(set).$promise.then(data => {
      console.log('Create new set:');
      console.log(data);
      this.currentSet = data;
      this.id = data.id; //TODO this is hotfix. !!!*ISN'T WORKING NOW*!!! without this one new cards for new cards set are sent to /api/card-set/new/cards/batch
    });
  };


  initialize() {
    this.name = 'setTitleController';
    this.id = this.$state.params.id;
  }

}
