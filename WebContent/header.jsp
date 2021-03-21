<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%@ page import="org.unbescape.html.HtmlEscape"%>
<!DOCTYPE html>

<%!		
		String login = "nothing";
%>
<%	

try{
	if(!((String)session.getAttribute("Name")).isEmpty()){
			login =  HtmlEscape.escapeHtml5((String)session.getAttribute("Name"));
		}else{
			login = "Patient";
		}}catch(Exception e){
			login="Patient";
		}
%>
<header>
	<div class="mb-5 p-2" style="background-color: #474747;">
		<div class="container bg-black">
			<div class="row bg-black text-white">
				<div class="col-4 text-left p-0">
					<a class="btn btn-dark" href="Index" role="button"><i class="fas fa-clinic-medical"></i>    SecureMedocs</a>
				</div>
				<% if(request.getParameter("title")!=null && !request.getParameter("title").equals("Connexion")){ %>
					<div class="col-4 text-center mt-1 p-0"> 
					<%=(!login.equals("Patient")?login:"")%>

					</div>
					<div class="col-4 text-right p-0">
						<% if(login.equals("Patient")){ %>
							<a class="btn btn-dark" href="Login" role="button"><i class="fas fa-user-md"></i>   Connexion</a>
						<% }else {%>
							<a class="btn btn-dark" href="Logout" role="button"><i class="fas fa-user-alt-slash"></i>   Déconnexion</a>
						<%}%>
					</div>
				<%}%>
			</div>
		</div>
	</div>
</header>