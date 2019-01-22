package jerseydemo;

import org.springframework.http.HttpHeaders;

public interface MyMethodsInterface {
	
	public HttpHeaders createHeaders(String username, String password);

}
