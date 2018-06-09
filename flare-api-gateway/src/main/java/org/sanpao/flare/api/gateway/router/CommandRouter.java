package org.sanpao.flare.api.gateway.router;

import org.sanpao.flare.api.gateway.FunctionProxy;
import org.sanpao.flare.common.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
public class CommandRouter {

	@Autowired
	private FunctionProxy functionProxy;

	@PostMapping(path = "/command/{function}", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public Mono<ApiResult> execute(@PathVariable("function") String function, String payload) {
		return Mono.just(functionProxy.execute(function, payload));
	}

	@PostMapping(path = "/auth/command/{action}", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public Mono<String> executeWithAuth() {
		return null;
	}

}
