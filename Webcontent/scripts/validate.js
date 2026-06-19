
const nomeoCognPattern=/^[A-z]+$/g;
const emailPattern=/^\S+@\S+\.\S+$/g;
const indirizzoPattern=/^\w+(\s\w+)+$/g;
const telefonoPattern=/^[3]\d{9}$/;

const nomeErr="Il nome inserito non è valido (sono ammessi solo caratteri)";
const cognErr="il cognome inserito non è valido(sono ammessi solo caratteri)";
const emailErr="L'E-mail inserita non è valida(nomecognome@dominio.com/.it)";
const indErr="L'indirizzo inserito non è valido";
const telErr="Il numero di telefono non è valido";

function validate(){
	
	let valid=true;
	let form=document.getElementById("reg");
	let spanNome=document.getElementById("errorNome");
	
	if(!validateForm(form.nome,nomeoCognPattern,spanNome,nomeErr)){
		
		valid=false;
	}
	
	let spanCogn=document.getElementById("errorCognome");
	
	if(!validateForm(form.cognome,nomeoCognPattern,spanCogn,cognErr)){
		
		valid=false;
	}
	
	let spanEmail=document.getElementById("email");
	
	if(!validateForm(form.email,emailPattern,spanEmail,emailErr)){
		
		valid=false;
	}
	
	let spanInd=document.getElementById("indirizzo");
	
	if(!validateForm(form.indirizzo,indirizzoPattern,spanInd,indErr)){
		
		valid=false;
	}
	
	let spanTel=document.getElementById("tel");
		
		if(!validateForm(form.telefono,telefonoPattern,spanTel,telErr)){
			
			valid=false;
		}
	return valid;
}


function validateForm(elemForm, pattern, span, message) {
	if(elemForm.value.match(pattern)){
		elemForm.classList.remove("error");
		span.style.color = "black";
		span.innerHTML = "";
		return true;
	}
	formElem.classList.add("error");
	span.innerHTML = message;
	span.style.color = "red";
	return false;
}