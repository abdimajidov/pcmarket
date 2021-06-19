package uz.pdp.apppcmarket.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.apppcmarket.entity.Character;
import uz.pdp.apppcmarket.entity.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query(value = "select *\n" +
            "from product\n" +
            "         join character c on c.id = product.character_id\n" +
            "where product.name = :name\n" +
            "  and character_id = :chId\n" +
            "  and product.price = :price\n" +
            "  and product.model = :model;", nativeQuery = true)
    public Page<Product> getProductsbyNameModelCharacterPrice(String name, String model, String price, Integer chId);

    @Query(value = "select *from product\n" +
            "         join character c on c.id = product.character_id\n" +
            "         join property p on p.id = c.property_id\n" +
            "where p.id in(?)\n", nativeQuery = true)
    public Page<Product> getProductsbyPropertyList(List<Integer> propertyList);
}
