package guru.springframework.msscbrewery.model;

import java.math.BigDecimal;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

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
	@Size(min = 12, max = 12)
	@Pattern(regexp = "\\d*")
	private String upc;
	@Positive
	private BigDecimal price;
	@NotNull
	@PositiveOrZero
	private Integer minOnHand;
	@NotNull
	@PositiveOrZero
	private Integer quantityToBrew;

}
