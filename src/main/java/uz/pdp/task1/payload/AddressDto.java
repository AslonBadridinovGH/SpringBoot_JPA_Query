package uz.pdp.task1.payload;

import lombok.Data;
import uz.pdp.task1.entity.District;

import javax.persistence.Column;
import javax.persistence.ManyToOne;

@Data
public class AddressDto {

    private String  houseNumber;

    private String  street;

    private Integer districtId;
}
