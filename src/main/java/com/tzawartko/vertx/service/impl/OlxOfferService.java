package com.tzawartko.vertx.service.impl;

import com.tzawartko.vertx.service.AbstractOfferService;
import com.tzawartko.vertx.strategy.impl.OlxSendOffersStrategy;

import io.vertx.core.http.HttpServerResponse;

/**
 * Allows to get offers from the olx.pl website and stores them in
 * {@link HttpServerResponse} response in JSON format.
 * 
 * @author Tomasz Zawartko
 *
 */
public class OlxOfferService extends AbstractOfferService {

	public OlxOfferService(int port, String host, String baseRequestURI) {
		super(port, host, baseRequestURI, new OlxSendOffersStrategy());
	}
}
