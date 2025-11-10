package cn.bugstack.mcp.server.csdn;

import cn.bugstack.mcp.server.csdn.domain.service.JueJinArticleService;
import cn.bugstack.mcp.server.csdn.infrastructure.gateway.IJueJinService;
import cn.bugstack.mcp.server.csdn.type.properties.JueJinApiProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import jakarta.annotation.Resource;
import okhttp3.OkHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
@EnableConfigurationProperties(JueJinApiProperties.class)
public class McpServerApplication implements CommandLineRunner { // 在应用启动后立即执行一次启动检查逻辑（校验并输出掘金 cookie）。
    @Resource
    private JueJinApiProperties jueJinApiProperties;
        private static final Logger log = LoggerFactory.getLogger(McpServerApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(McpServerApplication.class, args);
    }

    @Bean
    public OkHttpClient okHttpClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();
    }

    @Bean
    public IJueJinService jueJinService(OkHttpClient okHttpClient) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);

        return new Retrofit.Builder()
                .baseUrl("https://api.juejin.cn/")
                .client(okHttpClient)
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .build()
                .create(IJueJinService.class);
    }

    @Bean
    public ToolCallbackProvider jueJinTools(JueJinArticleService jueJinArticleService) {
        return MethodToolCallbackProvider.builder().toolObjects(jueJinArticleService).build();
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("check juejin cookie ...");
        if (jueJinApiProperties.getCookie() == null || jueJinApiProperties.getCookie().isEmpty()) {
            log.warn("juejin cookie key is null or empty, please set it in application.yml");
        } else {
            String cookie = jueJinApiProperties.getCookie();
            String maskedCookie = cookie.length() > 6 ? cookie.substring(0, 6) + "..." : "***";
            log.info("juejin cookie key is (masked): {}", maskedCookie);
        }
    }


}
