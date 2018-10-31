
'use strict';
 
app.controller('CreateAppFormController', ['$scope', '$mdDialog', function($scope, $mdDialog) {
 
		$scope.appinfo = {
			id: '1',
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
	    	$mdDialog.hide($scope.appinfo);
	    };
    }
]);