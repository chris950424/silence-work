package com.silence.admin.infrastructure.sentinel.config;

import com.alibaba.csp.sentinel.init.InitFunc;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * FlowRuleInitFunc
 *
 * @author leo
 * @version 1.1.0
 * @date 2022/4/21
 */
//@Service
public class FlowRuleInitFunc implements InitFunc {
    @Override
    public void init() {
        List<FlowRule> rules = new ArrayList<>();
        FlowRule rule = new FlowRule();
        rule.setCount(1);
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rule.setResource("categoryList");
        rule.setLimitApp("default");
        rules.add(rule);
        FlowRuleManager.loadRules(rules);
    }
}
