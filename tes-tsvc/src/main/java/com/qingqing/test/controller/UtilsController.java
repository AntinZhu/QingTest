package com.qingqing.test.controller;

import com.google.common.collect.Sets;
import com.qingqing.api.proto.v1.UserProto;
import com.qingqing.api.proto.v1.util.Common.SimpleStringRequest;
import com.qingqing.common.auth.domain.User;
import com.qingqing.common.auth.domain.UserType;
import com.qingqing.common.exception.ErrorCodeException;
import com.qingqing.common.util.OrderIdEncoder;
import com.qingqing.common.util.TimeUtil;
import com.qingqing.common.web.protobuf.ProtoRequestBody;
import com.qingqing.test.bean.base.BaseResponse;
import com.qingqing.test.bean.base.SimpleResponse;
import com.qingqing.test.bean.common.UserWithDataBean;
import com.qingqing.test.bean.common.response.SingleResponse;
import com.qingqing.test.bean.index.IndexQueryRequestBean;
import com.qingqing.test.bean.index.IndexUpdateRequestBean;
import com.qingqing.test.controller.errorcode.SimpleErrorCode;
import com.qingqing.test.manager.BITeacherIndexManager;
import com.qingqing.test.manager.PhoneNumberManager;
import com.qingqing.test.manager.QingApiLabManager;
import com.qingqing.test.service.user.UserService;
import com.qingqing.test.util.QingFileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Created by zhujianxing on 2018/2/4.
 */
@Controller
@RequestMapping("/v1/utils")
public class UtilsController {

    private static final Logger logger = LoggerFactory.getLogger(UtilsController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private QingApiLabManager qingApiLabManager;
    @Autowired
    private BITeacherIndexManager biTeacherIndexManager;
    @Autowired
    private PhoneNumberManager phoneNumberManager;

    @RequestMapping("phoneNumber/sync")
    @ResponseBody
    public SingleResponse<Integer> encode() throws Exception {
        long start = System.currentTimeMillis();
        Integer count = phoneNumberManager.sync();
        logger.info("cost:" + (System.currentTimeMillis() - start));

        return new SingleResponse<>(count);
    }

    @RequestMapping("order/encode")
    @ResponseBody
    public SingleResponse<String> encode(@ProtoRequestBody SimpleStringRequest request){
        String orderIdStr = request.getData();

        String qingqingOrderId;
        try{
            qingqingOrderId = OrderIdEncoder.encodeOrderId(Long.valueOf(orderIdStr));
        }catch (Exception e){
            throw new ErrorCodeException(new SimpleErrorCode(1001, "order id encode error","加密失败，请检查参数"),"convert order id error, value:" + orderIdStr, e);
        }

        SingleResponse<String> result = new SingleResponse<String>();
        result.setResponse(com.qingqing.test.bean.base.BaseResponse.SUCC_RESP);
        result.setResultList(qingqingOrderId);
        return result;
    }

    @RequestMapping("order/decode")
    @ResponseBody
    public SingleResponse<Long> decode(@ProtoRequestBody SimpleStringRequest request){
        String qingqingOrderId = request.getData();

        Long orderId;
        try{
            orderId = OrderIdEncoder.decodeQingqingOrderId(qingqingOrderId);
        }catch (Exception e){
            throw new ErrorCodeException(new SimpleErrorCode(1001, "order id encode error","解密失败，请检查参数"),"convert order id error, value:" + qingqingOrderId, e);
        }

        SingleResponse<Long> result = new SingleResponse<Long>();
        result.setResponse(com.qingqing.test.bean.base.BaseResponse.SUCC_RESP);
        result.setResultList(orderId);
        return result;
     }

    @RequestMapping("user_phone/convert")
    @ResponseBody
    public SingleResponse<String> userPhoneConvert(@ProtoRequestBody UserProto.User request){
        String userPhoneNumber = userService.getUserPhone(UserType.valueOf(request.getUserType().getNumber()), request.getUserId());

        SingleResponse<String> result = new SingleResponse<String>();
        result.setResponse(com.qingqing.test.bean.base.BaseResponse.SUCC_RESP);
        if(userPhoneNumber != null){
            result.setResultList(userPhoneNumber);
        }
        return result;
    }

    @RequestMapping("phone_user/convert")
    @ResponseBody
    public SingleResponse<Long> userPhoneConvert(@RequestBody UserWithDataBean request){
        User user = userService.getUserByPhone(request.getUserType(), request.getData());

        SingleResponse<Long> result = new SingleResponse<Long>();
        result.setResponse(com.qingqing.test.bean.base.BaseResponse.SUCC_RESP);
        if(user != null){
            result.setResultList(user.getUserId());
        }
        return result;
    }

    @RequestMapping("user/encode")
    @ResponseBody
    public SingleResponse<String> encodeUserID(@ProtoRequestBody UserProto.User request){
        String qingqingUserId = userService.encodeUser(UserType.valueOf(request.getUserType().getNumber()), request.getUserId());

        SingleResponse<String> result = new SingleResponse<String>();
        result.setResponse(com.qingqing.test.bean.base.BaseResponse.SUCC_RESP);
        if(qingqingUserId != null){
            result.setResultList(qingqingUserId);
        }
        return result;
    }

    @RequestMapping("phone/decode")
    public @ResponseBody void decodePhone(@RequestParam("file") MultipartFile file,
                                          @RequestParam("session") String session,
                                          HttpServletResponse response) throws IOException {
        List<String> encodeList = QingFileUtils.readLines(file.getInputStream());
        Map<String, String> resultMap =  qingApiLabManager.decodePhoneNumber(Sets.newHashSet(encodeList), session);

        String fileName = "result-" + TimeUtil.dateToString(new Date(), TimeUtil.TIME_IN_DETAIL_STRING_FORMAT) + ".csv";
        response.setContentType("application/force-download");
        response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);

        BufferedWriter writer = null; //输出流
        try {
            writer = new BufferedWriter(new OutputStreamWriter(response.getOutputStream()));
            for (Entry<String, String> entry : resultMap.entrySet()) {
                String line = entry.getKey() + "," + entry.getValue();
                writer.write(line);
                writer.newLine();
            }
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("----------file download" + fileName);
    }

    @RequestMapping("es/student-teacher")
    public String biStudentTeacherIndexPage() {
        return "utils/es_tr_st";
    }

    @RequestMapping("es/query")
    @ResponseBody
    public SingleResponse<String> esQuery(@RequestBody IndexQueryRequestBean request){
        SingleResponse<String> result = new SingleResponse<String>();
        result.setResponse(com.qingqing.test.bean.base.BaseResponse.SUCC_RESP);
        result.setResultList(biTeacherIndexManager.queryIndex(request.getQueryString(), request.getIndexName()));
        return result;
    }

    @RequestMapping("es/update")
    @ResponseBody
    public SimpleResponse esUpdate(@RequestBody IndexUpdateRequestBean requestBean){
        boolean updateResult = biTeacherIndexManager.updateIndex(requestBean.getUniqueKey(), requestBean.getUniqueValue(), requestBean.getIndexName(), requestBean.getData());

        BaseResponse baseResponse = BaseResponse.SUCC_RESP;
        if(!updateResult){
            baseResponse = new BaseResponse(1001, "update fail", "更新失败");
        }
        return new SimpleResponse(baseResponse);
    }

    @RequestMapping("es/delete")
    @ResponseBody
    public SimpleResponse esDelete(@RequestBody IndexUpdateRequestBean requestBean){
        boolean updateResult = biTeacherIndexManager.deleteIndex(requestBean.getUniqueKey(), requestBean.getUniqueValue(), requestBean.getIndexName());

        BaseResponse baseResponse = BaseResponse.SUCC_RESP;
        if(!updateResult){
            baseResponse = new BaseResponse(1001, "update fail", "删除失败");
        }
        return new SimpleResponse(baseResponse);
    }

    @RequestMapping("es/indexs")
    @ResponseBody
    public SingleResponse<String> indexs(){
        biTeacherIndexManager.allIndex();

        return new SingleResponse<>("");
    }


    @RequestMapping("ws")
    public String ws() {
        return "utils/ws";
    }
}
