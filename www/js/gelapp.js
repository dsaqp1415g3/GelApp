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
                $('<strong> Nombre: </strong> ' + + '<br>').appendTo($('#result_1'));
                $('<strong> ID: </strong> ' + helado.heladoid + '<br>').appendTo($('#result_2'));
				$('<strong> Capa 1 (Topping): </strong> ' + helado.capa1Topping + '<br>').appendTo($('#result_3'));
                $('<strong> Capa 2 (Helado): </strong> ' + helado.capa2Helado + '<br>').appendTo($('#result_4'));
                $('<strong> Capa 3 (Topping): </strong> ' + helado.capa3Topping + '<br>').appendTo($('#result_5'));
                $('<strong> Capa 4 (Helado): </strong> ' + helado.capa4Helado + '<br>').appendTo($('#result_6'));
                $('<strong> Capa 5 (Topping): </strong> ' + helado.capa5Topping + '<br>').appendTo($('#result_7'));
                $('<strong> Fecha de creación: </strong> ' + helado.creationTimestamp + '<br>').appendTo($('#result_8'));
                $('<strong> Última modificación: </strong> ' + helado.lastModified + '<br>').appendTo($('#result_9'));
                
        
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


/*oooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo*/


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

$("#button_to_delete").click(function(e) {
	e.preventDefault();	
	deleteRepo($("#repository_name_to_delete").val());
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


function deleteRepo(repository_name_to_delete) {
	var url = API_BASE_URL + '/repos/' + USERNAME + '/' + repository_name_to_delete;
	$.ajax({
		url : url,
		type : 'DELETE',
		crossDomain : true,
		dataType : 'json',
	}).done(function(data, status, jqxhr) {
	$("#delete_repo_result").text('');
	$('<div class="alert alert-succes"> <strong>Okey!</strong> Repository has been delete </div>').appendTo($("#delete_repo_result"));
				

			}).fail(function() {
				$('<div class="alert alert-danger"> <strong>Oh!</strong> Repository not found </div>').appendTo($("#delete_repo_result"));
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