package uz.pdp.apppcmarket.repository;

import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import uz.pdp.apppcmarket.entity.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query(value = "select * from product\n" +
            "join character c on product.id = c.product_id" +
            "where product.name = :name\n" +
            "  and c.id = :chId\n" +
            "  and product.price = :price\n" +
            "  and product.model = :model;", nativeQuery = true)
     Page<Product> getProductsbyNameModelCharacterPrice(String name, String model, String price, Integer chId, Pageable pageable);

    @Query(value = "select * from product\n" +
            "join character c on product.id = c.product_id\n" +
            "join property p on c.id = p.character_id\n" +
            "where p.id in (?)", nativeQuery = true)
     Page<Product> getProductsbyPropertyList(List<Integer> propertyList,Pageable pageable);
}
