
'use strict';
 
app.controller('AppController', ['AppService', '$scope', '$state',  function( AppService, $scope, $state) {
 
		$scope.state = $state;
        var self = this;
        var apps = [];
        $scope.apps_per_row = 4;
        $scope.scrollTo = function (target){};
        $scope.appcardwidth = $('#apps-container').css("width");
        
        //initilize controller data
        var init = function getAllApps(){
        	self.apps = AppService.getAllApps();
        	if(self.apps.length === 0){
        		self.apps = [];
        	}
        };
        init();
        
        // filter
        self.searchText = null;
        self.querySearch = querySearch;
        self.tags = loadTags();
        self.selectedTags = [];
        self.addTag = addTag;
        self.removeTag = removeTag;
        self.transformChip = transformChip;
        self.isFilterOpen = false;
        self.fostyle = {'opacity':'0.5'};
        
        //0: top rated; 1: most downloads; 2: most recent
        self.selectedFilter = 1;
  
        function transformChip(chip) {
            if (angular.isObject(chip)) {
            	return chip;
            }

            return { text: chip, type: 'keyword' };
        }
        
        function querySearch (query) {
        	var results = query ? self.tags.filter(createFilterFor(query)) : [];
        	return results;
        }
        
        function createFilterFor(query) {
            var lowercaseQuery = query.toLowerCase();

            return function filterFn(tags) {
            	return tags._lowertext.indexOf(lowercaseQuery) === 0;
            };

        }
        
        function addTag(text) {
        	if(!text) {
        		return;
        	}
        	var tag_already_exist = false;
        	self.selectedTags.forEach(function(selectedTag) {
    			if(text === selectedTag.text && selectedTag.type === 'keyword'){
    				tag_already_exist = true;
    				return;
    			}
    		});
        	if(!tag_already_exist){
        		var newtag = {'text':text,'type':'keyword'};
                self.selectedTags.push(newtag);
        	}
            
            clearInputText();
        }
        
        function clearInputText() {
        	self.searchText = "";
        }
        
        function removeTag(index) {
        	self.selectedTags.splice(index, 1);
        }
        
        function loadTags() {
            var tags = [{'text':'TPT','type':'keyword'},{'text':'TDC','type':'keyword'},
		            	{'text':'TDCEU','type':'keyword'},{'text':'TDCNY','type':'keyword'},
		            	{'text':'TDCSF','type':'keyword'},{'text':'TPTBCN','type':'keyword'},
		            	{'text':'AD-COM','type':'keyword'},{'text':'ARCHITEXT','type':'keyword'},
		            	{'text':'CRIMSON','type':'keyword'},{'text':'ISP','type':'keyword'},
		            	{'text':'IVERSON','type':'keyword'},{'text':'OVERTAAL','type':'keyword'},
		            	{'text':'ADAMS','type':'keyword'},{'text':'TDCAPAC','type':'keyword'},
		            	{'text':'TDC CORVALLIS','type':'keyword'},{'text':'TP-TOKYO','type':'keyword'},
		            	{'text':'WORLDLINGO','type':'keyword'},{'text':'TDC TOKYO','type':'keyword'},
		            	{'text':'Preflight','type':'keyword'},{'text':'File Conversion','type':'keyword'},
		            	{'text':'Project Automation','type':'keyword'},{'text':'Backend QA','type':'keyword'},
		            	{'text':'TM Manipulation','type':'keyword'},{'text':'VBA Macro','type':'keyword'},
		            	{'text':'VBA Add-In','type':'keyword'},{'text':'C#','type':'keyword'},
		            	{'text':'Java','type':'keyword'},{'text':'Python','type':'keyword'},
		            	{'text':'AHK','type':'keyword'},{'text':'AutoIt','type':'keyword'},
		            	{'text':'TXML','type':'keyword'},{'text':'TXLF','type':'keyword'},
		            	{'text':'Word','type':'keyword'},{'text':'Excel','type':'keyword'},
		            	{'text':'PowerPoint','type':'keyword'},{'text':'XML','type':'keyword'},
		            	{'text':'XLIFF','type':'keyword'},{'text':'PO','type':'keyword'},
		            	{'text':'Properties','type':'keyword'},{'text':'RESX','type':'keyword'},
		            	{'text':'Text-Based File','type':'keyword'}];

            return tags.map(function (tag) {
            	tag._lowertext = tag.text.toLowerCase();
            	return tag;
            });
        }
}]);