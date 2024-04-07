package uz.pdp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.entity.Region;

public interface RegionRepository extends JpaRepository<Region,Integer> {

       boolean existsByName (String name);

}
