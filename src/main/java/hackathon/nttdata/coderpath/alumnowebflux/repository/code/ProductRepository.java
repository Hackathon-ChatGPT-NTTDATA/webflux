package hackathon.nttdata.coderpath.alumnowebflux.repository.code;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import hackathon.nttdata.coderpath.alumnowebflux.documents.codewebflux.Product;
import reactor.core.publisher.Mono;

@Repository
public interface ProductRepository extends ReactiveMongoRepository<Product, Integer> {
	Mono<Product> findByName(String name);

	@Query("SELECT * FROM product WHERE id <> :id AND name = :name")
	Mono<Product> repeatedName(int id, String name);
}
 