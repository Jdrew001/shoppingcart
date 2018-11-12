package com.dtatkison.shoppingcart.Controllers;

import com.dtatkison.shoppingcart.Models.Product;
import com.dtatkison.shoppingcart.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class DashboardController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = {"/dashboard"}, method = RequestMethod.GET)
    public String dashboardHome()
    {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/Dashboard");

        return "Dashboard";
    }

    //product page
    @RequestMapping(value = {"/dashboard/products"}, method = RequestMethod.GET)
    public String productPage(Model model)
    {
        ModelAndView mv = new ModelAndView();
        List<Product> products = this.productService.getAllProducts();
        model.addAttribute("products", products);
        mv.setViewName("/Products");

        return "Products";
    }

    @PostMapping("/dashboard/product")
    public String addProduct(@RequestParam(value = "productName", required = true) String productName,
                             @RequestParam(value = "productDescription", required = true) String description,
                             @RequestParam(value = "productImage", required = true) MultipartFile productImage,
                             @RequestParam(value = "productPrice", required = true) String productPrice, RedirectAttributes redirectAttributes)
    {
        try {
            this.productService.addProduct(new Product(productName, Double.parseDouble(productPrice), description, this.productService.convertImageFile(productImage)));
        } catch(IOException ex) {
            System.out.println(ex.fillInStackTrace());
        }

        return "redirect:/dashboard/products";
    }

    @PostMapping("/dashboard/product/update")
    public String editProduct(@RequestParam(value = "pName", required = true) String productName,
                              @RequestParam(value = "pDescription", required = true) String description,
                              @RequestParam(value = "pImage", required = true) MultipartFile productImage,
                              @RequestParam(value = "pPrice", required = true) String productPrice,
                              @RequestParam(value = "productId", required = true) String productId, RedirectAttributes redirectAttributes) {

        try {
            if(productImage.isEmpty())
                this.productService.editProduct(new Product(Integer.parseInt(productId), productName, Double.parseDouble(productPrice), description, null));
            else
                this.productService.editProduct(new Product(Integer.parseInt(productId), productName, Double.parseDouble(productPrice), description, this.productService.convertImageFile(productImage)));

        } catch(IOException ex) {
            System.out.println(ex.fillInStackTrace());
        }

        return "redirect:/dashboard/products";
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
