package cn.bugstack.mcp.server.csdn.test;

import cn.bugstack.mcp.server.csdn.infrastructure.gateway.IJueJinService;
import cn.bugstack.mcp.server.csdn.infrastructure.gateway.dto.ArticleRequest;
import cn.bugstack.mcp.server.csdn.infrastructure.gateway.dto.ArticleResponse;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import retrofit2.Call;
import retrofit2.Response;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Collections;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiTest {

    @Resource
    private IJueJinService jueJinService;

    @Test
    public void test_createArticle() throws IOException {
        ArticleRequest request = ArticleRequest.builder()
                .categoryId("6809637769959178254")
                .tagIds(Collections.singletonList("6809640408797167623"))
                .linkUrl("")
                .coverImage("")
                .title("test")
                .briefContent("1111111111111111111111111111111111111111111111111111111111111111111111111111111111111")
                .editType(10)
                .htmlContent("deprecated")
                .markContent("hello!")
                .themeIds(Collections.emptyList())
                .pics(Collections.emptyList())
                .build();

        String cookie = "__tea_cookie_tokens_2608=...your_cookie..."; // Replace with your actual cookie

        Call<ArticleResponse> call = jueJinService.createArticle(cookie, request);
        Response<ArticleResponse> response = call.execute();

        log.info("Response: {}", JSON.toJSONString(response.body()));
    }

}
