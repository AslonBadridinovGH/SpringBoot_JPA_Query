package uz.pdp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.pdp.entity.District;

import java.util.List;

@Repository
public interface DistrictRepository extends JpaRepository<District,Integer>{


    boolean existsByNameAndRegionId(String name, Integer region_id);


//    Native query that retrieves all Districts per regionID that belong to the respective region
    @Query(value = "select * from district ds join region rg on rg.id=ds.region_id where rg.id=:regionId", nativeQuery=true)
    List<District> getDistrictsByRegionIdNative(Integer regionId);
}
