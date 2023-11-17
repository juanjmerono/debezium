package es.um.atica.debezium.demo;

import static org.junit.Assert.assertEquals;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class DemoApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoaded() {
	}

	@Test
	void indexReady() throws Exception {
		mockMvc.perform(get("/"))
			.andExpect(status().isOk())
			.andExpect(content().string(containsString("Debezium")));
	}

	@Test
	void usersSourceReady() throws Exception {
		mockMvc.perform(get("/usersource"))
			.andExpect(status().isOk())
			.andExpect(content().string(containsString("[]")));
	}

	@Test
	void usersTargetReady() throws Exception {
		mockMvc.perform(get("/usertarget"))
			.andExpect(status().isOk())
			.andExpect(content().string(containsString("[]")));
	}

	@Test
	void usersSourceCreate() throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("username", "Spiderman");
		mockMvc.perform(post("/usersource")
							.contentType(MediaType.APPLICATION_JSON)
							.content(jsonObject.toString()))
			.andExpect(status().isCreated())
			.andExpect(content().string(containsString("Spiderman")));
	}

	@Test
	void usersSourceChanged() throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("username", "Spiderman");
		mockMvc.perform(put("/usersource/1")
							.contentType(MediaType.APPLICATION_JSON)
							.content(jsonObject.toString()))
			.andExpect(status().isNotFound());
	}

}
