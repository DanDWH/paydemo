package com.dwh.paydemo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
    @RequestMapping("/")
    public String index(){
        return "index.html";
    }

    @RequestMapping("/sign")
    public ModelAndView showUsername(){
        ModelAndView mv = new ModelAndView();
        mv.addObject("name","dongguo");
        mv.setViewName("test");
        return  mv;
    }
}
