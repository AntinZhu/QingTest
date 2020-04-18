package ${basePackage}.xinterface.controller.v1;

import com.qingqing.api.proto.v1.ProtoBufResponse;
import com.qingqing.api.proto.v1.util.Common;
import com.qingqing.common.web.protobuf.ProtoRespGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
* Created by ${user!'test-api'} on ${date}
*/
@Controller
@RequestMapping("/pi/v1/xxxxx/")
public class PrivateTemplateController {

    @RequestMapping("/xxxxx")
    @ResponseBody
    public ProtoBufResponse.SimpleResponse xxxx(@RequestBody Common.SimpleStringRequest request){
        return ProtoBufResponse.SimpleResponse.newBuilder().setResponse(ProtoRespGenerator.SUCC_BASE_RESP).build();
    }
}
