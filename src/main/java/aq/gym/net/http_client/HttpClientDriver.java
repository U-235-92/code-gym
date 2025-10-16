package aq.gym.net.http_client;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Redirect;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
public class HttpClientDriver {

	public void getRequestWithParameters() throws URISyntaxException, IOException, InterruptedException {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(new URI("https://postman-echo.com/get?foo1=bar1&foo2=bar2")).
				header("Content-Type", "application/x-www-form-urlencoded").GET().build();//.method("GET", BodyPublishers.ofString("foo1=bar1&foo2=bar2")).build();
		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
		System.out.println(response.body());
	}
	
	public void getRequestWithoutParameters() throws IOException, InterruptedException, URISyntaxException, ExecutionException {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(new URI("https://myfitlife.ru/")).GET().build();
		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
		String responseBody = response.body();
		HttpHeaders headers = response.headers();
		headers.map().forEach((key, values) -> System.out.println(key + ":" + values));
		System.out.println(responseBody);
	}
	
	public void getRequestWithoutParametersAsych() throws IOException, InterruptedException, URISyntaxException, ExecutionException {
		Executor executor = Executors.newCachedThreadPool(runnable -> {
			Thread thread = new Thread(runnable);
			thread.setDaemon(true);
			return thread;
		});
		HttpClient clientAsynch = HttpClient.newBuilder().executor(executor).followRedirects(Redirect.ALWAYS).build();
		HttpRequest request = HttpRequest.newBuilder().uri(new URI("https://myfitlife.ru/")).GET().build();
		CompletableFuture<String> responseAsynch = clientAsynch.sendAsync(request, BodyHandlers.ofString()).thenApply(response -> response.body());
		String bodyAsynch = responseAsynch.join();
		System.out.println(bodyAsynch);
	}
	
	public void postRequest() throws URISyntaxException, IOException, InterruptedException {
		HttpClient client = HttpClient.newHttpClient();
		List<String> requestBodyParts = new ArrayList<>();
		requestBodyParts.add("foo1=" + URLEncoder.encode("bar1", "UTF-8"));
		requestBodyParts.add("foo2=" + URLEncoder.encode("bar2", "UTF-8"));
		String requestBody = requestBodyParts.stream().collect(Collectors.joining("&"));
		HttpRequest request = HttpRequest.newBuilder().uri(new URI("https://postman-echo.com/post")).
				header("Content-Type", "application/x-www-form-urlencoded").POST(BodyPublishers.ofString(requestBody)).build();
		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
		String responseBody = response.body();
		System.out.println(responseBody);
	}
	
	public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException, ExecutionException {
		// TODO Auto-generated method stub
		HttpClientDriver driver = new HttpClientDriver();
//		driver.getRequestWithoutParameters();
//		driver.getRequestWithParameters();
		driver.getRequestWithoutParametersAsych();
//		driver.postRequest();
	}
}
