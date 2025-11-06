package cn.bugstack.mcp.server.csdn.domain.model;

import cn.bugstack.mcp.server.csdn.type.utils.MarkdownConverter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArticleFunctionRequest {

    @JsonProperty(required = true, value = "title")
    @JsonPropertyDescription("文章标题")
    private String title;

    @JsonProperty(required = true, value = "content")
    @JsonPropertyDescription("文章内容")
    private String content;

    @JsonProperty(value = "categoryId")
    @JsonPropertyDescription("分类ID，默认为后端分类")
    private String categoryId;

    @JsonProperty(required = true, value = "tags")
    @JsonPropertyDescription("文章标签，英文逗号隔开")
    private String tags;

    @JsonProperty(required = true, value = "briefContent")
    @JsonPropertyDescription("文章简介")
    private String briefContent;

    public String getContent() {
        return MarkdownConverter.convertToHtml(content);
    }

}
