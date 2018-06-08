package org.sanpao.flare.api.gateway.router;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;

@RestController
public class QueryRouter {

	@PostMapping(path = "/query/**", produces = { MediaType.APPLICATION_STREAM_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public Flux<String> execute() {
		return null;
	}

	@PostMapping(path = "/auth/query/**", produces = { MediaType.APPLICATION_STREAM_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public Flux<String> executeWithAuth() {
		return null;
	}

}
