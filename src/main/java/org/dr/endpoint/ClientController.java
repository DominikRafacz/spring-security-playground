package org.dr.endpoint;


import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.util.Collections;

@Profile("client")
@RestController
public class ClientController {

    private static final String OUTER_URL = "https://127.0.0.1:8086/shelter";

    @Value("${trust.store}")
    private Resource trustStore;

    @Value("${trust.store.password}")
    private String trustStorePassword;

    @RequestMapping("/inside")
    public String inside() throws Exception {
//        javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(
//                (hostname, sslSession) -> hostname.equals("localhost"));
        SSLContext sslContext = new SSLContextBuilder()
                .loadTrustMaterial(trustStore.getURL(), trustStorePassword.toCharArray())
                .build();
        SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(sslContext);
        CloseableHttpClient httpClient = HttpClients.custom()
                .setSSLSocketFactory(socketFactory)
                .build();
        HttpComponentsClientHttpRequestFactory factory =
                new HttpComponentsClientHttpRequestFactory(httpClient);
//        String tmp = new RestTemplateBuilder()
//                .requestFactory(() -> factory)
//                .basicAuthentication("user1", "user1Pass").build()
        RestTemplate restTemplate = new RestTemplate(factory);
        restTemplate
                .getInterceptors().add(new BasicAuthenticationInterceptor("user1", "user1Pass"));
        String tmp = restTemplate
                .getForEntity(OUTER_URL, String.class, Collections.emptyMap())
                .getBody();
//        new RestTemplate(factory)
        System.out.println(tmp);
        return tmp;
    }
}
