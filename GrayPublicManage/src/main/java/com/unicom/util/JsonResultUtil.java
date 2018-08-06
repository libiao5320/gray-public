package com.unicom.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import java.util.Collection;

/**
 * @author
 * @create 2018-05-08 16:20
 **/
public class JsonResultUtil {


  private static final String _success_code_ = "0000";
  private static final String _failed_code_ = "9999";

  private static final String _success_msg_ = "success";
  private static final String _failed_msg_ = "failed";


  public static JSONObject failed(Object value) {

    ResultInfo resultInfo = new ResultInfo(_failed_code_,
        _failed_msg_, value);
    return JSON.parseObject(JSON.toJSONString(resultInfo));

  }


  public static JSONObject failed(String msg, Object value) {

    ResultInfo resultInfo = new ResultInfo(_failed_code_,
        Optional.fromNullable(msg).or(_failed_msg_), value);
    return JSON.parseObject(JSON.toJSONString(resultInfo));

  }

  public static JSONObject success(String msg, Object value) {

    ResultInfo resultInfo = new ResultInfo(_success_code_,
        Optional.fromNullable(msg).or(_success_msg_), value);

    return JSON.parseObject(JSON.toJSONString(resultInfo));

  }

  public static JSONObject success(Object value) {

    ResultInfo resultInfo = new ResultInfo(_success_code_,
        _success_msg_, value);

    return JSON.parseObject(JSON.toJSONString(resultInfo));

  }


  public static JSONObject toGridJson(int totalCount, Object obj) {
    // 如果数据集对象为null做个特殊处理
    if (null == obj) {
      JSONObject jsonResult = new JSONObject();
      jsonResult.put("total", totalCount);
      jsonResult.put("rows", new JSONArray());
      return jsonResult;
    }

    if (!Collection.class.isAssignableFrom(obj.getClass())) {
      JSONObject jsonResult = new JSONObject();
      jsonResult.put("total", totalCount);
      jsonResult.put("rows", new JSONArray());
      return jsonResult;
    }

    JSONArray jsonArray = JSON.parseArray(JSON.toJSONString(obj));
    JSONObject jsonResult = new JSONObject();
    jsonResult.put("total", totalCount);
    jsonResult.put("rows", jsonArray);

    return jsonResult;

  }
}
