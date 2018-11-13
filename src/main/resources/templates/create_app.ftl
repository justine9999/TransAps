<md-dialog>
  <form ng-cloak name="appinfoform" ng-submit="appinfoform.$valid && submit()">
    <div class="tps-modal-headline">
    	<span>Create New App</span>
    	<span flex></span>
    	<md-button class="fright md-icon-button" ng-click="cancel()">
          	<i class="material-icons">close</i>
        </md-button>	
    </div>

    <md-dialog-content>
      <md-tabs>
      	<md-tab label="Basic Info" ui-sref="my-apps.basicinfo" md-active="true"></md-tab>
      	<md-tab label="App Icon" ui-sref="my-apps.appicon"></md-tab>
      	<md-tab label="App Details" ui-sref="my-apps.appdetails" md-on-deselect="deselect()"></md-tab>
      </md-tabs> 
      
      <div id="view_app_creation_form" ui-view="view_app_creation_form"></div>
      
    </md-dialog-content>

    <md-dialog-actions layout="row">
      <span flex></span>
      <md-button ng-click="cancel()">Cancel</md-button>
      <md-button type="submit">Submit</md-button>
    </md-dialog-actions>
  </form>
</md-dialog>