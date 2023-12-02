package es.um.atica.debezium.demo;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UncheckedIOException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import static java.nio.charset.StandardCharsets.UTF_8;

@Component
public class DemoStartup implements ApplicationRunner {

    @Autowired
    private RestTemplate restTemplate;
    
    @Value("classpath:orcl-connector.json")
    private Resource resource;    

    private String file2String() {
        try (Reader reader = new InputStreamReader(resource.getInputStream(), UTF_8)) {
            return FileCopyUtils.copyToString(reader);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.ALL));
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>(file2String(),headers);
        String result = null;
        try {
            ResponseEntity<String> response = restTemplate.exchange("http://connect:8083/connectors/",HttpMethod.POST,entity,String.class);
            result = String.format("Status: %s\nBody: %s",response.getStatusCode(),response.getBody());
        } catch (HttpStatusCodeException ce) {
            // Nothing to-do
            result = String.format("Status: %s\nBody: %s",ce.getStatusCode(),ce.getResponseBodyAsString());
        } catch (Exception ex) {
            result = ex.getMessage();
        }
        System.err.println("*************************");
        System.err.println(result);
        System.err.println("*************************");
    }

}