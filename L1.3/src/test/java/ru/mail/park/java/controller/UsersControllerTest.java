package ru.mail.park.java.controller;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import ru.mail.park.java.service.AccountServiceMapImpl;

@RunWith(SpringRunner.class)
@WebMvcTest
@Import(AccountServiceMapImpl.class)
public class UsersControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testGetDefaultUsers() throws Exception {
		mockMvc.perform(get("/api/user"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").isArray())
				.andExpect(jsonPath("$.length()", is(2)))
				.andExpect(jsonPath("$.[0].login").exists())
				.andExpect(jsonPath("$.[0].password").doesNotExist())
				.andExpect(jsonPath("$.[0].passwordHash").doesNotExist());
	}
	
	@Test
	public void testGetAdmin() throws Exception {
		mockMvc.perform(get("/api/user/admin"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.login", is("admin")))
				.andExpect(jsonPath("$.password").doesNotExist())
				.andExpect(jsonPath("$.passwordHash").doesNotExist());;
	}

	@Test
	public void testPostNewUser() throws Exception {
		mockMvc.perform(post("/api/user")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"login\":\"newuser\",\"password\":\"newuser\"}"))
				.andExpect(status().isOk())
				.andExpect(content().string("newuser"));
	}

}
