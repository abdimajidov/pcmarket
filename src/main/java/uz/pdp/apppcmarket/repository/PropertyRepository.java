package uz.pdp.apppcmarket.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.apppcmarket.entity.Property;
import uz.pdp.apppcmarket.projection.PropertyProjection;

@RepositoryRestResource(path = "property",excerptProjection = PropertyProjection.class)
public interface PropertyRepository extends JpaRepository<Property,Integer> {
}
