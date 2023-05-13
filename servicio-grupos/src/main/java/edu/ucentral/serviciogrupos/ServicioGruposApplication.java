package edu.ucentral.serviciogrupos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
@EnableEurekaClient
@EntityScan({"edu.ucentral.commonestudiantes.model","edu.ucentral.serviciogrupos.model"})
@SpringBootApplication
public class ServicioGruposApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicioGruposApplication.class, args);
	}

}
