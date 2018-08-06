import com.unicom.ApplicationEngine;
import com.unicom.biz.rule.IRuleBiz;
import com.unicom.model.UserModel;
import com.unicom.pojo.rule.AbstractBaseRule;
import com.unicom.pojo.rule.RuleResult;
import com.unicom.valiedate.UserValidate;
import java.util.List;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @author
 * @create 2018-05-29 15:39
 **/



@RunWith(value = SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ApplicationEngine.class)
@WebAppConfiguration
public class Test {


  @Autowired
  private IRuleBiz ruleBiz;


  @org.junit.Test
  public void testtest() {
    List l = ruleBiz.fetchAll();
    System.out.println("***************" + l.size() + "***************");
    testValidate(new UserModel("1111"),l);
  }



  private void testValidate(UserModel userModel,List<AbstractBaseRule> ruleList)
  {
    ruleList.stream().forEach((userRule)-> {
          UserValidate userValidate = new UserValidate();
          RuleResult result = userValidate.validate(userRule, userModel);
          System.out.println(">>>>>"+result.isMatch()+"<<<<<");
//          System.out.println(">>>>>"+result.getMatchValue()+"<<<<<");
          System.out.println(">>>>>"+result.getUnMatchValue()+"<<<<<");
        }
    );
  }



}
