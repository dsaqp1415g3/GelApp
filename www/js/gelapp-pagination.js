var API_BASE_URL = "http://147.83.7.157:8080/gelapp";
var USERNAME = "";
var PASSWORD = "";

$.ajaxSetup({
    headers: { 'Authorization': "Basic "+ btoa(USERNAME+':'+PASSWORD) }
});

/*
Details about repository of GitHub API 
https://developer.github.com/v3/repos/
https://developer.github.com/guides/traversing-with-pagination/
http://tools.ietf.org/html/rfc5988
*/


$("#button_to_list_pagination").click(function(e) {
	e.preventDefault();
	
	var url = API_BASE_URL + '/helados';
	getRepos(url);
});

function RepoCollection(repoCollection){
	this.repos = repoCollection;
        var href = [];

	var instance = this;

	this.buildLinks = function(header){
		this.links = weblinking.parseHeader(header);
	}

	this.getLink = function(rel){
                return this.links.getLinkValuesByRel(rel);
	}

	this.toHTML = function(){
		var html = '';
		$.each(this.repos, function(i, v) {
			var repo = v.helados;
			html = html.concat('<br><strong> Name: ' + repo.heladoid+ '</strong><br>');
			
		});
		
		html = html.concat(' <br> ');

                var prev = this.getLink('prev');
		if (prev.length == 1) {
			console.log(prev[0].href);
			html = html.concat(' <a onClick="getRepos(\'' + prev[0].href + '\');" style="cursor: pointer; cursor: hand;">[Prev]</a> ');
		}
                var next = this.getLink('next');
		if (next.length == 1) {
			html = html.concat(' <a onClick="getRepos(\'' + next[0].href + '\');" style="cursor: pointer; cursor: hand;">[Next]</a> ');
		}

 		return html;	
	}

}


function getRepos(url) {
	$("#helados_result").text('');

	$.ajax({
		url : url,
		type : 'GET',
		cache : false,
		crossDomain : true,
		dataType : 'json',
	}).done(function(data, status, jqxhr) {
        	var response = data;
		    var repoCollection = new RepoCollection(response);
			
            var linkHeader = data.links;
			console.log(data);
        
            repoCollection.buildLinks(linkHeader);
		    
			console.log(linkHeader);
			
		    var html = repoCollection.toHTML();
			
		    $("#helados_result").html(html);

	}).fail(function(jqXHR, textStatus) {
		console.log(textStatus);
	});

}


