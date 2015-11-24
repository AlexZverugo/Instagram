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

    $('div[id^=likeButton]').click(function () {
        var likeButtonId = $(this)[0].id;
        var id = likeButtonId.substring('likeButton'.length);
        setLike(id);
    });

    $('div[id^=dislikeButton]').click(function () {
        var dislikeButtonId = $(this)[0].id;
        var id = dislikeButtonId.substring('dislikeButton'.length);
        setDislike(id);
    });

    $('[data-toggle="tooltip"]').tooltip();
});

function setLike(postId) {
    var rating = {};
    rating["post"] = postId;
    $.ajax({
        type: "POST",
        url: "/rating/setLike",
        data: JSON.stringify(rating),
        dataType: 'json',
        contentType: 'application/json',
        mimeType: 'application/json',

        success: function (data) {
            setRatingsCount(data);
        }
    });
}

function setRatingsCount(data) {
    $('#likeCount' + data.id).html(data.like);
    $('#dislikeCount' + data.id).html(data.dislike);
}

function setDislike(postId) {
    var rating = {};
    rating["post"] = postId;
    $.ajax({
        type: "POST",
        url: "/rating/setDislike",
        data: JSON.stringify(rating),
        dataType: 'json',
        contentType: 'application/json',
        mimeType: 'application/json',

        success: function (data) {
            setRatingsCount(data);
        }
    });
}

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

function deleteComment(id) {
    var comment = {};
    comment["id"] = id;

    $.ajax({
        type: "GET",
        url: "/comment/deleteComment",
        data: comment,
        dataType: 'json',
        contentType: 'application/json',
        mimeType: 'application/json',


        success: function (data) {
            var comment = $('#fullComment' + id);
            comment.remove();
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
    var authUserId =  $('div[id^=authUser]')[0].id.substring('authUser'.length);
    for (var commentIndex = 0; commentIndex < data.length; commentIndex++) {
        var fullComment = '<div id="fullComment' + data[commentIndex].id + '">';
        if (authUserId == data[commentIndex].owner || data[commentIndex].sender == authUserId) {
            var divRemoveCross = '<div id="deleteComment' + data[commentIndex].id + '" class="post-del cursor-pointer">';
            var iconCross = '<span class="glyphicon glyphicon-remove" aria-hidden="true">';
            var commentHeader = '<div align="left" style="border: 2px solid #b9b9b9"><b>' + data[commentIndex].senderName
                + '</b>';
            var commentContent = '<pre class="pre-post">' + data[commentIndex].commentContent + '</pre><hr>';
            commentsViewHTML += fullComment + commentHeader + divRemoveCross + iconCross + '</div></div>'
            + commentContent + '</div>';
        } else {
            commentsViewHTML += fullComment + '<div align="left" style="border: 2px solid #b9b9b9"><b>'
            + data[commentIndex].senderName + '</b></div>'
            + '<pre class="pre-post">' + data[commentIndex].commentContent + '</pre><hr></div>';
        }

    }
    $('#commentsOfPost').html(commentsViewHTML);
    addDeleteCommentListeners();
}

function addDeleteCommentListeners() {
    $('div[id^=deleteComment]').click(function () {
        var removeId = $(this)[0].id;
        var id = removeId.substring('deleteComment'.length);
        deleteComment(id);
    });
}

function addComment() {
    var comment = {};
    comment["post"] = $('#commentInput').attr("id-parameter");
    comment["commentContent"] = $('#commentContent').val();
    comment["owner"] = $('div[id^=authUser]').attr("current-user");
    $.ajax({
        type: "POST",
        url: "/comment/addComment",
        data: JSON.stringify(comment),
        dataType: 'json',
        contentType: 'application/json',
        mimeType: 'application/json',

        success: function (data) {
            var fullComment = '<div id="fullComment' + data.id + '">';
            var divRemoveCross = '<div id="deleteComment' + data.id + '" class="post-del cursor-pointer">';
            var commentHeader = '<div align="left" style="border: 2px solid #b9b9b9"><b>' + data.senderName + '</b>';
            var iconCross = '<span class="glyphicon glyphicon-remove" aria-hidden="true">';
            var commentContent = '<pre class="pre-post">' + data.commentContent + '</pre><hr>';
            var comment = fullComment + commentHeader + divRemoveCross + iconCross + '</div></div>' + commentContent
                + '</div>';

            $('#commentsOfPost').append(comment);
            addDeleteCommentListeners();
            var textarea = $('#commentContent');
            textarea.val('');
            textarea.focus();
            var popup = $('#myModal');
            popup.scrollTop(popup.prop('scrollHeight'));
        }
    });
}

