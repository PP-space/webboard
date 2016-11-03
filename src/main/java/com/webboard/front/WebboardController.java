package com.webboard.front;

import com.webboard.models.UserVO;
import com.webboard.models.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Sorravit on 2/11/2559.
 *
 */
@Controller
public class WebboardController {

    @Autowired
    UserDAO userDAO;

    @ResponseBody
    @RequestMapping(value = "/")
    public String main(){
//        UserVO userVO = new UserVO("MAIL","MAIL","MAIL");
//        userDAO.save(userVO);
        System.out.print("HERE");
        return "main";
    }
}
