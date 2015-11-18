$(document).ready(function () {
    $('div[id^=post]').click(function () {
        $('#popupImg').html($(this).find('[id^=imgPost]').clone());
        $('#popupContent').html($(this).find('[id^=contentPost]').clone());

        $('#comment').attr('action', '/comment/addComment?postId=' + $(this).attr("id-parameter"));
        $('#commentInput').attr("id-parameter", $(this).attr("id-parameter"));

        showComments($(this).attr("id-parameter"))
    });

    $('div[id^=remove]').click(function () {
        var removeId = $(this)[0].id;
        var id = removeId.substring('remove'.length);
        deletePost(id);
    });

    $('#commentInput').submit(function (event) {
        event.preventDefault();
        addComment();
    });
});


function deletePost(id) {
    var post = {};
    post["id"] = id;

    $.ajax({
        type: "GET",
        url: "/post/deletePost",
        data: post,
        dataType: 'json',
        contentType: 'application/json',
        mimeType: 'application/json',


        success: function (data) {
            var post = $('#fullPostContent' + id);
            post.remove();
        }
    });
}


function showComments(id) {
    var post = {};
    post["id"] = id;

    $.ajax({
        type: "GET",
        url: "/comment/getCommentOfPost",
        data: post,
        dataType: 'json',
        contentType: 'application/json',
        mimeType: 'application/json',

        success: function (data) {
            display(data);
        }
    });
}

function display(data) {
    var commentsViewHTML = '';

    for (var commentIndex = 0; commentIndex < data.comments.length; commentIndex++) {
        commentsViewHTML += '<div align="left"><b>' + data.comments[commentIndex].senderName + ':</b></div><br>'
        + '<pre class="pre-post">' + data.comments[commentIndex].commentContent + '</pre><hr>';
    }

    $('#commentsOfPost').html(commentsViewHTML);
}

function addComment() {
    var comment = {};
    comment["post"] = $('#commentInput').attr("id-parameter");
    comment["commentContent"] = $('#commentContent').val();

    $.ajax({
        type: "POST",
        url: "/comment/addComment",
        data: JSON.stringify(comment),
        dataType: 'json',
        contentType: 'application/json',
        mimeType: 'application/json',

        success: function (data) {
            var comment = '<div align="left"><b>' + data.senderName + ':</b></div><br>'
                + '<pre class="pre-post">' + data.commentContent + '</pre><hr>';
            $('#commentsOfPost').append(comment);
            var textarea = $('#commentContent');
            textarea.val('');
            textarea.focus();
            var popup = $('#myModal');
            popup.scrollTop(popup.prop('scrollHeight'));
        }
    });
}
