package FanFam.api.model.resumo;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class NullKeySerializer extends JsonSerializer<Object> {
    @Override
    public void serialize(Object key, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName(key == null ? "Null" : key.toString());
    }
}
