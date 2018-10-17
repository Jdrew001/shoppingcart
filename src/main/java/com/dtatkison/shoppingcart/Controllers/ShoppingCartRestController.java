package com.dtatkison.shoppingcart.Controllers;

import com.dtatkison.shoppingcart.Models.PendingOrderItem;
import com.dtatkison.shoppingcart.Services.PendingOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;

@RestController
@RequestMapping(value = "/api/shopping")
@CrossOrigin
public class ShoppingCartRestController {

    @Autowired
    private PendingOrderService pendingOrderService;

    @PostMapping("/update/{itemId}/{quantity}")
    public ResponseEntity<?> updateQuantity(@PathVariable int itemId, @PathVariable int quantity)
    {
        if(this.pendingOrderService.updateOrderItem(itemId, quantity))
        {
            return new ResponseEntity<>("Successful update", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Error updating quantity", HttpStatus.BAD_REQUEST);
        }


    }
}
