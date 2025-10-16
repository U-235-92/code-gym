package aq.gym.net.url_client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

@SuppressWarnings("unused")
public class ConnectionDriver {

	public void getRequestWithoutParam() throws MalformedURLException, IOException {
		URL url = new URL("https://myfitlife.ru/");
//		HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
//		connection.setRequestMethod("GET");
		URLConnection connection = url.openConnection();
		connection.connect();
		System.out.println("HEADER FIELDS:");
		connection.getHeaderFields().forEach((k, v) -> System.out.println(k + ":" + v));
		System.out.println("-----");
		System.out.println("The 5th header field:" + connection.getHeaderField(5));
		System.out.println("Content-type:" + connection.getContentType());
		System.out.println("Content-length:" + connection.getContentLength());
		System.out.println("Response:" + connection.getHeaderField(0));
		System.out.println("DATA:");
		InputStream is = connection.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String line = "";
		int lineCount = 0;
		while ((line = br.readLine()) != null && lineCount++ < 10) {
			System.out.println(line);
		}
		br.close();
	}

	public void getRequestWithParam() throws MalformedURLException, IOException {
		List<String> params = new ArrayList<>();
		params.add("foo1=" + URLEncoder.encode("bar1", Charset.defaultCharset()));
		params.add("foo2=" + URLEncoder.encode("bar2", Charset.defaultCharset()));
		URL url = new URL("https://postman-echo.com/get" + "?" + String.join("&", params));
		URLConnection connection = url.openConnection();
		Scanner scanner = new Scanner(connection.getInputStream());
		while(scanner.hasNext()) {
			System.out.println(scanner.nextLine());
		}
		scanner.close();
	}

	public void postRequest() throws MalformedURLException, IOException {
		URL url = new URL("https://postman-echo.com/post");
		URLConnection connection = url.openConnection();
		connection.addRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		connection.setDoOutput(true);
		PrintWriter pw = new PrintWriter(connection.getOutputStream());
		pw.print("foo1=" + URLEncoder.encode("bar1", Charset.defaultCharset()) + "&");
		pw.print("foo2=" + URLEncoder.encode("bar2", Charset.defaultCharset()));
		pw.close();
		Scanner scanner = new Scanner(connection.getInputStream());
		while(scanner.hasNextLine()) {
			System.out.println(scanner.nextLine());
		}
		scanner.close();
	}

	public static void main(String[] args) throws MalformedURLException, IOException {
		ConnectionDriver driver = new ConnectionDriver();
//		driver.getRequestWithoutParam();
		driver.getRequestWithParam();
//		driver.postRequest();
	}
}
