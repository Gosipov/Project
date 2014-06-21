var x = 0;
function myFunction(c) {
    var container = document.getElementById('myList');
    var node=document.createElement('input');
    node.type = 'radio';
    node.name = 'answer';
    node.value = x;
    node.checked = c;
    var text=document.createElement('input');
    text.type = 'text';
    text.name = 'answer' + x++;
    text.id = text.name;
    container.appendChild(node);
    container.appendChild(text);
    container.appendChild(document.createElement('br'));
};
myFunction(true);
myFunction(false);