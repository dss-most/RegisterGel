function appUrl(url) {
	return '/RegisterGel/API'+url;
}

(function(){
	
	
window.App = {
  Models: {},
  Collections: {},
  Pages: {},
  Views: {}
};

App.Models.Registration = Backbone.Model.extend({
	urlRoot : appUrl('/Registration')
});

App.Collections.RegistrationCollection = Backbone.Collection.extend({
	  model: App.Models.Registration
});

})();

