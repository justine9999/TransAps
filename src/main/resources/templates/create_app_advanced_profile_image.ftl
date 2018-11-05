<div id="profile_image_upload_container">
	<input ng-show="false" type="file" id="imageInput" />
	<div class="tps_label">Create App Icon</div>
	<div id="profile_image_upload_menu" class="mdl-shadow--2dp">
		<button id="select_profile_photo_button" type="button" class="mdl-button mdl-js-button mdl-button--icon" ng-click="selectPic()">
		  	<md-tooltip>Select picture</md-tooltip>
		  	<i class="material-icons">add_photo_alternate</i>
		</button>
		<button id="preview_crop_photo_button" type="button" class="mdl-button mdl-js-button mdl-button--icon" ng-click="iconPreview($event)" ng-disabled="Model.myImage==''">
			<md-tooltip>Preview app icon</md-tooltip>
		  	<i class="material-icons">visibility</i>
		</button>
		<button id="upload_profile_photo_button" type="button" class="mdl-button mdl-js-button mdl-button--icon" ng-disabled="Model.myCroppedImage==''">
			<md-tooltip>Upload app icon</md-tooltip>
		  	<i class="material-icons">cloud_upload</i>
		</button>
	</div>
	<div id="profile_image_upload_content" style="width:100%;background-color:#f2f2f2;display:flex;align-items:center;border-style:dashed;border-color:#ddd;border-width:2px;">
		<div class="cropArea">
			<img-crop image="Model.myImage" result-image="Model.myCroppedImage" inipos="Model.inicropinfo"></img-crop>
		</div>
	</div>
</div>