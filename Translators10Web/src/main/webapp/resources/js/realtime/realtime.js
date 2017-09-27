var stompClient;
function connect(funcs) {
    var socket = new SockJS('/realtime');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        for (var i = 0; i < funcs.length; i++) {
            funcs[i]();
        }
    });
}
function subscribeChatMessage(conversationId) {
    stompClient.subscribe('/chatMessage/' + conversationId, function (messageOutput) {
        var body = JSON.parse(messageOutput.body);
        if (body.type == 'MESSAGE_CREATED') {
            addMessage(body.content)
        }
        else if (body.type == 'MARK_AS_READ') {
            markAsRead(body.content)
        }
    });
}
function subscribeUnreadMessages() {
    stompClient.subscribe('/user/countUnreadMessages', function (messageOutput) {
        var body = JSON.parse(messageOutput.body);
        setCountOfUnreadMessages(body.content)
    });
}
function subscribeJobUnreadMessages() {
    stompClient.subscribe('/user/unreadMessages', function (messageOutput) {
        var body = JSON.parse(messageOutput.body);
        var innerHTML = document.getElementsByClassName('servicerequestid');
        for (var i = 0; i < innerHTML.length; i++) {
            if (innerHTML[i].textContent == body.content.conversationId) {
                var found = findChildNodesByParentElementAndChildClass(innerHTML[i].parentElement, "unreadmessages");
                found.innerHTML = body.content.count;
                break;
            }
        }
    });
}

function findChildNodesByParentElementAndChildClass(parentElement, classChild) {
    for (var i = 0; i < parentElement.childNodes.length; i++) {
        var childClassName = parentElement.childNodes[i].className;
        if (childClassName && childClassName.includes(classChild)) {
            return parentElement.childNodes[i];
        }
    }
}
function subscribeConverstationsEvent(){
    stompClient.subscribe('/user/unreadMessages', function (messageOutput) {
        var body = JSON.parse(messageOutput.body);
        var tr = document.getElementById(body.content.conversationId);
        if(body.content.count>0) {
            tr.style.backgroundColor = "#cacaca";
            tr.className = "";
        }else{
            tr.className = "active";
        }
    });
}
function addMessage(content) {
    var tbody = document.getElementById('datatables').getElementsByTagName('tbody')[0];
    var newRow = tbody.insertRow(tbody.rows.length);
    newRow.style.backgroundColor = "#cacaca";
    newRow.id = content.id;


    var img = document.createElement('img');
    img.src = content.photoUrl;
    img.className="img-circle";
    img.height="72";
    img.width="52";

    var newCell = newRow.insertCell(0);
    newCell.className = 'text-center';
    var newText = document.createTextNode(content.sender);
    newCell.appendChild(img);

    newCell = newRow.insertCell(1);
    newCell.className = 'text-center';
    newText = document.createTextNode(content.message);
    newCell.appendChild(newText);

    newCell = newRow.insertCell(2);
    newCell.className = 'text-center';
    newText = document.createTextNode(toStringDate(content.date));
    newCell.appendChild(newText);
}
function toStringDate(date2) {
    var date = new Date(date2);
    var month = ("0" + (date.getMonth() + 1)).slice(-2);
    var day = ("0" + date.getDate()).slice(-2);
    var year = date.getFullYear();

    var seconds = ("0" + date.getSeconds()).slice(-2);
    var minutes = ("0" + date.getMinutes()).slice(-2);
    var hour = ("0" + date.getHours()).slice(-2);
    var milliSeconds = date.getMilliseconds();

    return day + " " + month + " " + year+" "+ hour + ":" + minutes + ":" + seconds + "." + milliSeconds
}
function markAsRead(id) {
    var tr = document.getElementById(id);
    tr.className = 'active';
}
function setCountOfUnreadMessages(num) {
    if (num > 0) {
        document.getElementById('countUnreadMessages').innerHTML = '<i class="icon-envelope-letter"></i><span class="badge badge-default">'+ num +'</span> ';
    }else {
        document.getElementById('countUnreadMessages').innerHTML = '<i class="icon-envelope-open"></i>';
    }
}
function requestSetCountOfUnreadMessages() {
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState == XMLHttpRequest.DONE) {
            setCountOfUnreadMessages(xhr.responseText);
        }
    };
    xhr.open("POST", "/countOfUnreadMessages");
    xhr.send();

}