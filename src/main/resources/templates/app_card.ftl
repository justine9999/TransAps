<div class="app-card-square mdl-card mdl-shadow--2dp">
  <div id="app-card-header" class="mdl-card__title mdl-card--expand">
  	<div id="app-card-icon-container">
  		<img id="app-card-icon" class="mdl-shadow--2dp" ng-src="{{app.profile_picture || './image/default_profile_pic.png'}}" />
  	</div>
  	<div id="app-card-basic-info">
  		<div id="app-card-basic-info-container">
  			<div id="app-card-title">
  				{{app.title}}
  			</div>
	  		<div id="app-card-author">
	  			{{app.author}}
	  		</div>
		</div>
  	</div>
  </div>
  <table class="mdl-data-table mdl-js-data-table">
	<tbody>
		<tr>
			<td id="app-card-recommendation-info" class="mdl-data-table__cell--non-numeric">
				<div id="rating">
					<rating-star></rating-star>
				</div>
				<div id="downloads">
				  	<div>
						<img id="download_icon" src="./image/download_icon.png" />
					</div>
				  	<div id="downloads-cnt">{{app.downloads}}</div>
				</div>
			</td>
		</tr>
		<tr>
			<td id="app-card-description" class="mdl-data-table__cell--non-numeric">{{app.description}}</td>
		</tr>
		<tr>
			<td id="app-card-detail" class="mdl-data-table__cell--non-numeric">
				<a class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect">VIEW DETAILS</a>
			</td>
		</tr>
	</tbody>
  </table>
</div>