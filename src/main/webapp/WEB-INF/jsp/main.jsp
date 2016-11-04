<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Sorravit
  Date: 3/11/2559
  Time: 21:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>webboard</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="http://botmonster.com/jquery-bootpag/jquery.bootpag.js"></script>
</head>
<body>
<div class="container">
    <p class="topPagging"></p>
    <div class="list-group">
    </div>
    <p class="bottomPagging"></p>
</div>
<script>
    var options = {
        weekday: "long", year: "numeric", month: "short",
        day: "numeric", hour: "2-digit", minute: "2-digit"
    };

    function genModal(id,title,data){
        modal='<div id="modal'+id+'" class="modal fade" role="dialog"><div class="modal-dialog"><div class="modal-content"><div class="modal-header"><button type="button" class="close" data-dismiss="modal">&times;</button> <h4 class="modal-title">'+title+'</h4></div><div class="modal-body"><p>'+data+'</p></div><div class="modal-footer"><button type="button" class="btn btn-default" data-dismiss="modal">Close</button></div></div></div></div>';
        return modal;
    }
    function getPost(blockNo){
        $.ajax({
            type: 'POST',
            url: "getPost",
            data:"blockNo="+blockNo,
            success: function(result){
                $(".list-group").html("")
                for(i in result){
                    $(".list-group").append("<a class=\'list-group-item\'  data-toggle=\'modal\' data-target=\'#modal"+result[i].idPost+"\' onclick=\'updateViewCount("+result[i].idPost+")\'>"+
                            "Post No."+result[i].idPost+
                            " Title: "+result[i].title +
                            " by: "+result[i].userName +
                            " Posted on: "+new Date(result[i].postDate).toLocaleTimeString("en-us", options) +
                            "Viewed : "+ result[i].viewCount+"</a>"+genModal(result[i].idPost,result[i].title,result[i].data))
                }
                $("[id*='modal']").on("hidden.bs.modal", function () {
                    getPost(blockNo);
                });
            }
        });
    }
    function updateViewCount(id) {
        $.ajax({
            type: 'POST',
            url: "updateViewCount",
            data:"id="+id,
            success: function(result){
            }
        });
    }
    function genPagging(page){
        $.ajax({
            type: 'POST',
            url: "getPostCount",
            success: function(result){
                $('.topPagging,.bottomPagging').bootpag({
                    total: Math.ceil(parseInt(result)/10),
                    page: page,
                    maxVisible: 5,
                    leaps: true,
                    firstLastUse: true,
                    first: '←',
                    last: '→',
                    wrapClass: 'pagination',
                    activeClass: 'active',
                    disabledClass: 'disabled',
                    nextClass: 'next',
                    prevClass: 'prev',
                    lastClass: 'last',
                    firstClass: 'first'
                }).on("page", function(event, num){
                    getPost(num-1);
                });
            }
        });
    }
    genPagging(1);
    getPost(0);

</script>
</body>
</html>
