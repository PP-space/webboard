package com.webboard.front;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Sorravit on 2/11/2559.
 *
 */
@Controller
public class WebboardController {
    @ResponseBody
    @RequestMapping(value = "/")
    public String main(){
        return "Pizza";
    }
}
