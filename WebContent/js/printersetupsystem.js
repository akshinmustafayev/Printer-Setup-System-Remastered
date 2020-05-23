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

function SearchBranchPrinter(pssbranchid, pssprintername, pssresult)
{
	var printerbranch = GetDataFromItem(pssbranchid);
	var printername = document.getElementById(pssprintername);
	var resultform = document.getElementById(pssresult);
	
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

function SearchPrinter(pssprintername, pssresult)
{
	var printername = document.getElementById(pssprintername);
	var resultform = document.getElementById(pssresult);
	
	$.ajax({
		type:"GET",
		url : 'api/SearchPrinterByNameAll',
		data : {
			printername : printername.value
		},
		success : function(responseText) 
		{
			var result = JSON.parse(responseText);
			var printersText = "";
			
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

function ResetBranchPrinter(pssbranchid, pssresult)
{
	var printerbranch = GetDataFromItem(pssbranchid);
	var resultform = document.getElementById(pssresult);
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

function BranchReset(pssresult)
{
	var resultform = document.getElementById(pssresult);
	var branchname = document.getElementById("PSSBranchName");
	branchname.value = "";
	
	$.ajax({
		type:"GET",
		url : 'api/GetAllBranches',
		success : function(responseText) 
		{
			var result = JSON.parse(responseText);
			var branchesText = "";
			
			if(result.branches[0].branchid == -1)
			{
				resultform.innerHTML = "<div class=\"col mb-4\"><div class=\"card position-relative\"><div class=\"card-body\"><h5 class=\"card-title\">(empty)</h5></div></div></div>";
			}
			else
			{
				resultform.innerHTML = "";
				for (var i = 0; i < result.branches.length; i++) 
				{
					branchesText = branchesText + "<div class=\"col mb-4\"><div class=\"card position-relative\"><img src=\"" + result.branches[i].branchimage + "\" class=\"card-img-top\" alt=\"Printer Image\"><div class=\"card-body\"><h5 class=\"card-title\">" + result.branches[i].branchname + "</h5><p class=\"card-text\">" + result.branches[i].branchdescription + "</p><a href=\"" + GetContextPath() + "/branch?id=" + result.branches[i].branchid + "\" class=\"stretched-link\"></a></div></div></div>"
				}
				
				resultform.innerHTML = branchesText;
			}
		},
	    error: function (request, status, error) {
	    	
	    }
	});
}

function BranchSearch(pssbranchname, pssresult)
{
	var branchname = document.getElementById(pssbranchname);
	var resultform = document.getElementById(pssresult);
	
	$.ajax({
		type:"GET",
		url : 'api/SearchBranchByName',
		data : {
			branchname : branchname.value
		},
		success : function(responseText) 
		{
			var result = JSON.parse(responseText);
			var branchesText = "";
			
			if(result.branches[0].branchid == -1)
			{
				resultform.innerHTML = "<div class=\"col mb-4\"><div class=\"card position-relative\"><div class=\"card-body\"><h5 class=\"card-title\">(Not found)</h5></div></div></div>";
			}
			else
			{
				resultform.innerHTML = "";
				for (var i = 0; i < result.branches.length; i++) 
				{
					branchesText = branchesText + "<div class=\"col mb-4\"><div class=\"card position-relative\"><img src=\"" + result.branches[i].branchimage + "\" class=\"card-img-top\" alt=\"Printer Image\"><div class=\"card-body\"><h5 class=\"card-title\">" + result.branches[i].branchname + "</h5><p class=\"card-text\">" + result.branches[i].branchdescription + "</p><a href=\"" + GetContextPath() + "/branch?id=" + result.branches[i].branchid + "\" class=\"stretched-link\"></a></div></div></div>"
				}
				
				resultform.innerHTML = branchesText;
			}
		},
	    error: function (request, status, error) {
	    	
	    }
	});
}

function SearchShowPrinters()
{
	var printerscontent = document.getElementById("PSSPrintersContent");
	printerscontent.classList.remove("hidden");
	var printerscontent = document.getElementById("PSSBranchesContent");
	printerscontent.classList.add("hidden");
}

function SearchShowBranches()
{
	var printerscontent = document.getElementById("PSSPrintersContent");
	printerscontent.classList.add("hidden");
	var printerscontent = document.getElementById("PSSBranchesContent");
	printerscontent.classList.remove("hidden");
}