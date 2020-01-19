package guru.springframework.msscbrewery.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import guru.springframework.msscbrewery.model.Beer;
import guru.springframework.msscbrewery.model.BeerStyleEnum;

@Repository
public interface BeerRepository extends PagingAndSortingRepository<Beer, UUID> {

	@Query("FROM Beer b WHERE "
			+ "(:name IS NULL OR LOWER(b.beerName) LIKE LOWER(CONCAT('%',:name,'%'))) "
			+ "AND (:style IS NULL OR b.beerStyle = :style)")
	Page<Beer> findAll(@Param("name") String name,
			@Param("style") BeerStyleEnum style, Pageable pageable);

}
