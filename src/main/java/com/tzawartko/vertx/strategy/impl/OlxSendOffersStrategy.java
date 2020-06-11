package com.tzawartko.vertx.strategy.impl;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.tzawartko.vertx.offer.Offer;
import com.tzawartko.vertx.strategy.ISendOffersStrategy;

import io.vertx.core.AsyncResult;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.client.HttpResponse;

/**
 * Strategy to get offers from olx.pl searched by keyword and send them to
 * {@link HttpServerResponse} in JSON format. Notice: this strategy returns only
 * offers from first page!
 * 
 * @author Tomasz Zawartko
 *
 */
public class OlxSendOffersStrategy implements ISendOffersStrategy {

	private static final Logger LOGGER = LoggerFactory.getLogger(OlxSendOffersStrategy.class);

	@Override
	public void sendOffersToHttpResponse(AsyncResult<HttpResponse<Buffer>> result, HttpServerResponse response) {
		List<Offer> offers = getOffersFromHtmlElements(getHtmlElementsFromHttpResponse(result));
		sendOffersAsJsonToResponse(response, offers);
	}

	private void sendOffersAsJsonToResponse(HttpServerResponse response, List<Offer> products) {
		response.putHeader("content-type", "application/json");
		response.end(Json.encodePrettily(products));
	}

	private Elements getHtmlElementsFromHttpResponse(AsyncResult<HttpResponse<Buffer>> result) {
		HttpResponse<Buffer> response = result.result();
		LOGGER.info("Received response with status code " + response.statusCode());
		String html = response.body().toString();
		Document htmlDoc = Jsoup.parse(html);
		return htmlDoc.select("td.offer");
	}

	private List<Offer> getOffersFromHtmlElements(Elements htmlElements) {
		List<Offer> offers = new ArrayList<>();
		for (Element e : htmlElements) {
			Offer offer = getOfferFromHtmlElement(e);
			if (isOfferValid(offer)) {
				offers.add(offer);
			}
		}
		return offers;
	}

	private Offer getOfferFromHtmlElement(Element htmlElement) {
		String id = htmlElement.select("table").attr("data-id");
		String price = htmlElement.select("p.price").select("strong").text();
		String name = htmlElement.select("h3").select("a").select("strong").text();
		return new Offer(id, name, price);
	}

	private boolean isOfferValid(Offer offer) {
		return offer.getId() != null && !offer.getId().isEmpty() && offer.getPrice() != null
				&& !offer.getPrice().isEmpty() && offer.getName() != null && !offer.getName().isEmpty();
	}

}
