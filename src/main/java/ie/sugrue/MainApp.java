package ie.sugrue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import ie.sugrue.domain.Movie;
import ie.sugrue.domain.Tester1;
import ie.sugrue.domain.User;
import ie.sugrue.repository.MySQLUserRepositoryImpl;
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

		callGateway();
		
		List<Movie> movies= populateMovies();
		
		displayMovies(movies);
		
		// ApplicationContext context = new ClassPathXmlApplicationContext("Context.xml");

		// MySQLUserRepositoryImpl mySQLUserRepositoryImpl = (MySQLUserRepositoryImpl) context.getBean("MySQLUserRepositoryImpl");

		// popUsers(mySQLUserRepositoryImpl);

		// runAbstracts();
	}

	
	
	public List<Movie> populateMovies(){
		
		List<Movie> movies = new ArrayList<Movie>();
		
		return movies;
	}
	
	public void callGateway(){
		
		String urlString = "http://localhost:8080/rest/default/direct/v1/TestResource";
		HttpURLConnection conn = null;
		BufferedReader br = null;
		
		try{
			URL url = new URL(urlString);
			log.info("URL {} created", url);
			
			conn = (HttpURLConnection) url.openConnection();
			
			conn.setRequestMethod("GET");
			log.info("Success - Set Request Method");
			conn.setRequestProperty("Accept", "application/json");
			log.info("Success - Set Accept property");
			conn.setRequestProperty("Authorization", "Basic YWRtaW46UGFzc3dvcmQx");
			log.info("Success - Set Authorization method");

			log.info("Connection opened. Response Code = {} ", conn.getResponseCode());
			
			if (conn.getResponseCode() == 200) {
				br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			} else {
				br = new BufferedReader(new InputStreamReader((conn.getErrorStream())));
			}
			
			String output;
			
			System.out.println("Output from server:\n");
			
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}
			
		} catch(MalformedURLException mue){
			log.error("MalformedURLException encountered trying to specify {} as a URL", urlString, mue);
		} catch (IOException ioe) {
			log.error("Major IO Exception !!!", ioe);
		} catch (Exception e) {
			log.error("Generic Error Encounterd", e);
		} finally {
			
			if (Utils.isNotNull(br)){
				try{
					br.close();
				} catch(IOException ioe) {
					log.error("Error closing buffered reader", ioe);
				}
			}
			if (Utils.isNotNull(conn)){
				log.info("Disconnecting");
				conn.disconnect();
				log.info("Disconnected");
			}
			
		}
	}

	public void displayMovies(List<Movie> movies){
		
		for( Movie movie : movies){
			System.out.println(movie.getName());
		}
	}
}