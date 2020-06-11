package com.tzawartko.vertx.service;

import com.tzawartko.vertx.exceptions.WebSiteConnectionException;
import com.tzawartko.vertx.strategy.ISendOffersStrategy;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.client.WebClient;

/**
 * Abstract Service to get offers from offer website like olx.pl and then
 * returning them in JSON format.
 * 
 * @author Tomasz Zawartko
 *
 */
public abstract class AbstractOfferService implements IOfferService {

	private int port;
	private String host;
	private String baseRequestURI;
	private ISendOffersStrategy strategy;

	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractOfferService.class);

	public AbstractOfferService(int port, String host, String baseRequestURI, ISendOffersStrategy strategy) {
		super();
		this.port = port;
		this.host = host;
		this.baseRequestURI = baseRequestURI;
		this.strategy = strategy;

	}

	@Override
	public void sendOffersFromOfferWebsite(Vertx vertx, String keyword, HttpServerResponse response) {

		WebClient client = WebClient.create(vertx);
		client.get(port, host, baseRequestURI + keyword).send(ar -> {
			if (ar.succeeded()) {
				strategy.sendOffersToHttpResponse(ar, response);
			} else {
				LOGGER.error("Something went wrong " + ar.cause().getMessage(),
						new WebSiteConnectionException(host + baseRequestURI + keyword, port));
			}
		});
	}

}
