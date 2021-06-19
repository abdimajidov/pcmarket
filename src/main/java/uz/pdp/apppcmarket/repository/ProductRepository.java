package uz.pdp.apppcmarket.repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.apppcmarket.entity.Character;
import uz.pdp.apppcmarket.entity.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer> {
    Page<Product> findProductsByNameAndModelAndPriceAndCharacter
            (String name, String model, String price, Character character, Pageable pageable);

    @Query(value = "select * from product\n" +
            "join character c on c.id = product.character_id\n" +
            "join property p on p.id = c.property_id\n" +
            "where property_id in (?);",nativeQuery = true)
    public Page<Product> getProducts( List<Integer> propertyList);


}
