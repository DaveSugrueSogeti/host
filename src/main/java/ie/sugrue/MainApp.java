package ie.sugrue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Type;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import ie.sugrue.domain.Category;
import ie.sugrue.domain.Movie;
import ie.sugrue.domain.ResponseWrapper;
import ie.sugrue.domain.Status;
import ie.sugrue.domain.Tester1;
import ie.sugrue.domain.User;
import ie.sugrue.gateway.MoviesGateway;
import ie.sugrue.repository.MySQLUserRepositoryImpl;
import ie.sugrue.utils.GatewayConnector;
import ie.sugrue.utils.Utils;

@EnableAutoConfiguration
@Configuration
@ComponentScan
public class MainApp extends SpringBootServletInitializer implements CommandLineRunner {

	// Only needed for deploying war
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(MainApp.class);
	}
	
	private final Logger	log	= LoggerFactory.getLogger(this.getClass());

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(MainApp.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
	}

	@Override
	public void run(String... strings) throws Exception {

		System.out.println("------Starting Up--------");
		
//		MoviesGateway moviesGateway = new MoviesGateway();
//		
//		List<Movie> movies = moviesGateway.populateMovies();
//		
//		if ( !movies.isEmpty() ){
//			moviesGateway.displayMovies(movies);
//		}
	}
}