<md-dialog>
  <form ng-cloak>
    <div class="tps-modal-headline">
    	<span>Create New App</span>
    	<span flex></span>
    	<md-button class="fright md-icon-button" ng-click="cancel()">
          	<i class="material-icons">close</i>
        </md-button>	
    </div>

    <md-dialog-content>
      <div class="md-dialog-content">
      	<div layout-gt-sm="row">
      		<md-input-container class="md-block" flex-gt-sm="">
	            <label>Title</label>
	            <input ng-model="appinfo.title">
	        </md-input-container>
	        
	        <md-input-container class="md-block" flex-gt-sm="">
	            <label>Division</label>
	            <md-select ng-model="appinfo.division">
	              <md-option ng-repeat="division in divisions" value="{{division}}">
	                {{division}}
	              </md-option>
	            </md-select>
	        </md-input-container>
      	</div>
		<br/>
        <md-input-container class="md-block">
          <label>Description</label>
          <textarea ng-model="appinfo.description" md-maxlength="100" rows="3" md-select-on-focus=""></textarea>
      	</md-input-container>
      	<br/>
      	<md-input-container class="md-block" flex-gt-sm="">
	    	<label>Purpose</label>
	        <md-select ng-model="appinfo.purpose">
	          <md-option ng-repeat="purpose in purposes" value="{{purpose}}">
	            {{purpose}}
	          </md-option>
	        </md-select>
	    </md-input-container>
	    <br/>
	    <md-input-container class="md-block" flex-gt-sm="">
	    	<label>Support Languages</label>
	        <md-select ng-model="appinfo.language">
	          <md-option ng-repeat="language in languages" value="{{language}}">
	            {{language}}
	          </md-option>
	        </md-select>
	    </md-input-container>
	    <br/>
	    <md-input-container class="md-block" flex-gt-sm="">
	    	<label>Application Type</label>
	        <md-select ng-model="appinfo.apptype">
	          <md-option ng-repeat="apptype in apptypes" value="{{apptype}}">
	            {{apptype}}
	          </md-option>
	        </md-select>
	    </md-input-container>
	    <br/>
	    <md-input-container class="md-block" flex-gt-sm="">
	    	<label>Support File Types</label>
	        <md-select ng-model="appinfo.filetype">
	          <md-option ng-repeat="filetype in filetypes" value="{{filetype}}">
	            {{filetype}}
	          </md-option>
	        </md-select>
	    </md-input-container>
      </div>  
    </md-dialog-content>

    <md-dialog-actions layout="row">
      <span flex></span>
      <md-button ng-click="cancel()">Cancel</md-button>
      <md-button ng-click="submit()">Submit</md-button>
    </md-dialog-actions>
  </form>
</md-dialog>