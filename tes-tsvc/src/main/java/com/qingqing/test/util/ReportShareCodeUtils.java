package com.qingqing.test.util;

import com.qingqing.common.exception.RequestValidateException;
import com.qingqing.common.util.MixedEncryption;
import org.springframework.util.StringUtils;

/**
 * created by mahengshan on 2020/4/28.
 */
public class ReportShareCodeUtils {

    private static final String STUDENT_REPORT_SHARE_CODE_V1 = "v1";
    private static final String MIX_CODE = "Report";

    private static final String STUDENT_REPORT_SHARE_CODE_V2 = "v2";


    public static String generatorStudentShareCodeV2(Long reportId, Long studentId) {
        if (studentId == null) {
            return MixedEncryption.encode(MIX_CODE, STUDENT_REPORT_SHARE_CODE_V2, reportId);
        }
        return MixedEncryption.encode(MIX_CODE, STUDENT_REPORT_SHARE_CODE_V2, reportId, studentId);
    }


    public static class ReportShareBean {

        private Long studentId;
        private Long reportId;

        public Long getStudentId() {
            return studentId;
        }

        public void setStudentId(Long studentId) {
            this.studentId = studentId;
        }

        public Long getReportId() {
            return reportId;
        }

        public void setReportId(Long reportId) {
            this.reportId = reportId;
        }
    }



    public static ReportShareBean parseStudentShareCode(String code) {
        if (StringUtils.isEmpty(code)) {
            throw new RequestValidateException("unknown share code:" + code, "code error");
        }

        try {
            ReportShareBean bean = new ReportShareBean();
            String[] arr = MixedEncryption.decode(code, MIX_CODE);
            if (arr == null) {
                throw new RequestValidateException("parse array is null or length mismatch", "code error");
            }

            switch (arr[0]) {
                case STUDENT_REPORT_SHARE_CODE_V1:
                    if (arr.length != 3) {
                        throw new RequestValidateException("parse array is null or length mismatch", "share code error");
                    }
                    Long reportId = Long.valueOf(arr[1]);
                    Long studentId = Long.valueOf(arr[2]);

                    bean.setReportId(reportId);
                    bean.setStudentId(studentId);
                    break;

                case STUDENT_REPORT_SHARE_CODE_V2:
                    if (arr.length > 3) {
                        throw new RequestValidateException("parse share code length mismatch", "share code error");
                    }
                    bean.setReportId(Long.valueOf(arr[1]));
                    if (arr.length > 2) {
                        bean.setStudentId(Long.valueOf(arr[2]));
                    }

                    break;

                default:
                    throw new RequestValidateException("unknow share code version:" + arr[0], "share code error");
            }
            return bean;

        } catch (RuntimeException ex) {
            throw new RequestValidateException("code parse failed. code:" + code, "code error", ex);
        }
    }




}
