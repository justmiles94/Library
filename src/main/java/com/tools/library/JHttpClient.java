package com.tools.library;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

public class JHttpClient {

	String getUrl = "http://www.google.com/search?q=httpClient";
	String postUrl = "https://selfsolve.apple.com/wcResults.do";
	String userAgent = "";
	HttpClient client = HttpClientBuilder.create().build();

	public void getMethod(String getUrl) throws ClientProtocolException, IOException {
		HttpGet request = new HttpGet(getUrl);
		// add request header
		request.addHeader("User-Agent", userAgent);
		HttpResponse response = client.execute(request);

		int responseCode = response.getStatusLine().getStatusCode();
		System.out.println(String.format("Response Code : %d", responseCode));

		InputStream iStream = response.getEntity().getContent();
		InOutUtil.readBufferList(iStream).forEach(System.out::println);
	}

	public void postMethod(String postUrl) throws ClientProtocolException, IOException {
		HttpPost post = new HttpPost(postUrl);

		// add header
		post.setHeader("User-Agent", userAgent);

		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("sn", "C02G8416DRJM"));
		urlParameters.add(new BasicNameValuePair("cn", ""));
		urlParameters.add(new BasicNameValuePair("locale", ""));
		urlParameters.add(new BasicNameValuePair("caller", ""));
		urlParameters.add(new BasicNameValuePair("num", "12345"));

		post.setEntity(new UrlEncodedFormEntity(urlParameters));

		HttpResponse response = client.execute(post);
		int statusCode = response.getStatusLine().getStatusCode();
		System.out.println(String.format("Response Code : %d", statusCode));

		InputStream iStream = response.getEntity().getContent();
		InOutUtil.readBufferList(iStream).forEach(System.out::println);
	}

	public void setHeaders(HttpGet request, Map<String, String> map) {
		map.forEach(request::addHeader);
	}

	public void setHeaders(HttpPost request, Map<String, String> map) {
		map.forEach(request::addHeader);
	}
}
