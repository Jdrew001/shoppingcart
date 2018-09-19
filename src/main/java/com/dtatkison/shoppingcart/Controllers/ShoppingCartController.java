package com.dtatkison.shoppingcart.Controllers;

        import com.dtatkison.shoppingcart.Models.PendingOrder;
        import com.dtatkison.shoppingcart.Models.Product;
        import com.dtatkison.shoppingcart.Repositories.OrderRepository;
        import com.dtatkison.shoppingcart.Repositories.PendingOrderRepository;
        import com.dtatkison.shoppingcart.Repositories.ProductRepository;
        import com.dtatkison.shoppingcart.Services.OrderService;
        import com.dtatkison.shoppingcart.Services.PendingOrderService;
        import com.dtatkison.shoppingcart.Services.ProductService;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.PathVariable;
        import org.springframework.web.servlet.ModelAndView;

        import javax.servlet.http.HttpServletRequest;
        import java.util.ArrayList;
        import java.util.List;

@Controller
public class ShoppingCartController {

    //services
    @Autowired
    private PendingOrderService pendingOrderService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/")
    public String Cart(Model model, HttpServletRequest request)
    {
        ModelAndView vm = new ModelAndView();
        model.addAttribute("ipaddress", request.getRemoteAddr());
        List<Product> products = new ArrayList<>();
        products = this.pendingOrderService.getAllByCustomerIpAddress(request.getRemoteAddr());
        model.addAttribute("products", products);

        return "Cart";
    }

    @GetMapping("/{productId}")
    public String AddToCart(@PathVariable String productId, Model model, HttpServletRequest request)
    {
        Product product = this.productService.getProductById(Integer.parseInt(productId));
        PendingOrder existingOrder = this.pendingOrderService.getPendingOrderByIpAddress(request.getRemoteAddr());

        if(existingOrder == null)
        {
            if(product != null)
            {
                this.pendingOrderService.addNewPendingOrder(product, request.getRemoteAddr());

                return "redirect:/";
            }
        } else {
            //update existing pendingOrder
            existingOrder.getProducts().add(product);
            product.getPendingOrders().add(existingOrder);
            this.pendingOrderService.addNewProduct(existingOrder);
            return "redirect:/";
        }



        return "redirect:/";
    }
}
