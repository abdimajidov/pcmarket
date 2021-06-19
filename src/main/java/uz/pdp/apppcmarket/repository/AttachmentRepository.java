package uz.pdp.apppcmarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.apppcmarket.entity.Attachment;

public interface AttachmentRepository extends JpaRepository<Attachment,Integer> {
}
