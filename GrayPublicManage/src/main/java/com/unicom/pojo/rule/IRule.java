package com.unicom.pojo.rule;

import com.unicom.pojo.condition.ICondition;
import com.unicom.pojo.condition.UserCondition;
import java.util.List;

public interface IRule<T extends ICondition> {

      public List<T>  getConditions ();
}
