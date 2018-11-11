package com.dtatkison.shoppingcart.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AuthenticationController {

    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public ModelAndView login()
    {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("Login");
        System.out.println("This is a test to see if i");
        return mv;
    }

    @RequestMapping(value = {"/loginfailure"}, method = RequestMethod.POST)
    public ModelAndView LoginFailure(Model model)

    {
        ModelAndView mv = new ModelAndView();
        model.addAttribute("error", new String("Your login attempt is unsuccessful"));
        mv.setViewName("Login");

        return mv;
    }
}
