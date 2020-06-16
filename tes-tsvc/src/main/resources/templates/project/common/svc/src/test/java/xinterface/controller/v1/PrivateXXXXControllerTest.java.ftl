package ${basePackage}.xinterface.controller.v1;

import com.qingqing.api.proto.v1.util.Common;
import ${basePackage}.xinterface.controller.ControllerTestBaseV2;
import org.junit.Test;
import com.qingqing.test.annotation.AssignEnv;
import com.qingqing.test.bean.common.Env;

public class PrivateXXXXControllerTest extends ControllerTestBaseV2 {

    String urlPrefix = "/api/pi/v1/xxxx";

    @Test
    @AssignEnv(Env.dev_local)
    public void allName() throws Exception {
        String urlMapping = "/xxx";
        Common.SimpleLongRequest requestParam = Common.SimpleLongRequest.newBuilder()
                .setData(1L)
                .build();

        postPI(urlPrefix, urlMapping, requestParam);
    }
}
