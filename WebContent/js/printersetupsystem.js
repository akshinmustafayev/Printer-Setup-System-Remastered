function SetDataToItem(item_name, value)
{
	var item = document.getElementById(item_name);
	item.setAttribute('data-info', value);
}

function GetDataFromItem(item_name)
{
	var item = document.getElementById(item_name);
	return item.getAttribute('data-info');
}

function GetContextPath()
{
	return window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));
}

function BranchSearchPrinter()
{
	var printerbranch = GetDataFromItem("PSSBranchID");
	var printername = document.getElementById("PSSPrinterName");
	var resultform = document.getElementById("PSSResult");
	
	$.ajax({
		type:"GET",
		url : 'api/SearchPrinterByName',
		data : {
			printername : printername.value,
			printerbranch : printerbranch
		},
		success : function(responseText) 
		{
			var result = JSON.parse(responseText);
			var printersText = "";
			
			console.log(result);
			
			if(result.printers[0].printerid == -1)
			{
				resultform.innerHTML = "<div class=\"col mb-4\"><div class=\"card position-relative\"><div class=\"card-body\"><h5 class=\"card-title\">(Not found)</h5></div></div></div>";
			}
			else
			{
				resultform.innerHTML = "";
				for (var i = 0; i < result.printers.length; i++) 
				{
					printersText = printersText + "<div class=\"col mb-4\"><div class=\"card position-relative\"><img src=\"" + result.printers[i].printerimage + "\" class=\"card-img-top\" alt=\"Printer Image\"><div class=\"card-body\"><h5 class=\"card-title\">" + result.printers[i].printername + "</h5><p class=\"card-text\">" + result.printers[i].printerdescription + "</p><a href=\"" + GetContextPath() + "/printer?id=" + result.printers[i].printerid + "\" class=\"stretched-link\"></a></div></div></div>"
				}
				
				resultform.innerHTML = printersText;
			}
		},
	    error: function (request, status, error) {
	    	
	    }
	});
}

function BranchResetPrinter()
{
	var printerbranch = GetDataFromItem("PSSBranchID");
	var resultform = document.getElementById("PSSResult");
	var printername = document.getElementById("PSSPrinterName");
	printername.value = "";
	$.ajax({
		type:"GET",
		url : 'api/GetAllBranchPrinters',
		data : {
			printerbranch : printerbranch
		},
		success : function(responseText) 
		{
			var result = JSON.parse(responseText);
			var printersText = "";
			
			console.log(result);
			
			if(result.printers[0].printerid == -1)
			{
				resultform.innerHTML = "<div class=\"col mb-4\"><div class=\"card position-relative\"><div class=\"card-body\"><h5 class=\"card-title\">(empty)</h5></div></div></div>";
			}
			else
			{
				resultform.innerHTML = "";
				for (var i = 0; i < result.printers.length; i++) 
				{
					printersText = printersText + "<div class=\"col mb-4\"><div class=\"card position-relative\"><img src=\"" + result.printers[i].printerimage + "\" class=\"card-img-top\" alt=\"Printer Image\"><div class=\"card-body\"><h5 class=\"card-title\">" + result.printers[i].printername + "</h5><p class=\"card-text\">" + result.printers[i].printerdescription + "</p><a href=\"" + GetContextPath() + "/printer?id=" + result.printers[i].printerid + "\" class=\"stretched-link\"></a></div></div></div>"
				}
				
				resultform.innerHTML = printersText;
			}
		},
	    error: function (request, status, error) {
	    	
	    }
	});
}