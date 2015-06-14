var API_BASE_URL = "http://147.83.7.157:8080/GelApp";

/*
Details about GelApp API on GitHub
 https://github.com/dsaqp1415g3/GelApp
*/

$("#button_to_delete").click(function(e) {
	e.preventDefault();	
	deleteHelado($("#id_helado_a_eliminar").val());
});


function deleteHelado(id_helado_a_eliminar) {

	var USERNAME = $.cookie('username');
	var PASSWORD = $.cookie('password');

	$.ajaxSetup({
		headers: { 'Authorization': "Basic "+ btoa(USERNAME+':'+PASSWORD) }
	});

	var url = API_BASE_URL + '/helados/' + id_helado_a_eliminar;
	
	$("#delete_helado_result").text('');

	$.ajax({
		url : url,
		type : 'DELETE',
		crossDomain : true,
		dataType : 'json'
        
	/*	statusCode: {
    		202: function() {$('<div class="alert alert-danger"> <strong>Ok!</strong> File deleted successfully </div>').appendTo($("#delete_helado_result"));}
    	} */
        
	}).done(function(data, status, jqxhr) {
		$('<div class="alert alert-success"> <strong>Ok!</strong> File deleted successfully</div>').appendTo($("#delete_helado_result"));
		window.location.reload();
  	}).fail(function() {
		$('<div class="alert alert-danger"> <strong>Oh!</strong> No existe ningún helado con esa ID! </div>').appendTo($("#delete_helado_result"));
	});

}


/*oooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo*/

$("#top1_caramelo").click(function(e) {
	e.preventDefault();   
    $("#topping_1").val('caramelo');
    $("#topping_1").text('Caramelo');
	console.log(topping_1.value);
	$('<button class="iceCream-btn" id="iceCream">' +
	'<span class="stick"></span>' +
	'<span class="nuts"></span>' +
	'<span class="coconut"></span>' +
	'<span class="nuts"></span>' +
	'<span class="bola">' +
	'<span class='+topping_1.value+'></span>' +
	'<span class=""></span>' +
	'<span class=""></span>' +
	'<span class=""></span>' +
	'<span class=""></span>' +
	'</span>' +
	'</button>').appendTo($('#mi_helado'));

});
$("#top1_chocolate_negro").click(function(e) {
	e.preventDefault();
	$("#topping_1").val('chocolate_negro');
    $("#topping_1").text('Chocolate Negro');
	$('<button class="iceCream-btn" id="iceCream">' +
	'<span class="stick"></span>' +
	'<span class="nuts"></span>' +
	'<span class="coconut"></span>' +
	'<span class="nuts"></span>' +
	'<span class="bola">' +
	'<span class='+topping_1.value+'></span>' +
	'<span class=""></span>' +
	'<span class=""></span>' +
	'<span class=""></span>' +
	'<span class=""></span>' +
	'</span>' +
	'</button>').appendTo($('#mi_helado'));
});
$("#top1_chocolate_blanco").click(function(e) {
	e.preventDefault();
	$("#topping_1").val('chocolate_blanco');
    $("#topping_1").text('Chocolate Blanco');
	$('<button class="iceCream-btn" id="iceCream">' +
	'<span class="stick"></span>' +
	'<span class="nuts"></span>' +
	'<span class="coconut"></span>' +
	'<span class="nuts"></span>' +
	'<span class="bola">' +
	'<span class='+topping_1.value+'></span>' +
	'<span class=""></span>' +
	'<span class=""></span>' +
	'<span class=""></span>' +
	'<span class=""></span>' +
	'</span>' +
	'</button>').appendTo($('#mi_helado'));
});
$("#top1_sirope_fresa").click(function(e) {
	e.preventDefault();
	$("#topping_1").val('sirope_fresa');
    $("#topping_1").text('Sirope de Fresa');
	$('<button class="iceCream-btn" id="iceCream">' +
	'<span class="stick"></span>' +
	'<span class="nuts"></span>' +
	'<span class="coconut"></span>' +
	'<span class="nuts"></span>' +
	'<span class="bola">' +
	'<span class='+topping_1.value+'></span>' +
	'<span class=""></span>' +
	'<span class=""></span>' +
	'<span class=""></span>' +
	'<span class=""></span>' +
	'</span>' +
	'</button>').appendTo($('#mi_helado'));
});
$("#top1_multicolor").click(function(e) {
	e.preventDefault();
	$("#topping_1").val('multicolor');
    $("#topping_1").text('Multicolor');
	$('<button class="iceCream-btn" id="iceCream">' +
	'<span class="stick"></span>' +
	'<span class="nuts"></span>' +
	'<span class="coconut"></span>' +
	'<span class="nuts"></span>' +
	'<span class="bola">' +
	'<span class="multicolorgreen"></span>' +
	'<span class="multicolorred"></span>' +
	'<span class="multicolorblue"></span>' +
	'<span class="multicolorwhite"></span>' +
	'</span>' +
	'</button>').appendTo($('#mi_helado'));
});
$("#capa2_fresa").click(function(e) {
	e.preventDefault();
	$("#capa_2").val('fresa');
    $("#capa_2").text('Fresa');
	$('<button class="iceCream-btn" id="iceCream">' +
	'<span class="stick"></span>' +
	'<span class="nuts"></span>' +
	'<span class="coconut"></span>' +
	'<span class="nuts"></span>' +
	'<span class="bola">' +
	'<span class='+topping_1.value+'></span>' +
	'<span class='+capa_2.value+'></span>' +
	'<span class=""></span>' +
	'<span class=""></span>' +
	'<span class=""></span>' +
	'</span>' +
	'</button>').appendTo($('#mi_helado'));
});
$("#capa2_nata").click(function(e) {
	e.preventDefault();
	$("#capa_2").val('nata');
    $("#capa_2").text('Nata');
	$('<button class="iceCream-btn" id="iceCream">' +
	'<span class="stick"></span>' +
	'<span class="nuts"></span>' +
	'<span class="coconut"></span>' +
	'<span class="nuts"></span>' +
	'<span class="bola">' +
	'<span class='+topping_1.value+'></span>' +
	'<span class='+capa_2.value+'></span>' +
	'<span class=""></span>' +
	'<span class=""></span>' +
	'<span class=""></span>' +
	'</span>' +
	'</button>').appendTo($('#mi_helado'));
});
$("#capa2_vainilla").click(function(e) {
	e.preventDefault();
	$("#capa_2").val('vainilla');
    $("#capa_2").text('Vainilla');
	$('<button class="iceCream-btn" id="iceCream">' +
	'<span class="stick"></span>' +
	'<span class="nuts"></span>' +
	'<span class="coconut"></span>' +
	'<span class="nuts"></span>' +
	'<span class="bola">' +
	'<span class='+topping_1.value+'></span>' +
	'<span class='+capa_2.value+'></span>' +
	'<span class=""></span>' +
	'<span class=""></span>' +
	'<span class=""></span>' +
	'</span>' +
	'</button>').appendTo($('#mi_helado'));
});
$("#capa2_chocolate").click(function(e) {
	e.preventDefault();
	$("#capa_2").val('chocolate');
    $("#capa_2").text('Chocolate');
	$('<button class="iceCream-btn" id="iceCream">' +
	'<span class="stick"></span>' +
	'<span class="nuts"></span>' +
	'<span class="coconut"></span>' +
	'<span class="nuts"></span>' +
	'<span class="bola">' +
	'<span class='+topping_1.value+'></span>' +
	'<span class='+capa_2.value+'></span>' +
	'<span class=""></span>' +
	'<span class=""></span>' +
	'<span class=""></span>' +
	'</span>' +
	'</button>').appendTo($('#mi_helado'));
});
$("#capa2_turron").click(function(e) {
	e.preventDefault();
	$("#capa_2").val('turron');
    $("#capa_2").text('Turrón');
	$('<button class="iceCream-btn" id="iceCream">' +
	'<span class="stick"></span>' +
	'<span class="nuts"></span>' +
	'<span class="coconut"></span>' +
	'<span class="nuts"></span>' +
	'<span class="bola">' +
	'<span class='+topping_1.value+'></span>' +
	'<span class='+capa_2.value+'></span>' +
	'<span class=""></span>' +
	'<span class=""></span>' +
	'<span class=""></span>' +
	'</span>' +
	'</button>').appendTo($('#mi_helado'));
});
$("#top3_caramelo").click(function(e) {
	e.preventDefault();
	$("#topping_3").val('caramelo');
    $("#topping_3").text('Caramelo');
	$('<button class="iceCream-btn" id="iceCream">' +
	'<span class="stick"></span>' +
	'<span class="nuts"></span>' +
	'<span class="coconut"></span>' +
	'<span class="nuts"></span>' +
	'<span class="bola">' +
	'<span class='+topping_1.value+'></span>' +
	'<span class='+capa_2.value+'></span>' +
	'<span class='+topping_3.value+'1></span>' +
	'<span class=""></span>' +
	'<span class=""></span>' +
	'</span>' +
	'</button>').appendTo($('#mi_helado'));
});
$("#top3_chocolate_negro").click(function(e) {
	e.preventDefault();
	$("#topping_3").val('chocolate_negro');
    $("#topping_3").text('Chocolate Negro');
	$('<button class="iceCream-btn" id="iceCream">' +
	'<span class="stick"></span>' +
	'<span class="nuts"></span>' +
	'<span class="coconut"></span>' +
	'<span class="nuts"></span>' +
	'<span class="bola">' +
	'<span class='+topping_1.value+'></span>' +
	'<span class='+capa_2.value+'></span>' +
	'<span class='+topping_3.value+'1></span>' +
	'<span class=""></span>' +
	'<span class=""></span>' +
	'</span>' +
	'</button>').appendTo($('#mi_helado'));
});
$("#top3_chocolate_blanco").click(function(e) {
	e.preventDefault();
	$("#topping_3").val('chocolate_blanco');
    $("#topping_3").text('Chocolate Blanco');
	$('<button class="iceCream-btn" id="iceCream">' +
	'<span class="stick"></span>' +
	'<span class="nuts"></span>' +
	'<span class="coconut"></span>' +
	'<span class="nuts"></span>' +
	'<span class="bola">' +
	'<span class='+topping_1.value+'></span>' +
	'<span class='+capa_2.value+'></span>' +
	'<span class='+topping_3.value+'1></span>' +
	'<span class=""></span>' +
	'<span class=""></span>' +
	'</span>' +
	'</button>').appendTo($('#mi_helado'));
});
$("#top3_sirope_fresa").click(function(e) {
	e.preventDefault();
	$("#topping_3").val('sirope_fresa');
    $("#topping_3").text('Sirope Fresa');
	$('<button class="iceCream-btn" id="iceCream">' +
	'<span class="stick"></span>' +
	'<span class="nuts"></span>' +
	'<span class="coconut"></span>' +
	'<span class="nuts"></span>' +
	'<span class="bola">' +
	'<span class='+topping_1.value+'></span>' +
	'<span class='+capa_2.value+'></span>' +
	'<span class='+topping_3.value+'1></span>' +
	'<span class=""></span>' +
	'<span class=""></span>' +
	'</span>' +
	'</button>').appendTo($('#mi_helado'));
});
$("#top3_multicolor").click(function(e) {
	e.preventDefault();
	$("#topping_3").val('multicolor');
    $("#topping_3").text('Multicolor');
	$('<button class="iceCream-btn" id="iceCream">' +
	'<span class="stick"></span>' +
	'<span class="nuts"></span>' +
	'<span class="coconut"></span>' +
	'<span class="nuts"></span>' +
	'<span class="bola">' +
	'<span class="multicolorgreen1"></span>' +
	'<span class="multicolorred1"></span>' +
	'<span class="multicolorblue1"></span>' +
	'<span class="multicolorwhite1"></span>' +
	'<span class='+capa_2.value+'></span>' +
	'<span class='+topping_3.value+'1></span>' +
	'<span class=""></span>' +
	'<span class=""></span>' +
	'</span>' +
	'</button>').appendTo($('#mi_helado'));
});
$("#capa4_fresa").click(function(e) {
	e.preventDefault();
	$("#capa_4").val('fresa');
    $("#capa_4").text('Fresa');
	$('<button class="iceCream-btn" id="iceCream">' +
	'<span class="stick"></span>' +
	'<span class="nuts"></span>' +
	'<span class="coconut"></span>' +
	'<span class="nuts"></span>' +
	'<span class="bola">' +
	'<span class='+topping_1.value+'></span>' +
	'<span class='+capa_2.value+'></span>' +
	'<span class='+topping_3.value+'1></span>' +
	'<span class='+capa_4.value+'1></span>' +
	'<span class=""></span>' +
	'</span>' +
	'</button>').appendTo($('#mi_helado'));
});
$("#capa4_nata").click(function(e) {
	e.preventDefault();
	$("#capa_4").val('nata');
    $("#capa_4").text('Nata');
	$('<button class="iceCream-btn" id="iceCream">' +
	'<span class="stick"></span>' +
	'<span class="nuts"></span>' +
	'<span class="coconut"></span>' +
	'<span class="nuts"></span>' +
	'<span class="bola">' +
	'<span class='+topping_1.value+'></span>' +
	'<span class='+capa_2.value+'></span>' +
	'<span class='+topping_3.value+'1></span>' +
	'<span class='+capa_4.value+'1></span>' +
	'<span class=""></span>' +
	'</span>' +
	'</button>').appendTo($('#mi_helado'));
});
$("#capa4_vainilla").click(function(e) {
	e.preventDefault();
	$("#capa_4").val('vainilla');
    $("#capa_4").text('Vainilla');
	$('<button class="iceCream-btn" id="iceCream">' +
	'<span class="stick"></span>' +
	'<span class="nuts"></span>' +
	'<span class="coconut"></span>' +
	'<span class="nuts"></span>' +
	'<span class="bola">' +
	'<span class='+topping_1.value+'></span>' +
	'<span class='+capa_2.value+'></span>' +
	'<span class='+topping_3.value+'1></span>' +
	'<span class='+capa_4.value+'1></span>' +
	'<span class=""></span>' +
	'</span>' +
	'</button>').appendTo($('#mi_helado'));
});
$("#capa4_chocolate").click(function(e) {
	e.preventDefault();
	$("#capa_4").val('chocolate');
    $("#capa_4").text('Chocolate');
	$('<button class="iceCream-btn" id="iceCream">' +
	'<span class="stick"></span>' +
	'<span class="nuts"></span>' +
	'<span class="coconut"></span>' +
	'<span class="nuts"></span>' +
	'<span class="bola">' +
	'<span class='+topping_1.value+'></span>' +
	'<span class='+capa_2.value+'></span>' +
	'<span class='+topping_3.value+'1></span>' +
	'<span class='+capa_4.value+'1></span>' +
	'<span class=""></span>' +
	'</span>' +
	'</button>').appendTo($('#mi_helado'));
});
$("#capa4_turron").click(function(e) {
	e.preventDefault();
	$("#capa_4").val('turron');
    $("#capa_4").text('Turrón');
	$('<button class="iceCream-btn" id="iceCream">' +
	'<span class="stick"></span>' +
	'<span class="nuts"></span>' +
	'<span class="coconut"></span>' +
	'<span class="nuts"></span>' +
	'<span class="bola">' +
	'<span class='+topping_1.value+'></span>' +
	'<span class='+capa_2.value+'></span>' +
	'<span class='+topping_3.value+'1></span>' +
	'<span class='+capa_4.value+'1></span>' +
	'<span class=""></span>' +
	'</span>' +
	'</button>').appendTo($('#mi_helado'));
});
$("#top5_caramelo").click(function(e) {
	e.preventDefault();   
    $("#topping_5").val('caramelo');
    $("#topping_5").text('Caramelo');
	$('<button class="iceCream-btn" id="iceCream">' +
	'<span class="stick"></span>' +
	'<span class="nuts"></span>' +
	'<span class="coconut"></span>' +
	'<span class="nuts"></span>' +
	'<span class="bola">' +
	'<span class='+topping_1.value+'></span>' +
	'<span class='+capa_2.value+'></span>' +
	'<span class='+topping_3.value+'1></span>' +
	'<span class='+capa_4.value+'1></span>' +
	'<span class='+topping_5.value+'2></span>' +
	'</span>' +
	'</button>').appendTo($('#mi_helado'));
});
$("#top5_chocolate_negro").click(function(e) {
	e.preventDefault();
	$("#topping_5").val('chocolate_negro');
    $("#topping_5").text('Chocolate Negro');
	$('<button class="iceCream-btn" id="iceCream">' +
	'<span class="stick"></span>' +
	'<span class="nuts"></span>' +
	'<span class="coconut"></span>' +
	'<span class="nuts"></span>' +
	'<span class="bola">' +
	'<span class='+topping_1.value+'></span>' +
	'<span class='+capa_2.value+'></span>' +
	'<span class='+topping_3.value+'1></span>' +
	'<span class='+capa_4.value+'1></span>' +
	'<span class='+topping_5.value+'2></span>' +
	'</span>' +
	'</button>').appendTo($('#mi_helado'));
});
$("#top5_chocolate_blanco").click(function(e) {
	e.preventDefault();
	$("#topping_5").val('chocolate_blanco');
    $("#topping_5").text('Chocolate Blanco');
	$('<button class="iceCream-btn" id="iceCream">' +
	'<span class="stick"></span>' +
	'<span class="nuts"></span>' +
	'<span class="coconut"></span>' +
	'<span class="nuts"></span>' +
	'<span class="bola">' +
	'<span class='+topping_1.value+'></span>' +
	'<span class='+capa_2.value+'></span>' +
	'<span class='+topping_3.value+'1></span>' +
	'<span class='+capa_4.value+'1></span>' +
	'<span class='+topping_5.value+'2></span>' +
	'</span>' +
	'</button>').appendTo($('#mi_helado'));
});
$("#top5_sirope_fresa").click(function(e) {
	e.preventDefault();
	$("#topping_5").val('sirope_fresa');
    $("#topping_5").text('Sirope de Fresa');
	$('<button class="iceCream-btn" id="iceCream">' +
	'<span class="stick"></span>' +
	'<span class="nuts"></span>' +
	'<span class="coconut"></span>' +
	'<span class="nuts"></span>' +
	'<span class="bola">' +
	'<span class='+topping_1.value+'></span>' +
	'<span class='+capa_2.value+'></span>' +
	'<span class='+topping_3.value+'1></span>' +
	'<span class='+capa_4.value+'1></span>' +
	'<span class='+topping_5.value+'2></span>' +
	'</span>' +
	'</button>').appendTo($('#mi_helado'));
});
$("#top5_multicolor").click(function(e) {
	e.preventDefault();
	$("#topping_5").val('multicolor');
    $("#topping_5").text('Multicolor');
	$('<button class="iceCream-btn" id="iceCream">' +
	'<span class="stick"></span>' +
	'<span class="nuts"></span>' +
	'<span class="coconut"></span>' +
	'<span class="nuts"></span>' +
	'<span class="bola">' +
	'<span class='+topping_1.value+'></span>' +
	'<span class='+capa_2.value+'></span>' +
	'<span class='+topping_3.value+'1></span>' +
	'<span class='+capa_4.value+'1></span>' +
	'<span class="multicolorgreen2"></span>' +
	'<span class="multicolorred2"></span>' +
	'<span class="multicolorblue2"></span>' +
	'<span class="multicolorwhite2"></span>' +
	'</span>' +
	'</button>').appendTo($('#mi_helado'));
});

/*-----------------------------------------------------------*/
$("#button_to_create").click(function(e) {
	e.preventDefault();

	var user_id = $.cookie('user_id');

    var newIce = new Object();
    
    newIce.nombreHelado = $("#nombre_helado").val();   
    newIce.capa1Topping = $("#topping_1").val();  
    newIce.capa2Helado = $("#capa_2").val();
    newIce.capa3Topping = $("#topping_3").val();
    newIce.capa4Helado = $("#capa_4").val();
    newIce.capa5Topping = $("#topping_5").val();
    newIce.autorid = user_id;
	createIce(newIce);
     
});

function createIce(newIce) {
    
	var url = API_BASE_URL + '/helados';
	var data = JSON.stringify(newIce);

	$("#create_result").text('');

	console.log(newIce.nombreHelado);

	if( newIce.nombreHelado == "" || newIce.nombreHelado == " " || newIce.nombreHelado == "  "|| newIce.nombreHelado == "   "
	    || newIce.capa2Helado == "" && newIce.capa4Helado == ""){
		console.log("Nombre vacío");
		$('<div class="alert alert-danger"> <strong>Oh!</strong> Introduce un nombre y al menos una capa de sabor </div>').appendTo($("#create_result"));

	}
	else
	{
		$.ajax({
			url : url,
			type : 'POST',
			crossDomain : true,
			dataType : 'json',
			data : data,
			contentType : 'application/vnd.gelapp.api.helado+json'
		}).done(function(data, status, jqxhr) {
			$("#create_result").empty("#create_result");
			$('<div class="alert alert-success"> <strong>¡Hecho!</strong></div>').appendTo($("#create_result"));
			window.location.reload();
		}).fail(function() {
			$("#create_result").empty("#create_result");
			$('<div class="alert alert-danger"> <strong>Oh!</strong> Ese nombre ya está en uso </div>').appendTo($("#create_result"));
		});



	}



}


/*ooooooooooooooooo MIS HELADOS ooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo*/
$(document).ready(function(){
	getMisHelados();
});

function getMisHelados() {

	var USERNAME = $.cookie('username');
	var PASSWORD = $.cookie('password');

	$.ajaxSetup({
		headers: { 'Authorization': "Basic "+ btoa(USERNAME+':'+PASSWORD) }
	});

	var url = API_BASE_URL + '/helados/user/' +USERNAME;

	$("#mis_helados_result").text('');
	
	$.ajax({
		url : url,
		type : 'GET',
		crossDomain : true,
		dataType : 'json'
	}).done(function(data, status, jqxhr) {
				var bigdata = data.helados;
				
				$.each(bigdata, function(i, h) {
					var helado = h;
                    
                    var dc = new Date(helado.creationTimestamp);        
                    var _mes=dc.getMonth()+1;
                    var _dia=dc.getDate();
                    var _anyo=dc.getFullYear();
                    var _hora = dc.getHours();
                    var _minuto = dc.getMinutes();
                    var _segundo = dc.getSeconds();
                              
                    $('<div class="col-md-4 text-center">                                                                                                                                                      ' +
					'<img class="img-circle" src="images/mis_helados.jpg">  ' +
					'<h2>' + helado.nombreHelado + '</h2>    <div class="panel-body">' +
					'<div class="list-group">   <a  style="background-color:coral" class="list-group-item active"> Sabores </a>   ' +
					'<a class="list-group-item disabled"> Capa Topping 1: ' +helado.capa1Topping+ ' </a> ' +
					'<a class="list-group-item disabled"> Capa Helado 2:  ' +helado.capa2Helado+ ' </a>  ' +
					'<a class="list-group-item disabled"> Capa Topping 3:  ' +helado.capa3Topping+ ' </a> ' +
					'<a class="list-group-item disabled"> Capa Helado 4:  ' +helado.capa4Helado+ ' </a>    ' +
					'<a class="list-group-item disabled"> Capa Topping 5: ' +helado.capa5Topping+ ' </a> ' +
					'<a class="list-group-item disabled"><strong> Creado el '  + _dia+"-"+_mes+"-"+_anyo +" a las "+_hora+":"+_minuto+":"+_segundo+ '</strong> </a>   ' +
					'</div>  </div>     ' +
					'<button type="button" class="btn btn-info" id="#comprar_en_mis_helados">Comprar »</button>     ' +
					'<button onclick="deleteHelado('+helado.heladoid+')" style="background-color:red" type="button" class="btn btn-info" id="button_to_delete">Borrar »</button></br></br></br></br>   ' +
					'<span class="output-group-addon" id="mishelados_comprar">  </span></br> ').appendTo($('#mis_helados_result'));
                    $('</div>').appendTo($('#mis_helados_result'));

				});
				

	}).fail(function() {
		$("#mis_helados_result").text("¡Todavía no has creado ningún helado!");
	});

}



/*oooooooooooooooooooooooooo TODOS LOS HELADOS oooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo*/
$(document).ready(function(){
	getHelados();
});
function getHelados() {
	var url = API_BASE_URL + '/helados';
	$("#helados_result").text('');
	$.ajax({
		url : url,
		type : 'GET',
		crossDomain : true,
		dataType : 'json'
	}).done(function(data, status, jqxhr) {
				var bigdata = data.helados;
				$.each(bigdata, function(i, h) {
					var helado = h;
                    var dc = new Date(helado.creationTimestamp);        
                    var _mes=dc.getMonth()+1;
                    var _dia=dc.getDate();
                    var _anyo=dc.getFullYear();
                    var _hora = dc.getHours();
                    var _minuto = dc.getMinutes();
                    var _segundo = dc.getSeconds();
                    $('<div class="col-md-4 text-center">' +
					'<img class="img-circle" src="images/nuestros_helados.jpg">     ' +
					'<h2>' + helado.nombreHelado + '</h2>          ' +
					'<div class="panel-body"> <div class="list-group"> <a class="list-group-item active"> Sabores </a> ' +
					'<a class="list-group-item disabled"> Capa Topping 1: ' +helado.capa1Topping+ ' </a> ' +
					'<a class="list-group-item disabled"> Capa Helado 2:  ' +helado.capa2Helado+ ' </a>' +
					'<a class="list-group-item disabled"> Capa Topping 3:  ' +helado.capa3Topping+ ' </a> ' +
					'<a class="list-group-item disabled"> Capa Helado 4:  ' +helado.capa4Helado+ ' </a>' +
					'<a class="list-group-item disabled"> Capa Topping 5: ' +helado.capa5Topping+ ' </a>' +
					'<a class="list-group-item disabled"> <strong>Votos: </strong> ' +helado.votos+ ' </a> ' +
					'<a class="list-group-item disabled"> <strong>Autor del helado: </strong> ' +helado.autor+ ' </a>' +
					'<a class="list-group-item disabled"><strong> Creado el '  + _dia+"-"+_mes+"-"+_anyo +" a las "+_hora+":"+_minuto+":"+_segundo+ '</strong> </a>' +
					'</div>  </div>    ' +
					'<button type="button" class="btn btn-info" id="#comprar_en_mis_helados">Comprar »</button>    ' +
					'<button id="button_to_vote" onclick="voteHelado('+helado.heladoid+')" style="background-color:green" type="button" class="btn btn-info"> Votar »</button></br></br></br></br>' +
					'</br> ').appendTo($('#helados_result'));
                    $('</div>').appendTo($('#helados_result'));
				});
	}).fail(function() {
		$("#helados_result").text("¡Todavía no has creado ningún helado!");
	});
}


/*ooooooooooooooooooooooooooo HELADOS DE UN USUARIO ooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo*/

$("#button_to_get_helados_por_usuario").click(function(e) {
	e.preventDefault();

    var user = $("#user_helado").val();

    console.log($("#user_helado").val());

	getHelados_by_user(user);
});

function getHelados_by_user(user) {
    
	var url = API_BASE_URL + '/helados/user/' +user;
	$("#helados_result").text('');
	
	$.ajax({
		url : url,
		type : 'GET',
		crossDomain : true,
		dataType : 'json'
	}).done(function(data, status, jqxhr) {
				var bigdata = data.helados;
				
				$.each(bigdata, function(i, h) {
					var helado = h;
                    
                    var dc = new Date(helado.creationTimestamp);        
                    var _mes=dc.getMonth()+1;
                    var _dia=dc.getDate();
                    var _anyo=dc.getFullYear();
                    var _hora = dc.getHours();
                    var _minuto = dc.getMinutes();
                    var _segundo = dc.getSeconds();    
                    
                              
                    $('<div class="col-md-4 text-center">' +
					'<img class="img-circle" src="images/helados_autor.jpg">' +
					'<h2>' + helado.nombreHelado + '</h2>' +
					'<div class="panel-body"> <div class="list-group"> <a class="list-group-item active"> Sabores </a>' +
					'<a class="list-group-item disabled"> Capa Topping 1: ' +helado.capa1Topping+ ' </a> ' +
					'<a class="list-group-item disabled"> Capa Helado 2:  ' +helado.capa2Helado+ ' </a>' +
					'<a class="list-group-item disabled"> Capa Topping 3:  ' +helado.capa3Topping+ ' </a> ' +
					'<a class="list-group-item disabled"> Capa Helado 4:  ' +helado.capa4Helado+ ' </a>' +
					'<a class="list-group-item disabled"> Capa Topping 5: ' +helado.capa5Topping+ ' </a> ' +
					'<a class="list-group-item disabled"><strong> Creado el '  + _dia+"-"+_mes+"-"+_anyo +" a las "+_hora+":"+_minuto+":"+_segundo+ '</strong> </a>' +
					'</div>  </div>' +
					'<button type="button" class="btn btn-info" id="#comprar_en_mis_helados">Comprar »</button>' +
					'<button onclick="voteHelado('+helado.heladoid+')" style="background-color:green" type="button" class="btn btn-info"> Votar »</button></br></br></br></br>' +
					'<span class="output-group-addon" id="mishelados_comprar">' +
					'</span></br> ').appendTo($('#helados_result'));
                    $('</div>').appendTo($('#helados_result'));
                             
				});
				

	}).fail(function() {
		$('<div class="alert alert-danger"> <strong>Oh!</strong> Este autor todavía no ha creado helados o no existe </div>').appendTo($("#helados_result"));
	});

}

/*ooooooooooooooooooooooooooo HELADOS POR SABOR oooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo*/

$("#button_to_get_helados_por_sabor").click(function(e) {
	e.preventDefault();

    var flavour = $("#sabor_helado").val();

	getHelados_by_flavour(flavour);
});

function getHelados_by_flavour(flavour) {
    
	var url = API_BASE_URL + '/helados/missabores/' +flavour;
	$("#helados_result").text('');
	
	$.ajax({
		url : url,
		type : 'GET',
		crossDomain : true,
		dataType : 'json'
	}).done(function(data, status, jqxhr) {
				var bigdata = data.helados;
				
				$.each(bigdata, function(i, h) {
					var helado = h;
                    
                    var dc = new Date(helado.creationTimestamp);        
                    var _mes=dc.getMonth()+1;
                    var _dia=dc.getDate();
                    var _anyo=dc.getFullYear();
                    var _hora = dc.getHours();
                    var _minuto = dc.getMinutes();
                    var _segundo = dc.getSeconds();    
                    
                              
                    $('<div class="col-md-4 text-center">                                                                                                                       <img class="img-circle" src="images/helados_sabor.jpg">                                                                                                        <h2>' + helado.nombreHelado + '</h2>                                                                                                  <div class="panel-body"> <div class="list-group"> <a class="list-group-item active"> Sabores </a>                                                        <a class="list-group-item disabled"> Capa Topping 1: ' +helado.capa1Topping+ ' </a>                                                              <a class="list-group-item disabled"> Capa Helado 2:  ' +helado.capa2Helado+ ' </a>                                                                 <a class="list-group-item disabled"> Capa Topping 3:  ' +helado.capa3Topping+ ' </a>                                                             <a class="list-group-item disabled"> Capa Helado 4:  ' +helado.capa4Helado+ ' </a>                                                                  <a class="list-group-item disabled"> Capa Topping 5: ' +helado.capa5Topping+ ' </a>                                                                                                                                  <a class="list-group-item disabled"> <strong>Autor del helado: </strong> ' +helado.autor+ ' </a>                                                  <a class="list-group-item disabled"><strong> Creado el '  + _dia+"-"+_mes+"-"+_anyo +" a las "+_hora+":"+_minuto+":"+_segundo+ '</strong> </a>                                                                                                                                                                                                                                                                               </div>  </div>                                                                                                                           <button type="button" class="btn btn-info" id="#comprar_en_mis_helados">Comprar »</button>                                                                                                                           <button onclick="voteHelado('+helado.heladoid+')" style="background-color:green" type="button" class="btn btn-info"> Votar »</button></br></br></br></br>                      <span class="output-group-addon" id="mishelados_comprar">  </span></br> ').appendTo($('#helados_result'));
                    $('</div>').appendTo($('#helados_result'));
				});
				

	}).fail(function() {
        $('<div class="alert alert-danger"> <strong>Oh!</strong> No disponemos de helados con ese sabor </div>').appendTo($("#helados_result"));
	});

}


/*oooooooooooooooooooooooooooo HELADOS POR NOMBRE oooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo*/

$("#button_to_get_helados_por_nombre").click(function(e) {
	e.preventDefault();

	var name = $("#helado_nombre").val();

	console.log($("#helado_nombre").val());

	getHelados_by_name(name);
});

function getHelados_by_name(name) {

	var url = API_BASE_URL + '/helados/nombre-helado/' +name;
	$("#helados_result").text('');

	$.ajax({
		url : url,
		type : 'GET',
		crossDomain : true,
		dataType : 'json'
	}).done(function(data, status, jqxhr) {
		var bigdata = data.helados;

		$.each(bigdata, function(i, h) {
			var helado = h;

			var dc = new Date(helado.creationTimestamp);
			var _mes=dc.getMonth()+1;
			var _dia=dc.getDate();
			var _anyo=dc.getFullYear();
			var _hora = dc.getHours();
			var _minuto = dc.getMinutes();
			var _segundo = dc.getSeconds();


			$('<div class="col-md-4 text-center">                                                                                                                       <img class="img-circle" src="images/helados_nombre.jpg">                                                                                                        <h2>' + helado.nombreHelado + '</h2>                                                                                                  <div class="panel-body"> <div class="list-group"> <a class="list-group-item active"> Sabores </a>                                                        <a class="list-group-item disabled"> Capa Topping 1: ' +helado.capa1Topping+ ' </a>                                                                <a class="list-group-item disabled"> Capa Helado 2:  ' +helado.capa2Helado+ ' </a>                                                                  <a class="list-group-item disabled"> Capa Topping 3:  ' +helado.capa3Topping+ ' </a>                                                                <a class="list-group-item disabled"> Capa Helado 4:  ' +helado.capa4Helado+ ' </a>                                                                  <a class="list-group-item disabled"> Capa Topping 5: ' +helado.capa5Topping+ ' </a>       <a class="list-group-item disabled"><strong> Creado el '  + _dia+"-"+_mes+"-"+_anyo +" a las "+_hora+":"+_minuto+":"+_segundo+ '</strong> </a>                                                                                                                                                                                                                                                                                  </div>  </div>                                                                                                                                      <button type="button" class="btn btn-info" id="#comprar_en_mis_helados">Comprar »</button>                                                                              <button onclick="voteHelado('+helado.heladoid+')" style="background-color:green" type="button" class="btn btn-info"> Votar »</button></br></br></br></br>                      <span class="output-group-addon" id="mishelados_comprar">  </span></br> ').appendTo($('#helados_result'));
			$('</div>').appendTo($('#helados_result'));

		});


	}).fail(function() {
		$('<div class="alert alert-danger"> <strong>Oh!</strong> Este autor todavía no ha creado helados o no existe </div>').appendTo($("#helados_result"));
	});

}



/*oooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo*/

$(document).ready(function(){
	getRanking();
});


function getRanking() {
	var url = API_BASE_URL + '/helados/ranking';
	$("#ranking_space").text('');

	$.ajax({
		url : url,
		type : 'GET',
		crossDomain : true,
		dataType : 'json'
	}).done(function(data, status, jqxhr) {
		var bigdata = data.helados;


		console.log(bigdata);

		$.each(bigdata, function(i, h) {
			var helado = h;

			var dc = new Date(helado.creationTimestamp);
			var _mes=dc.getMonth()+1;
			var _dia=dc.getDate();
			var _anyo=dc.getFullYear();
			var _hora = dc.getHours();
			var _minuto = dc.getMinutes();
			var _segundo = dc.getSeconds();


			if (i==0){
				$('<div class="featurette">' +
				'<img class="featurette-image img-circle pull-right" src="images/1.jpg">' +
				'<h2 class="featurette-heading"> #1: '+helado.nombreHelado+' <span class="text-muted"> '+helado.votos+' votos </span></h2> ' +
				'<p class="lead"> En primera posición tenemos el helado '+helado.nombreHelado+' de <strong>'+helado.autor+'  </p>' +
				'<p class="lead"> Helado de '+helado.capa2Helado+' y '+helado.capa4Helado+'  </p>' +
				'<p class="lead"> Complementado con los toppings de '+helado.capa1Topping+', '+helado.capa3Topping+' y '+helado.capa5Topping+'.</p>' +
				'<p class="lead"><strong> Creado el '  + _dia+"-"+_mes+"-"+_anyo +" a las "+_hora+":"+_minuto+":"+_segundo+ '</strong> </a>' +
				'</div>  </div>     ' +
				'<button type="button" class="btn btn-info" id="#comprar">Comprar »</button>     ' +
				'</div>').appendTo($('#ranking_space'));
				$('<hr class="featurette-divider">').appendTo($('#ranking_space'));
			}

			if (i==1){
				$('<div class="featurette">' +
				'<img class="featurette-image img-circle pull-left" src="images/2.jpg">' +
				'<h2 class="featurette-heading"> #2: '+helado.nombreHelado+' <span class="text-muted"> '+helado.votos+' votos </span></h2> ' +
				'<p class="lead"> En segundo lugar tenemos el helado '+helado.nombreHelado+' de <strong>'+helado.autor+'  </p>' +
				'<p class="lead"> Helado de '+helado.capa2Helado+' y '+helado.capa4Helado+'  </p>' +
				'<p class="lead"> Complementado con los toppings de '+helado.capa1Topping+', '+helado.capa3Topping+' y '+helado.capa5Topping+'.</p>' +
				'<p class="lead"><strong> Creado el '  + _dia+"-"+_mes+"-"+_anyo +" a las "+_hora+":"+_minuto+":"+_segundo+ '</strong> </a>' +
				'</div>  </div>     ' +
				'<button type="button" class="btn btn-info" id="#comprar">Comprar »</button>     ' +
				'</div>').appendTo($('#ranking_space'));
				$('<hr class="featurette-divider">').appendTo($('#ranking_space'));
			}

			if (i==2){
				$('<div class="featurette">' +
				'<img class="featurette-image img-circle pull-right" src="images/3.jpg">' +
				'<h2 class="featurette-heading"> #3: '+helado.nombreHelado+' <span class="text-muted"> '+helado.votos+' votos </span></h2> ' +
				'<p class="lead"> En tercer lugar tenemos el helado '+helado.nombreHelado+' de <strong>'+helado.autor+'  </p>' +
				'<p class="lead"> Helado de '+helado.capa2Helado+' y '+helado.capa4Helado+'  </p>' +
				'<p class="lead"> Complementado con los toppings de '+helado.capa1Topping+', '+helado.capa3Topping+' y '+helado.capa5Topping+'.</p>' +
				'<p class="lead"><strong> Creado el '  + _dia+"-"+_mes+"-"+_anyo +" a las "+_hora+":"+_minuto+":"+_segundo+ '</strong> </a>' +
				'</div>  </div>     ' +
				'<button type="button" class="btn btn-info" id="#comprar">Comprar »</button>     ' +
				'</div>').appendTo($('#ranking_space'));
				$('<hr class="featurette-divider">').appendTo($('#ranking_space'));
			}

			if (i==3){
				$('<div class="featurette">' +
				'<img class="featurette-image img-circle pull-left" src="images/4.jpg">' +
				'<h2 class="featurette-heading"> #4: '+helado.nombreHelado+' <span class="text-muted"> '+helado.votos+' votos </span></h2> ' +
				'<p class="lead"> En cuarto lugar tenemos el helado '+helado.nombreHelado+' de <strong>'+helado.autor+'  </p>' +
				'<p class="lead"> Helado de '+helado.capa2Helado+' y '+helado.capa4Helado+'  </p>' +
				'<p class="lead"> Complementado con los toppings de '+helado.capa1Topping+', '+helado.capa3Topping+' y '+helado.capa5Topping+'.</p>' +
				'<p class="lead"><strong> Creado el '  + _dia+"-"+_mes+"-"+_anyo +" a las "+_hora+":"+_minuto+":"+_segundo+ '</strong> </a>' +
				'</div>  </div>     ' +
				'<button type="button" class="btn btn-info" id="#comprar">Comprar »</button>     ' +
				'</div>').appendTo($('#ranking_space'));
				$('<hr class="featurette-divider">').appendTo($('#ranking_space'));
			}

			if (i==4){
				$('<div class="featurette">' +
				'<img class="featurette-image img-circle pull-right" src="images/5.jpg">' +
				'<h2 class="featurette-heading"> #5: '+helado.nombreHelado+' <span class="text-muted"> '+helado.votos+' votos </span></h2> ' +
				'<p class="lead"> En última posición tenemos el helado '+helado.nombreHelado+' de <strong>'+helado.autor+'  </p>' +
				'<p class="lead"> Helado de '+helado.capa2Helado+' y '+helado.capa4Helado+'  </p>' +
				'<p class="lead"> Complementado con los toppings de '+helado.capa1Topping+', '+helado.capa3Topping+' y '+helado.capa5Topping+'.</p>' +
				'<p class="lead"><strong> Creado el '  + _dia+"-"+_mes+"-"+_anyo +" a las "+_hora+":"+_minuto+":"+_segundo+ '</strong> </a>' +
				'</div>  </div>     ' +
				'<button type="button" class="btn btn-info" id="#comprar">Comprar »</button>     ' +
				'</div>').appendTo($('#ranking_space'));
				$('<hr class="featurette-divider">').appendTo($('#ranking_space'));
			}





		});






	}).fail(function() {
		$("#ranking_result").text("¡El ranking todavía está vacío!");
	});


}

/*oooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo*/

function voteHelado(id_helado_a_votar) {
    
    var url = API_BASE_URL + '/votos';

	var id_usuario = $.cookie('user_id');
	var info_voto = new Object();

	info_voto.id_usuario = id_usuario;
	info_voto.id_helado = id_helado_a_votar;	

	var data = JSON.stringify(info_voto);

	$.ajax({
		url : url,
		type : 'POST',
		crossDomain : true,
		dataType : 'json',
		data : data,
		contentType : 'application/vnd.gelapp.api.votos+json'
		/*	statusCode: {
		 202: function() {$('<div class="alert alert-danger"> <strong>Ok!</strong> File deleted successfully </div>').appendTo($("#delete_helado_result"));}
		 } */
	}).done(function(data, status, jqxhr) {
		console.log("voto añadido correctamente");       
        window.location.reload();
	}).fail(function() {
		console.log("no se ha podido añadir el voto");
        console.log(data);
		if($.cookie('username'))
		{
			alert("¡No puedes votar dos veces el mismo helado!");
		}
        else
		{
			alert("¡Debes inicar sesión para votar el helado!");
		}
	});

}



/*oooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo*/

$(document).ready(function(){
	getCookie();
});


function getCookie() {

	if($.cookie('username')) {
		console.log("logeado");
		var user_tag = $.cookie('username');
	    $('#login_info').html('<a href="logout.html"><strong> '+user_tag+' </strong></a>');
    }
	else
	{
		console.log("no logeado")
		$('#login_info').html('<a href="login.html"><strong> Iniciar sesión - Registrarse </strong></a>');
		$('#create_result').html('<a href="login.html"><strong> Debes iniciar sesión antes de crear helados </strong></a>');
		$('#mis_helados_result').html('<a align="center" href="login.html"><strong> Para ver tu lista de helados debes iniciar sesión o registrarte </strong></a>');
		document.getElementById('button_to_create').style.visibility='hidden';
		/*document.getElementById('button_to_vote').style.visibility='hidden';*/
	}

}

/*oooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo*/
$("#registrarse").click(function(e) {
	e.preventDefault();
	if($("#login_usuario").val() == "" || $("#login_contrasena").val() == "")
	{
		if($("#login_usuario").val() == "")
		{
			document.getElementById('login_usuario').style.background='#F6B5B5';
			$('#login_usuario').attr('placeholder','Usuario...');
		}
		if($("#login_contrasena").val() == "")
		{
			document.getElementById('login_contrasena').style.background='#F6B5B5';
			$('#login_contrasena').attr('placeholder','Contraseña...');
		}
	}
	else
	{
		var login = new Object();
		login.username = $("#login_usuario").val();
		login.password = $("#login_contrasena").val();
		register(login);
	}
});

function register(login)
{
	console.log(login);
	var url = API_BASE_URL + '/users';
	var data = JSON.stringify(login);

	$.ajax({
		url : url,
		type : 'POST',
		crossDomain : true,
		contentType : 'application/vnd.gelapp.api.user+json',
		dataType : 'json',
		data : data
	}).done(function(data, status, jqxhr) {
        var inf = data;
		/*alert("¡Bienvenido "+inf.username+"! Ya puedes iniciar sesión");*/
		log(login);

  	}).fail(function() {
		alert("Error al registrarse: Nombre de usuario ya en uso");
	});
}


/*oooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo*/

$("#login").click(function(e) {
	e.preventDefault();
	if($("#login_usuario").val() == "" || $("#login_contrasena").val() == "")
	{
		if($("#login_usuario").val() == "")
		{
			document.getElementById('login_usuario').style.background='#F6B5B5';
			$('#login_usuario').attr('placeholder','Usuario...');
		}
		if($("#login_contrasena").val() == "")
		{
			document.getElementById('login_contrasena').style.background='#F6B5B5';
			$('#login_contrasena').attr('placeholder','Contraseña...');
		}
	}
	else
	{
		var login = new Object();
		login.username = $("#login_usuario").val();
		login.password = $("#login_contrasena").val();
		log(login);
	}
});

function log(login)
{
	console.log(login);
	var url = API_BASE_URL + '/users/login';
	var data = JSON.stringify(login);

	$.ajax({
		url : url,
		type : 'POST',
		crossDomain : true,
		contentType : 'application/vnd.gelapp.api.user+json',
		dataType : 'json',
		data : data
	}).done(function(data, status, jqxhr) {

		var inf = data;

		if(inf.loginSuccesful!= true){
			alert("¡Usuario o contraseña incorrectos!");
		}
		else{

			var user_id_login = inf.usuarioid;
			var inputname = $('#login_usuario').val();
			var inputpass  = $('#login_contrasena').val();

			$.cookie('username', inputname, { expires: 1 });
			var currentusr = $.cookie('username');

			$.cookie('password', inputpass, { expires: 1 });
			var currentpss = $.cookie('pasword');

			$.cookie('user_id', user_id_login, { expires: 1 });
			var user_id_log = $.cookie('user_id');

			console.log(user_id_log);
			console.log(currentusr);
			console.log(currentpss);

			alert("¡Bienvenido "+inf.username+"!");
			window.location = "index.html"

		}


	}).fail(function() {
		alert("Usuario y/o contraseña incorrectos");
	});
}


/*oooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo*/


$('#logout').on('click', function(e){
	e.preventDefault();
	if(($.removeCookie('username'))&&($.removeCookie('password'))&&($.removeCookie('user_id'))){
		alert("¡Hasta pronto!");
		/*$('#logoutcontainer').html('<strong> ¡Te esperamos pronto! </strong>');
		window.setTimeout('location.reload()', 1000); // refresh after 2 sec*/
		window.location = "index.html"
	}
	else
	{
		alert("¡Antes debes iniciar sesión");
	}
});

/*oooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo*/

$("#comprar_en_mis_helados").click(function(e) {
	e.preventDefault();	
	comprarHelado($("#helado_id").val());
});


function comprarHelado(helado_id) {
	var url = API_BASE_URL + '/helados/' + helado_id;
	
	$("#mishelados_comprar").text('');

	$.ajax({
		url : url,
		type : 'GET',
		crossDomain : true,
		dataType : 'json'
	/*	statusCode: {
    		202: function() {$('<div class="alert alert-danger"> <strong>Ok!</strong> File deleted successfully </div>').appendTo($("#delete_helado_result"));}
    	} */
        
	}).done(function(data, status, jqxhr) {
		$('<div class="alert alert-success"> <strong>¡Gracias!</strong> Puedes pasar a recogerlo a tu tienda GelApp más cercana </div>').appendTo($("#mishelados_comprar"));
        alert("<strong>¡Gracias!</strong> Puedes pasar a recogerlo a tu tienda GelApp más cercana");
  	}).fail(function() {
		$('<div class="alert alert-danger"> <strong>Oh!</strong> El helado que quieres comprar no existe </div>').appendTo($("#mishelados_comprar"));
        alert("<strong>¡Oh!</strong> Revisa que el helado exista");
	});

}

/*oooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo*/

