package guru.springframework.msscbrewery.services;

import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import guru.springframework.msscbrewery.mapper.BeerMapper;
import guru.springframework.msscbrewery.model.Beer;
import guru.springframework.msscbrewery.model.BeerDto;
import guru.springframework.msscbrewery.model.BeerPagedList;
import guru.springframework.msscbrewery.model.BeerStyleEnum;
import guru.springframework.msscbrewery.repository.BeerRepository;

@Service
public class BeerServiceImplV3 implements BeerServiceV3 {

	@Autowired
	private BeerRepository repository;

	@Override
	public void delete(UUID id) {
		Assert.notNull(id, "Beer Id shall not be null!");

		repository.deleteById(id);
	}

	@Override
	public BeerDto getById(UUID id) {
		Assert.notNull(id, "Beer Id shall not be null!");

		Beer beer = repository.findById(id).orElseThrow();

		return BeerMapper.INSTANCE.toDto(beer);
	}

	@Override
	public UUID save(BeerDto dto) {
		Beer beer = BeerMapper.INSTANCE.toEntity(dto);

		return repository.save(beer).getId();
	}

	@Override
	public void update(UUID id, BeerDto dto) {
		Assert.notNull(id, "Beer Id shall not be null!");

		Beer beer = repository.findById(id).orElseThrow();

		BeerMapper.INSTANCE.copyProperties(dto, beer);

		repository.save(beer);
	}

	@Override
	public BeerPagedList list(String beerName, BeerStyleEnum beerStyle,
			PageRequest pageRequest) {
		Page<Beer> page = repository.findAll(beerName, beerStyle, pageRequest);

		BeerPagedList pagedList = new BeerPagedList(
				page.getContent().stream().map(BeerMapper.INSTANCE::toDto)
						.collect(Collectors.toList()),
				pageRequest,
				page.getTotalElements());

		return pagedList;
	}

}
