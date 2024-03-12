import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.InputStream;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.security.KeyStore;

public class RestClient {

    public static void main(String[] args) {
        // Set proxy information
        String proxyHost = "your_https_proxy_host";
        int proxyPort = 8080;
        String username = "your_proxy_username";
        String password = "your_proxy_password";

        // Configure proxy
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new java.net.InetSocketAddress(proxyHost, proxyPort));

        // Set proxy credentials using Authenticator
        Authenticator.setDefault(new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password.toCharArray());
            }
        });

        // Create RestTemplate with proxy, SSL context, and keystore/truststore configuration
        RestTemplate restTemplate = new RestTemplateBuilder()
                .requestFactory(() -> clientHttpRequestFactory(proxy))
                .build();

        // Now you can use restTemplate for making HTTPS requests with the configured proxy, credentials, SSL context, and keystore/truststore
        // For example: restTemplate.getForObject("https://example.com/api/resource", String.class);
    }

    private static ClientHttpRequestFactory clientHttpRequestFactory(Proxy proxy) {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setProxy(proxy);

        // Load keystore and truststore
        KeyStore keyStore = loadKeyStore("path/to/keystore.jks", "keystore_password");
        KeyStore trustStore = loadKeyStore("path/to/truststore.jks", "truststore_password");

        // Set up SSL context with keystore and truststore
        SSLContext sslContext = createSSLContext(keyStore, "keystore_password", trustStore, "truststore_password");
        requestFactory.setSslContext(sslContext);

        return requestFactory;
    }

    private static KeyStore loadKeyStore(String path, String password) {
        try (InputStream inputStream = RestClient.class.getClassLoader().getResourceAsStream(path)) {
            KeyStore keyStore = KeyStore.getInstance("JKS");
            keyStore.load(inputStream, password.toCharArray());
            return keyStore;
        } catch (Exception e) {
            throw new RuntimeException("Error loading keystore/truststore", e);
        }
    }

    private static SSLContext createSSLContext(KeyStore keyStore, String keyPassword, KeyStore trustStore, String trustPassword) {
        try {
            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            keyManagerFactory.init(keyStore, keyPassword.toCharArray());

            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(trustStore);

            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), null);

            return sslContext;
        } catch (Exception e) {
            throw new RuntimeException("Error creating SSL context", e);
        }
    }
}
