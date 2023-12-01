package es.um.atica.debezium.demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-tests.properties")
class DemoApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	private String readFromFileToString(String filePath) throws IOException {
        File resource = new ClassPathResource(filePath).getFile();
        byte[] byteArray = Files.readAllBytes(resource.toPath());
        return new String(byteArray);
    }

	@Test
	void contextLoaded() {
	}

	@Test
	void postCreateMessage() throws Exception {
		mockMvc.perform(post("/dbz")
            	.contentType(MediaType.APPLICATION_JSON)
            	.content(readFromFileToString("create.json")))
            .andExpect(status().isOk())
            .andReturn();		
	}

	@Test
	void postUpdateMessage() throws Exception {
		mockMvc.perform(post("/dbz")
            	.contentType(MediaType.APPLICATION_JSON)
            	.content(readFromFileToString("update.json")))
            .andExpect(status().isOk())
            .andReturn();		
	}

}
