package guru.springframework.msscbrewery.model;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

/**
 * Created by jt on 2019-05-12.
 */
public class BeerPagedList extends PageImpl<BeerDto> implements Serializable {

	static final long serialVersionUID = 1114715135625836949L;

	// @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
	// public BeerPagedList(@JsonProperty("content") List<BeerDto> content,
	// @JsonProperty("number") int number,
	// @JsonProperty("size") int size,
	// @JsonProperty("totalElements") Long totalElements,
	// @JsonProperty("pageable") JsonNode pageable,
	// @JsonProperty("last") boolean last,
	// @JsonProperty("totalPages") int totalPages,
	// @JsonProperty("sort") JsonNode sort,
	// @JsonProperty("first") boolean first,
	// @JsonProperty("numberOfElements") int numberOfElements) {
	//
	// super(content, PageRequest.of(number, size), totalElements);
	// }

	public BeerPagedList(List<BeerDto> content, Pageable pageable, long total) {
		super(content, pageable, total);
	}

	public BeerPagedList(List<BeerDto> content) {
		super(content);
	}
}
