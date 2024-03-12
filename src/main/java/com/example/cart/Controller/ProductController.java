package com.example.cart.Controller;

import com.example.cart.dtos.productdto;
import com.example.cart.models.Category;
import com.example.cart.models.product;
import com.example.cart.services.cartproductservices;
import com.example.cart.services.cartproductservices;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    private final cartproductservices cartproductservices;

    public ProductController(cartproductservices cartproductservices) {
        this.cartproductservices = cartproductservices;
    }

    @GetMapping("/hello")
    public static String sayHello(){
        return  "Hello World";
    }


    @GetMapping("/products/{id}")
    public product getSingleProduct(@PathVariable("id") Long id) {
        return cartproductservices.getSingleProduct(id);
    }

    @GetMapping("/products")
    public List<product> getAllProducts(){
        return cartproductservices.getAllProducts();
    }

    @GetMapping("/products/category")
    public List<String> getAllCategories(){
        return cartproductservices.getCategory();
    }

    @DeleteMapping("/products/{id}")
    public void deletebyId(@PathVariable("id") Long id){
        cartproductservices.deleteProduct(id);
    }

    @PutMapping("/update")
        public void updateProduct(@RequestBody product p){
            cartproductservices.updateProduct(p);
        }

        @PostMapping("/products")
        public productdto createProduct(@RequestBody product p){
            return cartproductservices.createProduct(p);
        }

    @GetMapping("/products/category/{categoryN}")
    public List<product> getInCategory(@PathVariable("categoryN") String categoryName) {
        return cartproductservices.getProductByCategory(categoryName);
    }
}