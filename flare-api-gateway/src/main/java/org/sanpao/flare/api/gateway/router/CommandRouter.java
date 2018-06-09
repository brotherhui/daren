package org.sanpao.flare.api.gateway.router;

import org.sanpao.flare.common.ApiResult;
import org.sanpao.flare.common.IgniteFunctionProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
public class CommandRouter {

	@Autowired
	private IgniteFunctionProxy igniteFunctionProxy;

	@PostMapping(path = "/command/{function}", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public Mono<ApiResult> execute(@PathVariable("function") String function, String payload) {
		return Mono.just(igniteFunctionProxy.execute(function, payload));
	}

	@PostMapping(path = "/auth/command/{function}", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public Mono<ApiResult> executeWithAuth(@PathVariable("function") String function, String payload) {
		// 后续增加与OAuth集成
		return execute(function, payload);
	}

}
