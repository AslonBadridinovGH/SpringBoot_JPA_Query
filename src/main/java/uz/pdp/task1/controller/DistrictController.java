package uz.pdp.task1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.task1.entity.Address;
import uz.pdp.task1.entity.District;
import uz.pdp.task1.entity.Region;
import uz.pdp.task1.payload.AddressDto;
import uz.pdp.task1.payload.DistrictDto;
import uz.pdp.task1.repository.DistrictRepository;
import uz.pdp.task1.repository.RegionRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/district")
public class DistrictController {

    @Autowired
    DistrictRepository districtRepository;

    @Autowired
    RegionRepository regionRepository;


    @PostMapping
    public String addDistrict(@RequestBody DistrictDto dto) {

        boolean existNameRegionId = districtRepository.existsByNameAndRegionId(dto.getName(), dto.getRegionId());
        if (existNameRegionId) {
            return "This District with such region already exist";
        } else {
            District district = new District();
            district.setName(dto.getName());

            Optional<Region> repositoryById = regionRepository.findById(dto.getRegionId());
            if (!repositoryById.isPresent())
                return "such Region not found";
            else district.setRegion(repositoryById.get());
            districtRepository.save(district);
            return "District added";
        }
    }

    @GetMapping
    public List<District> getDistrict(){
        List<District> districts = districtRepository.findAll();
        return districts;
    }


    @GetMapping(value = "/byRegionId/{regionId}")
    public List<District>getDistrictsByRegionIdNative(@PathVariable Integer regionId){
        List<District> districtsByRegionIdNative = districtRepository.getDistrictsByRegionIdNative(regionId);
        return districtsByRegionIdNative;
    }


    @PutMapping(value = "/{id}")
    public String editDistrict(@PathVariable Integer id, @RequestBody DistrictDto dto){

        boolean existNameRegionId = districtRepository.existsByNameAndRegionId(dto.getName(), dto.getRegionId());
        if (existNameRegionId)
            return "This District with such region already exist";

        Optional<District> optionalDistrict = districtRepository.findById(id);
        if (optionalDistrict.isPresent())
        {
            District district = optionalDistrict.get();
            district.setName(dto.getName());

            Optional<Region> repositoryById = regionRepository.findById(dto.getRegionId());
            if (!repositoryById.isPresent()){
                return "Region not found";
            }else {
                district.setRegion(repositoryById.get());
                districtRepository.save(district);
                return "District edited";
            }
        }
        return "District not found";
    }


    @DeleteMapping(value = "/{id}")
    public String deleteDistrict(@PathVariable Integer id){
        districtRepository.deleteById(id);
        return "District deleted";
    }


}
