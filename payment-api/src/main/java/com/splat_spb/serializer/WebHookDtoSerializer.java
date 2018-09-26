package com.splat_spb.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.spb_splat.dto.WebHookEventDto;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

/**
 * Default comment.
 **/
@JsonComponent
public class WebHookDtoSerializer extends JsonSerializer<WebHookEventDto> {
    @Override
    public void serialize(WebHookEventDto value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeStringField("id", value.getId());
        gen.writeStringField("currency", value.getCurrency());
        gen.writeStringField("amount", value.getAmount());
        gen.writeStringField("external_id", value.getExternalId());
        gen.writeStringField("status", value.getStatus().toString().toLowerCase());
        gen.writeStringField("sha2sig", value.getSignature());
        gen.writeEndObject();
    }
}
