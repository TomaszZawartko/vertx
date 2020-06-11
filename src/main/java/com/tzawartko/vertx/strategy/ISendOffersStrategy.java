package com.tzawartko.vertx.strategy;

import io.vertx.core.AsyncResult;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.client.HttpResponse;

/**
 * Interface to sending offers.
 * 
 * @author Tomasz Zawartko
 *
 */
public interface ISendOffersStrategy {

	/**
	 * The algorithm sending offers to {@link HttpServerResponse}
	 * 
	 * @param result
	 *            the result of a previously sent HttpRequest
	 * @param response
	 *            to store offers
	 */
	void sendOffersToHttpResponse(AsyncResult<HttpResponse<Buffer>> result, HttpServerResponse response);

}
