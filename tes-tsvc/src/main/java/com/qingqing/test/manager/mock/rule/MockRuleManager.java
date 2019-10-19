package com.qingqing.test.manager.mock.rule;

import com.qingqing.common.util.CollectionsUtil;
import com.qingqing.test.domain.mock.InterfaceType;
import com.qingqing.test.domain.mock.MockRule;
import com.qingqing.test.domain.mock.RuleType;
import com.qingqing.test.manager.ISyncable;
import com.qingqing.test.manager.mock.rule.impl.AmountRangeRuleHandler;
import com.qingqing.test.manager.mock.rule.impl.ValueMatchRuleHandler;
import com.qingqing.test.service.mock.MockRuleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhujianxing on 2018/6/12.
 */
@Component
public class MockRuleManager implements ISyncable {

    private static final Logger logger = LoggerFactory.getLogger(MockRuleManager.class);

    @Autowired
    private MockRuleService mockRuleService;

    private static Map<RuleType, IRuleHandler> ruleHandlerMapping;
    private Map<String, List<MockRule>> interfaceRuleListMapping;

    static{
        ruleHandlerMapping = new HashMap<>();
        ruleHandlerMapping.put(RuleType.AMOUNT_RANGE, new AmountRangeRuleHandler());
        ruleHandlerMapping.put(RuleType.VALUE_MATCH, new ValueMatchRuleHandler());
    }

    @PostConstruct
    public void sync(){
        List<MockRule> mockRuleList = mockRuleService.selectAll();

        Map<String, List<MockRule>> tmpInterfaceRuleListMapping = new HashMap<>(mockRuleList.size());
        for(MockRule mockRule : mockRuleList){
            String interfaceType = mockRule.getMockType();
            if(interfaceType.equals(InterfaceType.unknown)){
                continue;
            }

            List<MockRule> tmpMockRuleList = tmpInterfaceRuleListMapping.get(interfaceType);
            if(tmpMockRuleList == null){
                tmpMockRuleList = new ArrayList<>(20);
                tmpInterfaceRuleListMapping.put(interfaceType, tmpMockRuleList);
            }

            tmpMockRuleList.add(mockRule);
        }

        for(List<MockRule> ruleList : tmpInterfaceRuleListMapping.values()){
            Collections.sort(ruleList);
        }

        interfaceRuleListMapping = tmpInterfaceRuleListMapping;
    }

    @Override
    public SyncType[] syncTypes() {
        return new SyncType[]{SyncType.mock_rule, SyncType.all};
    }

    public Map<String, List<MockRule>> getInterfaceRuleListMapping() {
        return interfaceRuleListMapping;
    }

    public MockRule getMockRule(String ruleType, String matchValue){
        List<MockRule> ruleList = interfaceRuleListMapping.get(ruleType);
        if(!CollectionsUtil.isNullOrEmpty(ruleList)){
            for(MockRule  mockRule : ruleList){
                if(mockRule.getRuleType().equals(RuleType.unknown)){
                    logger.error("unknown rule type, id:" + mockRule.getId());
                    continue;
                }

                if(mockRule.getDefault()){
                    return mockRule;
                }

                IRuleHandler ruleHandler = ruleHandlerMapping.get(mockRule.getRuleType());
                if(ruleHandler != null){
                    if(ruleHandler.meetRule(mockRule.getRuleValue(), matchValue)){
                        return mockRule;
                    }
                }else{
                    logger.error("cannot found rule handler for ruleType:" + mockRule.getRuleType());
                }
            }
        }

        return null;
    }
}
