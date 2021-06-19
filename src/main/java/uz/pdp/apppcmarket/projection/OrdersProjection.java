package uz.pdp.apppcmarket.projection;

import org.springframework.data.rest.core.config.Projection;
import uz.pdp.apppcmarket.entity.Basket;
import uz.pdp.apppcmarket.entity.Customer;
import uz.pdp.apppcmarket.entity.Orders;

@Projection(types = Orders.class)
public interface OrdersProjection {
    Integer getId();
    Customer getCustomer();
    Basket getBasket();
}
