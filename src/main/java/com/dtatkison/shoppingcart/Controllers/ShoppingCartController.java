package com.dtatkison.shoppingcart.Controllers;

        import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.servlet.ModelAndView;

        import javax.servlet.http.HttpServletRequest;

@Controller
public class ShoppingCartController {

    @GetMapping("/")
    public String Cart(Model model, HttpServletRequest request)
    {
        ModelAndView vm = new ModelAndView();
        model.addAttribute("ipaddress", request.getRemoteAddr());
        return "Cart";
    }
}
