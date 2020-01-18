package guru.springframework.msscbrewery.bootstrap;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import guru.springframework.msscbrewery.model.Beer;
import guru.springframework.msscbrewery.model.BeerStyleEnum;
import guru.springframework.msscbrewery.repository.BeerRepository;
import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class BeerLoader implements CommandLineRunner {

	@Autowired
	private BeerRepository beerRepository;

	@Override
	public void run(String... args) throws Exception {
		this.loadBeerObjects();
	}

	private void loadBeerObjects() {
		if (beerRepository.count() == 0) {
			beerRepository.save(Beer.builder()
					.beerName("Jimbo's Ale")
					.beerStyle(BeerStyleEnum.PIELSEN)
					.quantityToBrew(200)
					.minOnHand(12)
					.upc(10101L)
					.price(new BigDecimal(12.95))
					.build());

			beerRepository.save(Beer.builder()
					.beerName("Igarapé Amazônico")
					.beerStyle(BeerStyleEnum.PURO_MALTE)
					.quantityToBrew(100)
					.minOnHand(6)
					.upc(20202L)
					.price(new BigDecimal(6.75))
					.build());

			beerRepository.save(Beer.builder()
					.beerName("Jacaré do Lago")
					.beerStyle(BeerStyleEnum.PIELSEN)
					.quantityToBrew(50)
					.minOnHand(6)
					.upc(30303L)
					.price(new BigDecimal(40.00))
					.build());

			beerRepository.save(Beer.builder()
					.beerName("Caxiri")
					.beerStyle(BeerStyleEnum.PURO_MALTE)
					.quantityToBrew(10)
					.minOnHand(1)
					.upc(40404L)
					.price(new BigDecimal(10.00))
					.build());

			beerRepository.save(Beer.builder()
					.beerName("Caxiri de batata roxa")
					.beerStyle(BeerStyleEnum.PURO_MALTE)
					.quantityToBrew(10)
					.minOnHand(1)
					.upc(50505L)
					.price(new BigDecimal(12.00))
					.build());

			beerRepository.save(Beer.builder()
					.beerName("Caxiri do Uiramutâ")
					.beerStyle(BeerStyleEnum.PURO_MALTE)
					.quantityToBrew(10)
					.minOnHand(1)
					.upc(60606L)
					.price(new BigDecimal(15.00))
					.build());

			beerRepository.save(Beer.builder()
					.beerName("Cariri Waimiri Atroari")
					.beerStyle(BeerStyleEnum.PURO_MALTE)
					.quantityToBrew(10)
					.minOnHand(1)
					.upc(70707L)
					.price(new BigDecimal(14.00))
					.build());

			log.debug("Loaded {} Beers into Database", 7);
		}
	}

}
