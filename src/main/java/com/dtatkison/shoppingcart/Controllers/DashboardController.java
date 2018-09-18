package com.dtatkison.shoppingcart.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DashboardController {


    @RequestMapping(value = {"/dashboard"}, method = RequestMethod.GET)
    public String dashboardHome()
    {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/Dashboard");

        return "Dashboard";
    }

    //product page
    @RequestMapping(value = {"/dashboard/products"}, method = RequestMethod.GET)
    public String productPage()
    {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/Products");

        return "Products";
    }

    //customer page
    @RequestMapping(value = {"/dashboard/customers"}, method = RequestMethod.GET)
    public String customerPage()
    {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/Customers");

        return "Customers";
    }
}
