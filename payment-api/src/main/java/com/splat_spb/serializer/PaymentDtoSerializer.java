package com.splat_spb.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.splat_spb.dto.payment.PaymentDto;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

/**
 * Default comment.
 **/
@JsonComponent
public class PaymentDtoSerializer extends JsonSerializer<PaymentDto> {
    @Override
    public void serialize(PaymentDto value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeStringField("id", value.getId());
        gen.writeStringField("create_time", value.getCreateTime().toString());
        gen.writeStringField("state", value.getState().toString().toLowerCase());
        gen.writeEndObject();
    }
}
