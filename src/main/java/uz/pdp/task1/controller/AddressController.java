package uz.pdp.task1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.task1.entity.Address;
import uz.pdp.task1.entity.District;
import uz.pdp.task1.payload.AddressDto;
import uz.pdp.task1.repository.AddressRepository;
import uz.pdp.task1.repository.DistrictRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/address")
public class AddressController {

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    DistrictRepository districtRepository;


    @PostMapping
    public String addAddress(@RequestBody AddressDto dto) {

        Optional<District> optionalDistrict = districtRepository.findById(dto.getDistrictId());
        if (!optionalDistrict.isPresent()) {
            return "such district not found";
        } else {
            Address address = new Address();
            address.setHouseNumber(dto.getHouseNumber());
            address.setStreet(dto.getStreet());
            address.setDistrict(optionalDistrict.get());
            addressRepository.save(address);
            return "Address added";
        }
    }


    @GetMapping
    public List<Address> getAddress(){
        List<Address> addresses = addressRepository.findAll();
        return addresses;
    }


    @GetMapping(value = "/byDistrictId/{districtId}")
    public List<Address> getAddressesByDistrictIdNative(@PathVariable Integer districtId) {
        List<Address> addressesByDistrictIdNative = addressRepository.getAddressesByDistrictIdNative(districtId);
        return addressesByDistrictIdNative;
    }


    @PutMapping(value = "/{id}")
    public String editAddress(@PathVariable Integer id, @RequestBody AddressDto dto){

        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (optionalAddress.isPresent())
        {
            Address currentAddress = optionalAddress.get();
            currentAddress.setHouseNumber(dto.getHouseNumber());
            currentAddress.setStreet(dto.getStreet());

            Optional<District> optionalDistrict = districtRepository.findById(dto.getDistrictId());
            if (!optionalDistrict.isPresent()){
                return "District not found";
            }else {
              currentAddress.setDistrict(optionalDistrict.get());
              addressRepository.save(currentAddress);
              return "Address edited";
            }
        }
        return "Address not found";
    }


    @DeleteMapping(value = "/{id}")
    public String deleteAddress(@PathVariable Integer id){
        addressRepository.deleteById(id);
        return "Address deleted";
    }


}
