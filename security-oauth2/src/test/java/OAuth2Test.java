import java.net.URI;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.tistory.seungchul.model.user.UserAuthority;
import com.tistory.seungchul.model.user.UserEntity;

public class OAuth2Test {

	public static final String REST_URL = "http://localhost:8080";
	
	public static final String OAUTH_URL = "http://localhost:8080/oauth/token";
	
	public static final String PARAM_ACCESS_TOKEN = "?access_token=";
	
	public static final String PARAM_ADMIN = "?grant_type=password&username=admin&password=1234";

	public static final String PARAM_USER = "?grant_type=password&username=user1&password=1234";
	
	public static final String CLIENT_CREDENTIAL = "trust-client-first:1234";
	
	private static HttpHeaders getHeader() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		return headers;
	}
	
	private static HttpHeaders getClientCredentialHeader() {
		HttpHeaders headers = getHeader();
		String clientCredentialToBase64 = new String(Base64.encodeBase64(CLIENT_CREDENTIAL.getBytes()));
		headers.add("Authorization", "Basic "+clientCredentialToBase64);
		return headers;
	}
	
	public static Map<String, String> getOAuthToken(){
		HttpHeaders headers = getClientCredentialHeader();
		RestTemplate template = new RestTemplate();
		HttpEntity<String> request = new HttpEntity<>(headers);
		ResponseEntity<Object> res = template.exchange(OAUTH_URL+PARAM_ADMIN, HttpMethod.POST, request, Object.class);
		LinkedHashMap<String, Object> map = (LinkedHashMap<String, Object>)res.getBody();
		Map<String, String> result = new HashMap<>();
		if(map !=null) {
			result.put("access_token", (String)map.get("access_token"));
			result.put("token_type", (String)map.get("token_type"));
			result.put("refresh_token", (String)map.get("refresh_token"));
			result.put("expires_in", ((int)map.get("expires_in"))+"");
			result.put("scope", (String)map.get("scope"));
			System.out.println(result);
		}
		return result;
	}
	public static Map<String, String> getUserOAuthToken(){
		HttpHeaders headers = getClientCredentialHeader();
		RestTemplate template = new RestTemplate();
		HttpEntity<String> request = new HttpEntity<>(headers);
		ResponseEntity<Object> res = template.exchange(OAUTH_URL+PARAM_USER, HttpMethod.POST, request, Object.class);
		LinkedHashMap<String, Object> map = (LinkedHashMap<String, Object>)res.getBody();
		Map<String, String> result = new HashMap<>();
		if(map !=null) {
			result.put("access_token", (String)map.get("access_token"));
			result.put("token_type", (String)map.get("token_type"));
			result.put("refresh_token", (String)map.get("refresh_token"));
			result.put("expires_in", ((int)map.get("expires_in"))+"");
			result.put("scope", (String)map.get("scope"));
			System.out.println(result);
		}
		return result;
	}
	
	
	public static void getUser(Map<String, String> token,String userId) {
		HttpHeaders headers = getHeader();
		RestTemplate template = new RestTemplate();
		HttpEntity<String> request = new HttpEntity<>(headers);
		ResponseEntity<UserEntity> res = template.exchange(REST_URL+"/user/"+userId+PARAM_ACCESS_TOKEN+token.get("access_token"), HttpMethod.GET, request, UserEntity.class);
		UserEntity user = res.getBody();
		System.out.println(user);
		
	}
	
	
	public static void addUser(Map<String, String> token, UserEntity user) {
		HttpHeaders headers = getHeader();
		RestTemplate template = new RestTemplate();
		HttpEntity<Object> request = new HttpEntity<Object>(user, headers);
		URI uri = template.postForLocation(REST_URL+"/user/add"+PARAM_ACCESS_TOKEN+token.get("access_token"), request, UserEntity.class);
		getUser(token, user.getUserId());
	}
	
	
	public static void removeUser(Map<String, String> token,String userId) {
		HttpHeaders headers = getHeader();
		RestTemplate template = new RestTemplate();
		HttpEntity<String> request = new HttpEntity<>(headers);
		template.delete(REST_URL+"/user/remove/"+userId+PARAM_ACCESS_TOKEN+token.get("access_token"));
	}
	
	
	public static void main(String[] args) {
		
		Map<String, String> map = getOAuthToken();
		Map<String, String> userToken = getUserOAuthToken();
		
//		getUser(map, "admin");
		getUser(userToken, "admin");
//		UserEntity user = new UserEntity();
//		user.setPassword("1234");
//		user.setUserId("admin2");
		UserEntity user1 = new UserEntity();
		user1.setPassword("1234");
		user1.setUserId("admin3");
//		addUser(map, user);
		addUser(userToken, user1);
//		removeUser(map,user.getUserId());
		removeUser(userToken,user1.getUserId());
	}
}
