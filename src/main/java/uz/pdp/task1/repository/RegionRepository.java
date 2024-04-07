package uz.pdp.task1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.task1.entity.Region;

public interface RegionRepository extends JpaRepository<Region,Integer> {

       boolean existsByName (String name);

}
