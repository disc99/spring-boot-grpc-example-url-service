package com.example;

import io.example.url.Url;
import io.example.url.UrlServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootGrpcExampleUrlServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootGrpcExampleUrlServiceApplication.class, args);
	}

	@GRpcService
	public static class UrlService extends UrlServiceGrpc.UrlServiceImplBase {
		@Override
		public void shortcut(Url.ShortcutRequest request, StreamObserver<Url.ShortcutResponse> responseObserver) {
			Url.ShortcutResponse res = Url.ShortcutResponse.newBuilder().setShortUrl("short").build();
			responseObserver.onNext(res);
			responseObserver.onCompleted();
		}

		@Override
		public void parse(Url.ParseRequest request, StreamObserver<Url.ParseResponse> responseObserver) {
			Url.ParseResponse res = Url.ParseResponse.newBuilder().setBaseUrl("https://google.com").build();
			responseObserver.onNext(res);
			responseObserver.onCompleted();
		}
	}
}
