package cn.bugstack.mcp.server.csdn.infrastructure.gateway;

import cn.bugstack.mcp.server.csdn.infrastructure.gateway.dto.ArticleRequest;
import cn.bugstack.mcp.server.csdn.infrastructure.gateway.dto.ArticleResponse;
import cn.bugstack.mcp.server.csdn.infrastructure.gateway.dto.PublishRequest;
import cn.bugstack.mcp.server.csdn.infrastructure.gateway.dto.PublishResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface IJueJinService {

    @Headers({
            "Content-Type: application/json",
            "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36",
            "Accept: */*",
            "Host: api.juejin.cn",
            "Connection: keep-alive"
    })
    @POST("content_api/v1/article_draft/create")
    Call<ArticleResponse> createArticle(@Header("Cookie") String cookie, @Body ArticleRequest request);

    @Headers({
            "Content-Type: application/json",
            "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36",
            "Accept: */*",
            "Host: api.juejin.cn",
            "Connection: keep-alive"
    })
    @POST("content_api/v1/article/publish")
    Call<PublishResponse> publishArticle(@Header("Cookie") String cookie, @Body PublishRequest request);


}