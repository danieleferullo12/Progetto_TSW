function createXMLHttpRequest() {
	var request;
	try {
		
		request = new XMLHttpRequest();
	} catch (e) {
		
		try {
			request = new ActiveXObject("Msxml2.XMLHTTP");  
		} catch (e) {
			try {
				request = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (e) {
				alert("Il browser non supporta AJAX");
				return null;
			}
		}
	}
	return request;
}


function aggiungiAlCarrello(idProdotto,bottone){
	
	var request=createXMLHttpRequest();
	
	var url="carrello";
	var param="action=add&code=" + idProdotto;
	
	if(request){
		
		request.onreadystatechange=function(){
			
			if(this.readyState==4 && this.status==200){
				
				var risposta=JSON.parse(this.responseText);
				var contatore=document.getElementById("contatoreCar")
				
				if(contatore){
					
					contatore.innerText=risposta.nuovoTotale;
				}
				
				var testoOriginale=bottone.innerText;
				bottone.innerText="Aggiunto!";
				bottone.style.backgroundColor="#B1BF41";
				bottone.disabled=true;
				
				setTimeout(function(){
					
				bottone.innerText=testoOriginale;
				bottone.style.backgroundColor="";	
				bottone.disabled=false;	
					
				},2000);
				
			}
				
		};
		
		 request.open("POST",url,true);
		 request.setRequestHeader("X-Requested-With","XMLHttpRequest");
		 request.setRequestHeader("Connection", "close");
		 request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		 request.send(param);
		
		
	}
	
}