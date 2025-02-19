package id.ac.ui.cs.advprog.eshop.controller;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
@WebMvcTest(HomePageController.class)
class HomePageControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @BeforeEach
    void setUp() {
    }
    @Test
    void testHomePage() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(
                        get("/"))
                .andReturn().getResponse();
        assert (response.getStatus() == 200);
    }
}