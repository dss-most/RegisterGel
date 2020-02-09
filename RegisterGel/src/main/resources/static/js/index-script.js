var AppRouter = Backbone.Router.extend({
	initialize : function(options) {
	// now we're ready for initialize the view
	
	this.formView = new FormView({el: '#registrationModal'});
	this.registerSectionView = new RegisterSectionView({el: '#registerSection'}); 
	},
    routes: {
        "*actions": "defaultRoute" // Backbone will try match the route above first
    }
	
});

var RegisterSectionView = Backbone.View.extend({
	initialize: function(options){
		this.collection = new App.Collections.RegistrationCollection();
		this.dataViewTemplate = Handlebars.compile($("#dataTemplate").html());
	},
	events: {
		"click .viewForm" : "onClickBtn",
		"click .viewData" : "onClickViewData"
	},
	onClickBtn: function(e) {
		var round = $(e.currentTarget).attr('data-round');
		appRouter.formView.newForm(parseInt(round));
	},
	onClickViewData: function(e) {
		e.preventDefault();
		var round = $(e.currentTarget).attr('data-round');
		
		this.collection.fetch({
			url: appUrl('/Registration/round/' + round),
			success:_.bind(function(collection, response, options) {
				var json = {};
				json.content = collection.toJSON();
				console.log(json);
				
				this.$el.find('#dataModalLabel').html('ข้อมูลการลงทะเบียนรอบที่'+ round);
				this.$el.find('#dataModalBody').html(this.dataViewTemplate(json));
				
				this.$el.find('#dataModal').modal('show');
		},this)});

		
		
	}
  });


var FormView =  Backbone.View.extend({
	initialize: function(options){
		this.entity = null;
		this.formViewTemplate = Handlebars.compile($("#formTemplate").html());
	},
	
	events: {
		"change .txtInput" : "onChangeTxtInput",
		"change .sltInput" : "onChangeSltInput",
		"click .chkInput" : "onClickChkInput",
		"click #saveBtn" : "onClickSaveBtn",
		"click #backBtn" : "onClickBackBtn"
	},
	onChangeTxtInput: function(e) {
		var field = $(e.currentTarget).attr('data-field');
		var value = $(e.currentTarget).val();
		
		
		this.entity.set(field, value);
		
	},
	onClickSaveBtn: function(e) {
		var m = this.entity;
		if(m.get('firstName') == null || m.get('lastName') == null 
				|| m.get('email') == null || m.get('phone') == null 
				|| m.get('workplace') == null || m.get('occupation') == null  ) {
			alert('กรุณากรอกข้อมูลให้ครบถ้วน');
			return;
		}
		

		this.entity.save(null, {
			success:_.bind(function(model, response, options) {

					
				alert("บันทึกข้อมูลแล้ว");
		},this)});
	},
	newForm: function(round) {
		this.entity = new App.Models.Registration();
		this.entity.set('round', round);
		
		this.$el.find('#registrationModalLabel').html('ลงทะเบียนรอบที่'+ round);
		this.$el.find('#registrationModalBody').html(this.formViewTemplate());
		
		this.$el.modal('show');
	}
});
	
	
	