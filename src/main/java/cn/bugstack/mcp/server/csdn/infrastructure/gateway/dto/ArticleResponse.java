package cn.bugstack.mcp.server.csdn.infrastructure.gateway.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleResponse {

    @JsonProperty("err_no")
    private int errNo;
    @JsonProperty("err_msg")
    private String errMsg;
    @JsonProperty("data")
    private Data data;

    @lombok.Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Data {
        private String id;
        @JsonProperty("article_id")
        private String articleId;
        @JsonProperty("user_id")
        private String userId;
        @JsonProperty("category_id")
        private String categoryId;
        @JsonProperty("tag_ids")
        private List<Long> tagIds;
        @JsonProperty("link_url")
        private String linkUrl;
        @JsonProperty("cover_image")
        private String coverImage;
        @JsonProperty("is_gfw")
        private int isGfw;
        private String title;
        @JsonProperty("brief_content")
        private String briefContent;
        @JsonProperty("is_english")
        private int isEnglish;
        @JsonProperty("is_original")
        private int isOriginal;
        @JsonProperty("edit_type")
        private int editType;
        @JsonProperty("html_content")
        private String htmlContent;
        @JsonProperty("mark_content")
        private String markContent;
        private String ctime;
        private String mtime;
        private int status;
        @JsonProperty("original_type")
        private int originalType;
        @JsonProperty("theme_ids")
        private List<String> themeIds;
    }

}