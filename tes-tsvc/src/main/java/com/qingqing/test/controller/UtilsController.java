package com.qingqing.test.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Sets;
import com.qingqing.api.proto.v1.ProtoBufResponse;
import com.qingqing.api.proto.v1.UserProto;
import com.qingqing.api.proto.v1.util.Common.SimpleRepeatedStringRequest;
import com.qingqing.api.proto.v1.util.Common.SimpleStringRequest;
import com.qingqing.common.auth.domain.User;
import com.qingqing.common.auth.domain.UserType;
import com.qingqing.common.exception.ErrorCodeException;
import com.qingqing.common.exception.RequestValidateException;
import com.qingqing.common.util.JsonUtil;
import com.qingqing.common.util.MixedEncryption;
import com.qingqing.common.util.OrderIdEncoder;
import com.qingqing.common.util.StringUtils;
import com.qingqing.common.util.TimeUtil;
import com.qingqing.common.util.UserIdEncoder;
import com.qingqing.common.util.encode.TripleDESUtil;
import com.qingqing.common.web.protobuf.ProtoRequestBody;
import com.qingqing.common.web.protobuf.ProtoRespGenerator;
import com.qingqing.test.aspect.validate.IpLoginValid;
import com.qingqing.test.bean.base.BaseResponse;
import com.qingqing.test.bean.base.KeyAndValue;
import com.qingqing.test.bean.base.SimpleResponse;
import com.qingqing.test.bean.common.UserWithDataBean;
import com.qingqing.test.bean.common.response.ListResponse;
import com.qingqing.test.bean.common.response.SingleResponse;
import com.qingqing.test.bean.index.IndexQueryRequestBean;
import com.qingqing.test.bean.index.IndexUpdateRequestBean;
import com.qingqing.test.client.PiClient;
import com.qingqing.test.controller.errorcode.SimpleErrorCode;
import com.qingqing.test.dao.test.mock.MockRuleMapper;
import com.qingqing.test.domain.mock.MockRule;
import com.qingqing.test.domain.tool.TestCronTask;
import com.qingqing.test.domain.user.TestUserIp;
import com.qingqing.test.manager.BITeacherIndexManager;
import com.qingqing.test.manager.CommonSyncManager;
import com.qingqing.test.manager.ISyncable.SyncType;
import com.qingqing.test.manager.PhoneNumberManager;
import com.qingqing.test.manager.QingApiLabManager;
import com.qingqing.test.manager.TestProtoClassNameManager;
import com.qingqing.test.manager.UserIpManager;
import com.qingqing.test.manager.WxNotifyManager;
import com.qingqing.test.service.common.CommonService;
import com.qingqing.test.service.tool.TestCronTaskService;
import com.qingqing.test.service.user.UserService;
import com.qingqing.test.spring.filter.IpFilter;
import com.qingqing.test.util.QingFileUtils;
import com.qingqing.test.util.ReportShareCodeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.*;
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
    @Autowired
    private TestProtoClassNameManager testProtoClassNameManager;
    @Autowired
    private CommonService commonService;
    @Autowired
    private MockRuleMapper mockRuleMapper;
    @Autowired
    private TestCronTaskService testCronTaskService;
    @Autowired
    private WxNotifyManager wxNotifyManager;
    @Autowired
    private UserIpManager userIpManager;
    @Autowired
    private CommonSyncManager commonSyncManager;
    @Autowired
    private PiClient piClient;

    private final static String ENCODE_KEY = "erahsQqx";
    private final static String VERSION_V1 = "v1";
    private static final String MIX_CODE = "Report";
    private static final String v1 = "v1";

    @RequestMapping("phoneNumber/sync")
    @ResponseBody
    public SingleResponse<Integer> encode() throws Exception {
        long start = System.currentTimeMillis();
        Integer count = phoneNumberManager.sync();
        logger.info("cost:" + (System.currentTimeMillis() - start));

        SingleResponse<Integer> result = new SingleResponse<Integer>();
        result.setResultList(count);
        return result;
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

    @RequestMapping("phone/encode")
    @ResponseBody
    public SingleResponse<String> encodePhone(@ProtoRequestBody SimpleStringRequest request){
        String phoneNumber = request.getData();

        String qingqingPhoneNumber;
        try{
            qingqingPhoneNumber = TripleDESUtil.encrypt(phoneNumber);
        }catch (Exception e){
            throw new ErrorCodeException(new SimpleErrorCode(1001, "phone number encode error","加密失败，请检查参数"),"convert phone number error, value:" + phoneNumber, e);
        }

        SingleResponse<String> result = new SingleResponse<String>();
        result.setResponse(com.qingqing.test.bean.base.BaseResponse.SUCC_RESP);
        result.setResultList(qingqingPhoneNumber);
        return result;
    }

    @RequestMapping("phone/decode")
    @ResponseBody
    public SingleResponse<String> decodePhone(@ProtoRequestBody SimpleStringRequest request){
        String qingqingPhoneNumber = request.getData();

        String phoneNumber;
        try{
            phoneNumber = TripleDESUtil.decrypt(qingqingPhoneNumber);
        }catch (Exception e){
            throw new ErrorCodeException(new SimpleErrorCode(1001, "phone number encode error","解密失败，请检查参数"),"convert phone number error, value:" + qingqingPhoneNumber, e);
        }

        SingleResponse<String> result = new SingleResponse<String>();
        result.setResponse(com.qingqing.test.bean.base.BaseResponse.SUCC_RESP);
        result.setResultList(phoneNumber);
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
        SingleResponse<String> result = new SingleResponse<String>();
        result.setResponse(com.qingqing.test.bean.base.BaseResponse.SUCC_RESP);
        result.setResultList(UserIdEncoder.encodeUser(UserType.valueOf(request.getUserType().getNumber()), request.getUserId()));
        return result;
    }

    @RequestMapping("user/decode")
    @ResponseBody
    public SingleResponse<User> decodeUserId(@ProtoRequestBody SimpleStringRequest request){
        User user = null;
        try{
            user = UserIdEncoder.decodeUser(request.getData());
        }catch(Exception e){
            throw new ErrorCodeException(new SimpleErrorCode(1001, "", "解码用户ID失败"), "fail decode qingqingUserId:" + request.getData());
        }

        SingleResponse<User> result = new SingleResponse<User>();
        result.setResponse(com.qingqing.test.bean.base.BaseResponse.SUCC_RESP);
        if(user != null){
            result.setResultList(user);
        }
        return result;
    }

    @RequestMapping("phone/online/decode")
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
    public String biStudentTeacherIndexPage(Model model) {
        model.addAttribute("alias", JsonUtil.format(biTeacherIndexManager.allIndex()));

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

//    @RequestMapping("es/indexs")
//    @ResponseBody
//    public SingleResponse<String> indexs(){
//        biTeacherIndexManager.allIndex();
//
//        return new SingleResponse<String>("");
//    }


    @RequestMapping("ws")
    public String ws() {
        return "utils/ws";
    }

    @RequestMapping("/get_full_name")
    @ResponseBody
    public ListResponse<String> getFullClassName(@RequestBody SimpleStringRequest request){
        List<String> fullClassName = testProtoClassNameManager.getFullClassName(request.getData());

        ListResponse<String> result = new ListResponse<>();
        result.setResponse(BaseResponse.SUCC_RESP);
        result.setResultList(fullClassName);
        return result;
    }

    @RequestMapping("sql/run")
    public @ResponseBody void fullNameUpdate(@RequestParam("file") MultipartFile file) throws IOException {
        List<String> encodeList = QingFileUtils.readLines(file.getInputStream());
        for (String sql : encodeList) {
            commonService.insert(sql);
        }

        commonSyncManager.sync(SyncType.all);
    }

    @RequestMapping("mock")
    public @ResponseBody ListResponse<MockRule> mock() {
        ListResponse<MockRule> result = new ListResponse<>();
        result.setResponse(BaseResponse.SUCC_RESP);
        result.setResultList(mockRuleMapper.selectAll());

        return result;
    }

    @RequestMapping("cron_task/all")
    public @ResponseBody ListResponse<TestCronTask> allCronTask() {
        List<TestCronTask> resultList = testCronTaskService.selectAll();

        ListResponse<TestCronTask> result = new ListResponse<>();
        result.setResponse(BaseResponse.SUCC_RESP);
        result.setResultList(resultList);
        return result;
    }

    @RequestMapping("cron_task/add")
    @IpLoginValid
    public @ResponseBody
    ProtoBufResponse.SimpleResponse addCronTask(@RequestBody TestCronTask cronTask) {
        if(cronTask.getId() == null || cronTask.getId() == 0){
            cronTask.setDeleted(false);
            testCronTaskService.add(cronTask);
            wxNotifyManager.markdown("有用户新增定时任务", new KeyAndValue("用户", userIpManager.getUserNameByIp(IpFilter.getRequestUserIp())), new KeyAndValue("任务名称", cronTask.getName()));
        }else{
            testCronTaskService.update(cronTask.getId(), cronTask.getName(), cronTask.getUrl());
        }

        return ProtoRespGenerator.SIMPLE_SUCC_RESP;
    }

    @RequestMapping("cron_task/edit")
    @IpLoginValid
    public String editCronTask(@RequestParam(value="id", defaultValue = "0") Long id, Model model){
        if(id > 0){
            TestCronTask task = testCronTaskService.findById(id);
            model.addAttribute("task", task);
        }
        return "utils/edit_cron_task";
    }

    @RequestMapping("cron_task/del")
    @IpLoginValid
    public @ResponseBody
    ProtoBufResponse.SimpleResponse delCronTask(@RequestParam("id") Long id) {
        testCronTaskService.deleted(id);

        return ProtoRespGenerator.SIMPLE_SUCC_RESP;
    }

    @RequestMapping("ip/up")
    public String upIp(@RequestParam("userName") String userName, Model model) {
        model.addAttribute("userName", userName);

        return "utils/up_ip";
    }

    @RequestMapping("jump/test/{path}")
    public String jump(@PathVariable("path") String path, @RequestParam(value = "serIp", defaultValue = "172.22.7.82") String serIp, Model model) {
        model.addAttribute("serIp", serIp);

        return "test/" + path;
    }

    @RequestMapping("/new")
    public String newFun() {
        return "utils/new";
    }

    @RequestMapping("common/config")
    public String commonConfigPage(@RequestParam(value = "key", defaultValue = "") String configKey, Model model){
        model.addAttribute("configKey", configKey);

        return "utils/common_config";
    }

    @RequestMapping("common/common_config")
    public String commonConfigPage2(@RequestParam(value = "key", defaultValue = "") String configKey, Model model){
        model.addAttribute("configKey", configKey);

        return "utils/common_config_2";
    }

    @RequestMapping("common_config/set")
    @ResponseBody
    public void setCommonConfig(
            @RequestParam(value = "oper") String oper,
            @RequestParam(value = "id", required = false) String id,
            @RequestParam(value = "configKey", required = false) String configKey,
            @RequestParam(value = "configValue", required = false) String configValue,
            Model model){
        if("edit".equals(oper) || "add".equals(oper) ){
            logger.info("configKey:" + configKey + " configValue:" + configValue);

            JSONObject obj = new JSONObject();
            obj.put("configKey", configKey);
            obj.put("configValue", configValue);
            obj.put("configScope", "common");
            obj.put("operateUserId", 1);
            obj.put("operateUserType", "system");
            logger.info("param:" + obj.toJSONString());

            piClient.commonRequest("/svc/api/pi/v1/test/common/config/reset.json?guid=api-test_reset_common_config", obj.toJSONString(), "");
        }else if("del".equals(oper)){
            piClient.commonRequest("/svc/api/pi/v1/test/common/config/del.json?guid=api-test_reset_common_config&id=" + id, "", "");
        }

    }

    @RequestMapping("report/teacher/encode")
    @ResponseBody
    public SingleResponse<String> encodeStudentReportId(@ProtoRequestBody SimpleStringRequest request) {
        String reportId = request.getData();
        String encodeId = null;
        try {
            encodeId = MixedEncryption.encode(ENCODE_KEY, VERSION_V1, OrderIdEncoder.encodeOrderId(Long.valueOf(reportId)));
        } catch (Exception e) {
            throw new ErrorCodeException(new SimpleErrorCode(1001, "report id encode error", "加密失败，请检查参数"),
                    "encode report id error, value:" + reportId, e);
        }

        SingleResponse<String> result = new SingleResponse<String>();
        result.setResponse(com.qingqing.test.bean.base.BaseResponse.SUCC_RESP);
        result.setResultList(encodeId);
        return result;
    }


    @RequestMapping("report/teacher/decode")
    @ResponseBody
    public SingleResponse<String> decodeTeacherReportId(@ProtoRequestBody SimpleStringRequest request){
        String shareCode = request.getData();
        Long reportId=null;

        try {
            String[] decodeValues = MixedEncryption.decode(shareCode, ENCODE_KEY);
            if (decodeValues.length == 2) {
                String version = decodeValues[0];
                String reportIdStr = decodeValues[1];
                if (!VERSION_V1.equals(version)) {
                    throw new RequestValidateException("decode code fail, version not match. version:" + version
                            + ", encodeString:" + shareCode,
                            "unknown encodeString");
                }
                if (org.apache.commons.lang3.StringUtils.isNumeric(reportIdStr)) {
                    reportId = OrderIdEncoder.decodeQingqingOrderId(reportIdStr);
                    SingleResponse<String> result = new SingleResponse<String>();
                    result.setResponse(com.qingqing.test.bean.base.BaseResponse.SUCC_RESP);
                    result.setResultList(String.valueOf(reportId));
                    return result;
                }
            }
            throw new RequestValidateException("decode code fail, encodeString:" + shareCode, "unknown encodeString");

        } catch (ErrorCodeException ex) {
            throw ex;
        } catch (RequestValidateException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new RequestValidateException("decode code fail, encodeString:" + shareCode, "unknown encodeString");
        }

    }

    @RequestMapping("report/student/encode")
    @ResponseBody
    public SingleResponse<String> encodeTeacherReportId(@ProtoRequestBody SimpleRepeatedStringRequest request) {
        int dataCount = request.getDataCount();
        String reportIdStr = request.getData(0);
        String studentIdStr=null;
        if(dataCount>1){
            studentIdStr = request.getData(1);
        }
        String encodeId = null;
        try {
            //reportId  is not allow empty
            Long reportId = Long.valueOf(reportIdStr);
            Long studentId = null;
            if (!StringUtils.isEmpty(studentIdStr)) {
                studentId = Long.valueOf(studentIdStr);
            }
            encodeId = ReportShareCodeUtils.generatorStudentShareCodeV2(reportId, studentId);
        } catch (Exception e) {
            throw new ErrorCodeException(new SimpleErrorCode(1001, "report id encode error", "加密失败，请检查参数"),
                    "encode report id error, value:" + reportIdStr, e);
        }

        SingleResponse<String> result = new SingleResponse<String>();
        result.setResponse(com.qingqing.test.bean.base.BaseResponse.SUCC_RESP);
        result.setResultList(encodeId);
        return result;
    }

    @RequestMapping("report/student/decode")
    @ResponseBody
    public ListResponse<Long> decodeStudentReportId(@ProtoRequestBody SimpleStringRequest request){
        String shareCode = request.getData();

        if (StringUtils.isEmpty(shareCode)) {
            throw new RequestValidateException("unknown share code:" + shareCode, "code error");
        }
        try {
            ReportShareCodeUtils.ReportShareBean reportShareBean = ReportShareCodeUtils.parseStudentShareCode(shareCode);
            ListResponse<Long> result = new ListResponse<Long>();
            result.setResponse(com.qingqing.test.bean.base.BaseResponse.SUCC_RESP);
            ArrayList<Long> resultList = new ArrayList<>(2);
            resultList.add(reportShareBean.getReportId());
            if(reportShareBean.getStudentId()!=null){
                resultList.add(reportShareBean.getStudentId());
            }
            result.setResultList(resultList);
            return result;
        } catch (RuntimeException ex) {
            throw new RequestValidateException("code parse failed. code:" + shareCode, "code error", ex);
        }
    }

    @RequestMapping("ip/query_by_name")
    @ResponseBody
    public SingleResponse<String> queryUserIp(@ProtoRequestBody SimpleStringRequest request){
        TestUserIp userIpBean = userIpManager.getUserInfoByName(request.getData());
        String userIp = "";
        if(userIpBean != null){
            userIp = userIpBean.getUserIp();
        }

        SingleResponse<String> result = new SingleResponse<>();
        result.setResponse(BaseResponse.SUCC_RESP);
        result.setResultList(userIp);
        return result;
    }

    @RequestMapping("ip/query_by_ip")
    @ResponseBody
    public SingleResponse<String> queryUserName(@ProtoRequestBody SimpleStringRequest request){
        TestUserIp userIpBean = userIpManager.getUserInfoIncTmp(request.getData());
        String userName = "";
        if(userIpBean != null){
            userName = userIpBean.getUserName();
        }

        SingleResponse<String> result = new SingleResponse<>();
        result.setResponse(BaseResponse.SUCC_RESP);
        result.setResultList(userName);
        return result;
    }

    @RequestMapping("/test")
    public String test(){
        return "utils/common_config_2";
    }

    @RequestMapping("common/city_config")
    public String cityConfigManager(@RequestParam(value = "key", defaultValue = "") String configKey, Model model){
        model.addAttribute("configKey", configKey);

        return "utils/city_rule_config";
    }

    @RequestMapping("city_config/set")
    @ResponseBody
    public void setCityConfig(
            @RequestParam(value = "oper") String oper,
            @RequestParam(value = "id", required = false) String id,
            @RequestParam(value = "cityId", required = false) Integer cityId,
            @RequestParam(value = "ruleType", required = false) String ruleType,
            @RequestParam(value = "configValue", required = false) String configValue,
            Model model){
        if("edit".equals(oper) || "add".equals(oper) ){
            logger.info("city cityId:" + cityId + " ruleType:" + ruleType + " configValue:" + configValue);

            JSONObject obj = new JSONObject();
            obj.put("cityId", cityId);
            obj.put("ruleType", ruleType);
            obj.put("configValue", configValue);
            obj.put("isDeleted", false);

            piClient.commonRequest("/svc/api/pi/v1/test/city_config/reset.json?guid=api-test_reset_city_config", obj.toJSONString(), "");
        }else if("del".equals(oper) ){
            piClient.commonRequest("/svc/api/pi/v1/test/city_config/del.json?guid=api-test_reset_city_config&id=" + id, "", "");
        }
    }
}
