package cn.bugstack.mcp.server.csdn.domain.service;

import cn.bugstack.mcp.server.csdn.domain.adapter.IJueJinPort;
import cn.bugstack.mcp.server.csdn.domain.model.ArticleFunctionRequest;
import cn.bugstack.mcp.server.csdn.domain.model.ArticleFunctionResponse;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
public class JueJinArticleService {

    @Resource
    private IJueJinPort port;
    @Tool(description = "发布文章到JueJin")
    public ArticleFunctionResponse saveArticle(ArticleFunctionRequest request) throws IOException {
        log.info("JueJin发帖，标题:{} 内容:{} 标签:{}", request.getTitle(), request.getContent(), request.getTags());
        return port.publish(request);
    }

}

