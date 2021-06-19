package uz.pdp.apppcmarket.projection;

import org.springframework.data.rest.core.config.Projection;
import uz.pdp.apppcmarket.entity.Property;

@Projection(types = Property.class)
public interface PropertyProjection {
    Integer getId();
    String getValue();
    Character getCharacter();
}
