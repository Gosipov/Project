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
   var i = 0;
   while(true){
      var cur = form.answers[i];
      if(cur == null) break;
      if(form.answers[i].value == ans){
         cnt++;
         form.class = 'right';
         return;
      }
      i++;
   }
   form.class = 'wrong';
};

function updateScore() {
	document.getElementById('sum').innerHTML = cnt+"/"+all;
};

updateScore();

function endQuiz(form) {
   form.result.value = cnt;
   form.submit();
};