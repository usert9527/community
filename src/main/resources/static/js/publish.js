$(function () {
    var a = new Array();
    var mynum = 0;

    $(".myForm").submit(function () {

        var tar = a.join(",");
        $(".commint").val(tar);

    });

    $("#tag").focus(function () {

        console.log("触发");

        var ulElement = $(".nav-tabs");

        if (ulElement.children().length > 1) {

            $(".publish-tar").css("display", "inline-block");

        } else {
            $(".publish-tar").css("display", "inline-block");
            var tarDiv = $(".tab-content");
            $.ajax({
                type: "POST",
                url: "/allTarType",
                contentType: 'application/json',
                success: function (response) {

                    $.each(response.reverse(), function (index, object) {


                        var div = $("<div/>", {
                            "role": "tabpanel",
                            "class": " tab-pane",
                            "id": "home" + object.id,
                        })

                        tarDiv.prepend(div);
                        var aElement = $("<a/>", {
                            "role": "tab",
                            "data-toggle": "tab",
                            "id": object.id,
                            "href": "#home" + object.id,
                            "aria-controls": "home" + object.id,
                            "html": object.typeName
                        });

                        var liElement = $("<li/>", {
                            "role": "presentation"
                        }).append(aElement);


                        $(".nav-tabs").prepend(liElement);
                    });

                    ulElement.children(":first").addClass("active");
                    tarDiv.children(":first").addClass("active");
                    //一选择的时候就加载的
                    var tarOne = ulElement.children(":first").children(":first").attr("id");

                    $.ajax({
                        type: "post",
                        url: "/allTar/" + tarOne,
                        contentType: 'application/json',
                        // data:JSON.stringify({
                        //     "id": tarOne
                        // }),
                        success: function (tar) {
                            var divhome = $("#home" + tarOne);
                            // <div role="tabpanel" class="tab-pane active" id="home">1...</div>
                            // <span class="label label-info">Info</span>
                            $.each(tar.reverse(), function (index, tarOne) {

                                divhome.prepend($("<span/>", {
                                    "class": "label label-info publish-span",
                                    "html": tarOne.tarName,
                                    "id":tarOne.id
                                }));

                                // tarDiv.prepend(div);
                            })
                            $(".publish-span").click(function () {
                                console.log($(this));
                            });

                        },
                        dataType: "json"
                    });


                    $(".nav-tabs li").click(function () {

                        var tarId = $(this).children(":first").attr("id");

                        var thishome = $("#home" + tarId);


                        if (thishome.children().length > 1) {

                        } else {
                            $.ajax({
                                type: "post",
                                url: "/allTar/" + tarId,
                                contentType: 'application/json',
                                // data:JSON.stringify({
                                //     "id": tarOne
                                // }),
                                success: function (tar) {
                                    var divhome = $("#home" + tarOne);
                                    // <div role="tabpanel" class="tab-pane active" id="home">1...</div>
                                    // <span class="label label-info">Info</span>
                                    $.each(tar.reverse(), function (index, tarOne) {

                                        $("#home" + tarId).prepend($("<span/>", {
                                            "class": "label label-info publish-span",
                                            "html": tarOne.tarName,
                                            "id": tarOne.id
                                        }));

                                        // tarDiv.prepend(div);
                                    })


                                    //添加标签
                                    $(".publish-span").click(function () {

                                        var leng = $(".publish-div-tar span").length;

                                        console.log(leng);
                                        if(leng >= 6){
                                            alert("只能选择三个");
                                            return;
                                        }
                                        // .append("&nbsp;&nbsp;<span class= 'remove-span' onclick='onspan(this)'>×</span>")

                                        var remove = $(this);

                                        // var tar = $(".commint");
                                        var innum = remove.attr("id");

                                        if (a.length == 0) {
                                            a[mynum] = remove.attr("id");
                                            mynum++;
                                        } else {
                                            for (var i = 0; i < a.length; i++) {
                                                var ele = a[i];

                                                if (innum.indexOf(ele) == 0) {
                                                    return;
                                                }
                                            }
                                            a[mynum] = remove.attr("id");
                                            mynum++;
                                        }
                                        var copy = remove.clone();

                                        console.log(a)
                                        copy.append("&nbsp;&nbsp;<span class= 'remove-span'>×</span>");
                                        var span = copy.prop("outerHTML");

                                        $(".publish-div-tar").prepend(span);

                                        $(".remove-span").click(function () {
                                            console.log("56555555555");

                                            var id = $(this).parent().attr("id");

                                            // a.remove(id);
                                            for (var i = 0; i < a.length; i++) {
                                                if (a[i] == id) {
                                                    a.splice(i,1);
                                                    break;
                                                }
                                            }
                                            $(this).parent().remove();

                                        });
                                    });


                                },
                                dataType: "json"
                            });
                        }
                        thishome.siblings().removeClass("active");
                        thishome.addClass("active");
                    });

                },
                dataType: "json"
            });
        }

    });

    $("#tag").blur(function () {
        // $(".publish-tar").css("display","none");
    });

    $(".publish-div-tar").click(function () {
        $("#tag").focus();
    });


});

function onspan(e, a) {


    var id = $(e).parent().attr("id");

    console.log(a);


    $(e).parent().remove();
}

