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
      <div class="md-dialog-content">
      	<div layout-gt-sm="row">
      		<md-input-container class="md-block" flex-gt-sm="">
	            <label>Title</label>
	            <input md-maxlength="30" required="" ng-model="appinfo.title" name="title">
	            <div ng-messages="appinfoform.title.$error">
		          	<div ng-message="required">This is required.</div>
		          	<div ng-message="md-maxlength">The title must be less than 30 characters long.</div>
		        </div>
	        </md-input-container>
	        
	        <md-input-container class="md-block" flex-gt-sm="">
	            <label>Division</label>
	            <md-select ng-model="appinfo.division" required="" name="division">
	              <md-option ng-repeat="division in divisions" value="{{division}}">
	                {{division}}
	              </md-option>
	            </md-select>
	            <div ng-messages="appinfoform.deivision.$error">
		          	<div ng-message="required">This is required.</div>
		        </div>
	        </md-input-container>
      	</div>
		<br/>
        <md-input-container class="md-block">
          	<label>Description</label>
          	<textarea ng-model="appinfo.description" md-maxlength="100" rows="3" md-select-on-focus="" required="" name="description"></textarea>
          	<div ng-messages="appinfoform.description.$error">
		    	<div ng-message="required">This is required.</div>
		        <div ng-message="md-maxlength">The title description be less than 100 characters long.</div>
		    </div>
      	</md-input-container>
      	<br/>
      	<md-input-container class="md-block" flex-gt-sm="">
	    	<label>Purpose</label>
	        <md-select ng-model="appinfo.purpose" required="" name="purpose">
	          <md-option ng-repeat="purpose in purposes" value="{{purpose}}">
	            {{purpose}}
	          </md-option>
	        </md-select>
	        <div ng-messages="appinfoform.purpose.$error">
		    	<div ng-message="required">This is required.</div>
		    </div>
	    </md-input-container>
	    <br/>
	    <md-input-container class="md-block" flex-gt-sm="">
	    	<label>Support Languages</label>
	        <md-select ng-model="appinfo.language" required="" name="language">
	          <md-option ng-repeat="language in languages" value="{{language}}">
	            {{language}}
	          </md-option>
	        </md-select>
	        <div ng-messages="appinfoform.language.$error">
		    	<div ng-message="required">This is required.</div>
		    </div>
	    </md-input-container>
	    <br/>
	    <md-input-container class="md-block" flex-gt-sm="">
	    	<label>Application Type</label>
	        <md-select ng-model="appinfo.apptype" required="" name="apptype">
	          <md-option ng-repeat="apptype in apptypes" value="{{apptype}}">
	            {{apptype}}
	          </md-option>
	        </md-select>
	        <div ng-messages="appinfoform.apptype.$error">
		    	<div ng-message="required">This is required.</div>
		    </div>
	    </md-input-container>
	    <br/>
	    <md-input-container class="md-block" flex-gt-sm="">
	    	<label>Support File Types</label>
	        <md-select ng-model="appinfo.filetype" required="" name="filetype">
	          <md-option ng-repeat="filetype in filetypes" value="{{filetype}}">
	            {{filetype}}
	          </md-option>
	        </md-select>
	        <div ng-messages="appinfoform.filetype.$error">
		    	<div ng-message="required">This is required.</div>
		    </div>
	    </md-input-container>
      </div>  
    </md-dialog-content>

    <md-dialog-actions layout="row">
      <span flex></span>
      <md-button ng-click="cancel()">Cancel</md-button>
      <md-button type="submit">Submit</md-button>
    </md-dialog-actions>
  </form>
</md-dialog>