function pollAvailableUsers() {
	dojo.event.topic.publish("getAvailableUsers");
}

function pollInvitations() {
	dojo.event.topic.publish("getInvitations");
}


function pollMoves() {
	dojo.event.topic.publish("getMoves");
}


function mainscreenpoll() {
	setInterval(pollAvailableUsers,1000);
	setInterval(pollInvitations,1000);
}

function gamescreenpoll() {
	setInterval(pollMoves,500);
}
  
dojo.event.topic.subscribe("updateConversation", function(event, widget){
	updateConversation();
});

dojo.event.topic.subscribe("clearNewMessage", function(event, widget){
	clearNewMessage();
});

dojo.event.topic.subscribe("updateGame", function(event, widget){
	updateGame();
});

function makeMove(id) {
	var lable = document.getElementById(id);
	var req=XMLHttpRequest();
	req.open('POST','SendMove.action',false);
	req.setRequestHeader( 'Content-type', 'application/x-www-form-urlencoded');
	req.send('move='+id);
}


function updateGame() {
	var buffer = document.getElementById('movebuffer').value;
	var json = JSON.parse(buffer);
	
		
	for(i=0; i<9; i++){
		var target = document.getElementById(i.toString());
		target.innerHTML = json.gameboard[i];
	}
	
	if(json.oppoOffline == '1'){
		alert("Your competitor is offline. You will be back to main screen.");
		window.history.go(-1);			
	}
	
	if(json.rejection == '1'){
		alert("Your invitation is rejected...");
		window.history.go(-1);			
	}
	
	if(json.winner != null){
		var target = document.getElementById('gameover');
		target.innerHTML = 'Winner is ' + json.winner + '! With symbol ' + json.symbol + '!';
		var turn = document.getElementById('turn');
		turn.innerHTML = "";
		addButton();
	}
	
	if(json.draw == '1'){
		var target = document.getElementById('gameover');
		target.innerHTML = 'This is a draw!';
		var turn = document.getElementById('turn');
		turn.innerHTML = "";
		addButton();
	}

		
	if(json.turn != null && json.winner == null && json.draw == null){
		var target = document.getElementById('turn');
		target.innerHTML = json.turn;
	}
}

function addButton(){
	if(document.getElementById('backbutton') == null){
		var target = document.getElementById('gameover');
		var back_button = document.createElement("input");
		back_button.setAttribute("id", "backbutton");
		back_button.setAttribute("type", "button");
		back_button.setAttribute("value", "OK");
		back_button.setAttribute("onclick", "goBack()");
		target.appendChild(back_button);
	}
}

function goBack(){
	var req=XMLHttpRequest();
	req.open('POST','back.action',false);
	req.setRequestHeader( 'Content-type', 'application/x-www-form-urlencoded');
	req.send();
	window.history.go(-1);
}
