package com.unicom.valiedate;

import com.google.common.base.Optional;
import com.unicom.pojo.condition.AbstractCondition;
import com.unicom.pojo.condition.index.IIndex;
import com.unicom.constant.LogicSymbol;
import com.unicom.model.BaseModel;
import com.unicom.pojo.rule.AbstractBaseRule;
import com.unicom.pojo.rule.IRule;
import java.util.List;

/**
 * @author
 * @create 2018-05-29 16:43
 **/
public abstract class AbstractValidate implements IValidate<IRule, BaseModel> {

  @Override
  public boolean validate(IRule iRule, BaseModel baseModel) throws NullPointerException {

    List<AbstractCondition> userCondition = iRule.getConditions();

    com.google.common.base.Preconditions.checkNotNull(userCondition);

    boolean flag = false;


    if (Optional.fromNullable(userCondition).isPresent() && userCondition.size() > 0) {




      flag = userCondition.stream().anyMatch(condition -> {

        if (condition.getLogicSymbol() == LogicSymbol.ALL) {

          List<IIndex> iIndexList = condition.getIndexs();

          boolean _flag  = false;
          if (Optional.fromNullable(iIndexList).isPresent() && iIndexList.size() > 0 ) {
            _flag = iIndexList.stream().allMatch(index -> {
              try {
                return index.expression(baseModel);
              } catch (NoSuchFieldException e) {
                e.printStackTrace();
              } catch (IllegalAccessException e) {
                e.printStackTrace();
              }
              return false;

            });

          }
          return _flag;

        } else {

          List<IIndex> iIndexList = condition.getIndexs();

          boolean _flag  = false;
          if (Optional.fromNullable(iIndexList).isPresent() && iIndexList.size() > 0 ) {

            _flag = iIndexList.stream().anyMatch(index -> {
                  try {
                    return index.expression(baseModel);
                  } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                  } catch (IllegalAccessException e) {
                    e.printStackTrace();
                  }
                  return false;
                }
            );

          }
          return _flag;
        }
      });
    }

    return flag;
  }

}
