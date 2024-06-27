package tp.msk.mskrestdocsexample.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import tp.msk.mskrestdocsexample.domain.Beer;
import tp.msk.mskrestdocsexample.repositories.BeerRepository;

import java.math.BigDecimal;

@Component
public class BeerLoader implements CommandLineRunner {

    private final BeerRepository beerRepository;

    public BeerLoader(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        loadBeerObjects();
    }

    private void loadBeerObjects() {
        if (beerRepository.count() == 0){
            beerRepository.save(Beer.builder()
                            .beerName("Mango Bobs")
                            .beerStyle("IPA")
                            .quantityToBrew(200)
                            .minOnHand(12)
                            .upc(12312345500001L)
                            .price(new BigDecimal("12.99"))
                            .build());

            beerRepository.save(Beer.builder()
                    .beerName("Galaxy Cat")
                    .beerStyle("PALE_ALE")
                    .quantityToBrew(250)
                    .minOnHand(30)
                    .upc(12312345500002L)
                    .price(new BigDecimal("10.99"))
                    .build());
        }
    }
}
