package com.sob.identity;

import com.google.common.base.Predicates;
import com.sob.identity.repo.models.Address;
import com.sob.identity.repo.models.Identity;
import com.sob.identity.repo.IdentityRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.HashSet;

@SpringBootApplication
@EnableSwagger2
public class AuthServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner runner(IdentityRepository repo) {
        return e -> {
            Identity idenity = new Identity();
            idenity.setRole("ROLE_USER");
            idenity.setEmail("test@gmail.com");
            idenity.setPassword("test");
            idenity.setCompanyName("abc technologies");
            idenity.setContactPerson("DJ");
            idenity.setPhoneNumber("9040010697");

            Address address = new Address();
            address.setAddress("marathalli");
            address.setCity("Bangalore");
            address.setCountry("india");
            address.setDistrict("Bangalore");
            address.setPin("560037");
            address.setState("Karnataka");
            address.setAddressType("OfficeAddress");


            idenity.setAddress(Arrays.asList(address));
            repo.save(idenity);

        };
    }

    @Bean
    public Docket demoApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .protocols(new HashSet<>(Arrays.asList("http", "https"))).select()
                .apis(RequestHandlerSelectors.any())
                .paths(Predicates.not(PathSelectors.regex("/error.*")))
                .build()
                .apiInfo(ApiInfo.DEFAULT);
    }





}
