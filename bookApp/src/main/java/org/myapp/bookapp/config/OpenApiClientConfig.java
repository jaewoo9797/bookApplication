package org.myapp.bookapp.config;

import java.util.Map;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.UriBuilderFactory;

@Configuration
public class OpenApiClientConfig {
    //TODO 실제 값 설정 파일에 넣고 불러오기
    private static final String BASE_URL = "https://api.openapi.models.com";
    private static final String API_KEY = "asda";

    // TODO 하드 코딩된 부분 처리하기
    @Bean
    public UriBuilderFactory uriBuilderFactory() {
        var defaultUriBuilderFactory = new DefaultUriBuilderFactory(BASE_URL);
        defaultUriBuilderFactory.setDefaultUriVariables(
                Map.of(
                        "key", API_KEY,
                        "type", "온라인자료"
                )
        );
        return defaultUriBuilderFactory;
    }

    @Bean
    public WebClient nationalLibraryWebClient(UriBuilderFactory factory,WebClient.Builder webClientBuilder) {
        return webClientBuilder
                .uriBuilderFactory(factory)
                .build();
    }
}
