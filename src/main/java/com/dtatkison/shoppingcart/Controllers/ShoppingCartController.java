package com.dtatkison.shoppingcart.Controllers;

        import com.dtatkison.shoppingcart.Models.*;
        import com.dtatkison.shoppingcart.Repositories.OrderRepository;
        import com.dtatkison.shoppingcart.Repositories.PendingOrderRepository;
        import com.dtatkison.shoppingcart.Repositories.ProductRepository;
        import com.dtatkison.shoppingcart.Services.OrderService;
        import com.dtatkison.shoppingcart.Services.PendingOrderService;
        import com.dtatkison.shoppingcart.Services.ProductService;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.validation.BindingResult;
        import org.springframework.validation.Errors;
        import org.springframework.web.bind.annotation.*;
        import org.springframework.web.context.request.WebRequest;
        import org.springframework.web.servlet.ModelAndView;

        import javax.servlet.http.HttpServletRequest;
        import javax.validation.Valid;
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
    {   List<Product> products = new ArrayList<>();
        products = this.pendingOrderService.getAllByCustomerIpAddress(request.getRemoteAddr());

        ModelAndView vm = new ModelAndView();
        List<Address> addresses = new ArrayList<>();

        //When the form is submitted, update these based on the data submitted - if only one is submitted, then delete index 1
        addresses.add(new Address()); //shipping address -- index 0
        addresses.add(new Address()); //billing address -- index 1


        Order order = new Order();
        order.setProducts(products); // add the products that are in the cart to the order
        order.setAddresses(addresses);

        model.addAttribute("states", Constants.states);
        model.addAttribute("order", order);
        model.addAttribute("ipaddress", request.getRemoteAddr());


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
    
    @PostMapping("/submit")
    public String SubmitOrder(@ModelAttribute("order") Order order, BindingResult result)
    {
        System.out.println(order.getCardNum());


        return "redirect:/"; //TODO: Eventually add a page that says they successfully ordered with an order id
    }
}
