package com;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import csv.MyCsv;
import jerseydemo.Employee;
import promartist.MyUserDto;
//https://howtodoinjava.com/jersey/jersey-rest-security/
@SpringBootApplication
public class ClientRestSpringBootApplication {

	public static void main(String[] args) throws IOException {
        SpringApplication app = new SpringApplication(ClientRestSpringBootApplication.class);
        app.setDefaultProperties(Collections
          .singletonMap("server.port", "8083"));
        app.run(args);
		System.out.println("******************************************************");
		System.out.println("app start");
		getMyUsersDto();
		MyUserDto[] myUsersDto = getMyUserDtoMethod2();
		getMyUserDto();
		PrintWriter printWriter = new PrintWriter ("/home/jeanno/Bureau/file.csv");
		MyCsv.writeCities(printWriter, myUsersDto);
		List<MyUserDto> myUserDtoMist = new ArrayList();
		for(int i=0;i<myUsersDto.length;i++) {
			MyUserDto m = new MyUserDto(myUsersDto[i].getId(), myUsersDto[i].getEmail(), myUsersDto[i].getArtistName(),
					myUsersDto[i].getLastName(), myUsersDto[i].getFirstName());
			myUserDtoMist.add(m);
		}
		
		MyCsv.writeCsv(myUserDtoMist);
//		getWithHeader();
//		testBasicAuthentification();
//		getString();
		System.out.println("app stop");		
		System.out.println("******************************************************");

		
	}
	

	private static void getMyUsersDto()
	{
	    final String uri = "http://localhost:8080/PromArtisteJEEBack-web/rest/TestController/getMyUserDtoList";
	    RestTemplate restTemplate = new RestTemplate();
	     
	    MyUserDto[] myUsersDto =  restTemplate.getForObject(uri, MyUserDto[].class);
	    
	     for(int i=0;i<myUsersDto.length;i++) {
	 	    System.out.println(myUsersDto[i].getEmail());
	     }
	    System.out.println(myUsersDto);
	}
	
	private static MyUserDto[] getMyUserDtoMethod2() {
		System.out.println("**********getMyUserDtoMethod2 ************ START" );
		RestTemplate restTemplate = new RestTemplate();	
		final String uri = "http://localhost:8080/PromArtisteJEEBack-web/rest/TestController/getMyUserDtoList";

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setBasicAuth("howtodoinjava", "password");		
		HttpEntity<String> entity = new HttpEntity<String> ("parameter", headers);
		ResponseEntity<MyUserDto[]>respEntity = restTemplate.exchange(uri, HttpMethod.GET, entity, MyUserDto[].class);
		MyUserDto[] myUsersDto = respEntity.getBody();

	     for(int i=0;i<myUsersDto.length;i++) {
		 	    System.out.println(myUsersDto[i].getEmail());
		     }
		    System.out.println(myUsersDto.toString());
			System.out.println("**********getMyUserDtoMethod2 ************ END" );
			
			return myUsersDto;
	}
	
	private static void getMyUserDto()
	{
	    final String uri = "http://localhost:8080/PromArtisteJEEBack-web/rest/TestController/getMyUserDto/2";
	    RestTemplate restTemplate = new RestTemplate();
	     
	    MyUserDto result = restTemplate.getForObject(uri, MyUserDto.class);
	     
	    System.out.println(result);
	}
	
	//http://localhost:8080/PromArtisteJEEBack-web/rest/TestController/withheaders/ENSMA
	//header doesn't work
	private static void getWithHeader()
	{
	    final String uri = "http://localhost:8080/PromArtisteJEEBack-web/rest/TestController/withheaders/{id}";

	    Map<String, String> params = new HashMap<String, String>();
	    params.put("id", "John");
	    params.put("name", "c'est moi le roi");
	    RestTemplate restTemplate = new RestTemplate();
	    String result = restTemplate.getForObject(uri, String.class, params);
	     
	    System.out.println(result);
	}

	private static void testBasicAuthentification() {
		RestTemplate restTemplate = new RestTemplate();
		
		final String uri = "http://localhost:8080/JerseyDemos/rest/employees/getEmployee";

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setBasicAuth("howtodoinjava", "password");		
		HttpEntity<String> entity = new HttpEntity<String> ("parameter", headers);
		ResponseEntity<Employee >respEntity = restTemplate.exchange(uri, HttpMethod.GET, entity, Employee.class);
		Employee result = respEntity.getBody();
		MediaType contentType = respEntity.getHeaders().getContentType();
		System.out.println(result.toString());
		System.out.println(contentType.toString());

	}
	
	private static void testBasicAuthentificationGetAllEmployees() {
		RestTemplate restTemplate = new RestTemplate();
		
		final String uri = "http://localhost:8080/JerseyDemos/rest/employees/getAllEmployees";

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setBasicAuth("howtodoinjava", "password");		
		HttpEntity<String> entity = new HttpEntity<String> ("parameter", headers);
		ResponseEntity<Employee[] >respEntity = restTemplate.exchange(uri, HttpMethod.GET, entity, Employee[].class);
	    Employee[] result = respEntity.getBody();
	     for(int i=0;i<result.length;i++) {
	 	    System.out.println(result[i].getName());
	     }
	    System.out.println(result);


	}
	
	private static void getString()
	{
		RestTemplate restTemplate = new RestTemplate();
		final String uri = "http://localhost:8080/JerseyDemos/rest/employees/getString";
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setBasicAuth("howtodoinjava", "password");		

		HttpEntity<String> entity = new HttpEntity<String> ("parameter", headers);
		ResponseEntity<String >respEntity = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
		String result = respEntity.getBody();
		MediaType contentType = respEntity.getHeaders().getContentType();
		System.out.println(result.toString());
		System.out.println(contentType.toString());
	}
}

