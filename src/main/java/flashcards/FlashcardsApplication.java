package flashcards;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@ComponentScan(basePackages = {
		"flashcards.api",
		"flashcards.repository",
		"flashcards.controller",
		"flashcards.service",
		"flashcards.mapper"
})
@Import({MvcConfig.class, SecurityConfig.class})
public class FlashcardsApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlashcardsApplication.class, args);
	}
}
