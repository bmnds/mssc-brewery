package guru.springframework.msscbrewery.controller;

import java.net.URI;
import java.util.Collection;
import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import guru.springframework.msscbrewery.model.BeerDto;
import guru.springframework.msscbrewery.services.BeerService;

@Validated
@RequestMapping("/api/v2/beers")
@RestController
public class BeerControllerV2 {

	private final BeerService beerService;

	public BeerControllerV2(BeerService beerService) {
		this.beerService = beerService;
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public Collection<BeerDto> list() {
		return beerService.list();
	}

	@GetMapping({"/{beerId}"})
	@ResponseStatus(HttpStatus.OK)
	public BeerDto getBeer(
			@NotNull @PathVariable("beerId") UUID beerId) {
		return beerService.getBeerById(beerId);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> saveBeer(@Valid @RequestBody BeerDto beer) {
		UUID id = beerService.saveNewBeer(beer);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").build(id);

		return ResponseEntity.created(location).build();
	}

	@PutMapping("/{beerId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateBeer(@PathVariable("beerId") UUID beerId,
			@Valid @RequestBody BeerDto beer) {
		beerService.updateExistingBeer(beerId, beer);
	}

	@DeleteMapping("/{beerId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteBeer(@PathVariable("beerId") UUID beerId) {
		beerService.deleteBeer(beerId);
	}

}
