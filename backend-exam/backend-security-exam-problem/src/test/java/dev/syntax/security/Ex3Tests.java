package dev.syntax.security;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
// 문제 3. 각 User별 보유한 Role에 따른 전체 사용자 목록 조회 기능 테스트 시나리오
class Ex3Tests {

	@Autowired
	private MockMvc mvc;

	@Test
	@WithMockUser(roles = "TEMPORARY")
	@DisplayName("TEMPORARY 역할을 가진 사용자가 전체 사용자 목록 조회 페이지(GET: /users)에 접근할 경우 403 Forbidden이 응답된다.")
	public void test1() throws Exception {
		mvc.perform(get("/users"))
				.andExpect(status().isForbidden());
	}

	@Test
	@WithMockUser(roles = "USER")
	@DisplayName("USER 역할을 가진 사용자가 전체 사용자 목록 조회 페이지(GET: /users)에 접근할 경우 403 Forbidden이 응답된다.")
	public void test2() throws Exception {
		mvc.perform(get("/users"))
				.andExpect(status().isForbidden());
	}

	@Test
	@WithMockUser(roles = "ADMIN")
	@DisplayName("ADMIN 역할을 가진 사용자가 전체 사용자 목록 조회 페이지(GET: /users)에 접근할 경우 200 OK가 응답된다.")
	public void test3() throws Exception {
		mvc.perform(get("/users"))
				.andExpect(status().isOk());
	}

}
