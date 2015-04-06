<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<style>
			body {
				font: 13px/1.3 Lucida Grande, Verdana, Helvetica, Arial, sans-serif;
			}
			
			a {
				color: #ffffff;
				text-decoration: none;
				font-size: 32px;
			}
			
			div {
				text-align: center;
			}
			
			.version {
				float: left;
			}
			.jss {
				width: 80px;
				padding: 8px;
				margin: 0 auto;
				border-style: outset;
				border-width: 1px;
				border-color: #ffffff;
			}
		</style>
		<title>THE CASPERS</title>
	</head>
	<body style="padding-top: 20px; background-color: #15294b;">
		<div style="max-width: 500px; margin: auto;">
			<font style="font-size: 48px; color: #ffffff;">THE CASPERS</font>
			<br />
		</div>
		<div style="max-width: 700px; margin: auto;">
			<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
		    <c:forEach var="tomcatValue" items="${tomcats}">
		    	<div class="version" style="color:#ffffff; font-size:24px;width:100%">${tomcatValue.tomcatVersion} running Java Version: ${tomcatValue.javaVersion}</div>
		    	<div style="width:100%">
		    	<c:forEach var="jss" items="${tomcatValue.jssList}">
			    	<div id="${jss.id}" class="version jss" >
						<a href="<%=request.getRequestURL().toString().split(":84")[0]%>:${tomcatValue.port}${jss.url}">${jss.version}</a>
					</div>
				</c:forEach>
				</div>
		    </c:forEach>
		</div>
	</body>
</html>