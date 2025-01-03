package org.myapp.bookapp.bookinfo.service;

import lombok.RequiredArgsConstructor;
import org.myapp.bookapp.config.OpenApiClientConfig;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class BookInfoService {

    private final WebClient openApiClientConfig;

    public void fetchBookInfoByBookname(String bookname) {
        openApiClientConfig.get()
                .uri(uriBuilder ->
                        uriBuilder.)
    }
}
