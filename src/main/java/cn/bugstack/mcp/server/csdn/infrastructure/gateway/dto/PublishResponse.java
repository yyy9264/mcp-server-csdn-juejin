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
public class PublishResponse {

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
        @JsonProperty("article_id")
        private String articleId;
        @JsonProperty("user_id")
        private String userId;
        @JsonProperty("category_id")
        private String categoryId;
        @JsonProperty("tag_ids")
        private List<Long> tagIds;
        @JsonProperty("visible_level")
        private int visibleLevel;
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
        @JsonProperty("user_index")
        private int userIndex;
        @JsonProperty("original_type")
        private int originalType;
        @JsonProperty("original_author")
        private String originalAuthor;
        private String content;
        private String ctime;
        private String mtime;
        private String rtime;
        private int status;
        @JsonProperty("verify_status")
        private int verifyStatus;
        @JsonProperty("audit_status")
        private int auditStatus;
        @JsonProperty("mark_content")
        private String markContent;
        @JsonProperty("org_id")
        private String orgId;
        @JsonProperty("homepage_top_time")
        private int homepageTopTime;
        @JsonProperty("homepage_top_status")
        private int homepageTopStatus;
    }

}