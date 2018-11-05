
'use strict';
 
app.controller('CreateAppFormController', ['$scope', '$mdDialog', function($scope, $mdDialog) {
 
		$scope.Model = $scope.Model || {myImage : "", myCroppedImage : "", appinfo : {}};
	
		//app basic information
		
		$scope.Model.appinfo = {
			profile_picture: '',
			title: 'test_title',
			description: 'test_description',
			author: 'test_user',
			division: 'TPT',
			downloads: '0',
			rate: '0',
			creation_time: Date.now(),
			last_update_time: Date.now(),
			content: 'some html content',
			languages: ['English','Spanish'],
		    purposes: ['Preflight'],		    
		    app_types: ['VBA Add-In'],
		    source_file_types: ['MS Word']
		};
		
		$scope.divisions = ['TPT','TDC','TDCEU','TDCNY','TDCSF','TPTBCN','AD-COM','ARCHITEXT','CRIMSON','ISP','IVERSON','OVERTAAL','ADAMS','TDCAPAC','TDC CORVALLIS','TP-TOKYO','WORLDLINGO','TDC TOKYO','OTHERS'];
		
		$scope.purposes = ['Preflight','File Conversion','Project Automation','Backend QA','TM Manipulation','Others'];
		
		$scope.languages = ['English','Chinese','Japanese','Korean','Hindi','Spanish','Arabic','Malay','Russian','Bengali','Portuguese','French','Others'];
		
		$scope.apptypes = ['VBA Macro','VBA Add-In','C# Application','Java Application','Python Application','AHK Script','AutoIt Script','Others'];
		
		$scope.filetypes = ['TXML','TXLF','MS Word','MS Excel','MS PowerPoint','XML','XLIFF','PO','Properties','RESX','Ohter Text-Based File','Others'];
		
		$scope.cancel = function() {
			$mdDialog.cancel();
	    };

	    $scope.submit = function() {
	    	$mdDialog.hide($scope.Model.appinfo);
	    };
	    
	    
	    

	    
	    //app icon upload
	    
	    $scope.Model.inicropinfo = {
			x: -1,
	    	y: -1,
	    	s: -1
		};
	    
	    
		$scope.selectPic = function() {
	    	document.querySelector('#imageInput').click();
	    };
		
		$scope.iconPreview = function(event) {
		    $mdDialog.show({
		    	locals:{croppedImage: $scope.Model.myCroppedImage},
		        template: '<md-dialog aria-label="icon-preview-dialog" id="cropped_image_container"><img id="cropped_image" class="mdl-shadow--2dp" ng-src="{{croppedImage}}" /></md-dialog>',
		        controller: iconPreviewCtrl,
		        controllerAs: 'ipctrl',  
		        targetEvent: event,
	            multiple: true,
		        clickOutsideToClose:true
		    });
		};
		    
		    
		var iconPreviewCtrl = function ($scope, croppedImage) { 
		    $scope.croppedImage = croppedImage;  
		}
	    
	    function handleFileSelect(evt) {
	    	var file=evt.currentTarget.files[0];
	    	var reader = new FileReader();
	    	reader.onload = function (evt) {
	    		$scope.$apply(function($scope){
	    			$scope.Model.myImage=evt.target.result;
	    		});
	    	};
	    	
	    	reader.readAsDataURL(file);
	    };
	    
	    angular.element(document.querySelector('#imageInput')).on('change', handleFileSelect);
    }
]);