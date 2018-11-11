package com.dtatkison.shoppingcart.Controllers;

import com.dtatkison.shoppingcart.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api/dashboard")
@CrossOrigin
public class DashboardRestController
{
    @Autowired
    private ProductService productService;

    public DashboardRestController() {

    }

    @PostMapping("/delete/{itemId}")
    public ResponseEntity<?> deleteProductById(@PathVariable int itemId)
    {
        if(this.productService.deleteProductId(itemId)) {
            return new ResponseEntity<>("Successful Delete", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Successful update", HttpStatus.BAD_REQUEST);
        }
    }
}
