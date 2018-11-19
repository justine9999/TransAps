<div class="md-dialog-content">
	<div layout-gt-sm="row">
		<md-input-container class="md-block" flex-gt-sm="">
			<label>Title</label>
			<input md-maxlength="20" required="" ng-model="Model.appinfo.title" name="title">
				<div ng-messages="appinfoform.title.$error">
				<div ng-message="required">This is required.</div>
				<div ng-message="md-maxlength">The title must be less than 30 characters long.</div>
			</div>
		</md-input-container>
		<md-input-container class="md-block" flex-gt-sm="">
			<label>Division</label>
			<md-select ng-model="Model.appinfo.division" required="" name="division">
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
		<textarea ng-model="Model.appinfo.description" md-maxlength="200" rows="3" md-select-on-focus="" required="" name="description"></textarea>
		<div ng-messages="appinfoform.description.$error">
			<div ng-message="required">This is required.</div>
			<div ng-message="md-maxlength">The title description be less than 100 characters long.</div>
		</div>
	</md-input-container>
	<br/>
	<md-input-container class="md-block" flex-gt-sm="">
		<label>Purpose</label>
		<md-select multiple ng-model="Model.appinfo.purposes" required="" name="purposes">
			<md-option ng-repeat="purpose in purposes" value="{{purpose}}">
				{{purpose}}
			</md-option>
		</md-select>
		<div ng-messages="appinfoform.purposes.$error">
			<div ng-message="required">This is required.</div>
		</div>
	</md-input-container>
	<br/>
	<md-input-container class="md-block" flex-gt-sm="">
		<label>Support Languages</label>
		<md-select multiple ng-model="Model.appinfo.languages" required="" name="languages">
			<md-option ng-repeat="language in languages" value="{{language}}">
				{{language}}
			</md-option>
		</md-select>
		<div ng-messages="appinfoform.languages.$error">
			<div ng-message="required">This is required.</div>
		</div>
	</md-input-container>
	<br/>
	<md-input-container class="md-block" flex-gt-sm="">
		<label>Application Type</label>
		<md-select multiple ng-model="Model.appinfo.app_types" required="" name="appTypes">
			<md-option ng-repeat="apptype in apptypes" value="{{apptype}}">
				{{apptype}}
			</md-option>
		</md-select>
		<div ng-messages="appinfoform.appTypes.$error">
			<div ng-message="required">This is required.</div>
		</div>
	</md-input-container>
	<br/>
	<md-input-container class="md-block" flex-gt-sm="">
		<label>Support File Types</label>
		<md-select multiple ng-model="Model.appinfo.source_file_types" required="" name="sourceFileTypes">
			<md-option ng-repeat="filetype in filetypes" value="{{filetype}}">
				{{filetype}}
			</md-option>
		</md-select>
		<div ng-messages="appinfoform.sourceFileTypes.$error">
			<div ng-message="required">This is required.</div>
		</div>
	</md-input-container>
</div>