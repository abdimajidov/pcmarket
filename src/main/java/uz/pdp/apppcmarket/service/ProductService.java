package uz.pdp.apppcmarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import uz.pdp.apppcmarket.entity.Attachment;
import uz.pdp.apppcmarket.entity.Category;
import uz.pdp.apppcmarket.entity.Character;
import uz.pdp.apppcmarket.entity.Product;
import uz.pdp.apppcmarket.payLoad.ProductDto;
import uz.pdp.apppcmarket.payLoad.Result;
import uz.pdp.apppcmarket.repository.AttachmentRepository;
import uz.pdp.apppcmarket.repository.CategoryRepository;
import uz.pdp.apppcmarket.repository.CharacterRepository;
import uz.pdp.apppcmarket.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    CharacterRepository characterRepository;

    public Page<Product> getProducts(Integer page, Integer size) {
        Pageable pageable= PageRequest.of(page,size);
        return productRepository.findAll(pageable);
    }


    public Product getProduct(Integer id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        return optionalProduct.orElse(null);
    }

    public Result addProduct(ProductDto productDto) {
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDto.getAttachmentId());
        if(!optionalAttachment.isPresent()) return new Result("Attachment not found",false);
        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if(!optionalCategory.isPresent()) return new Result("Category not found",false);



        Product product=new Product();
        product.setActive(productDto.isActive());
        product.setAttachment(optionalAttachment.get());
        product.setCategory(optionalCategory.get());

        product.setPrice(productDto.getPrice());
        product.setModel(productDto.getModel());
        product.setName(productDto.getName());

        productRepository.save(product);
        return new Result("Successfully added",true);
    }

    public Result editProduct(Integer id, ProductDto productDto) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(!optionalProduct.isPresent()) return new Result("Prouct not found",false);
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDto.getAttachmentId());
        if(!optionalAttachment.isPresent()) return new Result("Attachment not found",false);
        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if(!optionalCategory.isPresent()) return new Result("Category not found",false);



        Product product=optionalProduct.get();
        product.setActive(productDto.isActive());
        product.setAttachment(optionalAttachment.get());
        product.setCategory(optionalCategory.get());
        product.setPrice(productDto.getPrice());
        product.setModel(productDto.getModel());
        product.setName(productDto.getName());

        productRepository.save(product);
        return new Result("Successfully edited",true);

    }

    public Result deleteProduct(Integer id){
       try{
           productRepository.deleteById(id);
           return new Result("Product deleted",true);
       }catch (Exception e){
           return new Result("Not deleted",false);
       }
    }

    public Page<Product> getProductsFiltr(String name, String model, String price, Integer chId, Integer page, Integer size) {
        Optional<Character> optionalCharacter = characterRepository.findById(chId);
        if(!optionalCharacter.isPresent()) return null;
        Pageable pageable=PageRequest.of(page,size);
        return productRepository.findProductsByNameAndModelAndPriceAndCharacter
                (name,model,price, optionalCharacter.get(), pageable);
    }

    public Page<Product> getProductsFiltr2(List<Integer> propertyList) {
        if(propertyList==null) return null;
        return productRepository.getProducts(propertyList);
    }
}
