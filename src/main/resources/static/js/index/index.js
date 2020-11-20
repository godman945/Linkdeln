
$(document).ready(

 	

);


(function() {
	ReactDOM.render(
        <h1>Hello, world !  @@</h1>,
        document.getElementById('pageTitle')
     );
      
      //產生按鈕
      const element = (
		  <div>
		    <div>
		    <button type="button" id="listerClickButton" class="btn btn-primary" onClick={handleLink}>點擊測試</button>
		      <ul>
				　<li>A01</li>
				　<li>A02</li>
				　<li>A03</li>
				</ul>
		    </div>
		  </div>
		)
       ReactDOM.render(element, document.getElementById('viewArea'));
      
      
      function tick() {
        const timeElement = (
		      <h2>現在時間 {new Date().toLocaleTimeString()}</h2>
		  );
  		ReactDOM.render(timeElement, document.getElementById('timeArea'));
      }
     setInterval(tick, 1000);
})()



	 

function handleLink() {
   alert('點擊變換資料.');
}

