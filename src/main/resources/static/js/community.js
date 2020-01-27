function post() {
    var questionId = $("#question_id").val();
    var content = $("#comment_content").val();
    if(!content) {
        alert("不能回复空内容");
        return;
    }
    $.ajax({
        type:"post",
        url:"/comment",
        data:JSON.stringify({
            "parentId":questionId,
            "content":content,
            "type":1
        }),
        success:function (response) {
            if(response.code==200) {
                window.location.reload();
            } else {
                if(response.code==2003) {
                    var isAccepted = confirm(response.message);
                    console.log(isAccepted);
                    if(isAccepted) {
                        console.log(isAccepted);
                        window.open("https://github.com/login/oauth/authorize?client_id=e38f3becbee9528b853a&redirect_uri=http://localhost:8888/callback&scope=user&state=1");
                        window.localStorage.setItem("closable", true);
                    }
                }else {
                    alert(response.message);
                }
            }
        },
        dataType:"json",
        contentType:'application/json'
    });
}