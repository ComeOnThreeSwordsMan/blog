package com.acfun.common.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * @author kwer
 * @Date 2018/5/7/007 10:49
 */
public class IntArr2StringArrSerializer extends JsonSerializer<int[]> {
    @Override
    public void serialize(int[] ints, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        String[] arr;
        if (ints == null) {
            arr = new String[0];
        } else {
            String[] strings = new String[ints.length];
            int i = 0;
            for (int t : ints) {
                strings[i++] = Integer.toString(t);
            }
            arr = strings;
        }
        jsonGenerator.writeObject(arr);
    }
}
