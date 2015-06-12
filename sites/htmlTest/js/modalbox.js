var vip_count = 0;
var connection = "Online";

var vips = new Array(6);
vips[0] = new Array("Sylvester", "Stallone", "Male", "Movie");
vips[1] = new Array("Elvis", "Presley", "Male", "Music");
vips[2] = new Array("Marie", "Curie", "Female", "Science");
vips[3] = new Array("Andre", "Agassi", "Male", "Sport");
vips[4] = new Array("Arnold", "Schwarzenegger", "Male", "Politics");
vips[5] = new Array("Marilyn", "Monroe", "Female", "Other");

function setTxt(txt)
{
	
	GET = document.location.search.substr(1,document.location.search.length);
	if (GET == "First")
		document.getElementById("alertTextOK").firstChild.nodeValue = "Please specify 'First Name' value";
	else if (GET == "Last")
		document.getElementById("alertTextOK").firstChild.nodeValue = "Please specify 'Last Name' value";
	else
		document.getElementById("alertTextOK").firstChild.nodeValue = GET + " VIP(s) stored sucessfully"
}

function add(){
	if (document.getElementById("FirstName").value == "")
	{
		mDialogOK = window.open('ModalDialogOK.html?First','modalDialogOK', 'width=200,height=100');
		return;
	}
	if (document.getElementById("LastName").value == "")
	{
		mDialogOK = window.open('ModalDialogOK.html?Last','modalDialogOK', 'width=200,height=100');
		return;
	}
    var tbody = document.getElementById("VIPs").getElementsByTagName("TBODY")[0];
    var row = document.createElement("TR")
    var td1 = document.createElement("TD")
	field1 = document.createElement("input");
	field1.setAttribute("type","radio");
	field1.setAttribute("name","VIP");
	field1.setAttribute("id","VIP");
	field1.setAttribute("value", 1);
	field1.setAttribute("checked", true);
    td1.appendChild(field1);
	
    var td2 = document.createElement("TD")
    td2.appendChild (document.createTextNode(document.getElementById("FirstName").value))
    var td3 = document.createElement("TD")
    td3.appendChild (document.createTextNode(document.getElementById("LastName").value))
    var td4 = document.createElement("TD")
	var gen = "";
	if (document.getElementById("Gender").checked == true) 
		gen = "Female";
	else 
		gen = "Male";
    td4.appendChild (document.createTextNode(gen))
    var td5 = document.createElement("TD")
    td5.appendChild (document.createTextNode(document.getElementById("Category").value))
    row.appendChild(td1);
    row.appendChild(td2);
	row.appendChild(td3);
    row.appendChild(td4);
	row.appendChild(td5);
    tbody.appendChild(row);
	document.getElementById("Delete").disabled = false;
	vip_count++;
	setStatusString();
	document.getElementById("FirstName").value = "";
	document.getElementById("LastName").value = "";
	
  }
  
function del(){
	if(typeof(document.getElementById("form").VIP[0])=="undefined")
	{
		document.getElementById("VIPs").deleteRow(1);	
		document.getElementById("Delete").disabled = true;
	}
	else
	{
		var row_number = 0;
		var found=false;
		while (!found)
		{
			if(typeof(document.getElementById("form").VIP[row_number])=="undefined")
				return;
			if (document.getElementById("form").VIP[row_number].checked == true)
				found = true;
			else
				row_number++;
		}
		document.getElementById("VIPs").deleteRow(row_number + 1);
		
		if(typeof(document.getElementById("form").VIP[0])=="undefined")
			document.getElementById("form").VIP.checked = true;
		else
			document.getElementById("form").VIP[0].checked = true;
	}
	vip_count--;
	setStatusString();
}

function load()
{
	clr();	
	var tbody = document.getElementById("VIPs").getElementsByTagName("TBODY")[0];
	for (var i = 0; i<vips.length; ++i)
	{
		var row = document.createElement("TR")
		var td1 = document.createElement("TD")
		field1 = document.createElement("input");
		field1.setAttribute("type","radio");
		field1.setAttribute("name","VIP");
		field1.setAttribute("id","VIP");
		field1.setAttribute("value", 1);
		field1.setAttribute("checked", true);
		td1.appendChild(field1);
		var td2 = document.createElement("TD")
		td2.appendChild (document.createTextNode(vips[i][0]))
		var td3 = document.createElement("TD")
		td3.appendChild (document.createTextNode(vips[i][1]))
		var td4 = document.createElement("TD")
		td4.appendChild (document.createTextNode(vips[i][2]))
		var td5 = document.createElement("TD")
		td5.appendChild (document.createTextNode(vips[i][3]))
		row.appendChild(td1);
		row.appendChild(td2);
		row.appendChild(td3);
		row.appendChild(td4);
		row.appendChild(td5);
		tbody.appendChild(row);
	}
	document.getElementById("Delete").disabled = false;
    vip_count = vips.length;
	setStatusString();

}

function save()
{
	var ms = vip_count * 1500;
	ms += new Date().getTime();
	while (new Date() < ms){}
	mDialogOK = window.open('ModalDialogOK.html?' + vip_count,'modalDialogOK', 'width=200,height=100');
}

function clr()
{
	for (i=1; i<=vip_count; i++)
		document.getElementById("VIPs").deleteRow(1);
	vip_count = 0;
	document.getElementById("Delete").disabled = true;
	setStatusString();
}

function connect()
{
	if (document.getElementById("connect").value == "Connect...")
	{
		mDialogOK = window.open('ModalDialogOKCancel.html?' + vip_count,'modalDialogOKCancel', 'width=200,height=100');
	}
	else
	{
		connection = "Offline";
		setStatusString();
		document.getElementById("Load").disabled = true;
		document.getElementById("Save").disabled = true;
	}
}

function connectOKSelected()
{
	connection = "Connecting...";
	setStatusString();
	setTimeout("connected()",3000);
}

function connected()
{
	connection = "Online";
	setStatusString();
	document.getElementById("Load").disabled = false;
	document.getElementById("Save").disabled = false;
}

function setStatusString(con)
{
	document.getElementById("count").replaceChild(document.createTextNode("VIP count: " + vip_count), document.getElementById("count").firstChild);
}
