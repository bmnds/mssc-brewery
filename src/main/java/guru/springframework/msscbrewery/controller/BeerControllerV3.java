package guru.springframework.msscbrewery.controller;

import java.net.URI;
import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import guru.springframework.msscbrewery.model.BeerDto;
import guru.springframework.msscbrewery.model.BeerPagedList;
import guru.springframework.msscbrewery.model.BeerStyleEnum;
import guru.springframework.msscbrewery.services.BeerServiceV3;

@Validated
@RequestMapping("/api/v3/beers")
@RestController
public class BeerControllerV3 {

	private final BeerServiceV3 service;

	public BeerControllerV3(BeerServiceV3 beerService) {
		this.service = beerService;
	}

	@GetMapping({"/{beerId}"})
	@ResponseStatus(HttpStatus.OK)
	public BeerDto getBeer(
			@NotNull @PathVariable("beerId") UUID beerId) {
		return service.getById(beerId);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> saveBeer(@Valid @RequestBody BeerDto beer) {
		UUID id = service.save(beer);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").build(id);

		return ResponseEntity.created(location).build();
	}

	@PutMapping("/{beerId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateBeer(@PathVariable("beerId") UUID beerId,
			@Valid @RequestBody BeerDto beer) {
		service.update(beerId, beer);
	}

	@DeleteMapping("/{beerId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteBeer(@PathVariable("beerId") UUID beerId) {
		service.delete(beerId);
	}

	@GetMapping
	public BeerPagedList findPaginated(
			@RequestParam(name = "page", required = false, defaultValue = "0") int page,
			@RequestParam(name = "size", required = false, defaultValue = "${spring.data.web.pageable.default-page-size}") int size,
			@RequestParam(name = "name", required = false) String name,
			@RequestParam(name = "style", required = false) BeerStyleEnum style) {

		return service.list(name, style, PageRequest.of(page, size));
	}

}
