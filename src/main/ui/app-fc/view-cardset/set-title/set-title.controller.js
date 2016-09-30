export default class setTitleController {
  /* @ngInject */
  constructor(setService, $state) {
    this._setService = setService;
    this.$state = $state;

    this.initialize();
    };


  initialize() {
    this.id = this.$state.params.id;
    this.name = 'Set: ' + this.id;
  }


//create new set
  saveSet(set) {
    this._setService.createSet(set).then(data => {
      console.log('Create new set:');
      console.log(data);
      this.currentSet = data;
      this.$state.go('cardset', {id: data.id});
    });
  };

}
