package com.tzawartko.vertx.handler;

import com.tzawartko.vertx.service.IOfferService;

import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.RoutingContext;

public class OfferSearchHandler implements Handler<RoutingContext> {
	
	private IOfferService offerService;
	private Vertx vertx;
	
	public OfferSearchHandler(IOfferService offerService, Vertx vertx){
		this.offerService = offerService;
		this.vertx = vertx;
	}
	
	@Override
	public void handle(RoutingContext routingContext) {
		String keyword = routingContext.request().getParam("keyword").replace(" ", "-");
		HttpServerResponse response = routingContext.response();
		offerService.sendOffersFromOfferWebsite(vertx, keyword, response);
	}

}
