package com.unicom.interrupter;


import com.unicom.constant.Constants;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by libia on 2016/1/19.
 */
public class AuthInterrupter implements HandlerInterceptor {

  Logger logger = LoggerFactory.getLogger(this.getClass());

  //  @Autowired
//  ManagerService managerService;
  private final String NO_PRIVILEGE = "/noprivilege";
  private final String NO_SESSION = "/nosession";

  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {

    boolean flag = true;
    HttpSession session = request.getSession();
//        String requestUrl = request.getRequestURI() ;
//
//        String isMenu = request.getParameter("isMenu");
//        String menuId = request.getParameter("menuId");



     if (!hasLogin())
     {
       response.sendRedirect(NO_SESSION);
     }
//    Object userInfo = session.getAttribute(Constants.SESSION_USERINFO);
//
//    if (userInfo == null) {
//      response.sendRedirect(NO_SESSION);
//    }
//        if(null != manager) {
//
//            List<Role> roles =  manager.getRole();
//                for (int i = 0; i < roles.size(); i++) {
//                    Role role = roles.get(i);
//
//                    if (null != isMenu && "1".equals(isMenu))
//                    {
//                        CopyOnWriteArraySet temMenu = (CopyOnWriteArraySet) RoleManageContainer.CACHE_ROLE_PRIVILEGEMENU.get(role.getRoleId());
//
//                        if (temMenu.contains(Long.valueOf(menuId))) {
//                            flag = true;
//                            break;
//                        }
//                    }
//                    else
//                    {
//                        flag  = true;
//                        break;
//                    }
//
//                    /**
//                     *   功能级别权限控制 ，放开的话需要配置 功能的URL 放到 权限表中去 ，暂时不控制功能级别的权限.
//                     */
////                    else {
////                        CopyOnWriteArraySet temPrivilege = (CopyOnWriteArraySet) RoleManageContainer.CACHE_ROLE_PRIVILEGEURL.get(role.getRoleId());
////
////                        if (temPrivilege.contains(requestUrl)) {
////                            flag = true;
////                            break;
////                        }
////                    }
//                }
//        }
//        else
//        {
//            response.sendRedirect(NO_SESSION);
//        }
//
    if (!flag) {
//        	 response.sendRedirect();

      request.getRequestDispatcher(NO_PRIVILEGE).forward(request, response);
    }

    return flag;
  }

  public void postHandle(HttpServletRequest request,
      HttpServletResponse response, Object handler,
      ModelAndView modelAndView) throws Exception {
    // TODO Auto-generated method stub

  }

  public void afterCompletion(HttpServletRequest request,
      HttpServletResponse response, Object handler, Exception ex)
      throws Exception {
    // TODO Auto-generated method stub

  }

  private boolean hasLogin()
  {

      boolean flag = true;
      return true;
  }

}
