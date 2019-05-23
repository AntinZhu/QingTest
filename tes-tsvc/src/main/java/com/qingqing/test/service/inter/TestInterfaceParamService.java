package com.qingqing.test.service.inter;

import com.qingqing.test.domain.inter.TestInterfaceParam;

import java.util.List;

/**
 * Created by zhujianxing on 2018/8/30.
 */
public interface TestInterfaceParamService {

    void save(TestInterfaceParam testInterface);

    TestInterfaceParam findById(Long id);

    List<TestInterfaceParam> selectListByInterfaceId(Long interfaceId);

    boolean resetDefault(Long interfaceId);

    boolean setDefault(Long id);
}
