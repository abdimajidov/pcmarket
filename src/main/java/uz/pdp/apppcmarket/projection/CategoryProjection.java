package uz.pdp.apppcmarket.projection;

import org.springframework.data.rest.core.config.Projection;
import uz.pdp.apppcmarket.entity.Category;

@Projection(types = Category.class)
public interface CategoryProjection {
    Integer getId();
    String getName();
    Category getParentCategory();
    boolean isActive();
}
