function showCommentTextarea(id, disabled) {
    var textareaButton = document.getElementById("commentTextareaButton" + id);
    textareaButton.setAttribute("disabled", disabled);
}

function setDisplayAttribute(state) {
    document.getElementById('window').style.display = state;
    document.getElementById('wrap').style.display = state;
}


function setPostPopUpData(img, postContent) {
    if (img != null) {
        $("#postImg").attr("src", img);
    }

    postContent = postContent.replace(/%0A/g, "\n").replace(/%0D/g, "\r");
    $("#postContent").html(postContent);
}
