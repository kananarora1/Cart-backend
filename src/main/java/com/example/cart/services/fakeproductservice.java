package com.example.cart.services;

import com.example.cart.dtos.productdto;
import com.example.cart.models.product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.example.cart.models.Category;

import java.util.ArrayList;
import java.util.List;

@Service
public class fakeproductservice implements cartproductservices{
    private RestTemplate restTemplate = new RestTemplate();
    @Override
    public List<product> getAllProducts() {
        productdto[] productdtos =  restTemplate.getForObject(
                "https://fakestoreapi.com/products",
                productdto[].class
        );
        List<product> prods = new ArrayList<>();
        for(productdto ele: productdtos){
            product product = new product();
            product.setId(ele.getId());
            product.setTitle(ele.getTitle());
            product.setPrice(ele.getPrice());
            product.setImageUrl(ele.getImage());
            product.setDescription(ele.getDescription());
            Category category = new Category();
            category.setName(ele.getCategory());
            product.setCategory(category);
            prods.add(product);
        }
        return prods;
    }

    public List<String> getCategory(){
        String[] catagories =  restTemplate.getForObject(
                "https://fakestoreapi.com/products/categories",
                 String[].class
        );
        List<String> prods = new ArrayList<>();
        for(String category : catagories) {
            prods.add(category);
        }
        return prods;
    }

        public void deleteProduct(Long id){
            restTemplate.delete("https://fakestoreapi.com/products/" + id);
        }

        public void updateProduct(product product){
            productdto storeProduct = new productdto();
            storeProduct.setId(product.getId());
            storeProduct.setTitle(product.getTitle());
            storeProduct.setPrice(product.getPrice());
            storeProduct.setImage(product.getImageUrl());
            storeProduct.setDescription(product.getDescription());
            storeProduct.setCategory(product.getCategory().getName());

            restTemplate.put(
                    "https://fakestoreapi.com/products/" + product.getId(),
                    storeProduct
            );
            System.out.println("Product updated successfully!!!");
        }

    @Override
    public product getSingleProduct(Long id) {

        productdto productdto =  restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + id,
                productdto.class
        );

        product product = new product();
        product.setId(productdto.getId());
        product.setTitle(productdto.getTitle());
        product.setPrice(productdto.getPrice());
        product.setImageUrl(productdto.getImage());
        product.setDescription(productdto.getDescription());
        product.setCategory(new Category());
        product.getCategory().setName(productdto.getCategory());

        System.out.println("product fetched");
        return product;
    }
    @Override
    public productdto createProduct(product product) {
        productdto prod = new productdto();
        prod.setId(product.getId());
        prod.setTitle(product.getTitle());
        prod.setPrice(product.getPrice());
        prod.setImage(product.getImageUrl());
        prod.setDescription(product.getDescription());
        prod.setCategory(product.getCategory().getName());

        restTemplate.postForObject(
                "https://fakestoreapi.com/products",
                prod,
                productdto.class
        );
        return prod;
    }

    public List<product> getProductByCategory(String CategoryN){
        productdto[] prods = restTemplate.getForObject(
                "https://fakestoreapi.com/products/category/" + CategoryN,
                productdto[].class
        );
        List<product> products = new ArrayList<>();
        for(productdto ele : prods) {
            product product = new product();
            product.setId(ele.getId());
            product.setTitle(ele.getTitle());
            product.setImageUrl(ele.getImage());
            product.setPrice(ele.getPrice());
            product.setDescription(ele.getDescription());
            Category category = new Category();
            category.setName(ele.getCategory());
            product.setCategory(category);
            products.add(product);
        }
        System.out.println("All products in " + CategoryN + " category fetched successfully!!!");
        return products;
    }
}