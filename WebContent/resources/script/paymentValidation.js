function paymentValidate(){
	var accountNumber = $('input[name=accountNumber]').val();
	var pin = $('input[name=pin]').val();
	var amount = $('input[name=amount]').val();
	
	var account = {
			"accountNumber" : $('input[name=accountNumber]').val(),
			"pin" : $('input[name=pin]').val(),
			"amount" : $('input[name=amount]').val()
	};

	//alert(JSON.stringify(account));
	
	$.ajax({
	      type: 'POST',
	      url: "http://192.168.9.154:8080/PaymentGWWeb/PaymentGatewayService/Inquiry",
	      data: JSON.stringify(account),
	      dataType: "json",
	      contentType: 'application/json; charset=utf-8',
	      success: function(data) { 
	    	  alert("Inquiry") 
	    	  var list = data == null ? [] : (data instanceof Array ? data : [data]);

	        	$.each(list, function(index, account) {
	                alert(account.accountName);
	            });
	    	},
	        error: function(data, textStatus, xhr) {
	        	//alert('post() error: ' + data.status);
	            console.log('post() error: ' + data.status);
	        }
	});
}