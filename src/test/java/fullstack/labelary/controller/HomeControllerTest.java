package fullstack.labelary.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@RunWith(SpringRunner.class)
@WebMvcTest(controllers = HomeController.class)
public class HomeControllerTest {

    // 웹 API 테스트할 때 사용
    // 스프링 MVC 테스트의 시작점
    @Autowired
    private MockMvc mvc;

    @Test
    public void returnHelloCheck() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("home"));
    }

    @Test
    public void returnHelloDtoCheck() throws Exception {
        String name = "hello";
        int amount = 1000;

        // jsonPath : JSON 응답값을 필드별로 검증할 수 있음
        // $를 기준으로 필드명을 명시
        // param : API 테스트할 때 사용될 요청 파라미터 설정
        // string 만 허용!
        mvc.perform(get("/hello/dto")
                .param("name", name)
                .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));
    }
}