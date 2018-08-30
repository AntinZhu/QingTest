package com.qingqing.test.service.inter;

import com.qingqing.test.domain.inter.TestInterfaceParam;

import java.util.List;

/**
 * Created by zhujianxing on 2018/8/30.
 */
public interface TestInterfaceParamService {
    List<TestInterfaceParam> selectParamListByInterface(Long interfaceId);
}
