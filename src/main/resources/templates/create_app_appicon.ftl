<div id="profile_image_upload_container">
	<input ng-show="false" type="file" id="imageInput" />
	<div id="profile_image_upload_menu">
		<button id="select_profile_photo_button" type="button" class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect" ng-click="selectPic()">
		  	<md-tooltip>Select picture</md-tooltip>
		  	<i class="material-icons">add_photo_alternate</i>
		</button>
		<button id="preview_crop_photo_button" type="button" class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect" ng-click="iconPreview($event)" ng-disabled="Model.myImage==''">
			<md-tooltip>Preview app icon</md-tooltip>
		  	<i class="material-icons">visibility</i>
		</button>
		<button id="upload_profile_photo_button" type="button" class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect" ng-disabled="Model.myImage==''">
			<md-tooltip>Upload app icon</md-tooltip>
		  	<i class="material-icons">cloud_upload</i>
		</button>
	</div>
	<div id="profile_image_upload_content">
		<div class="cropArea">
			<img-crop image="Model.myImage" result-image="Model.myCroppedImage" inipos="Model.inicropinfo"></img-crop>
		</div>
	</div>
</div>