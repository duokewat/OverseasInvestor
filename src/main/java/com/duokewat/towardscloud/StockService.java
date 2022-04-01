package com.duokewat.towardscloud;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/stock")
public class StockService {

	@GET
	@Path("/summary")
	@Produces("text/html")
	public Response getNriCust() {
		return Response.status(200).entity(urlAccess()).build();
	}
	private  String urlAccess() {
		String responseString = "";
		try {
			HttpRequest request = HttpRequest.newBuilder()
					.uri(new URI("https://yh-finance.p.rapidapi.com/stock/v2/get-summary?symbol=AAPL&region=US"))
					.header("X-RapidAPI-Host", "yh-finance.p.rapidapi.com")
					.header("X-RapidAPI-Key", "4259f66e17msh8106bfc804b3a29p1b340cjsnea72eaab8d20").GET().build();
			HttpResponse<String> response = HttpClient.newBuilder()
					.build().send(request, BodyHandlers.ofString());
			responseString = response.body();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseString;
	}
}