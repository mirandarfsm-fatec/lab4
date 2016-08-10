<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@tagdescription="templatebásico"pageEncoding="UTF-8"%>
<%@attributename="body"fragment="true"%>
<%@attributename="title"%>
<html>
	<head>
		<title>${title}</title>
		<script src="js/jquery.min.js"></script>
		<link rel="stylesheet" href="css/bootstrap.min.css">
		<link rel="stylesheet" href="css/fatec.css">
		<script src="js/bootstrap.min.js"></script>
	</head>
	
	<body>
		<divclass="fateccontainer"> <divid="body"> <jsp:invokefragment ="body"/>
		</div>
		</div>
	</body>
</html>