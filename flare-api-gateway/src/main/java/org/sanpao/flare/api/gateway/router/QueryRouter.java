package org.sanpao.flare.api.gateway.router;

import org.sanpao.flare.common.ApiResult;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;

@RestController
public class QueryRouter {

	@PostMapping(path = "/query/{function}", produces = { MediaType.APPLICATION_STREAM_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public Flux<ApiResult> execute(@PathVariable("function") String function, String payload) {
		return null;
	}

	@PostMapping(path = "/auth/query/{function}", produces = { MediaType.APPLICATION_STREAM_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public Flux<ApiResult> executeWithAuth(@PathVariable("function") String function, String payload) {
		// 后续增加与OAuth集成
		return execute(function, payload);
	}

}
