<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.9.0.js"></script>
<script src="https://unpkg.com/react@16/umd/react.production.min.js" crossorigin></script>
<script src="https://unpkg.com/react-dom@16/umd/react-dom.production.min.js" crossorigin></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/babel-core/5.8.29/browser.js"></script>
<script type="text/babel" src="js/index/index.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">

</head>
<body>
<div id="timeArea"></div>
	
	
	第一支

	<#if user?exists>
		<#list user as types>
			${types!}
		</#list>
	</#if>
	
	
<div id="app"></div>

<div id="pageTitle"></div>
<div id="viewArea"></div>





</body>


</html>


