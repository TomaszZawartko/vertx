package com.tzawartko.vertx.app;

import com.tzawartko.vertx.handler.OfferSearchHandler;
import com.tzawartko.vertx.service.IOfferService;
import com.tzawartko.vertx.service.impl.OlxOfferService;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;

/**
 * Main class storing endpoints.
 * 
 * @author Tomasz Zawartko
 *
 */
public class App {
	public static void main(String[] args) {

		final int olxPort = 80;
		final String olxHost = "www.olx.pl";
		final String olxBaseRequestURI = "/oferty/q-";

		Vertx vertx = Vertx.vertx();
		HttpServer httpServer = vertx.createHttpServer();

		Router router = Router.router(vertx);
		IOfferService service = new OlxOfferService(olxPort, olxHost, olxBaseRequestURI);

		router
			.get("/offers/olx/:keyword")
			.handler(routingContext -> {
				new OfferSearchHandler(service, vertx).handle(routingContext);;
		});
		
		httpServer.requestHandler(router).listen(8091);
	}
}
