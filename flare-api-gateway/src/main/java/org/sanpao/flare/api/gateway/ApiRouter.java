package org.sanpao.flare.api.gateway;

import java.util.Map;

import org.sanpao.flare.common.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
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
	public Flux<ApiResult> doGet(ServerHttpRequest request, @PathVariable("function") String function) {
		return Flux.just(functionProxy.invoke(request, function));
	}

	@GetMapping(path = "/auth/api/{function}", produces = { MediaType.APPLICATION_STREAM_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public Flux<ApiResult> doGetWithAuth(ServerHttpRequest request, @PathVariable("function") String function) {
		// 后续增加与OAuth集成
		return doGet(request, function);
	}

	@DeleteMapping(path = "/api/{function}", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public Mono<ApiResult> doDelete(ServerHttpRequest request, @PathVariable("function") String function) {
		return Mono.just(functionProxy.invoke(request, function));
	}

	@DeleteMapping(path = "/auth/api/{function}", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public Mono<ApiResult> doDeleteWithAuth(ServerHttpRequest request, @PathVariable("function") String function) {
		// 后续增加与OAuth集成
		return doDelete(request, function);
	}

	@PostMapping(path = "/api/{function}", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public Mono<ApiResult> doPost(ServerHttpRequest request, @PathVariable("function") String function, @RequestBody Map<String, Object> payload) {
		return Mono.just(functionProxy.invoke(request, function, payload));
	}

	@PostMapping(path = "/auth/api/{function}", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public Mono<ApiResult> doPostWithAuth(ServerHttpRequest request, @PathVariable("function") String function, @RequestBody Map<String, Object> payload) {
		// 后续增加与OAuth集成
		return doPost(request, function, payload);
	}

	@PutMapping(path = "/api/{function}", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public Mono<ApiResult> doPut(ServerHttpRequest request, @PathVariable("function") String function, @RequestBody Map<String, Object> payload) {
		return Mono.just(functionProxy.invoke(request, function, payload));
	}

	@PutMapping(path = "/auth/api/{function}", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public Mono<ApiResult> doPutWithAuth(ServerHttpRequest request, @PathVariable("function") String function, @RequestBody Map<String, Object> payload) {
		// 后续增加与OAuth集成
		return doPut(request, function, payload);
	}

}
