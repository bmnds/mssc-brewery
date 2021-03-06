package guru.springframework.msscbrewery.model;

import java.math.BigDecimal;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by jt on 2019-04-20.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeerDto {

	private UUID id;
	@NotBlank
	private String beerName;
	@NotBlank
	private String beerStyle;
	@Positive
	private Long upc;
	@Positive
	private BigDecimal price;
	@NotNull
	private Integer minOnHand;
	@NotNull
	private Integer quantityToBrew;

}
