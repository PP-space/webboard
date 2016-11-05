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
    <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#writePost">Write Post</button><div id="writePost" class="modal fade" role="dialog"><div class="modal-dialog"><div class="modal-content"><div class="modal-header"><button type="button" class="close" data-dismiss="modal">&times;</button><h4 class="modal-title">Createing new Post</h4></div><div class="modal-body"><form id="writepostform" class="form-horizontal"><div class="form-group"><label class="control-label col-sm-2" for="ID">ID:</label><div class="col-sm-10"><input type="text" class="form-control" name="userId" placeholder="Enter ID"></div></div><div class="form-group"><label class="control-label col-sm-2" for="Name">Name:</label><div class="col-sm-10"><input type="text" class="form-control" name="userName" placeholder="Enter Name"></div></div><div class="form-group"><label class="control-label col-sm-2" for="Title">Title:</label><div class="col-sm-10"><input type="text" class="form-control" name="title" placeholder="Enter Title"></div></div><div class="form-group"><label class="control-label col-sm-2" for="email">Email:</label><div class="col-sm-10"><input type="email" class="form-control" name="email" placeholder="Enter email"></div></div><div class="form-group"><label class="control-label col-sm-2" for="pwd">Password:</label><div class="col-sm-10"><input type="password" class="form-control" name="password" placeholder="Enter password"></div></div><div class="form-group"><label class="control-label col-sm-2" for="data">Post:</label><div class="col-sm-10"><textarea class="form-control" rows="5" name="data" placeholder="Enter Post"></textarea></div></div><div class="form-group"><div class="col-sm-offset-2 col-sm-10"><button type="submit" class="btn btn-default" data-dismiss="modal" id="writepostsubmit">Submit</button></div></div></form></div><div class="modal-footer"><button type="button" class="btn btn-default" data-dismiss="modal">Close</button></div></div></div></div>
    <p class="topPagging"></p>
    <div class="list-group" id="post">
    </div>
    <p class="bottomPagging"></p>
</div>
<script>
    var options = {
        weekday: "long", year: "numeric", month: "short",
        day: "numeric", hour: "2-digit", minute: "2-digit"
    };

    $('#writepostsubmit').click(function(e) {
        e.preventDefault();
        $.ajax({
            type: 'POST',
            url: "post",
            data:$('#writepostform').serialize(),
            success: function(result){
                alert(result);
                genPagging("last");
            }
        })
    });

    function genModal(id, title, data){
        modal=' <div id="modal' + id + '" class="modal fade" role="dialog"> <div class="modal-dialog"> <div class="modal-content"> <div class="modal-header"> <button type="button" class="close" data-dismiss="modal">&times;</button> <h4 class="modal-title">' + title + '</h4> </div><div class="modal-body"> <p>' + data + '</p><div class="list-group" id="reply' + id + '"> </div><form id="replyForm' + id + '" class="form-inline collapse"> <div class="form-group"> <textarea class="form-control" name="data" placeholder="Enter Reply" rows="1"> </textarea> </div><button class="btn btn-info">Reply</button> </form> </div><div class="modal-footer"> <button type="button" class="btn btn-info" data-toggle="collapse" data-target="#replyForm' + id + '"><span class="glyphicon glyphicon-share-alt"></span>Reply</button> <button type="button" class="btn btn-warning" data-toggle="modal" data-target="#editpost' + id + '">Edit</button> <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#deletepost' + id + '">Delete</button> <button type="button" class="btn btn-default" data-dismiss="modal">Close</button> </div></div></div></div><div id="deletepost' + id + '" class="modal fade" role="dialog"> <div class="modal-dialog"> <div class="modal-content"> <div class="modal-header"> <button type="button" class="close" data-dismiss="modal">&times;</button> <h4 class="modal-title">Deleting :' + title + '</h4> </div><div class="modal-body"> <p> <form id="deletepostform' + id + '" class="form-horizontal"> <div class="form-group"> <label class="control-label col-sm-2" for="pwd">Password:</label> <div class="col-sm-10"> <input type="password" class="form-control" name="password" placeholder="Enter password"> </div></div><input type="hidden" name="id" value="' + id + '"> <div class="form-group"> <div class="col-sm-offset-2 col-sm-10"> <button type="submit" class="btn btn-danger" data-dismiss="modal" id="deletepost" onclick="deletePost(' + id + ')">Confirm Delete</button> </div></div></form> </p></div><div class="modal-footer"> <button type="button" class="btn btn-default" data-dismiss="modal">Close</button> </div></div></div></div><div id="editpost' + id + '" class="modal fade" role="dialog"> <div class="modal-dialog"> <div class="modal-content"> <div class="modal-header"> <button type="button" class="close" data-dismiss="modal">&times;</button> <h4 class="modal-title">Editing : ' + title + '</h4> </div><div class="modal-body"> <p> <form id="editpostform' + id + '" class="form-horizontal"> <div class="form-group"> <label class="control-label col-sm-2" for="pwd">Password:</label> <div class="col-sm-10"> <input type="password" class="form-control" name="password" placeholder="Enter password"> </div></div><input type="hidden" name="id" value="' + id + '"> <div class="form-group"> <label class="control-label col-sm-2" for="data">Post:</label> <div class="col-sm-10"> <textarea class="form-control" rows="5" name="data" placeholder="Enter Post">' + data + '</textarea> </div></div><div class="form-group"> <div class="col-sm-offset-2 col-sm-10"> <button type="submit" class="btn btn-warning" data-dismiss="modal" id="editpost" onclick="editPost(' + id + ')">Confirm Edit</button> </div></div></form> </p></div><div class="modal-footer"> <button type="button" class="btn btn-default" data-dismiss="modal">Close</button> </div></div></div></div>';
        return modal;
    }
    function getPost(blockNo){
        $.ajax({
            type: 'POST',
            url: "getPost",
            data:"blockNo="+blockNo,
            success: function(result){
                $("#post").html("");
                for(i in result){
                    $("#post").append("<a class=\'list-group-item\'  data-toggle=\'modal\' data-target=\'#modal"+result[i].idPost+"\' onclick=\'updateViewCount("+result[i].idPost+")\'>"+
                            "Post No."+result[i].idPost+
                            " Title: "+result[i].title +
                            " by: "+result[i].userName +
                            " Posted on: "+new Date(result[i].postDate).toLocaleTimeString("en-us", options) +
                            "Viewed : "+ result[i].viewCount+"</a>"+genModal(result[i].idPost,result[i].title,result[i].data))
                }
                $("[id*='modal']").on("hidden.bs.modal", function () {
                    getPost(blockNo);
                });
                $("[id*='modal']").on("show.bs.modal", function (e) {
                    getReply(e.target.id.substring(5));
                });
            }
        });
    }
    function getReply(id){
        $.ajax({
            type: 'POST',
            url: "getComment",
            data:"id="+id,
            success: function(result){
                var reply = "#reply"+id;
                $(reply).html("");
                for(i in result){
                    $(reply).append("<a class=\'list-group-item\' style=\'border:none\'>&gt;" + result[i].message + "</a>");
                }
            }
        })
    }

    function updateViewCount(id) {
        $.ajax({
            type: 'POST',
            url: "updateViewCount",
            data:"id="+id,
            success: function(){
            }
        });
    }
    function deletePost(id) {
        var name ='#deletepostform'+id;
        var modal='#modal'+id;
        $.ajax({
            type: 'POST',
            url: "deletePost",
            data:$(name).serialize(),
            success: function(result){
                alert(result);
                $(modal).modal('toggle');
            }
        })
    }
    function editPost(id) {
        var name ='#editpostform'+id;
        var modal='#modal'+id;
        $.ajax({
            type: 'POST',
            url: "editPost",
            data:$(name).serialize(),
            success: function(result){
                alert(result);
                $(modal).modal('toggle');
            }
        })
    }
    function genPagging(page){
        $.ajax({
            type: 'POST',
            url: "getPostCount",
            success: function(result){
                $('.topPagging,.bottomPagging').bootpag({
                    total: Math.ceil(parseInt(result)/10),
                    page: page == "last" ? Math.ceil(parseInt(result)/10) : page,
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
                getPost( page == "last" ? Math.ceil(parseInt(result)/10)-1 : page-1)
            }
        });
    }
    genPagging(1);

</script>
</body>
</html>
