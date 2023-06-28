package hackathon.nttdata.coderpath.alumnowebflux.controller.codecontroller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import hackathon.nttdata.coderpath.alumnowebflux.documents.codewebflux.Product;
import hackathon.nttdata.coderpath.alumnowebflux.repository.code.ProductRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
@RequiredArgsConstructor
//*@CrossOrigin({"*"})
//*@CrossOrigin({"http://localhost:4200"})
public class ProductController {

	private final ProductRepository productRepository;

	@GetMapping("/luigi/all")
	public Flux<Product> getAll() {
		return productRepository.findAll();
	}

	@GetMapping("/{id}")
	public Mono<Product> getById(int id) {
		return productRepository.findById(id);
	}

	@PostMapping("/luigis")
	public Mono<Product> save(Product product) {
		return productRepository.save(product);
	}

	@PutMapping("/{id}")
	public Mono<Product> update(int id, Product product) {
		return productRepository.save(new Product(id, product.getName(), product.getPrice()));
	}

	@DeleteMapping("/{id}")
	public Mono<Void> delete(int id) {
		return productRepository.deleteById(id);
	}

}
