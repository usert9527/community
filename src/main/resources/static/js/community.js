/**
 * 一级评论
 */
function post() {
    //当前问题的 id
    var questionId = $("#question_id").val();
    var content = $("#comment_content").val();
    comment2target(questionId,1,content);
}

function comment2target(targetid,type,content){
    if(!content){
        alert("回复不能为空");
        return;
    }
    $.ajax({
        type: "POST",
        url: "/comment",
        contentType: 'application/json',
        data: JSON.stringify({
            "parentId": targetid,
            "comment": content,
            "type": type
        }),
        success: function (response) {
            console.log(response);
            if (response.code == 200) {
               window.location.reload();
            } else {
                if (response.code == 1004) {
                    var isAccepted = confirm(response.message);
                    if (isAccepted) {
                        window.open("https://github.com/login/oauth/authorize?client_id=a28778c13fddb2a9f970&redirect_uri=http://localhost:/callback&scope=user&state=1");
                        window.localStorage.setItem("closable", true);
                    }
                } else {
                    alert(response.message);
                }

            }

        },
        dataType: "json"
    });
}

function comment(e) {
    var commentId = e.getAttribute("data-id");
    var comments = $("#input"+commentId).val();
    console.log(comments);
    comment2target(commentId,2,comments);

}

/**
 * 二级评论
 */
function collapseComments(e){
    // HTML5 增加了一项新功能是 自定义数据属性 ，也就是  data-* 自定义属性。
    //     // 在HTML5中我们可以使用以 data- 为前缀来设置我们需要的自定义属性，来进行一些数据的存放。
    //     // 当然高级浏览器下可通过脚本进行定义和数据存取。在项目实践中非常有用。

    // <span class="glyphicon glyphicon-comment icon" th:data-id="${commentDTO.id}" onclick="collapseComments(this)"></span>

    // $(e).data("id") 这样也可以获取自定义的 data-id 里面的数据
    var id = e.getAttribute("data-id");
    var comments = $("#comment-"+id);

    var collapse = e.getAttribute("data-collapse");
    if(collapse){
        //折叠二级评论
        comments.removeClass("in");
        e.removeAttribute("data-collapse")
        e.classList.remove("avtive");

    }else{
        var subCommentContainer = $("#comment-"+ id);

        if(subCommentContainer.children().length != 1){
            //展开二级评论
            comments.addClass("in");
            //标记二级评论的 展开状态
            e.setAttribute("data-collapse","in");
            e.classList.add("avtive");
        }else{
            $.getJSON("/comment/"+id,function (data) {
                console.log(data);
                $.each(data.data.reverse(),function(index,comment){  //reverse() 将数组中的数据倒序

                    var mediaLeftElement = $("<div/>",{
                        "class":"media-left"
                    }).append($("<img/>",{
                        "class":"media-object img-circle my",
                        "src":comment.user.avatarUrl
                    }));


                    var mediaBodyElement = $("<div/>",{
                        "class":"media-body"
                    }).append($("<h6/>",{
                        "class":"media-heading",
                        "html":comment.user.name
                    })).append($("<div/>",{
                        "html":comment.comment
                    })).append($("<div/>",{
                        "class":"menu"
                    }).append($("<span/>",{
                        "class":"pull-right",
                        "html": moment(comment.gmtCreate).format('YYYY-MM-DD h:mm')
                    })));


                    var mediaElement = $("<div/>",{
                        "class":"media"
                    }).append(mediaLeftElement)
                        .append(mediaBodyElement);

                    var commentElement = $("<div/>",{
                        "class":"col-lg-12 col-md-12 col-sm-12 col-xs-12 comments"
                    }).append(mediaElement);

                    subCommentContainer.prepend(commentElement);
                });
                //展开二级评论
                comments.addClass("in");
                //标记二级评论的 展开状态
                e.setAttribute("data-collapse","in");
                e.classList.add("avtive");
            });
        }
    }
}
