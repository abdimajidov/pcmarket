package uz.pdp.apppcmarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.apppcmarket.entity.Orders;
import uz.pdp.apppcmarket.projection.OrdersProjection;

@RepositoryRestResource(path = "orders",excerptProjection = OrdersProjection.class)
public interface OrdersRepository extends JpaRepository<Orders,Integer> {
}
