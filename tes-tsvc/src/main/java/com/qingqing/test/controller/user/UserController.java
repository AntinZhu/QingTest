package com.qingqing.test.controller.user;

import com.qingqing.common.exception.ErrorCodeException;
import com.qingqing.common.exception.SimpleErrorCode;
import com.qingqing.test.aspect.validate.IpLoginValid;
import com.qingqing.test.aspect.validate.IpLoginValidType;
import com.qingqing.test.bean.base.BaseResponse;
import com.qingqing.test.bean.base.SimpleResponse;
import com.qingqing.test.bean.common.IdAndBoolBean;
import com.qingqing.test.bean.common.response.ListResponse;
import com.qingqing.test.domain.user.IpStatus;
import com.qingqing.test.domain.user.TestUserIp;
import com.qingqing.test.manager.CommonSyncManager;
import com.qingqing.test.manager.ISyncable.SyncType;
import com.qingqing.test.manager.UserIpManager;
import com.qingqing.test.service.user.TestUserIpService;
import com.qingqing.test.spring.filter.IpFilter;
import com.qingqing.test.util.QingFileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.util.List;

/**
 * Created by zhujianxing on 2018/2/4.
 */
@Controller
@RequestMapping("/v1/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private TestUserIpService testUserIpService;
    @Autowired
    private UserIpManager userIpManager;
    @Autowired
    private CommonSyncManager commonSyncManager;

    @RequestMapping("up/head_image")
    @ResponseBody
    @IpLoginValid
    public SimpleResponse upHeadImage(@RequestParam("file") MultipartFile file){
        String requestUserId = IpFilter.getRequestUserIp();
        String userName = userIpManager.getUserNameByIp(requestUserId);
        if(requestUserId.equals(userName)){
            throw new ErrorCodeException(new SimpleErrorCode(1001, "user error", "未知用户，无法使用该功能"), "user error for ip:" + requestUserId);
        }

        logger.info("contentType:" + file.getContentType());
        logger.info("fileName:" + file.getOriginalFilename());
        byte[] imageByte;
        try {
            imageByte = QingFileUtils.readBytes(file.getInputStream());
        } catch (IOException e) {
            throw new ErrorCodeException(new SimpleErrorCode(1001, "read error", "解析图片失败"), "read file error");
        }
        BASE64Encoder encoder = new BASE64Encoder();
        String imageBase64 = encoder.encode(imageByte);
        String headImage = "data:" + file.getContentType() +  ";base64," +  imageBase64;
        testUserIpService.updateUserHeadImage(requestUserId, headImage);
        commonSyncManager.sync(SyncType.user_ip);

        return new SimpleResponse(BaseResponse.SUCC_RESP);
    }

    @RequestMapping("/list_page")
    @IpLoginValid
    public String userIpPage(){
        return "user/user_ip_list";
    }

    @RequestMapping("/edit")
    @IpLoginValid(validaType = IpLoginValidType.assign)
    public String editUserPage(@RequestParam(value = "id", defaultValue = "0") Long userId, Model model){
        model.addAttribute("id", userId);
        if(userId > 0){
            model.addAttribute("bean", testUserIpService.findById(userId));
        }

        return "user/user_ip_edit";
    }

    @RequestMapping("/list")
    @ResponseBody
    public ListResponse<TestUserIp> userList(){
        List<TestUserIp> userList = testUserIpService.selectAll();

        ListResponse<TestUserIp> response = new ListResponse<>();
        response.setResponse(BaseResponse.SUCC_RESP);
        response.setResultList(userList);
        return response;
    }

    @RequestMapping("/add")
    @ResponseBody
    @IpLoginValid(validaType = IpLoginValidType.assign)
    public SimpleResponse addUser(@RequestBody  TestUserIp userIp){
        testUserIpService.insert(userIp);

        commonSyncManager.sync(SyncType.user_ip);
        return SimpleResponse.SUCC;
    }

    @RequestMapping("/isDeleted/set")
    @ResponseBody
    @IpLoginValid(validaType = IpLoginValidType.assign)
    public SimpleResponse updateDeleted(@RequestBody IdAndBoolBean request){
        testUserIpService.updateDeleted(request.getId(), request.getBool());

        commonSyncManager.sync(SyncType.user_ip);
        return SimpleResponse.SUCC;
    }

    @RequestMapping("/isBlack/set")
    @ResponseBody
    @IpLoginValid(validaType = IpLoginValidType.assign)
    public SimpleResponse updateBlack(@RequestBody IdAndBoolBean request){
        testUserIpService.updateIpStatus(request.getId(), request.getBool()? IpStatus.black : IpStatus.enable);

        commonSyncManager.sync(SyncType.user_ip);
        return SimpleResponse.SUCC;
    }

    @RequestMapping("/tmp/login")
    @ResponseBody
    public SimpleResponse tmpLogin(@RequestParam("userIp") String userIp, @RequestParam("userName") String userName){
        userIpManager.addTmpUser(userName, userIp, IpStatus.enable);

        return SimpleResponse.SUCC;
    }
}
