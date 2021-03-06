package com.webboard.front;

import com.webboard.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * Created by Sorravit on 4/11/2559.
 */
@RestController
public class WebboardRestController {
    @Autowired
    UserDAO userDAO;
    @Autowired
    PostDAO postDAO;
    @Autowired
    ReplyDAO replyDAO;

    @RequestMapping(value = "/postComment")
    public String postComment(HttpServletRequest request){
        try{
            ReplyVO replyVO = new ReplyVO(Integer.parseInt(request.getParameter("id")),request.getParameter("reply"));
            replyDAO.save(replyVO);
        }catch (Exception e){
            return "Something wrong";
        }
        return "Success";
    }
    @RequestMapping(value = "/getComment")
    public List<ReplyVO> getComment(HttpServletRequest request){
        return replyDAO.findReplyByPost(Integer.parseInt(request.getParameter("id")));
    }

    @RequestMapping(value = "/getPost")
    public List<PostVO> getPost(HttpServletRequest request){
        int pageSize=10;
        int block = Integer.parseInt(request.getParameter("blockNo"));
        return postDAO.findInIdRange(pageSize*block);
    }
    @RequestMapping(value = "/getPostCount")
    public long getPostCount(){
        return postDAO.count();
    }
    @RequestMapping(value = "/updateViewCount")
    public String updateViewCount(HttpServletRequest request){
        try{
            postDAO.updateViewCount(Integer.parseInt(request.getParameter("id")));
        }catch(Exception e){
            e.printStackTrace();
            return "Fail";
        }
        return "Success";
    }
    @RequestMapping(value = "/post")
    public String post(HttpServletRequest request){
        try {
            UserVO user = userDAO.findOne(Integer.parseInt(request.getParameter("userId")));
            if(user.getPassword().equals(request.getParameter("password"))){
                PostVO postVO = new PostVO(Integer.parseInt(request.getParameter("userId")),request.getParameter("userName"),request.getParameter("title"),request.getParameter("data"),new Date(),0);
                postDAO.save(postVO);
            }
            else{
//                return "Wrong password";
                return "Wrong user or password";
            }
        }catch (Exception e){
            e.printStackTrace();
//            return "Wrong member id";
            return "Wrong user or password";
        }
        return "Success";
    }
    @RequestMapping(value = "/deletePost")
    public String deletePost(HttpServletRequest request){
        try {
            PostVO post = postDAO.findOne(Integer.parseInt(request.getParameter("id")));
            UserVO user = userDAO.findOne(post.getUser_id());
            if(user.getPassword().equals(request.getParameter("password"))){
                postDAO.delete(Integer.parseInt(request.getParameter("id")));
                replyDAO.deleteAllReplyToPostId(Integer.parseInt(request.getParameter("id")));
            }
            else{
                return "Wrong password";
            }
        }catch (Exception e){
            e.printStackTrace();
            return "Something wrong";
        }
        return "Success";
    }

    @RequestMapping(value = "/editPost")
    public String editPost(HttpServletRequest request){
        try {
            PostVO post = postDAO.findOne(Integer.parseInt(request.getParameter("id")));
            UserVO user = userDAO.findOne(post.getUser_id());
            if(user.getPassword().equals(request.getParameter("password"))){
                postDAO.updatePost(Integer.parseInt(request.getParameter("id")),request.getParameter("data"));
            }
            else{
                return "Wrong password";
            }
        }catch (Exception e){
            e.printStackTrace();
            return "Something wrong";
        }
        return "Success";
    }
    @RequestMapping(value = "/saveMember")
    public String saveMember(HttpServletRequest request){
        try {
//            byte[] password =hash(request.getParameter("password").toCharArray(),"Salt".getBytes());
//            UserVO userVO = new UserVO(request.getParameter("name"),password.toString(),request.getParameter("email"));
            UserVO userVO = new UserVO(request.getParameter("name"),request.getParameter("password"),request.getParameter("email"));
            userDAO.save(userVO);
        }catch (Exception e){
            return "Something wrong";
        }
        return "Success";
    }
    @RequestMapping(value = "/checkMember")
    public String checkMember(HttpServletRequest request) {
        try {
            UserVO user = userDAO.findOne(Integer.parseInt(request.getParameter("id")));
            return String.valueOf(user.getPassword().equals(request.getParameter("password")));
//        return String.valueOf(isExpectedPassword(request.getParameter("password").toCharArray(),"Salt".getBytes(),request.getParameter("expectedHash").getBytes()));
        } catch (Exception e) {
            return "false";
        }
    }

}

