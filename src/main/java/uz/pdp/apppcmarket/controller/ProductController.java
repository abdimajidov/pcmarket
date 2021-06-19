package uz.pdp.apppcmarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.apppcmarket.entity.Product;
import uz.pdp.apppcmarket.payLoad.ProductDto;
import uz.pdp.apppcmarket.payLoad.Result;
import uz.pdp.apppcmarket.service.ProductService;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping
    public ResponseEntity<Page<Product>> getProducts(@RequestParam Integer page,Integer size){
        Page<Product> page1=productService.getProducts(page,size);
        return ResponseEntity.ok(page1);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Integer id){
        Product product=productService.getProduct(id);
        return ResponseEntity.status(product!=null?OK:CONFLICT).body(product);
    }

    @GetMapping("/filtr")
    public ResponseEntity<Page<Product>> getProductsFiltr(@RequestParam String name,
                                                     @RequestParam String model,
                                                     @RequestParam String price,
                                                     @RequestParam Integer chId){
        Page<Product> page1=productService.getProductsFiltr(name,model,price,chId);
        return ResponseEntity.status(page1!=null?202:409).body(page1);
    }

    @GetMapping("/filtr2")
    public ResponseEntity<Page<Product>> getProductsFiltr2(@RequestParam List<Integer> propertyList){
        Page<Product> page=productService.getProductsFiltr2(propertyList);
        return ResponseEntity.status(page!=null?202:409).body(page);
    }

    @PostMapping
    public ResponseEntity<Result> addProduct(@RequestBody ProductDto productDto){
            Result result=productService.addProduct(productDto);
            return ResponseEntity.status(result.isSucces()? CREATED:CONFLICT).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Result> editProduct(@PathVariable Integer id,@RequestBody ProductDto productDto){
        Result result=productService.editProduct(id,productDto);
        return ResponseEntity.status(result.isSucces()?202:409).body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Result> deleteProduct(@PathVariable Integer id){
        Result result = productService.deleteProduct(id);
        return ResponseEntity.status(result.isSucces()?202:409).body(result);
    }
}
