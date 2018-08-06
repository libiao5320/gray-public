package com.unicom.ctrl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Optional;
import com.unicom.biz.route.IRouteBiz;
import com.unicom.command.ServiceCommandInvoker;
import com.unicom.command.rx.BaseAbstractCommand;
import com.unicom.command.rx.execute.IExecuteCommand;
import com.unicom.command.rx.fallback.IFallBack;
import com.unicom.pojo.route.AbstractRoute;
import com.unicom.util.JsonResultUtil;
import java.util.Collections;
import java.util.List;
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
public class RouteManageCtrl {

  @Autowired
  private IRouteBiz routeBiz;


  @RequestMapping("/routemanage")
  public String gray() {
    return "routemanage";
  }


  @PostMapping(value = "/fetchRoute", produces = "application/json")
  @ResponseBody
  public JSONObject fetchRoute(@RequestParam("rows") String rows,
      @RequestParam("page") String page) {

    IFallBack fallBack = new IFallBack<List>() {
      @Override
      public List fallback() {
        return Collections.emptyList();
      }
    };
    IExecuteCommand executeCommand = new IExecuteCommand() {
      @Override
      public Object execute() {
        List l = routeBiz.fetchAll();
        return l;
      }
    };

    BaseAbstractCommand abstractCommand = new BaseAbstractCommand<List>(executeCommand, fallBack);

    List datas = new ServiceCommandInvoker<List>().execute(abstractCommand);

    return JsonResultUtil.toGridJson(datas.size(), datas);


  }


  @GetMapping(value = "/getRoute", produces = "application/json")
  @ResponseBody
  public JSONObject getRoute(@RequestParam String routeId) {
    AbstractRoute abstractRoute = Optional.fromNullable(routeBiz.getRoute(routeId)).orNull();
    if (null != abstractRoute) {
      return JsonResultUtil.success(JSON.toJSON(abstractRoute));
    } else {
      return JsonResultUtil.failed(abstractRoute);
    }
  }

  @PostMapping(value = "/addRoute", produces = "application/json", consumes = "application/json")
  @ResponseBody
  public JSONObject addRoute(@RequestBody AbstractRoute route) {
    return Optional.fromNullable(routeBiz.add(route)).get() ? JsonResultUtil.success("success")
        : JsonResultUtil.failed("faield");
  }


  @PostMapping(value = "/updateRoute", produces = "application/json", consumes = "application/json")
  @ResponseBody
  public JSONObject updateRoute(@RequestBody AbstractRoute route) {
    return Optional.fromNullable(routeBiz.update(route)).get() ? JsonResultUtil.success("success")
        : JsonResultUtil.failed("faield");
  }


  @PostMapping(value = "/delRoute", produces = "application/json", consumes = "application/json")
  @ResponseBody
  public JSONObject delRoute(@RequestBody String[] delIds) {
    return Optional.fromNullable(routeBiz.del(delIds)).get() ? JsonResultUtil.success("success")
        : JsonResultUtil.failed("faield");
  }


}
