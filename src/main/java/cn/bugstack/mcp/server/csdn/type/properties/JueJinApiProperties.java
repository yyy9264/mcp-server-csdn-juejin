package cn.bugstack.mcp.server.csdn.type.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@ConfigurationProperties(prefix = "juejin.api")
public class JueJinApiProperties {
    /**
     * JueJin Cookie
     */
    private String cookie;

    /**
     * 默认分类ID
     */
    private String defaultCategoryId = "6809637769959178254";

    /**
     * 默认标签ID
     */
    private String defaultTagId = "6809640408797167623";

    /**
     * API基础URL
     */
    private String baseUrl = "https://api.juejin.cn/";

    /**
     * 连接超时时间（秒）
     */
    private int connectTimeout = 30;

    /**
     * 读取超时时间（秒）
     */
    private int readTimeout = 30;

    /**
     * 写入超时时间（秒）
     */
    private int writeTimeout = 30;
}
