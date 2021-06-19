package uz.pdp.apppcmarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.apppcmarket.entity.Customer;
import uz.pdp.apppcmarket.projection.CustomerProjection;

@RepositoryRestResource(path ="customer",excerptProjection = CustomerProjection.class)
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
}
