package cpm.splat_spb.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.spb_splat.dto.State;
import com.spb_splat.dto.WebHookEventDto;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

/**
 * Default comment.
 **/
@JsonComponent
public class WebHookDtoDeserializer extends JsonDeserializer<WebHookEventDto> {

    @Override
    public WebHookEventDto deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException {
        JsonNode node = parser.getCodec().readTree(parser);
        WebHookEventDto webHookEventDto = new WebHookEventDto();
        webHookEventDto.setId(node.get("id").asText());
        webHookEventDto.setAmount(node.get("amount").asText());
        webHookEventDto.setCurrency(node.get("currency").asText());
        webHookEventDto.setStatus(State.valueOf(node.get("status").asText().toUpperCase()));
        webHookEventDto.setSignature(node.get("sha2sig").asText());
        webHookEventDto.setExternalId(node.get("external_id").asText());
        return webHookEventDto;
    }
}
