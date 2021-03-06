package guru.springframework.msscbrewery.services;

import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import guru.springframework.msscbrewery.mapper.BeerMapper;
import guru.springframework.msscbrewery.model.Beer;
import guru.springframework.msscbrewery.model.BeerDto;
import guru.springframework.msscbrewery.repository.BeerRepository;

@Service
public class BeerServiceImpl implements BeerService {

	@Autowired
	private BeerRepository repository;

	@Override
	public void deleteBeer(UUID id) {
		Assert.notNull(id, "Beer Id shall not be null!");

		repository.deleteById(id);
	}

	@Override
	public BeerDto getBeerById(UUID id) {
		Assert.notNull(id, "Beer Id shall not be null!");

		Beer beer = repository.findById(id).orElseThrow();

		return BeerMapper.INSTANCE.toDto(beer);
	}

	@Override
	public Collection<BeerDto> list() {
		return ((Collection<Beer>) repository.findAll()).stream()
				.map(BeerMapper.INSTANCE::toDto)
				.collect(Collectors.toList());
	}

	@Override
	public UUID saveNewBeer(BeerDto dto) {
		Beer beer = BeerMapper.INSTANCE.toEntity(dto);

		return repository.save(beer).getId();
	}

	@Override
	public void updateExistingBeer(UUID id, BeerDto dto) {
		Assert.notNull(id, "Beer Id shall not be null!");

		Beer beer = repository.findById(id).orElseThrow();

		BeerMapper.INSTANCE.copyProperties(dto, beer);

		repository.save(beer);
	}

}
