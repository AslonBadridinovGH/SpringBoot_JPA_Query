package uz.pdp.task1.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.task1.entity.Region;
import uz.pdp.task1.repository.RegionRepository;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/region")
public class RegionController {

    @Autowired
    RegionRepository regionRepository;

    @PostMapping
    public String addRegion(@RequestBody Region dto){
        boolean existsByName = regionRepository.existsByName(dto.getName());
        if (existsByName)
         return "such regionName already exist";
         regionRepository.save(dto);
        return "Region added";
    }


    @GetMapping
    public List<Region> getRegion(){
        return regionRepository.findAll();
    }


    @PutMapping(value = "/{id}")
    public String editRegion(@PathVariable Integer id, @RequestBody Region dto) {

        boolean existsByName = regionRepository.existsByName(dto.getName());
        Optional<Region> optionalRegion = regionRepository.findById(id);

        if (existsByName) {
            return "such region already exist";
        } else {
            if (optionalRegion.isPresent()) {
                Region region = optionalRegion.get();
                region.setName(dto.getName());
                regionRepository.save(region);
                return "Region edited";
            }
            return "Region not found";
        }
    }

    @DeleteMapping(value = "/{id}")
    public String deleteRegion(@PathVariable Integer id){
        regionRepository.deleteById(id);
        return "Region deleted";
    }


}
