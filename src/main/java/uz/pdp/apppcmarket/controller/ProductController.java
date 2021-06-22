package uz.pdp.apppcmarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize(value = "hasAuthority('READ_ALL_PRODUCT')")
    @GetMapping
    public ResponseEntity<Page<Product>> getProducts(@RequestParam Integer page, Integer size) {
        Page<Product> page1 = productService.getProducts(page, size);
        return ResponseEntity.ok(page1);
    }

    @PreAuthorize(value = "hasAuthority('READ_ONE_PRODUCT')")
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Integer id) {
        Product product = productService.getProduct(id);
        return ResponseEntity.status(product != null ? OK : CONFLICT).body(product);
    }

    @PreAuthorize(value = "hasAuthority('READ_BY_FILTR')")
    @GetMapping("/filtr")
    public ResponseEntity<Page<Product>> getProductsFiltr(@RequestParam String name,
                                                          @RequestParam String model,
                                                          @RequestParam String price,
                                                          @RequestParam Integer chId,
                                                          @RequestParam Integer page,
                                                          @RequestParam Integer size) {
        Page<Product> page1 = productService.getProductsFiltr(name, model, price, chId, page, size);
        return ResponseEntity.status(page1 != null ? 202 : 409).body(page1);
    }

    @PreAuthorize(value = "hasAuthority('READ_ALL_PRODUCT')")
    @GetMapping("/filtr2")
    public ResponseEntity<Page<Product>> getProductsFiltr2(@RequestParam List<Integer> propertyList,
                                                           @RequestParam Integer page, @RequestParam Integer size) {
        Page<Product> page1 = productService.getProductsFiltr2(propertyList, page, size);
        return ResponseEntity.status(page1 != null ? 202 : 409).body(page1);
    }

    @PreAuthorize(value = "hasAuthority('ADD_PRODUCT')")
    @PostMapping
    public ResponseEntity<Result> addProduct(@RequestBody ProductDto productDto) {
        Result result = productService.addProduct(productDto);
        return ResponseEntity.status(result.isSucces() ? CREATED : CONFLICT).body(result);
    }

    @PreAuthorize(value = "hasAuthority('EDIT_PRODUCT')")
    @PutMapping("/{id}")
    public ResponseEntity<Result> editProduct(@PathVariable Integer id, @RequestBody ProductDto productDto) {
        Result result = productService.editProduct(id, productDto);
        return ResponseEntity.status(result.isSucces() ? 202 : 409).body(result);
    }

    @PreAuthorize(value = "hasAuthority('DELETE_PRODUCT')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Result> deleteProduct(@PathVariable Integer id) {
        Result result = productService.deleteProduct(id);
        return ResponseEntity.status(result.isSucces() ? 202 : 409).body(result);
    }
}
