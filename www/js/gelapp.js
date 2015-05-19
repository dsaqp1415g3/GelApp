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

$("#button_get_sabor_id").click(function(e) {
	e.preventDefault();
	getSabor($("#sabor_id").val());
});

function getSabor(sabor_id) {
	var url = API_BASE_URL + '/sabores/' + sabor_id;
	$("#result").text('');

	$.ajax({
		url : url,
		type : 'GET',
		crossDomain : true,
		dataType : 'json',
	}).done(function(data, status, jqxhr) {

				var sabor = data;

				$("#result").text('');
				$('<strong> Nombre sabor: </strong> ' + sabor.name + '<br>').appendTo($('#result'));
				$('<strong> Código de color: </strong> ' + sabor.code_color + '<br>').appendTo($('#result'));	
				$('<strong> ID: </strong> ' + sabor.saborid + '<br>').appendTo($('#result'));

			}).fail(function() {
				$('<div class="alert alert-danger"> <strong>Oh!</strong> El identificador que has introducido es incorrecto </div>').appendTo($("#result"));
	});
}



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

$("#button_to_list").click(function(e) {
	e.preventDefault();
	getList();
})

function getList() {
	var url = API_BASE_URL + '/helados';
	$("#helados_result").text('');
	
	$.ajax({
		url : url,
		type : 'GET',
		crossDomain : true,
		dataType : 'json',
	}).done(function(data, status, jqxhr) {
				var files = data.helados;
				
				$.each(files, function(i, v) {
					var file = v;
	
				        $('<br><strong> Name: </strong>' + file.heladoid + '<br>').appendTo($('#helados_result'));
					$('<br><strong> Description: </strong>' + file.creationTimestamp + '<br>').appendTo($('#helados_result'));
                                        
					
				});
				

	}).fail(function() {
		$("#helados_result").text("No data to show.");
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
	$("topping_1").val('sirope_fresa');
    $("topping_1").text('Sirope de Fresa');
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
$("#top1_chocolate_negro").click(function(e) {
	e.preventDefault();
	$("#topping_5").val('chocolate_negro');
    $("#topping_5").text('chocolate_negro');
});
$("#top5_chocolate_blanco").click(function(e) {
	e.preventDefault();
	$("#topping_5").val('chocolate_blanco');
});
$("#top5_sirope_fresa").click(function(e) {
	e.preventDefault();
	$("#topping_5").val('sirope_fresa');
});
$("#top5_multicolor").click(function(e) {
	e.preventDefault();
	$("#topping_5").val('multicolor');
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

	$("#create_result").text('');

	$.ajax({
		url : url,
		type : 'POST',
		crossDomain : true,
		dataType : 'json',
		data : data,
        contentType : 'application/vnd.GelApp.helado+json',
	}).done(function(data, status, jqxhr) {
		$('<div class="alert alert-success"> <strong>Ok!</strong> Helado con nombre: '+ newIce.nombreHelado +' creado correctamente. </div>').appendTo($("#create_result"));				
  	}).fail(function() {
        $("#create_result").empty("#create_result");
		$('<div class="alert alert-danger"> <strong>Oh!</strong> Error </div>').appendTo($("#create_result"));
	});

}


/*oooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo*/


/*oooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo*/


/*oooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo*/


/*oooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo*/


/*oooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo*/


/*oooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo*/


/*oooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo*/


/*oooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo*/


/*oooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo*/


/*oooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo*/


/*oooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo*/


/*oooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo*/


/*oooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo*/


/*oooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo*/


/*oooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo*/


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








$("#button_get_repos").click(function(e) {
	e.preventDefault();
	getRepos();
});

$("#button_get_repo").click(function(e) {
	e.preventDefault();
	getRepo($("#repository_name").val());
});

$("#button_get_repo_to_edit").click(function(e) {
	e.preventDefault();
	getRepoToEdit($("#repository_name_get_to_edit").val());
});

$("#button_edit_repo").click(function(e) {
	e.preventDefault();

    var newRepo = new Object();
	newRepo.name = $("#repository_name_to_edit").val()
	newRepo.description = $("#description_to_edit").val()
	
	updateRepo(newRepo);
});

$("#button_to_create").click(function(e) {
	e.preventDefault();

    var newRepo = new Object();
	newRepo.name = $("#repository_name_to_create").val();
	newRepo.description = $("#description_to_create").val();
 	newRepo.homepage = "https://github.com";
 	newRepo.private = false;
	newRepo.has_issues = true;
	newRepo.has_wiki = true;
	newRepo.has_downloads = true;

	createRepo(newRepo);
});





function getRepos() {
	var url = API_BASE_URL + '/users/' + USERNAME + '/repos';
	$("#repos_result").text('');
	
	$.ajax({
		url : url,
		type : 'GET',
		crossDomain : true,
		dataType : 'json',
	}).done(function(data, status, jqxhr) {
				var repos = data;
				
				$.each(repos, function(i, v) {
					var repo = v;

					$('<br><strong> Name: ' + repo.name + '</strong><br>').appendTo($('#repos_result'));
					$('<strong> ID: </strong> ' + repo.id + '<br>').appendTo($('#repos_result'));
					$('<strong> URL: </strong> ' + repo.html_url + '<br>').appendTo($('#repos_result'));
					$('<strong> Description: </strong> ' + repo.description + '<br>').appendTo($('#repos_result'));
				});
				

	}).fail(function() {
		$("#repos_result").text("No repositories.");
	});

}

function getRepo(repository_name) {
	var url = API_BASE_URL + '/repos/' + USERNAME + '/' + repository_name;
	$("#get_repo_result").text('');

	$.ajax({
		url : url,
		type : 'GET',
		crossDomain : true,
		dataType : 'json',
	}).done(function(data, status, jqxhr) {

				var repo = data;

				$("#get_repo_result").text('');
				$('<strong> Name: ' + repo.name + '</strong>').appendTo($('#get_repo_result'));
				$('<strong> ID: </strong> ' + repo.id + '<br>').appendTo($('#get_repo_result'));
				$('<strong> URL: </strong> ' + repo.html_url + '<br>').appendTo($('#get_repo_result'));
				$('<strong> Description: </strong> ' + repo.description + '<br>').appendTo($('#get_repo_result'));
				$('<strong> Size: </strong> ' + repo.size + ' <br>').appendTo($('#get_repo_result'));

			}).fail(function() {
				$('<div class="alert alert-danger"> <strong>Oh!</strong> Repository not found </div>').appendTo($("#get_repo_result"));
	});
}



function getRepoToEdit(repository_name) {
	var url = API_BASE_URL + '/repos/' + USERNAME + '/' + repository_name;
	$("#update_result").text('');

	$.ajax({
		url : url,
		type : 'GET',
		crossDomain : true,
		dataType : 'json',
	}).done(function(data, status, jqxhr) {
		
				var repo = data;
				

				$("#update_result").text('');
				$("#repository_name_to_edit").val(repo.name);
				$("#description_to_edit").val(repo.description);

	}).fail(function() {
		$('<div class="alert alert-danger"> <strong>Oh!</strong> Repository not found </div>').appendTo($("#update_result"));
	});

}

function updateRepo(repository) {
	var url = API_BASE_URL + '/repos/' + USERNAME + '/' + repository.name;
	var data = JSON.stringify(repository);

	$("#update_result").text('');

	$.ajax({
		url : url,
		type : 'PATCH',
		crossDomain : true,
		dataType : 'json',
		data : data, <!--Afegeix dades al servidor--!-->
		statusCode: {
    		404: function() {$('<div class="alert alert-danger"> <strong>Oh!</strong> Page not found </div>').appendTo($("#update_result"));}
    	}
	}).done(function(data, status, jqxhr) {
		$('<div class="alert alert-success"> <strong>Ok!</strong> Repository Updated</div>').appendTo($("#update_result"));				
  	}).fail(function() {
		$('<div class="alert alert-danger"> <strong>Oh!</strong> Error </div>').appendTo($("#update_result"));
	});

}



function createRepo(repository) {
	var url = API_BASE_URL + '/user/repos';
	var data = JSON.stringify(repository);

	$("#create_result").text('');

	$.ajax({
		url : url,
		type : 'POST',
		crossDomain : true,
		dataType : 'json',
		data : data, <!--Afegeix dades al servidor--!-->
	}).done(function(data, status, jqxhr) {
		$('<div class="alert alert-success"> <strong>Ok!</strong> Repository Created</div>').appendTo($("#create_result"));				
  	}).fail(function() {
		$('<div class="alert alert-danger"> <strong>Oh!</strong> Error </div>').appendTo($("#create_result"));
	});

}
