package br.com.quintinno.parametriumapi;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableConfigServer
@RestController
@RequestMapping("/")
public class Application implements CommandLineRunner {

	private final Logger log = LoggerFactory.getLogger(Application.class);

	private final String dataHoraBuild = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyy HH:mm:ss"));

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@GetMapping({"", "/"})
    public String api() {
        return String.format("[ Application: %s | Port: %s | Version: %s | Build: %s ]", 
				"PARAMETRIUM", "8880", "v1.0.0", this.dataHoraBuild);
    }

	@Override
	public void run(String... args) throws Exception {
		log.info(this.api());
	}

}
