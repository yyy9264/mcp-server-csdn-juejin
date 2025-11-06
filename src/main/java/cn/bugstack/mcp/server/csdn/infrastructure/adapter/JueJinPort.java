package cn.bugstack.mcp.server.csdn.infrastructure.adapter;

import cn.bugstack.mcp.server.csdn.domain.adapter.IJueJinPort;
import cn.bugstack.mcp.server.csdn.domain.model.ArticleFunctionRequest;
import cn.bugstack.mcp.server.csdn.domain.model.ArticleFunctionResponse;
import cn.bugstack.mcp.server.csdn.infrastructure.gateway.IJueJinService;
import cn.bugstack.mcp.server.csdn.infrastructure.gateway.dto.PublishRequest;
import cn.bugstack.mcp.server.csdn.infrastructure.gateway.dto.PublishResponse;
import cn.bugstack.mcp.server.csdn.type.properties.JueJinApiProperties;
import cn.bugstack.mcp.server.csdn.type.utils.MarkdownConverter;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Response;

import java.io.IOException;
import java.util.Collections;

@Service
public class JueJinPort implements IJueJinPort {

    @Resource
    private IJueJinService jueJinService;
    @Autowired
    private JueJinApiProperties jueJinApiProperties;

    @Override
    public ArticleFunctionResponse publish(ArticleFunctionRequest request) throws IOException {
        // 1. 组装参数
        cn.bugstack.mcp.server.csdn.infrastructure.gateway.dto.ArticleRequest articleRequest = cn.bugstack.mcp.server.csdn.infrastructure.gateway.dto.ArticleRequest.builder()
                .categoryId(jueJinApiProperties.getDefaultCategoryId())
                .tagIds(Collections.singletonList(jueJinApiProperties.getDefaultTagId()))
                .linkUrl("")
                .coverImage("")
                .title(request.getTitle())
                .briefContent(generateBriefContent(request.getBriefContent()))
                .editType(10)
                .htmlContent("deprecated")
                .markContent(request.getContent())
                .themeIds(Collections.emptyList())
                .pics(Collections.emptyList())
                .build();

        // 1. 从配置中获取 Cookie
        String cookie = jueJinApiProperties.getCookie();

        // 2. 创建草稿
        Response<cn.bugstack.mcp.server.csdn.infrastructure.gateway.dto.ArticleResponse> articleResponse = jueJinService.createArticle(cookie, articleRequest).execute();
        cn.bugstack.mcp.server.csdn.infrastructure.gateway.dto.ArticleResponse articleResponseData = articleResponse.body();
        if (!articleResponse.isSuccessful() || null == articleResponseData || 0 != articleResponseData.getErrNo()) {
            throw new RuntimeException("创建草稿失败，错误码：" + articleResponseData.getErrNo() + "，错误信息：" + articleResponseData.getErrMsg());
        }

        // 3. 计算字数统计
        int originWordCount = calculateWordCount(request.getContent());
        int encryptedWordCount = originWordCount + 1077868; // 简单处理，实际可能需要根据平台算法调整

        // 3. 发布文章
        PublishRequest publishRequest = PublishRequest.builder()
                .draftId(articleResponseData.getData().getId())
                .syncToOrg(false)
                .columnIds(Collections.emptyList())
                .themeIds(Collections.emptyList())
                .encryptedWordCount(encryptedWordCount)
                .originWordCount(originWordCount)
                .build();

        Response<PublishResponse> publishResponse = jueJinService.publishArticle(cookie, publishRequest).execute();
        PublishResponse publishResponseData = publishResponse.body();
        if (!publishResponse.isSuccessful() || null == publishResponseData || 0 != publishResponseData.getErrNo()){
            throw new RuntimeException("发布草稿失败，错误码：" + publishResponseData.getErrNo() + "，错误信息：" + publishResponseData.getErrMsg());
        }

        return null;
    }

    private String generateBriefContent(String content) {
        //少于50字，则补全50字
        if(content.length() < 50){
            return content + ".".repeat(50 - content.length());
        }
        //少于100字，则截取100字
        if(content.length() <= 100){
            return content;
        }
        return content.substring(0, 100);
    }

    private int calculateWordCount(String content) {
        if(content == null || content.isEmpty()){
            return 0;
        }
        // 简单处理，实际可能需要根据平台算法调整
        return content.replaceAll("\\s+", "").length();
    }

}