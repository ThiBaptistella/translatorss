function onMessageSubmit() {
    var xhr = new XMLHttpRequest();

    var body = {
        "sender": document.getElementById("sender").value,
        "conversationid": document.getElementById("conversationid").value,
        "message": document.getElementById("messageSbmt").value,
        "photoUrl": document.getElementById("photoUrl").value
    };
    xhr.open("POST", "/sbmtMessage");
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.send(JSON.stringify(body));
    document.getElementById("messageSbmt").value = "";
}

function onMessageFocus(conversationId) {
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "/" + conversationId + "/markAsRead");
    xhr.send();
    console.log(xhr.status);
}