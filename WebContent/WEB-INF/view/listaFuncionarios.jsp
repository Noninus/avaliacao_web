<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"	%>
<%@	taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"	%>	
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

<meta charset="ISO-8859-1">
<title>Avaliação - ATOS</title>
</head>
<body>

<table class="table table-striped" style="position: relative;float: top">
  <thead>
    <tr>
      <th scope="col">Nome</th>
      <th scope="col">Cargo</th>
      <th scope="col">Salário</th>
      <th scope="col">Gerente</th>
      <th scope="col">Gcm</th>
      <th scope="col">Habilidades</th>
      <th scope="col">Certificados</th>
    </tr>
  </thead>
  <tbody>
  <c:forEach items="${funcionarios}" var="funcionarios">
     <tr>
      <td>${funcionarios.name}</td>
      <td>${funcionarios.role}</td>
      <td>${funcionarios.salary}</td>
      <td>${funcionarios.manager}</td>
      <td>${funcionarios.gcm}</td>
      <td>
      <button id="buttonHabilidades"><i class="fa fa-eye" aria-hidden="true"></i></button>
      <div style="display: none" id="habilidadesDIV">
		<br>
        <c:forEach items="${funcionarios.skills}" var="habilidades">
       		${habilidades}<br>
        </c:forEach>
	  </div>
      </td>
      <td>
      <button id="buttonCertificados"><i class="fa fa-eye" aria-hidden="true"></i></button>
      <div style="display: none" id="certificadosDIV">
		<br>
        <c:forEach items="${funcionarios.certification}" var="certificados">
       		${certificados}<br>
        </c:forEach>
	  </div>
      </td>
    </tr>
 </c:forEach>
  </tbody>
</table>
<div class="container" id="checkboxes">
  <h2>Filtro por Habilidades</h2>
  <p>Escolha opções abaixo para filtrar os funcinários: </p>
  <form action="listaFuncionariosFiltrado" method="post" accept-charset="UTF-8">
   <c:forEach items="${skillsList}" var="skillsList">
	     <label class="checkbox-inline">
      		<input id="checkbox" type="checkbox" value="${skillsList}">${skillsList}
    	</label>
  </c:forEach>
  <br>
  <br>

  <button type="submit" class="btn btn-primary">Filtrar!</button>
    <p style="color: red">Para escolher nenhum filtro, não selecione nenhuma opção!</p>
  </form>
</div>
  
<script>
	 $('button[id=buttonHabilidades]').on("click", function(){
		var display =  	$(this).next($("#habilidadesDIV")).css("display");
		if(display!="none")
	    {
			$(this).next($("#habilidadesDIV")).attr("style", "display:none");
	    }else{
	    	$(this).next($("#habilidadesDIV")).attr("style", "display:");
	    }
	});
	 
	 
	 $('button[id=buttonCertificados]').on("click", function(){
			var display =  	$(this).next($("#certificadosDIV")).css("display");
			if(display!="none")
		    {
				$(this).next($("#certificadosDIV")).attr("style", "display:none");
		    }else{
		    	$(this).next($("#certificadosDIV")).attr("style", "display:");
		    }
		});
	 
	 $(function () {
		  var checkboxes = $('#checkboxes :checkbox');
		  checkboxes.click(function (e) {
			  if ($(this).is(':checked')) {
				  
				  $(this).attr('name', 'selecionado');
			  }else{
				  $(this).attr('name', '');  
			  }  
		  });
		});
</script>
</body>
</html>