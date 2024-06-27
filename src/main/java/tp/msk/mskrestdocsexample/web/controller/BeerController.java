package tp.msk.mskrestdocsexample.web.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tp.msk.mskrestdocsexample.repositories.BeerRepository;
import tp.msk.mskrestdocsexample.web.mappers.BeerMapper;
import tp.msk.mskrestdocsexample.web.model.BeerDTO;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/beer")
public class BeerController {

    private final BeerMapper beerMapper;
    private final BeerRepository beerRepository;

    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDTO> getBeerById(@PathVariable UUID beerId){
        return new ResponseEntity<>(beerMapper.beerToBeerDto(beerRepository.findById(beerId).get()), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity saveNewBeer(@RequestBody @Validated BeerDTO beerDTO){
        beerRepository.save(beerMapper.beerDtoToBeer(beerDTO));

        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/{beerId}")
    public ResponseEntity updateBeerById(@PathVariable("beerId") UUID beerId,@RequestBody @Validated BeerDTO beerDTO){
        beerRepository.findById(beerId).ifPresent(beer -> {
            beer.setBeerName(beerDTO.getBeerName());
            beer.setBeerStyle(beerDTO.getBeerStyle().name());
            beer.setPrice(beerDTO.getPrice());
            beer.setUpc(beerDTO.getUpc());

            beerRepository.save(beer);
        });

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
