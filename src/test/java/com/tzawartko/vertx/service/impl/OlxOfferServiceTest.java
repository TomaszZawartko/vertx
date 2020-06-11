package com.tzawartko.vertx.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.tzawartko.vertx.strategy.impl.OlxSendOffersStrategy;

import io.vertx.core.AsyncResult;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.client.HttpResponse;

@RunWith(MockitoJUnitRunner.class)
public class OlxOfferServiceTest {

	@Mock
	HttpServerResponse response;
	@Mock
	Buffer buffer;
	@Mock
	HttpResponse<Buffer> responseBuffer;
	@Mock
	AsyncResult<HttpResponse<Buffer>> result;

	@Test
	public void testEmptyListWithProducts() {
		// when
		Mockito.when(buffer.toString()).thenReturn("");
		Mockito.when(responseBuffer.body()).thenReturn(buffer);
		Mockito.when(responseBuffer.statusCode()).thenReturn(200);
		Mockito.when(result.result()).thenReturn(responseBuffer);

		OlxSendOffersStrategy olxStrategy = new OlxSendOffersStrategy();
		olxStrategy.sendOffersToHttpResponse(result, response);

		// then
		Mockito.verify(response, Mockito.times(1)).putHeader("content-type", "application/json");
		Mockito.verify(response, Mockito.times(1)).end("[ ]");
	}
}
