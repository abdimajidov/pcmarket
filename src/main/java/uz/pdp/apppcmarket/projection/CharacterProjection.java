package uz.pdp.apppcmarket.projection;

import org.springframework.data.rest.core.config.Projection;
import uz.pdp.apppcmarket.entity.Character;
import uz.pdp.apppcmarket.entity.Product;
import uz.pdp.apppcmarket.entity.Property;

@Projection(types = Character.class)
public interface CharacterProjection {
    Integer getId();
    String getName();
    Product getProduct();
}
