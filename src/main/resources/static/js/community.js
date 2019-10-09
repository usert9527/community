function post() {
    var questionId = $("#question_id").val();
    var content = $("#comment_content").val();

    $.ajax({
        type: "POST",
        url: "/comment",
        contentType: 'application/json',
        data: JSON.stringify({
            "parentId": questionId,
            "comment": content,
            "type": 1
        }),
        success: function (response) {
            console.log(response);
            if (response.code == 200) {
                $("#comment_section").hide();
            } else {
                if (response.code == 1004) {
                    var isAccepted = confirm(response.message);
                    if (isAccepted) {
                        window.open("https://github.com/login/oauth/authorize?client_id=a28778c13fddb2a9f970&redirect_uri=http://localhost:8080/callback&scope=user&state=1");
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
