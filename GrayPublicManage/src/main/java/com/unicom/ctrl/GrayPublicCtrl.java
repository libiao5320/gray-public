package com.unicom.ctrl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.unicom.annotion.FieldDesc;
import com.unicom.annotion.ModelDesc;
import com.unicom.biz.condition.IConditionBiz;
import com.unicom.biz.index.IIndexBiz;
import com.unicom.biz.route.IRouteBiz;
import com.unicom.biz.rule.impl.RuleBizImpl;
import com.unicom.command.ICommand;
import com.unicom.command.ServiceCommandInvoker;
import com.unicom.command.rx.BaseAbstractCommand;
import com.unicom.command.rx.execute.IExecuteCommand;
import com.unicom.command.rx.fallback.BaseFallbackCommand;
import com.unicom.command.rx.fallback.IFallBack;
import com.unicom.pojo.condition.AbstractCondition;
import com.unicom.pojo.condition.index.AbstractIndex;

import com.unicom.pojo.rule.AbstractBaseRule;

import com.unicom.util.JsonResultUtil;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author
 * @create 2018-05-08 11:34
 **/

@Controller
public class GrayPublicCtrl {


  private Logger logger = LoggerFactory.getLogger(this.getClass());


  @Autowired
  private RuleBizImpl ruleBiz;
  @Autowired
  private IRouteBiz routeBiz;
  @Autowired
  private IConditionBiz conditionBiz;
  @Autowired
  private IIndexBiz indexBiz;


  @RequestMapping("/graymanage")
  public String gray() {

    return "graymanage";
  }


  @PostMapping(value = "/fetchRule", produces = "application/json")
  @ResponseBody
  public JSONObject fetchRule(@RequestParam("rows") String rows,
      @RequestParam("page") String page) {

    List datas = new ServiceCommandInvoker<List>().execute(
        new BaseAbstractCommand<List>(() -> {
          List l = ruleBiz.fetchAll();
          return l;
        }, () -> {
          return Collections.emptyList();
        }));

    return JsonResultUtil.toGridJson(datas.size(), datas);


  }

  @GetMapping(value = "/getGrayStrategy", produces = "application/json")
  @ResponseBody
  public JSONObject getGrayStrategy() {

    try {
      return JsonResultUtil.success(loadGrayStrategy());
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    } catch (ClassNotFoundException e) {
      return null;
    }
  }

  @GetMapping(value = "/getGrayStrategyField", produces = "application/json")
  @ResponseBody
  public JSONObject getGrayStrategyField(@RequestParam("strategy") String strategy) {

    try {
      return JsonResultUtil.success(findGrayStrategyField(strategy));
    } catch (ClassNotFoundException e) {
      return null;
    } catch (IOException e) {
      return null;
    }
  }

  @PostMapping(value = "/addRule", consumes = "application/json", produces = "application/json")
  @ResponseBody
  public JSONObject addRule(@RequestBody AbstractBaseRule rule) {
    return Optional.fromNullable(ruleBiz.add(rule)).get() ? JsonResultUtil.success("success")
        : JsonResultUtil.failed("faield");
  }

  @PostMapping(value = "/delRule", produces = "application/json", consumes = "application/json")
  @ResponseBody
  public JSONObject delRule(@RequestBody String[] delIds) {
    return Optional.fromNullable(ruleBiz.del(delIds)).get() ? JsonResultUtil.success("success")
        : JsonResultUtil.failed("faield");
  }

  @PostMapping(value = "/updateRule", consumes = "application/json", produces = "application/json")
  @ResponseBody
  public JSONObject updateRule(@RequestBody AbstractBaseRule rule) {
    return Optional.fromNullable(ruleBiz.update(rule)).get() ? JsonResultUtil.success("success")
        : JsonResultUtil.failed("faield");
  }

  @PostMapping(value = "/addCondition", consumes = "application/json", produces = "application/json")
  @ResponseBody
  public JSONObject addCondition(@RequestBody AbstractCondition condition) throws Exception {



    return Optional.fromNullable(conditionBiz.add(condition)).get() ? JsonResultUtil
        .success("success")
        : JsonResultUtil.failed("faield");
  }

  @PostMapping(value = "/delCondition", produces = "application/json", consumes = "application/json")
  @ResponseBody
  public JSONObject delCondition(@RequestBody String[] delIds) throws Exception {
    return Optional.fromNullable(conditionBiz.del(delIds)).get() ? JsonResultUtil
        .success("success")
        : JsonResultUtil.failed("faield");
  }

  @PostMapping(value = "/updateCondition", consumes = "application/json", produces = "application/json")
  @ResponseBody
  public JSONObject updateCondition(@RequestBody AbstractCondition condition) throws Exception {
    return Optional.fromNullable(conditionBiz.update(condition)).get() ? JsonResultUtil
        .success("success")
        : JsonResultUtil.failed("faield");
  }

  @PostMapping(value = "/addIndex", consumes = "application/json", produces = "application/json")
  @ResponseBody
  public JSONObject addIndex(@RequestBody AbstractIndex index) throws Exception {
    return Optional.fromNullable(indexBiz.add(index)).get() ? JsonResultUtil.success("success")
        : JsonResultUtil.failed("faield");
  }

  @PostMapping(value = "/delIndex", produces = "application/json", consumes = "application/json")
  @ResponseBody
  public JSONObject delIndex(@RequestBody String[] delIds) throws Exception {
    return Optional.fromNullable(indexBiz.del(delIds)).get() ? JsonResultUtil.success("success")
        : JsonResultUtil.failed("faield");
  }

  @PostMapping(value = "/updateIndex", consumes = "application/json", produces = "application/json")
  @ResponseBody
  public JSONObject updateIndex(@RequestBody AbstractIndex index) throws Exception {
    return Optional.fromNullable(indexBiz.update(index)).get() ? JsonResultUtil.success("success")
        : JsonResultUtil.failed("faield");
  }

  @PostMapping(value = "/fetchConditionByRuleId", produces = "application/json")
  @ResponseBody
  public JSONObject fetchConditionByRuleId(@RequestParam("rows") String rows,
      @RequestParam("page") String page, @RequestParam("ruleid") String ruleid) {

    List datas = new ServiceCommandInvoker<List>().execute(
        new BaseAbstractCommand<List>(() -> {
          List l = conditionBiz.fetchConditionByRuleId(ruleid);
          return l;
        }, () -> {
          return Collections.emptyList();
        }));

    return JsonResultUtil.toGridJson(datas.size(), datas);


  }

  @PostMapping(value = "/fetchCondition", produces = "application/json")
  @ResponseBody
  public JSONObject fetchCondition(@RequestParam("rows") String rows,
      @RequestParam("page") String page) {

    List datas = new ServiceCommandInvoker<List>().execute(
        new BaseAbstractCommand<List>(() -> {
          List l = conditionBiz.fetchAll();
          return l;
        }, () -> {
          return Collections.emptyList();
        }));

    return JsonResultUtil.toGridJson(datas.size(), datas);
  }

  @PostMapping(value = "/fetchIndexByCondtion", produces = "application/json")
  @ResponseBody
  public JSONObject fetchIndexByCondtion(@RequestParam("rows") String rows,
      @RequestParam("page") String page, @RequestParam("conditionId") String conditionId) {

    List datas = new ServiceCommandInvoker<List>().execute(
        new BaseAbstractCommand<List>(() -> {
          List l = indexBiz.fetchIndexByConditionId(conditionId);
          return l;
        }, () -> {
          return Collections.emptyList();
        }));

    return JsonResultUtil.toGridJson(datas.size(), datas);
  }

  @GetMapping(value = "/getRule", produces = "application/json")
  @ResponseBody
  public JSONObject getRoute(@RequestParam String ruleId) {
    Object ruleinfo = ruleBiz.get(ruleId);
    if (null != ruleinfo) {
      return JsonResultUtil.success(JSON.toJSON(ruleinfo));
    } else {
      return JsonResultUtil.failed(ruleinfo);
    }
  }

  @GetMapping(value = "/getRouteInfo", produces = "application/json")
  @ResponseBody
  public JSONObject getRouteInfo() {

    List l = Optional.fromNullable(routeBiz.fetchAll()).orNull();
    if (null != l) {
      return JsonResultUtil.success(JSON.toJSON(l));
    } else {
      return JsonResultUtil.failed(JSON.toJSON(l));
    }
  }

  private List loadGrayStrategy() throws IOException, ClassNotFoundException {

    List result = new ArrayList();
    InputStream inputStream = null;
    Properties properties = null;

    try {
      inputStream = new FileInputStream(
          Thread.currentThread().getContextClassLoader().getResource("").getPath()
              + "ModelDescrible.properties");

      String ss = null;
      properties = new Properties();
      properties.load(inputStream);
      String modelDescs = properties.getProperty("Model");

      Stream.of(modelDescs.split(",")).forEach((modelDesc) -> {
        Class clazz = null;
        try {
          clazz = Class.forName(modelDesc);
        } catch (ClassNotFoundException e) {
          e.printStackTrace();
        }
        ModelDesc strategy = (ModelDesc) clazz.getAnnotation(ModelDesc.class);
        result.add(JSON.parseObject(
            "{'strategyName':'" + strategy.value() + "','strategyValue':'" + clazz.getName()
                + "'}"));
      });

    } finally {
      if (null != inputStream) {
        inputStream.close();
      }
      if (null != properties) {
        properties = null;
      }
      inputStream = null;
    }
    return result;
  }

  private List findGrayStrategyField(String strateg)
      throws ClassNotFoundException, IOException {
    List result = new ArrayList();

    InputStream inputStream = null;
    Properties properties = null;

    String strategClazz = null;

    try {
      inputStream = new FileInputStream(
          Thread.currentThread().getContextClassLoader().getResource("").getPath()
              + "ModelDescrible.properties");

      String ss = null;
      properties = new Properties();
      properties.load(inputStream);
      String modelDescs = properties.getProperty("Model");
      strategClazz = Stream.of(modelDescs.split(",")).filter((modelDesc) -> {
        Class clazz = null;
        try {
          clazz = Class.forName(modelDesc);
        } catch (ClassNotFoundException e) {
          e.printStackTrace();
        }
        ModelDesc strategy = (ModelDesc) clazz.getAnnotation(ModelDesc.class);
        if (Objects.equal(strategy.value(), strateg)) {
          return true;
        }
        return false;
      }).findFirst().orElse("");

    } finally {
      if (null != inputStream) {
        inputStream.close();
      }
      if (null != properties) {
        properties = null;
      }
      inputStream = null;
    }
    if (!Objects.equal("", strategClazz)) {
      Class cls = Class.forName(strategClazz);

      Arrays.asList(cls.getDeclaredFields()).stream().forEach((_field) -> {
        String fieldName = _field.getName();

        FieldDesc fieldDesc = _field.getAnnotation(FieldDesc.class);

        result.add(JSON.parseObject(
            "{'fieldName':'" + fieldDesc.value() + "','fieldValue':'" + fieldName + "'}"));
      });
    }

    return result;
  }


}

