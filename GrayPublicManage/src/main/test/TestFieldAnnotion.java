import com.unicom.annotion.FieldDesc;
import com.unicom.annotion.ModelDesc;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Properties;
import org.junit.Test;

/**
 * @author
 * @create 2018-06-20 11:08
 **/


public class TestFieldAnnotion {


  @Test
  public void testtest() throws IOException {

    InputStream inputStream = this.getClass().getResourceAsStream("ModelDescrible.properties");
//    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
    String ss = null;
    Properties properties =  new  Properties();
    properties.load(inputStream);
    String modelDesc = properties.getProperty("Model");
    System.out.println(modelDesc);
    try {
      Class clazz =  Class.forName(modelDesc);
      ModelDesc modelDesc111  = (ModelDesc) clazz.getAnnotation(ModelDesc.class);
      System.out.println(modelDesc111.value());

      Arrays.asList(clazz.getDeclaredFields()).stream().forEach((_field)->{

        String fieldName = _field.getName();
        System.out.println(" field name ");
        System.out.println("--------------------------->" + fieldName  + "<---------------------------");

        FieldDesc fieldDesc = _field.getAnnotation(FieldDesc.class);
        System.out.println(" field lable ");
        System.out.println("--------------------------->" + fieldDesc.value()  + "<---------------------------");

      });

    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }

  }

}
