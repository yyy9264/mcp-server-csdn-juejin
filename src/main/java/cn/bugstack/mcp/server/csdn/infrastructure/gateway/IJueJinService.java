package cn.bugstack.mcp.server.csdn.infrastructure.gateway;

import cn.bugstack.mcp.server.csdn.infrastructure.gateway.dto.ArticleRequest;
import cn.bugstack.mcp.server.csdn.infrastructure.gateway.dto.ArticleResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface IJueJinService {

    @POST("content_api/v1/article_draft/create")
    Call<ArticleResponse> createArticle(@Header("Cookie") String cookie, @Body ArticleRequest request);

}