package com.webboard.front;

import com.webboard.models.PostDAO;
import com.webboard.models.PostVO;
import com.webboard.models.UserDAO;
import com.webboard.models.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
    @RequestMapping(value = "/getPost")
    public List<PostVO> getPost(HttpServletRequest request){
        List<PostVO> postVOList = postDAO.findInIdRange(10,20);
        return postVOList;
    }
    @RequestMapping(value = "/getPostCount")
    public long getPostCount(HttpServletRequest request){
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
        try{
            PostVO postVO = new PostVO(Integer.parseInt(request.getParameter("userId")),request.getParameter("userName"),request.getParameter("title"),request.getParameter("data"),new Date(),0);
            postDAO.save(postVO);
        }catch (Exception e){
            e.printStackTrace();
            return "false";
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
