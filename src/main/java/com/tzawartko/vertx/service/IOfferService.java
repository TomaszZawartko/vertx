package com.tzawartko.vertx.service;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerResponse;

public interface IOfferService {

	/**
	 * Send offers (searched by keyword) from website to
	 * {@link HttpServerResponse} in JSON format.
	 * 
	 * @param vertx
	 *            Vertx client
	 * @param keyword
	 *            to search offers in offers WebSite
	 * @param response
	 *            to store found offers in JSON format
	 */
	void sendOffersFromOfferWebsite(Vertx vertx, String keyword, HttpServerResponse response);
}
