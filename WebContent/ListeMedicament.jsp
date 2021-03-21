<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="medicaments.classes.*"%>


<%@ page import="org.unbescape.html.HtmlEscape"%>

<%@ page import="org.json.simple.JSONArray" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Liste Médicaments</title>
		<link rel="stylesheet" type="text/css" href="https://getbootstrap.com/docs/4.0/dist/css/bootstrap.min.css">
		<link rel="shortcut icon" href="./donnees/Icone/croix.ico" type="image/x-icon">

        <link rel="icon" href="./donnees/Icone/croix.ico" type="image/x-icon">        
        		<link rel="stylesheet" type="text/css" href="./completion.css">
        		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<!--         		<script type="text/javascript" src="./autosuggestion.js"></script> -->
        		
        		
        <link rel="icon" href="./donnees/Icone/croix.ico" type="image/x-icon">
          
	</head>
<body 
	style="background-image: url('https://images.unsplash.com/photo-1554188248-986adbb73be4?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1900&q=1280');">
	

 	<jsp:include page="header.jsp" >
		<jsp:param name="title" value="Detail"/>
	</jsp:include>

 	     <%  ArrayList<Medicament> ListeDeTousMedicaments  =(ArrayList<Medicament>)request.getAttribute("listeTousMedicaments");%>; <%-- recuperer la liste envoyé au niveau de la servelet --%>
	    <% 
	     
	     ArrayList<Medicament> ListeDeMedicaments = null;
	     if(request.getAttribute("listemedicaments")!=null){
	         ListeDeMedicaments =(ArrayList<Medicament>)request.getAttribute("listemedicaments");
	     }
	    %>
	    
	    
	    
	    
	    <% 
	     
	     ArrayList<Medicament> ListeDeMedicamentsCommercialise = null;
	     if(request.getAttribute("listemedicamentsCommercialise")!=null){
	         ListeDeMedicaments =(ArrayList<Medicament>)request.getAttribute("listemedicamentsCommercialise");
	     }
	    %>
        
	 
<% List<String> arr = new ArrayList<String>(); 
if(ListeDeTousMedicaments!=null){ 

for(int i = 0; i < ListeDeTousMedicaments.size(); i++) { 
     arr.add(ListeDeTousMedicaments.get(i).getDenomination()); 
	 
}}
	 %>
	 
	 <%
	 String medocs = JSONArray.toJSONString(arr); 

	 %>
	 
	  

	<div class="container">
 	<form action="ListeMedicament" method="get" id="autocomplet"> 

<!-- 	<button type="submit" class="btn btn-dark">Activer autocompletion</button> -->
</form> 

</br>
        
		<form method="post" class="form-inline" action="ListeMedicament" name="searchform" >
		
		
				<div class="form-group col-sm-10">
				<div class = "autocomplete">
					<input class="form-control form-control-dark w-100" type="text" name="search"  placeholder="Dénomination" aria-label="Search"  minlength=3 id ="search" required>
					
					
					<div class="dialog"> </div>  <!-- block de suggestion -->
					 </div>
					 
					<div class="form-group col-sm-3">
					<button type="submit" class="btn btn-dark btn-block" class="fas fa-user-md" >Rechercher</button>
					
					
				</div>
				
				<div class="input-group mb-3">
  <div class="input-group-prepend">
    <div >
      <input type="checkbox" class="form-check-input" id="Commercialise" name="Commercialise">
    </div>
  </div>
  <label class="form-check-label" for="Commercialise">N'afficher que les médicament commercialisés</label>
</div>
				
 				<div id = "result"></div> <!-- affciher le bloc des résultats de recherche-->
			
				
		</form>
		
		
		
		
		
	</div>
	
 <br>
 
	 	<div class="container">  
			    <%
				if(null != ListeDeMedicaments){
					int count=0;
					for(Medicament M : ListeDeMedicaments){
				%>
		
					<div class="row">
			    		<a href="./Detail?cis=<%= HtmlEscape.escapeHtml5(String.valueOf(M.getCodeCis()))%>" class="col btn-sm btn-outline-dark border border-dark m-1 text-center" style="background-color: <%=(count%2==0?"#8489ab":"#b9c0f0") %>" role="button"><%= HtmlEscape.escapeHtml5(M.getDenomination())%></a>
			    		<!--col-sm btn btn-outline-dark my-1  -->	
			    	</div>
			    <%
					count++;}
				}else{
				%>

			<%} %>
		</div>
		
		
		<button style="display: none; position: fixed; bottom: 50px; right: 30px; z-index: 99; font-size: 18px;
		border: none; outline: none; background-color: red; color: white; cursor: pointer;padding: 15px;
		border-radius: 4px;" onclick="topFunction()" id="myBtn" title="Go to top">
			Haut
		</button>

		<script type="text/javascript" >
		
				$(function() {
				    	
			    var alreadyFilled = false; 
			    var states =  <%= medocs %>  ; 
			    function initDialog() { 
			        clearDialog();
			        for (var i = 0; i < states.length; i++) {
			            $('.dialog').append('<div>' + states[i] + '</div>');
			        }
			    } 
			    function clearDialog() {
			        $('.dialog').empty();
			    } 
			    $('.autocomplete input').click(function() {
			        if (!alreadyFilled) {
			            $('.dialog').addClass('open');
			        } 
			
			    }); 
			    $('body').on('click', '.dialog > div', function() {
			        $('.autocomplete input').val($(this).text()).focus();
			        $('.autocomplete .close').addClass('visible');
			        alreadyFilled = true;
			    }); 
			    $('.autocomplete .close').click(function() {
			        alreadyFilled = false;
			        $('.dialog').addClass('open');
			        $('.autocomplete input').val('').focus();
			        $(this).removeClass('visible');
			    });
			
			    function match(str) {
			        str = str.toLowerCase();
			        clearDialog();
			        for (var i = 0; i < states.length; i++) {
			            if (states[i].toLowerCase().startsWith(str)) {
			                $('.dialog').append('<div>' + states[i] + '</div>');
			            }
			        }
			    } 
			    $('.autocomplete input').on('input', function() {
			        $('.dialog').addClass('open');
			        alreadyFilled = false;
			        match($(this).val());
			    });
			    $('body').click(function(e) {
			        if (!$(e.target).is("input, .close")) {
			            $('.dialog').removeClass('open');
			        }
			    });
			    initDialog(); 
			});
				    	    
	    </script>   
	      
  </body>
  
  	<!-- ------------------- -->
	<!--     Back to top     -->
	<!-- ------------------- -->
	
	<script>
		//Get the button
		var mybutton = document.getElementById("myBtn");
		
		// When the user scrolls down 20px from the top of the document, show the button
		window.onscroll = function() {scrollFunction()};
		function scrollFunction() 
		{
			if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) 
			{
				mybutton.style.display = "block";
				} else {
				mybutton.style.display = "none";
			}
		}
		
		// When the user clicks on the button, scroll to the top of the document
		
		function topFunction() 
		{
			document.body.scrollTop = 0;
			document.documentElement.scrollTop = 0;
		}
	</script>
</html>