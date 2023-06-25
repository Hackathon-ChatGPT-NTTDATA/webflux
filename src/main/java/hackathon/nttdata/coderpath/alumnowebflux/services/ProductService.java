package hackathon.nttdata.coderpath.alumnowebflux.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


import hackathon.nttdata.coderpath.alumnowebflux.codeexception.CustomException;
import hackathon.nttdata.coderpath.alumnowebflux.documents.codewebflux.Product;
import hackathon.nttdata.coderpath.alumnowebflux.documents.codewebflux.dto.ProductDto;
import hackathon.nttdata.coderpath.alumnowebflux.repository.code.ProductRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
	
@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {
	   private final static String NF_MESSAGE = "product not found";
	    private final static String NAME_MESSAGE = "product name already in use";

	    private final ProductRepository productRepository;

	    public Flux<Product> getAll() {
	        return productRepository.findAll();
	    }
	    
	    public Mono<Product> getById(int id) {
	        return productRepository.findById(id)
	                .switchIfEmpty(Mono.error(new CustomException(HttpStatus.NOT_FOUND, NF_MESSAGE)));
	    }
	    
	    public Mono<Product> save(ProductDto dto) {
	        Mono<Boolean> existsName = productRepository.findByName(dto.getName()).hasElement();
	        return existsName.flatMap(exists -> exists ? Mono.error(new CustomException(HttpStatus.BAD_REQUEST, NAME_MESSAGE))
	                : productRepository.save(Product.builder().name(dto.getName()).price(dto.getPrice()).build()));
	    }
	    
	    public Mono<Product> update(int id, ProductDto dto) {
	        Mono<Boolean> productId = productRepository.findById(id).hasElement();
	        Mono<Boolean> productRepeatedName = productRepository.repeatedName(id, dto.getName()).hasElement();
	        return productId.flatMap(
	                existsId -> existsId ?
	                        productRepeatedName.flatMap(existsName -> existsName ? Mono.error(new CustomException(HttpStatus.BAD_REQUEST, NAME_MESSAGE))
	                                : productRepository.save(new Product(id, dto.getName(), dto.getPrice())))
	        : Mono.error(new CustomException(HttpStatus.NOT_FOUND, NF_MESSAGE)));
	    }

	    public Mono<Void> delete(int id) {
	        Mono<Boolean> productId = productRepository.findById(id).hasElement();
	        return productId.flatMap(exists -> exists ? productRepository.deleteById(id) : Mono.error(new CustomException(HttpStatus.NOT_FOUND, NF_MESSAGE)));
	    }	
}
