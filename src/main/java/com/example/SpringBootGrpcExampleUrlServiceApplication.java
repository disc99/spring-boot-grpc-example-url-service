package com.example;

import io.example.url.Url;
import io.example.url.UrlServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SpringBootGrpcExampleUrlServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootGrpcExampleUrlServiceApplication.class, args);
	}

	@GRpcService
	public static class UrlService extends UrlServiceGrpc.UrlServiceImplBase {

		List<String> urls = new ArrayList<>();

		@Override
		public void shortcut(Url.ShortcutRequest request, StreamObserver<Url.ShortcutResponse> responseObserver) {
			urls.add(request.getBaseUrl());
			String url = String.valueOf(urls.size());
			Url.ShortcutResponse res = Url.ShortcutResponse.newBuilder().setShortUrl(url).build();
			responseObserver.onNext(res);
			responseObserver.onCompleted();
		}

		@Override
		public void parse(Url.ParseRequest request, StreamObserver<Url.ParseResponse> responseObserver) {
			String url = urls.get(Integer.parseInt(request.getShortUrl()) - 1);
			Url.ParseResponse res = Url.ParseResponse.newBuilder().setBaseUrl(url).build();
			responseObserver.onNext(res);
			responseObserver.onCompleted();
		}
	}
}
