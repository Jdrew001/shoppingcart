package com.dtatkison.shoppingcart.Controllers;

import com.dtatkison.shoppingcart.Models.PendingOrder;
import com.dtatkison.shoppingcart.Models.Product;
import com.dtatkison.shoppingcart.Services.PendingOrderService;
import com.dtatkison.shoppingcart.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;

@RestController
public class ShoppingCartRestController {

    @Autowired
    private PendingOrderService pendingOrderService;

    @Autowired
    private ProductService productService;

    @GetMapping("/dashboard/test")
    public String test()
    {
        return "test success";
    }

    @PostMapping("/remove/{id}")
    public ResponseEntity<Object> removeProduct(@PathVariable("id") String id, HttpServletRequest request)
    {
        PendingOrder pendingOrder = this.pendingOrderService.getPendingOrderByIpAddress(request.getRemoteAddr());
        Product product = this.productService.getProductById(Integer.parseInt(id));

        if(pendingOrder != null)
        {
            pendingOrder.removeProduct(product);
            product.getPendingOrders().remove(pendingOrder);
            this.pendingOrderService.removeProductFromCart(pendingOrder);

            //check to see how many products are in the cart -- delete if just one left
            if(pendingOrder.getProducts().size() == 0)
            {

                this.pendingOrderService.deletePendingOrder(pendingOrder.getPendingOrderId());
            }


            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
