package uz.pdp.task1.repository;

import com.oracle.xmlns.internal.webservices.jaxws_databinding.JavaParam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.pdp.task1.entity.Address;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address,Integer> {


//    Native query that retrieves all addresses per districtID, that belong to the respective district
    @Query(value = "select * " +
            "from address ad " +
            "join district dr on dr.id=ad.district_id " +
            "where dr.id=:districtId", nativeQuery=true)

    List<Address> getAddressesByDistrictIdNative(Integer districtId);
}
