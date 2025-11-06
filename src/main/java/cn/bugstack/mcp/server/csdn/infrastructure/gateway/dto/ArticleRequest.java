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
public class ArticleRequest {

    @JsonProperty("category_id")
    private String categoryId;
    @JsonProperty("tag_ids")
    private List<String> tagIds;
    @JsonProperty("link_url")
    private String linkUrl;
    @JsonProperty("cover_image")
    private String coverImage;
    private String title;
    @JsonProperty("brief_content")
    private String briefContent;
    @JsonProperty("edit_type")
    private int editType;
    @JsonProperty("html_content")
    private String htmlContent;
    @JsonProperty("mark_content")
    private String markContent;
    @JsonProperty("theme_ids")
    private List<String> themeIds;
    private List<String> pics;

}