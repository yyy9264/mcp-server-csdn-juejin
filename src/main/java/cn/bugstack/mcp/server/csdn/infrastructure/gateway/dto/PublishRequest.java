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
public class PublishRequest {

    @JsonProperty("draft_id")
    private String draftId;
    @JsonProperty("sync_to_org")
    private boolean syncToOrg;
    @JsonProperty("column_ids")
    private List<String> columnIds;
    @JsonProperty("theme_ids")
    private List<String> themeIds;
    @JsonProperty("encrypted_word_count")
    private int encryptedWordCount;
    @JsonProperty("origin_word_count")
    private int originWordCount;

}