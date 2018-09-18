package com.dtatkison.shoppingcart.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DashboardController {

    @RequestMapping(value = {"/dashboard"}, method = RequestMethod.GET)
    public ModelAndView dashboardHome()
    {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/Dashboard");

        return mv;
    }
}
