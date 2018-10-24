
'use strict';
 
app.controller('CreateAppController', ['$scope', '$mdDialog', function($scope, $mdDialog) {
 
		$scope.appinfo = {
			title: '',
			description: '',
		    division: 'TPT',
		    purpose: '',
		    language: 'English',
		    apptype: '',
		    filetype: ''
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
	    	$mdDialog.hide(appinfo);
	    };
    }
]);