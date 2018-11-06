<div class="app-card-square mdl-card mdl-shadow--2dp">
  <div class="mdl-card__title mdl-card--expand">
  	<img id="app-card-icon" src="{{app.profile_picture}}" />
  </div>
  <table class="mdl-data-table mdl-js-data-table">
	<tbody>
		<tr>
			<td id="app-card-title" class="mdl-data-table__cell--non-numeric">{{app.title}}</td>
		</tr>
		<tr>
			<td id="app-card-author" class="mdl-data-table__cell--non-numeric">{{app.author}}</td>
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