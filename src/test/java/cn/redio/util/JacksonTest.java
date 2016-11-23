package cn.redio.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by XIAOYAO on 2016/10/24.
 * jackson转换null测试
 */
public class JacksonTest {

    @Test
    public void jacksonIsNullTest1() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        Map<String, Object> map = new HashMap<>();
        map.put("a", "b");
        map.put("c", null);

        String json = mapper.writeValueAsString(map);
        System.out.println(json);

        User user = new User();
        user.setId(1);
        System.out.println(mapper.writeValueAsString(user));

        Map<String, Object> params = new HashMap<>();
        User u = new User();
        u.setId(2);
        params.put("user", u);
        System.out.println(mapper.writeValueAsString(params));
    }

}
