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
        import java.util.Date;
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
        List<PendingOrderItem> items = new ArrayList<>();
        items = this.pendingOrderService.getAllByCustomerIpAddress(request.getRemoteAddr());

        ModelAndView vm = new ModelAndView();
        List<Address> addresses = new ArrayList<>();

        //When the form is submitted, update these based on the data submitted - if only one is submitted, then delete index 1
        addresses.add(new Address()); //shipping address -- index 0
        addresses.add(new Address()); //billing address -- index 1


        Order order = new Order();
        order.setAddresses(addresses);

        model.addAttribute("states", Constants.states);
        model.addAttribute("order", order);
        model.addAttribute("ipaddress", request.getRemoteAddr());


        model.addAttribute("items", items);

        //calculate the total price
        model.addAttribute("totalPrice", calculatePrice(items));

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
    
    @PostMapping("/submitOrder")
    public String SubmitOrder(@ModelAttribute("order") Order order, BindingResult result, HttpServletRequest request)
    {
        List<PendingOrderItem> items = new ArrayList<>();
        List<OrderItem> orderItems = new ArrayList<>();
        items = this.pendingOrderService.getAllByCustomerIpAddress(request.getRemoteAddr());
        OrderItem orderItem = new OrderItem();

        for (PendingOrderItem pendingOrderItem: items) {
            orderItem.setProduct(pendingOrderItem.getProduct());
            orderItem.setQuantity(pendingOrderItem.getQuantity());
            orderItem.setOrder(order);
            orderItems.add(orderItem);
        }

        order.setOrderDate(new Date());
        order.setOrderStatus(Constants.PENDING_STATUS);
        order.setOrderItems(orderItems);

        //add a new order PARAMS - order
        this.orderService.addNewOrder(order);


        return "redirect:/"; //TODO: Eventually add a page that says they successfully ordered with an order id
    }

    private double calculatePrice(List<PendingOrderItem> items)
    {
        double total = 0.0;

        for (PendingOrderItem item: items) {
            total += (item.getProduct().getProductPrice() * item.getQuantity());
        }

        return total;
    }
}
