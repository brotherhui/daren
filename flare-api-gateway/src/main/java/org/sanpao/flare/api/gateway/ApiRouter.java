package org.sanpao.flare.api.gateway;

import org.sanpao.flare.common.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class ApiRouter {

	@Autowired
	private FunctionProxy functionProxy;

	@GetMapping(path = "/api/{function}", produces = { MediaType.APPLICATION_STREAM_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public Flux<ApiResult> doGet(@PathVariable("function") String function, @RequestBody String payload) {
		return Flux.just(functionProxy.invoke(function, payload));
	}

	@GetMapping(path = "/auth/api/{function}", produces = { MediaType.APPLICATION_STREAM_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public Flux<ApiResult> doGetWithAuth(@PathVariable("function") String function, @RequestBody String payload) {
		// 后续增加与OAuth集成
		return doGet(function, payload);
	}

	@PostMapping(path = "/api/{function}", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public Mono<ApiResult> doPost(@PathVariable("function") String function, @RequestBody String payload) {
		return Mono.just(functionProxy.invoke(function, payload));
	}

	@PostMapping(path = "/auth/api/{function}", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public Mono<ApiResult> doPostWithAuth(@PathVariable("function") String function, @RequestBody String payload) {
		// 后续增加与OAuth集成
		return doPost(function, payload);
	}

	@PutMapping(path = "/api/{function}", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public Mono<ApiResult> doPut(@PathVariable("function") String function, @RequestBody String payload) {
		return Mono.just(functionProxy.invoke(function, payload));
	}

	@PutMapping(path = "/auth/api/{function}", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public Mono<ApiResult> doPutWithAuth(@PathVariable("function") String function, @RequestBody String payload) {
		// 后续增加与OAuth集成
		return doPut(function, payload);
	}

	@DeleteMapping(path = "/api/{function}", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public Mono<ApiResult> doDelete(@PathVariable("function") String function, @RequestBody String payload) {
		return Mono.just(functionProxy.invoke(function, payload));
	}

	@DeleteMapping(path = "/auth/api/{function}", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public Mono<ApiResult> doDeleteWithAuth(@PathVariable("function") String function, @RequestBody String payload) {
		// 后续增加与OAuth集成
		return doDelete(function, payload);
	}

}
