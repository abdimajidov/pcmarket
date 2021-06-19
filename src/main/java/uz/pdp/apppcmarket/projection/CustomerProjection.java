package uz.pdp.apppcmarket.projection;

import org.springframework.data.rest.core.config.Projection;
import uz.pdp.apppcmarket.entity.Customer;

@Projection(types = Customer.class)
public interface CustomerProjection {
    Integer getId();
    String getAddress();
    String getPhoneNumber();
    String getEmail();
    String getNoteText();
}
