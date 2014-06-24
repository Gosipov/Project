var cnt = 0;
var all = 0;

function submitAnswer(form) {
	check(form);
	updateScore();
};

function check(form) {
   all++;
   var elements = form.elements;
      for (var i = 0, len = elements.length; i < len; ++i) {
         elements[i].disabled = true;
   }
   var ans = form.answer.value;
   if(form.answers != null && form.answers.value == ans){
	   cnt++;
       form.className = 'right';
       return;
   }   
   var i = 0;
   while(true){
      var cur = form.answers[i];
      if(cur == null) break;
      if(form.answers[i].value == ans){
         cnt++;
         form.className = 'right';
         return;
      }
      i++;
   }
   form.className = 'wrong';
};

function updateScore() {
	document.getElementById('sum').innerHTML = cnt+"/"+all;
};

updateScore();

function endQuiz(form) {
   form.result.value = cnt;
   form.submit();
};