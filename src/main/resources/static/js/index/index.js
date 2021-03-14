
$(document).ready(


);
$(document).ajaxStart(function(){
    $.blockUI({
    	message: "<b><font size=5>查詢中...</font></b>",
        css: {
            border: '3px solid #aaa',
            padding: '15px',
            backgroundColor: '#fff',
            '-webkit-border-radius': '10px',
            '-moz-border-radius': '10px',
            opacity: .9,
            textAlign:      'center',
            cursor:         'wait',
        }
    });
}).ajaxStop($.unblockUI);


(function() {
	ReactDOM.render(
        <h1>Hello, world !  @@</h1>,
        document.getElementById('pageTitle')
     );
      
      //產生按鈕
      const element = (
		  <div>
		    <div>
		    <button type="button" id="listerClickButton" class="btn btn-primary" onClick={handleLink}>查詢</button>
		      <ul>
				　<li>A01</li>
				　<li>A02</li>
				　<li>A03</li>
				</ul>
		    </div>
		  </div>
		)
       ReactDOM.render(element, document.getElementById('viewArea'));
      
      
      
      
      const search = (
            <input type="text" id="searchId" class="form-control" placeholder='請輸入Linkdeln中公司名稱 ex:pchome-online'/>
		)
       ReactDOM.render(search, document.getElementById('searchDiv'));
      
      
      
      
      //顯示時間
      function tick() {
        const timeElement = (
		      <h2>現在時間 {new Date().toLocaleTimeString()}</h2>
		  );
  		ReactDOM.render(timeElement, document.getElementById('timeArea'));
      }
     setInterval(tick, 1000);
})()






function handleLink() {
  console.log($("#searchId").val());
  $.ajax({
		url:"linkdelnInfo",
		data:{
			"company": $("#searchId").val()
		},
		type:"post",
		dataType:"json",
		async: true,
		success:function(response, status){
			console.log(response);
			
			$(".a1").val(response.規模);
			$(".a2").val(response.電話);
			$(".a3").val(response.產業);
			$(".a4").val(response.總部);
			$(".a5").val(response.網站);
			$(".a6").val(response.類型);
			$(".a7").val(response.創立);
			$(".a8").val(response.領域);
		},
		error: function(xtl) {
			console.log(xtl);
			$.unblockUI();
		}
	});
  
  
}

