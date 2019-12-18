package guru.springframework.msscbrewery.web.controller;

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

import guru.springframework.msscbrewery.domain.Beer;
import guru.springframework.msscbrewery.services.BeerService;
import guru.springframework.msscbrewery.web.model.BeerDto;

@Validated
@RequestMapping("/api/v2/beers")
@RestController
public class BeerControlleV2 {

	private final BeerService beerService;

	public BeerControlleV2(BeerService beerService) {
		this.beerService = beerService;
	}

	@GetMapping
	public ResponseEntity<Collection<Beer>> list() {
		return ResponseEntity.ok(beerService.list());
	}

	@GetMapping({
			"/{beerId}"
	})
	public ResponseEntity<BeerDto> getBeer(@NotNull @PathVariable("beerId") UUID beerId) {
		BeerDto beer = beerService.getBeerById(beerId);

		return ResponseEntity.ok(beer);
	}

	@PostMapping
	public ResponseEntity<?> saveBeer(@Valid @RequestBody BeerDto beer) {
		BeerDto saved = beerService.saveNewBeer(beer);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").build(saved.getId());

		return ResponseEntity.created(location).build();
	}

	@PutMapping("/{beerId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateBeer(@PathVariable("beerId") UUID beerId, @Valid @RequestBody BeerDto beer) {
		beerService.updateExistingBeer(beerId, beer);
	}

	@DeleteMapping("/{beerId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteBeer(@PathVariable("beerId") UUID beerId) {
		beerService.deleteBeer(beerId);
	}

}