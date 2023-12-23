package org.example.ejb.service;

import javax.ejb.Stateless;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.security.cert.X509Certificate;

@Stateless
public class NetworkServiceImpl implements NetworkService {

    private final String STUDY_GROUP_SERVICE = "http://localhost:10000/api";

    public WebTarget getTarget() {
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA ");
        URI uri = UriBuilder.fromUri(STUDY_GROUP_SERVICE).build();
        Client client = createClientBuilderSSL();
        return client.target(uri);
    }

    private Client createClientBuilderSSL() {
        SSLContext sslContext = getSslContext();
        return ClientBuilder.newBuilder()
                .sslContext(sslContext)
                .build();
    }

    private SSLContext getSslContext() {
        try {
            SSLContext sslContext = SSLContext.getInstance("TLS");

            TrustManager[] trustManagers = new TrustManager[]{
                    new X509TrustManager() {
                        public X509Certificate[] getAcceptedIssuers() {
                            return null;
                        }

                        public void checkClientTrusted(X509Certificate[] certs, String authType) {
                        }

                        public void checkServerTrusted(X509Certificate[] certs, String authType) {
                        }
                    }
            };

            sslContext.init(null, trustManagers, null);
            return sslContext;
        } catch (Exception e) {
            throw new RuntimeException("Error initializing SSL context", e);
        }
    }
}