package ${basePackage}.xinterface.controller;

import com.qingqing.test.xinterface.controller.ControllerTestBase;

public class ControllerTestBaseV2 extends ControllerTestBase{
    protected final static String webapp = "/${svcName}";

    @Override
    protected String getWabApp() {
        return webapp;
    }
}
