package uz.pdp.apppcmarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.apppcmarket.entity.BasketProduct;
import uz.pdp.apppcmarket.projection.BasketProductProjection;

@RepositoryRestResource(path = "basketProduct",excerptProjection = BasketProductProjection.class)
public interface BasketProductRepository extends JpaRepository<BasketProduct,Integer> {
}
