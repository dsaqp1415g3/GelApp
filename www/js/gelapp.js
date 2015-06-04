var API_BASE_URL = "http://147.83.7.157:8080/GelApp";
var USERNAME = "";
var PASSWORD = "";

$.ajaxSetup({
    headers: { 'Authorization': "Basic "+ btoa(USERNAME+':'+PASSWORD) }
});

/*
Details about repository of GitHub API 
https://developer.github.com/v3/repos/
*/

/*oooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo*/

/*oooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo*/
  
$("#get_helado").click(function(e) {
	e.preventDefault();
	getHelado($("#helado_id").val());
});

function getHelado(helado_id) {
	var url = API_BASE_URL + '/helados/' + helado_id;
	$("#result").text('');

	$.ajax({
		url : url,
		type : 'GET',
		crossDomain : true,
		dataType : 'json',
	}).done(function(data, status, jqxhr) {

				var helado = data;
        
                var dc = new Date(helado.creationTimestamp);        
                var _mes=dc.getMonth()+1;
                var _dia=dc.getDate();
                var _anyo=dc.getFullYear();
                var _hora = dc.getHours();
                var _minuto = dc.getMinutes();
                var _segundo = dc.getSeconds();    
        
                var dm = new Date(helado.lastModified);
                var mes_=dm.getMonth()+1;
                var dia_=dm.getDate();
                var anyo_=dm.getFullYear();
                var hora_ = dm.getHours();
                var minuto_ = dm.getMinutes();
                var segundo_ = dm.getSeconds();
     
                $("#result_1").empty("#result_1");
                $("#result_2").empty("#result_2");
                $("#result_3").empty("#result_3");
                $("#result_4").empty("#result_4");
                $("#result_5").empty("#result_5");
                $("#result_6").empty("#result_6");
                $("#result_7").empty("#result_7");
                $("#result_8").empty("#result_8");
                $("#result_9").empty("#result_9");

				$("#result").text('');
                $('<strong> Nombre: </strong> ' + helado.nombreHelado + '<br>').appendTo($('#result_1'));
                $('<strong> Autor ID: </strong> ' + helado.autorid + '<br>').appendTo($('#result_2'));
				$('<strong> Capa 1 (Topping): </strong> ' + helado.capa1Topping + '<br>').appendTo($('#result_3'));
                $('<strong> Capa 2 (Helado): </strong> ' + helado.capa2Helado + '<br>').appendTo($('#result_4'));
                $('<strong> Capa 3 (Topping): </strong> ' + helado.capa3Topping + '<br>').appendTo($('#result_5'));
                $('<strong> Capa 4 (Helado): </strong> ' + helado.capa4Helado + '<br>').appendTo($('#result_6'));
                $('<strong> Capa 5 (Topping): </strong> ' + helado.capa5Topping + '<br>').appendTo($('#result_7'));
                $('<strong> Fecha de creación: </strong> ' + _dia+"-"+_mes+"-"+_anyo +" a las "+_hora+":"+_minuto+":"+_segundo+'<br>').appendTo($('#result_8'));
              
        $('<strong> Última modificación: </strong> '+ dia_+"-"+mes_+"-"+anyo_ +" a las "+hora_+":"+minuto_+":"+segundo_+ '<br>').appendTo($('#result_9'));
                
        
			}).fail(function() {
                $("#result_1").empty("#result_1");
                $('<div class="alert alert-danger"> <strong>¡Lo sentimos!</strong> No disponemos de ningún helado con esa ID en la base de datos</div>').appendTo($("#result_1"));
                $("#result_2").empty("#result_2");
                $("#result_3").empty("#result_3");
                $("#result_4").empty("#result_4");
                $("#result_5").empty("#result_5");
                $("#result_6").empty("#result_6");
                $("#result_7").empty("#result_7");
                $("#result_8").empty("#result_8");
                $("#result_9").empty("#result_9");
        
	});
    
}

/*oooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo*/


$("#button_to_delete").click(function(e) {
	e.preventDefault();	
	deleteHelado($("#id_helado_a_eliminar").val());
});


function deleteHelado(id_helado_a_eliminar) {
	var url = API_BASE_URL + '/helados/' + id_helado_a_eliminar;
	
	$("#delete_helado_result").text('');

	$.ajax({
		url : url,
		type : 'DELETE',
		crossDomain : true,
		dataType : 'json',
        
	/*	statusCode: {
    		202: function() {$('<div class="alert alert-danger"> <strong>Ok!</strong> File deleted successfully </div>').appendTo($("#delete_helado_result"));}
    	} */
        
	}).done(function(data, status, jqxhr) {
		$('<div class="alert alert-success"> <strong>Ok!</strong> File deleted successfully</div>').appendTo($("#delete_helado_result"));				
  	}).fail(function() {
		$('<div class="alert alert-danger"> <strong>Oh!</strong> No existe ningún helado con esa ID! </div>').appendTo($("#delete_helado_result"));
	});

}


/*oooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo*/

$("#top1_caramelo").click(function(e) {
	e.preventDefault();   
    $("#topping_1").val('caramelo');
    $("#topping_1").text('Caramelo');
});
$("#top1_chocolate_negro").click(function(e) {
	e.preventDefault();
	$("#topping_1").val('chocolate_negro');
    $("#topping_1").text('Chocolate Negro');
});
$("#top1_chocolate_blanco").click(function(e) {
	e.preventDefault();
	$("#topping_1").val('chocolate_blanco');
    $("#topping_1").text('Chocolate Blanco');
});
$("#top1_sirope_fresa").click(function(e) {
	e.preventDefault();
	$("#topping_1").val('sirope_fresa');
    $("#topping_1").text('Sirope de Fresa');
});
$("#top1_multicolor").click(function(e) {
	e.preventDefault();
	$("#topping_1").val('multicolor');
    $("#topping_1").text('Multicolor');
});


$("#capa2_fresa").click(function(e) {
	e.preventDefault();
	$("#capa_2").val('fresa');
    $("#capa_2").text('Fresa');
});
$("#capa2_nata").click(function(e) {
	e.preventDefault();
	$("#capa_2").val('nata');
    $("#capa_2").text('Nata');
});
$("#capa2_vainilla").click(function(e) {
	e.preventDefault();
	$("#capa_2").val('vainilla');
    $("#capa_2").text('Vainilla');
});
$("#capa2_chocolate").click(function(e) {
	e.preventDefault();
	$("#capa_2").val('chocolate');
    $("#capa_2").text('Chocolate');
});
$("#capa2_turron").click(function(e) {
	e.preventDefault();
	$("#capa_2").val('turron');
    $("#capa_2").text('Turrón');
});

$("#top3_caramelo").click(function(e) {
	e.preventDefault();
	$("#topping_3").val('caramelo');
    $("#topping_3").text('Caramelo');
});
$("#top3_chocolate_negro").click(function(e) {
	e.preventDefault();
	$("#topping_3").val('chocolate_negro');
    $("#topping_3").text('Chocolate Negro');
});
$("#top3_chocolate_blanco").click(function(e) {
	e.preventDefault();
	$("#topping_3").val('chocolate_blanco');
    $("#topping_3").text('Chocolate Blanco');
});
$("#top3_sirope_fresa").click(function(e) {
	e.preventDefault();
	$("#topping_3").val('sirope_fresa');
    $("#topping_3").text('Sirope Fresa');
});
$("#top3_multicolor").click(function(e) {
	e.preventDefault();
	$("#topping_3").val('multicolor');
    $("#topping_3").text('Multicolor');
});


$("#capa4_fresa").click(function(e) {
	e.preventDefault();
	$("#capa_4").val('fresa');
    $("#capa_4").text('Fresa');
});
$("#capa4_nata").click(function(e) {
	e.preventDefault();
	$("#capa_4").val('nata');
    $("#capa_4").text('Nata');
});
$("#capa4_vainilla").click(function(e) {
	e.preventDefault();
	$("#capa_4").val('vainilla');
    $("#capa_4").text('Vainilla');
});
$("#capa4_chocolate").click(function(e) {
	e.preventDefault();
	$("#capa_4").val('chocolate');
    $("#capa_4").text('Chocolate');
});
$("#capa4_turron").click(function(e) {
	e.preventDefault();
	$("#capa_4").val('turron');
    $("#capa_4").text('Turrón');
});


$("#top5_caramelo").click(function(e) {
	e.preventDefault();   
    $("#topping_5").val('caramelo');
    $("#topping_5").text('Caramelo');
});
$("#top5_chocolate_negro").click(function(e) {
	e.preventDefault();
	$("#topping_5").val('chocolate_negro');
    $("#topping_5").text('Chocolate Negro');
});
$("#top5_chocolate_blanco").click(function(e) {
	e.preventDefault();
	$("#topping_5").val('chocolate_blanco');
    $("#topping_5").text('Chocolate Blanco');
});
$("#top5_sirope_fresa").click(function(e) {
	e.preventDefault();
	$("#topping_5").val('sirope_fresa');
    $("#topping_5").text('Sirope de Fresa');
});
$("#top5_multicolor").click(function(e) {
	e.preventDefault();
	$("#topping_5").val('multicolor');
    $("#topping_5").text('Multicolor');
});




/*-----------------------------------------------------------*/
$("#button_to_create").click(function(e) {
	e.preventDefault();

    var newIce = new Object();
    
    newIce.nombreHelado = $("#nombre_helado").val();   
    newIce.capa1Topping = $("#topping_1").val();  
    newIce.capa2Helado = $("#capa_2").val();
    newIce.capa3Topping = $("#topping_3").val();
    newIce.capa4Helado = $("#capa_4").val();
    newIce.capa5Topping = $("#topping_5").val();
    newIce.autorid = 2;

	createIce(newIce);
});

function createIce(newIce) {
    
	var url = API_BASE_URL + '/helados';
	var data = JSON.stringify(newIce);
    $("#create_result").empty("#create_result");
	$("#create_result").text('');

	$.ajax({
		url : url,
		type : 'POST',
		crossDomain : true,
		dataType : 'json',
		data : data,
        contentType : 'application/vnd.gelapp.api.helado+json',
	}).done(function(data, status, jqxhr) {
        $("#create_result").empty("#create_result");
		$('<div class="alert alert-success"> <strong>Ok!</strong> Helado con nombre: creado correctamente. </div>').appendTo($("#create_result"));				
  	}).fail(function() {
        $("#create_result").empty("#create_result");
		$('<div class="alert alert-danger"> <strong>Oh!</strong> Error </div>').appendTo($("#create_result"));
	});

}


/*oooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo*/


$(document).ready(function(){
	getMisHelados();
});


function getMisHelados() {
	var url = API_BASE_URL + '/helados/user/oriol';
	$("#mis_helados_result").text('');
	
	$.ajax({
		url : url,
		type : 'GET',
		crossDomain : true,
		dataType : 'json',
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
                    
                              
                    $('<div class="col-md-4 text-center">                                                                                                                       <img class="img-circle" src="images/ranking1.jpg">                                                                                                 <h2>' + helado.nombreHelado + '</h2>                                                                                                           <div class="panel-body"> <div class="list-group"> <a class="list-group-item active"> Sabores </a>                                                   <a class="list-group-item disabled"> Capa Topping 1: ' +helado.capa1Topping+ ' </a>                                                                 <a class="list-group-item disabled"> Capa Helado 2:  ' +helado.capa2Helado+ ' </a>                                                                 <a class="list-group-item disabled"> Capa Topping 3:  ' +helado.capa3Topping+ ' </a>                                                               <a class="list-group-item disabled"> Capa Helado 4:  ' +helado.capa4Helado+ ' </a>                                                                 <a class="list-group-item disabled"> Capa Topping 5: ' +helado.capa5Topping+ ' </a>                                                                 <a class="list-group-item disabled"> Identificador: ' +helado.heladoid+ ' </a>                                                                     <a class="list-group-item disabled"><strong> Creado el '  + _dia+"-"+_mes+"-"+_anyo +" a las "+_hora+":"+_minuto+":"+_segundo+ '</strong> </a>                                                                                                                                                                                                                                                                               </div>  </div>                                                                                                                       <button type="button" class="btn btn-info" id="#comprar_en_mis_helados">Comprar »</button>                                                                                                                <button onclick="deleteHelado('+helado.heladoid+')" style="background-color:red" type="button" class="btn btn-info" id="button_to_delete">Borrar »</button></br></br></br></br>                      <span class="output-group-addon" id="mishelados_comprar">  </span></br> ').appendTo($('#mis_helados_result'));                 
                    $('</div>').appendTo($('#mis_helados_result'));
                    
                    
 
                  
                       
                    
                    
				});
				

	}).fail(function() {
		$("#mis_helados_result").text("¡Todavía no has creado ningún helado!");
	});

}
/*oooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo*/


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
		dataType : 'json',
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
                    
                              
                    $('<div class="col-md-4 text-center">                                                                                                                 <img class="img-circle" src="images/ranking1.jpg">                                                                                                 <h2>' + helado.nombreHelado + '</h2>                                                                                                               <div class="panel-body"> <div class="list-group"> <a class="list-group-item active"> Sabores </a>                                                   <a class="list-group-item disabled"> Capa Topping 1: ' +helado.capa1Topping+ ' </a>                                                                 <a class="list-group-item disabled"> Capa Helado 2:  ' +helado.capa2Helado+ ' </a>                                                                 <a class="list-group-item disabled"> Capa Topping 3:  ' +helado.capa3Topping+ ' </a>                                                               <a class="list-group-item disabled"> Capa Helado 4:  ' +helado.capa4Helado+ ' </a>                                                                 <a class="list-group-item disabled"> Capa Topping 5: ' +helado.capa5Topping+ ' </a>                                                                 <a class="list-group-item disabled"> Identificador: ' +helado.heladoid+ ' </a>                                                                     <a class="list-group-item disabled"> <strong>Autor del helado: </strong> ' +helado.autor+ ' </a>                                                   <a class="list-group-item disabled"><strong> Creado el '  + _dia+"-"+_mes+"-"+_anyo +" a las "+_hora+":"+_minuto+":"+_segundo+ '</strong> </a>                                                                                                                                                                                                                                                                                                                                                                                                                                 </div>  </div>                                                                                                                               <button type="button" class="btn btn-info" id="#comprar_en_mis_helados">Comprar »</button>                                               <button style="background-color:green" type="button" class="btn btn-info" id="button_to_delete"> Votar »</button></br></br></br></br>                     <span class="output-group-addon" id="mishelados_comprar">  </span></br> ').appendTo($('#helados_result'));                 
                    $('</div>').appendTo($('#helados_result'));
                    
                    
 
                  
                       
                    
                    
				});
				

	}).fail(function() {
		$("#helados_result").text("¡Todavía no has creado ningún helado!");
	});

}

/*oooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo*/

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
		dataType : 'json',
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
                    
                              
                    $('<div class="col-md-4 text-center">                                                                                                                       <img class="img-circle" src="images/ranking1.jpg">                                                                                                        <h2>' + helado.nombreHelado + '</h2>                                                                                                  <div class="panel-body"> <div class="list-group"> <a class="list-group-item active"> Sabores </a>                                                        <a class="list-group-item disabled"> Capa Topping 1: ' +helado.capa1Topping+ ' </a>                                                                <a class="list-group-item disabled"> Capa Helado 2:  ' +helado.capa2Helado+ ' </a>                                                                  <a class="list-group-item disabled"> Capa Topping 3:  ' +helado.capa3Topping+ ' </a>                                                                <a class="list-group-item disabled"> Capa Helado 4:  ' +helado.capa4Helado+ ' </a>                                                                  <a class="list-group-item disabled"> Capa Topping 5: ' +helado.capa5Topping+ ' </a>                                                                <a class="list-group-item disabled"> Identificador: ' +helado.heladoid+ ' </a>                                                                      <a class="list-group-item disabled"><strong> Creado el '  + _dia+"-"+_mes+"-"+_anyo +" a las "+_hora+":"+_minuto+":"+_segundo+ '</strong> </a>                                                                                                                                                                                                                                                                                  </div>  </div>                                                                                                                                      <button type="button" class="btn btn-info" id="#comprar_en_mis_helados">Comprar »</button>                                         <button style="background-color:green" type="button" class="btn btn-info" id="button_to_delete"> Votar »</button></br></br></br></br>                      <span class="output-group-addon" id="mishelados_comprar">  </span></br> ').appendTo($('#helados_result'));                 
                    $('</div>').appendTo($('#helados_result'));
                             
				});
				

	}).fail(function() {
		$('<div class="alert alert-danger"> <strong>Oh!</strong> Este autor todavía no ha creado helados o no existe </div>').appendTo($("#helados_result"));
	});

}

/*oooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo*/

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
		dataType : 'json',
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
                    
                              
                    $('<div class="col-md-4 text-center">                                                                                                                       <img class="img-circle" src="images/ranking1.jpg">                                                                                                        <h2>' + helado.nombreHelado + '</h2>                                                                                                  <div class="panel-body"> <div class="list-group"> <a class="list-group-item active"> Sabores </a>                                                        <a class="list-group-item disabled"> Capa Topping 1: ' +helado.capa1Topping+ ' </a>                                                              <a class="list-group-item disabled"> Capa Helado 2:  ' +helado.capa2Helado+ ' </a>                                                                 <a class="list-group-item disabled"> Capa Topping 3:  ' +helado.capa3Topping+ ' </a>                                                             <a class="list-group-item disabled"> Capa Helado 4:  ' +helado.capa4Helado+ ' </a>                                                                  <a class="list-group-item disabled"> Capa Topping 5: ' +helado.capa5Topping+ ' </a>                                                                 <a class="list-group-item disabled"> Identificador: ' +helado.heladoid+ ' </a>                                                                     <a class="list-group-item disabled"> <strong>Autor del helado: </strong> ' +helado.autor+ ' </a>                                                  <a class="list-group-item disabled"><strong> Creado el '  + _dia+"-"+_mes+"-"+_anyo +" a las "+_hora+":"+_minuto+":"+_segundo+ '</strong> </a>                                                                                                                                                                                                                                                                               </div>  </div>                                                                                                                           <button type="button" class="btn btn-info" id="#comprar_en_mis_helados">Comprar »</button>                                                      <button style="background-color:green" type="button" class="btn btn-info" id="button_to_delete"> Votar »</button></br></br></br></br>                      <span class="output-group-addon" id="mishelados_comprar">  </span></br> ').appendTo($('#helados_result'));                 
                    $('</div>').appendTo($('#helados_result'));
				});
				

	}).fail(function() {
        $('<div class="alert alert-danger"> <strong>Oh!</strong> No disponemos de helados con ese sabor </div>').appendTo($("#helados_result"));
	});

}


/*oooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo*/

$(document).ready(function(){
	getRanking();
});


function getRanking() {
	var url = API_BASE_URL + '/helados/ranking';
	$("#ranking_result").text(''); 
	
	$.ajax({
		url : url,
		type : 'GET',
		crossDomain : true,
		dataType : 'json',
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
                             
            $('<div class="col-md-4 text-center">                                                                                                                  <img class="img-circle" src="images/ranking1.jpg">                                                                                                  <h2>' + helado.nombreHelado + '</h2>                                                                                                            <div class="panel-body"> <div class="list-group"> <a class="list-group-item active"> Sabores </a>                                                  <a class="list-group-item disabled"> Capa Topping 1: ' +helado.capa1Topping+ ' </a>                                                                <a class="list-group-item disabled"> Capa Helado 2:  ' +helado.capa2Helado+ ' </a>                                                                  <a class="list-group-item disabled"> Capa Topping 3:  ' +helado.capa3Topping+ ' </a>                                                                <a class="list-group-item disabled"> Capa Helado 4:  ' +helado.capa4Helado+ ' </a>                                                                  <a class="list-group-item disabled"> Capa Topping 5: ' +helado.capa5Topping+ ' </a>                                                                <a class="list-group-item disabled"> Identificador: ' +helado.heladoid+ ' </a>                                                                      <a class="list-group-item disabled"> <strong>Autor del helado: </strong> ' +helado.autor+ ' </a>                                            <a class="list-group-item disabled"><strong> Creado el '  + _dia+"-"+_mes+"-"+_anyo +" a las "+_hora+":"+_minuto+":"+_segundo+ '</strong> </a>              <a class="list-group-item disabled">' +helado.votos+ ' votos </a>                                                                                                                                                                                                                              </div>  </div>                                                                                                                                                 <button type="button" class="btn btn-info" id="#comprar_en_mis_helados">Comprar »</button>                                                         <button style="background-color:green" type="button" class="btn btn-info" id="button_to_delete"> Votar »</button></br></br></br></br>               <span class="output-group-addon" id="mishelados_comprar">  </span></br> ').appendTo($('#ranking_result'));                 
            $('</div>').appendTo($('#ranking_result'));
                  
                       
                    
                    
				});
				

	}).fail(function() {
		$("#ranking_result").text("¡El ranking todavía está vacío!");
	});

}

/*oooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo*/

$("#button_to_vote").click(function(e) {
	e.preventDefault();

    var newIce = new Object();
    
    newIce.nombreHelado = $("#nombre_helado").val();   
    newIce.capa1Topping = $("#topping_1").val();  
    newIce.capa2Helado = $("#capa_2").val();
    newIce.capa3Topping = $("#topping_3").val();
    newIce.capa4Helado = $("#capa_4").val();
    newIce.capa5Topping = $("#topping_5").val();
    newIce.autorid = 2;

	createIce(newIce);
});

function createIce(newIce) {
    
	var url = API_BASE_URL + '/helados';
	var data = JSON.stringify(newIce);
    $("#create_result").empty("#create_result");
	$("#create_result").text('');

	$.ajax({
		url : url,
		type : 'POST',
		crossDomain : true,
		dataType : 'json',
		data : data,
        contentType : 'application/vnd.gelapp.api.helado+json',
	}).done(function(data, status, jqxhr) {
        $("#create_result").empty("#create_result");
		$('<div class="alert alert-success"> <strong>Ok!</strong> Helado con nombre: creado correctamente. </div>').appendTo($("#create_result"));				
  	}).fail(function() {
        $("#create_result").empty("#create_result");
		$('<div class="alert alert-danger"> <strong>Oh!</strong> Error </div>').appendTo($("#create_result"));
	});

}



/*oooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo*/

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
		dataType : 'json',
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
                    
                              
                    $('<div class="col-md-4 text-center">                                                                                                                       <img class="img-circle" src="images/ranking1.jpg">                                                                                                        <h2>' + helado.nombreHelado + '</h2>                                                                                                  <div class="panel-body"> <div class="list-group"> <a class="list-group-item active"> Sabores </a>                                                        <a class="list-group-item disabled"> Capa Topping 1: ' +helado.capa1Topping+ ' </a>                                                                <a class="list-group-item disabled"> Capa Helado 2:  ' +helado.capa2Helado+ ' </a>                                                                  <a class="list-group-item disabled"> Capa Topping 3:  ' +helado.capa3Topping+ ' </a>                                                                <a class="list-group-item disabled"> Capa Helado 4:  ' +helado.capa4Helado+ ' </a>                                                                  <a class="list-group-item disabled"> Capa Topping 5: ' +helado.capa5Topping+ ' </a>                                                                <a class="list-group-item disabled"> Identificador: ' +helado.heladoid+ ' </a>                                                                      <a class="list-group-item disabled"><strong> Creado el '  + _dia+"-"+_mes+"-"+_anyo +" a las "+_hora+":"+_minuto+":"+_segundo+ '</strong> </a>                                                                                                                                                                                                                                                                                  </div>  </div>                                                                                                                                      <button type="button" class="btn btn-info" id="#comprar_en_mis_helados">Comprar »</button>                                         <button style="background-color:green" type="button" class="btn btn-info" id="button_to_delete"> Votar »</button></br></br></br></br>                      <span class="output-group-addon" id="mishelados_comprar">  </span></br> ').appendTo($('#helados_result'));                 
                    $('</div>').appendTo($('#helados_result'));
                             
				});
				

	}).fail(function() {
		$('<div class="alert alert-danger"> <strong>Oh!</strong> Este autor todavía no ha creado helados o no existe </div>').appendTo($("#helados_result"));
	});

}



/*oooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo*/



/*oooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo*/


/*oooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo*/


/*oooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo*/


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
		data : data,
	}).done(function(data, status, jqxhr) {
        var inf = data;
		alert("¡Bienvenido "+inf.username+"! Ya puedes iniciar sesión");
				

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
		data : data,
	}).done(function(data, status, jqxhr) {
		var inf = data;
				if(inf.loginSuccesful!= true){
                console.log(inf.loginSuccessful);    
				alert("Usuario y/o contraseña erróneo");		
				}
				else{
                    
					user.cookie = $("#login_usuario").val();
					pass.cookie = $("#login_contrasena").val();
					console.log(inf.loginSuccessful);
                    console.log(user.cookie);
                    console.log(pass.cookie);
					/*window.location = "index.html"*/
                    alert("Bienvenido!");
					}

  	}).fail(function() {
		alert("No se ha podido inicar sesión");
	});
}



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
		dataType : 'json',                
	/*	statusCode: {
    		202: function() {$('<div class="alert alert-danger"> <strong>Ok!</strong> File deleted successfully </div>').appendTo($("#delete_helado_result"));}
    	} */
        
	}).done(function(data, status, jqxhr) {
		$('<div class="alert alert-success"> <strong>¡Gracias!</strong> Puedes pasar a recogerlo a tu tienda GelApp más cercana </div>').appendTo($("#mishelados_comprar"));				
  	}).fail(function() {
		$('<div class="alert alert-danger"> <strong>Oh!</strong> El helado que quieres comprar no existe </div>').appendTo($("#mishelados_comprar"));
	});

}

/*oooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo*/