package tp.msk.mskrestdocsexample.web.mappers;

import org.mapstruct.Mapper;
import tp.msk.mskrestdocsexample.domain.Beer;
import tp.msk.mskrestdocsexample.web.model.BeerDTO;

@Mapper(uses = {DateMapper.class})
public interface BeerMapper {
    Beer beerDtoToBeer(BeerDTO beerDTO);
    BeerDTO beerToBeerDto(Beer beer);
}
