package uz.pdp.apppcmarket.projection;

import org.springframework.data.rest.core.config.Projection;
import uz.pdp.apppcmarket.entity.Basket;

@Projection(types = Basket.class)
public interface BasketProjection {
    Integer getId();
}
