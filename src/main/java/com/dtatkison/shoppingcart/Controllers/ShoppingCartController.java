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
    {   List<PendingOrderItem> items = new ArrayList<>();
        items = this.pendingOrderService.getAllByCustomerIpAddress(request.getRemoteAddr());

        ModelAndView vm = new ModelAndView();
        List<Address> addresses = new ArrayList<>();

        //When the form is submitted, update these based on the data submitted - if only one is submitted, then delete index 1
        addresses.add(new Address()); //shipping address -- index 0
        addresses.add(new Address()); //billing address -- index 1


        Order order = new Order();
        //order.setProducts(items); // add the products that are in the cart to the order
        order.setAddresses(addresses);

        model.addAttribute("states", Constants.states);
        model.addAttribute("order", order);
        model.addAttribute("ipaddress", request.getRemoteAddr());


        model.addAttribute("items", items);

        return "Cart";
    }

    @GetMapping("/{productId}")
    public String AddToCart(@PathVariable String productId, Model model, HttpServletRequest request)
    {
        Product product = this.productService.getProductById(Integer.parseInt(productId));
        PendingOrder existingOrder = this.pendingOrderService.getPendingOrderByIpAddress(request.getRemoteAddr());
        PendingOrderItem item = new PendingOrderItem();

        if(existingOrder == null)
        {
            if(product != null)
            {
                item.setProduct(product);
                product.getPendingOrderItems().add(item);
                item.setQuantity(1);
                this.pendingOrderService.addNewPendingOrder(item, request.getRemoteAddr());

                return "redirect:/";
            }
        } else {
            item.setProduct(product);
            item.setQuantity(1);
            item.setPendingOrder(existingOrder);
            //update existing pendingOrder
            existingOrder.getPendingOrderItems().add(item);
            product.getPendingOrderItems().add(item);
            this.pendingOrderService.addNewProduct(existingOrder, item);
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
