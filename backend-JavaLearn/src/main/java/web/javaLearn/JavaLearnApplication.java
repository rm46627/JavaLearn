package web.javaLearn;

import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;


@SpringBootApplication
@EnableAsync
@AllArgsConstructor
public class JavaLearnApplication {
	public static void main(String[] args) {SpringApplication.run(JavaLearnApplication.class, args);}
}