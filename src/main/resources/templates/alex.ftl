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
<script src="http://malsup.github.io/jquery.blockUI.js" type="text/javascript"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">


<style type="text/css">
.display-flex {
  display: flex;
}

.div-size {
  border: 1px solid red;
  margin-left:2%;
   display: inline-block;
}
</style>
</head>
<body>


<div id="timeArea"></div>
	
	
	第一支

	<#if user?exists>
		<#list user as types>
			${types!}
		</#list>
	</#if>
	
	
<div id="app">
	<div id="pageTitle"></div>

	<div id="searchDiv"></div>
	<br>

	<div id="viewArea"></div>
</div>


<div class="display-flex">
		<div class="div-size">
			<div>規模:</div>
			<textarea class="a1"  rows="10" cols="40" disabled="disabled"></textarea>
		</div>
		
		<div class="div-size">
			<div>電話:</div>
			<textarea class="a2"  rows="10" cols="40" disabled="disabled"></textarea>
		</div>
		
		<div class="div-size">
			<div>產業:</div>
			<textarea class="a3"  rows="10" cols="40" disabled="disabled"></textarea>
		</div>
		
		<div class="div-size">
			<div>總部:</div>
			<textarea class="a4"  rows="10" cols="40" disabled="disabled"></textarea>
		</div>
		
		<div class="div-size">
			<div>網站:</div>
			<textarea class="a5"  rows="10" cols="40" disabled="disabled"></textarea>
		</div>
		
		<div class="div-size">
			<div>類型:</div>
			<textarea class="a6"  rows="10" cols="40" disabled="disabled"></textarea>
		</div>
		
		<div class="div-size">
			<div>創立:</div>
			<textarea class="a7"  rows="10" cols="40" disabled="disabled"></textarea>
		</div>
		
		<div class="div-size">
			<div>領域:</div>
			<textarea class="a8"  rows="10" cols="40" disabled="disabled"></textarea>
		</div>

</div>

</body>


</html>


