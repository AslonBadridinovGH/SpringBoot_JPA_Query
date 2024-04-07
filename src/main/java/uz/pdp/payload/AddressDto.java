package uz.pdp.payload;

import lombok.Data;

@Data
public class AddressDto {

    private String  houseNumber;

    private String  street;

    private Integer districtId;
}
