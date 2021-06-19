package uz.pdp.apppcmarket.projection;

import org.springframework.data.rest.core.config.Projection;
import uz.pdp.apppcmarket.entity.Basket;
import uz.pdp.apppcmarket.entity.BasketProduct;
import uz.pdp.apppcmarket.entity.Product;

import java.util.List;

@Projection(types = BasketProduct.class)
public interface BasketProductProjection {
    Integer getId();
    List<Product> getProduct();
    Integer getCount();
    Double getSumm();
    Basket getBasket();
}
