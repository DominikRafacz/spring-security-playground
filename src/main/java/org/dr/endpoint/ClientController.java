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

    private static final String OUTER_URL = "https://192.168.1.100:8086/user/shelter";

    @Value("${trust.store}")
    private Resource trustStore;

    @Value("${trust.store.password}")
    private String trustStorePassword;

    @RequestMapping("/inside")
    public String inside() throws Exception {
        SSLContext sslContext = new SSLContextBuilder()
                .loadTrustMaterial(trustStore.getURL(), trustStorePassword.toCharArray())
                .build();
        CloseableHttpClient httpClient = HttpClients.custom()
                .setSSLSocketFactory(new SSLConnectionSocketFactory(sslContext))
                .build();
        RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory(httpClient));
        restTemplate
                .getInterceptors().add(new BasicAuthenticationInterceptor("user", "userPass"));
        String tmp = restTemplate
                .getForEntity(OUTER_URL, String.class, Collections.emptyMap())
                .getBody();
        System.out.println(tmp);
        return tmp;
    }
}
