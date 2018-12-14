<md-dialog>
  <form ng-cloak name="userloginform" ng-submit="userloginform.$valid && submit()">
    <div class="tps-modal-headline">
    	<span>User Login</span>
    	<span flex></span>
    	<md-button class="fright md-icon-button" ng-click="cancel()">
          	<i class="material-icons">close</i>
        </md-button>	
    </div>

    <md-dialog-content>
      <div class="md-dialog-content">
		<div layout-gt-sm="row">
			<md-input-container class="md-block" flex-gt-sm="">
				<label>Username</label>
				<input required="" ng-model="userdata.username" name="username">
					<div ng-messages="userdata.username.$error">
					<div ng-message="required">This is required.</div>
				</div>
			</md-input-container>
		</div>
		<br/>
		<div layout-gt-sm="row">
			<md-input-container class="md-block" flex-gt-sm="">
				<label>Password</label>
				<input required="" ng-model="userdata.password" name="password">
					<div ng-messages="userdata.password.$error">
					<div ng-message="required">This is required.</div>
				</div>
			</md-input-container>
		</div>
	  </div>    
    </md-dialog-content>

    <md-dialog-actions layout="row">
      <span flex></span>
      <md-button ng-click="cancel()">Cancel</md-button>
      <md-button type="submit">Submit</md-button>
    </md-dialog-actions>
  </form>
</md-dialog>