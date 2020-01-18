package guru.springframework.msscbrewery.services;

import java.util.UUID;

import org.springframework.data.domain.PageRequest;

import guru.springframework.msscbrewery.model.BeerDto;
import guru.springframework.msscbrewery.model.BeerPagedList;
import guru.springframework.msscbrewery.model.BeerStyleEnum;

public interface BeerServiceV3 {

	BeerDto getById(UUID beerId);

	UUID save(BeerDto beer);

	void update(UUID beerId, BeerDto beer);

	void delete(UUID beerId);

	BeerPagedList list(String beerName, BeerStyleEnum beerStyle,
			PageRequest pageRequest);

}
