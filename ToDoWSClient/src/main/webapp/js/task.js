var ws_endpoint = "ws://localhost:8025/websockets/todos";
var ws;
$( document ).ready(function() {
	
	$("#add_button").click(function () {
	
		if ( $('#add_context').val().length === 0 || $('#add_priority').val().length === 0 || $('#add_project').val().length === 0) {
			alert("No se han rellenado los campos de añadir tarea");
		}
		else{
			//task = JSON.stringify({context: $('#add_context').val(),priority: //$('#add_priority').val(),project: $('#add_project').val()});
			var task = toJSON();
			console.log(task);
			addToDo(task);
		}
	});
	
	$("#remove_button").click(function () {
		var id = $('#remove').val();
		console.log(id);
		if(!isNaN(id)){//Para comprobar si es un valor numérico
			removeToDo(id);
		}
	});
	
	$("#list_button").click(function () {
		$('#ListToDo').find('tbody').empty();//Eliminar valores anteriores a la tabla
		getTaskList();
		});
	
	prepareSocket();
});

function toJSON(){
	return JSON.stringify({
	tarea: 0,
	contexto: $(add_context).val(),
	projecto: $(add_project).val(),
	prioridad: $(add_priority).val()
	});
}

function prepareSocket(){
	ws = new WebSocket(ws_endpoint);
	ws.onopen = function () {
		ws.send("Abriendo conexion con el servidor:"); 
	};

	ws.onmessage = function (response){
		console.log(response);
		console.log(response.data.substring(0,response.data.indexOf(":")));
		switch (response.data.substring(0,response.data.indexOf(":"))) {
		case "addTask":
			document.getElementById('text_add').innerHTML=response.data.substring(1+event.data.indexOf(":"),response.length);
			break;
		case "removeTask":
			document.getElementById('text_remove').innerHTML=response.data.substring(1+event.data.indexOf(":"),response.length);
			break;
		case "getTaskList":

			var respuesta = response.data.substr(response.data.indexOf(":")+1);

			respuesta = JSON.parse(respuesta);
			for (var i in respuesta){
				console.log(respuesta[i]);
				var row = $('<tr><td>' + respuesta[i].tarea + '</td><td>' + respuesta[i].contexto + '</td><td>' + respuesta[i].prioridad + '</td><td>' + respuesta[i].projecto +'</td></tr>');
				$('#ListToDo').find('tbody').append(row);
			}
			break;
		}
	};
	
	ws.onerror = function (){
		alert("Un error ha ocurrido con el servidor");
	};
}



function getTaskList(){
	ws.send("getTaskList:");
}

function addToDo(task){
	ws.send("addToDo:"+task);
	console.log("addToDo:"+task);
}

function removeToDo(id){
	ws.send("removeToDo:"+id);
}