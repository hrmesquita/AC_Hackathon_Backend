package org.greytcoders.rent_a_bs;

import org.greytcoders.rent_a_bs.models.Product;
import org.greytcoders.rent_a_bs.models.User;
import org.greytcoders.rent_a_bs.repositories.ProductsRepo;
import org.greytcoders.rent_a_bs.repositories.UserRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RentABsApplication {

	public static void main(String[] args) {
		SpringApplication.run(RentABsApplication.class, args);
	}

	@Autowired
	private ProductsRepo productsRepo;

	@Autowired
	private UserRepository userRepository;

	// Testing products database
	@Bean
	InitializingBean sendDatabase(){
		return () -> {
			productsRepo.save(new Product("Daniel", "Fernandes", "daniel_product", "Codfish Tester", "Cod Lover"));
			productsRepo.save(new Product("Hugo", "Mesquita", "hugo_product", "Bush trimmer", "Gardener"));
			productsRepo.save(new Product("Caíque", "Xavier", "caique_product", "Rack cleaner", "Housekeeper"));
			productsRepo.save(new Product("António", "Cabral", "antonio_product", "Milkman", "Milkman"));
			productsRepo.save(new Product("João", "Carriço", "joao_product", "Spiritual Advisor", "Priest"));
		};
	}

	// Testing users database
	@Bean
	InitializingBean sendUserDB() {
		return () -> {
			userRepository.save(new User("João Messias", "testingg", "test@gmail.com"));
			userRepository.save(new User("Cardador Messias", "newTestt", "anotherTest@hotmail.com"));
		};
	}


}
