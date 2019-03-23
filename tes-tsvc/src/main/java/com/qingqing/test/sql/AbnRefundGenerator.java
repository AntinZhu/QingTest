package com.qingqing.test.sql;

import com.qingqing.common.exception.QingQingRuntimeException;
import com.qingqing.common.util.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * Created by zhujianxing on 2019/3/14.
 */
public class AbnRefundGenerator {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("D:/sql/abn_refund.csv"));
        BufferedWriter writer = new BufferedWriter(new FileWriter("D:/sql/abn_refund_result.tst"));
        Set<Pair<String, String>>  teacherStudentRelationSet = new HashSet<>(500);
        Map<Pair<String, String>, List<String>> relationOrderCourseListMap = new HashMap<>(100);

        String line = null;
        int lineNum = 0;
        while ((line = reader.readLine()) != null){
            lineNum++;
            if(StringUtils.isEmpty(line)){
                continue;
            }

            String[] lineSplitResult = line.split(",");
            if(lineSplitResult.length < 3){
                throw new QingQingRuntimeException("error content in line:" + lineNum);
            }

            String orderCourseId = lineSplitResult[0].trim();
            String teacherId = lineSplitResult[1].trim();
            String studentId = lineSplitResult[2].trim();

            Pair<String, String> relation = Pair.of(teacherId, studentId);
            teacherStudentRelationSet.add(relation);
            List<String> orderCourseList = relationOrderCourseListMap.get(relation);
            if(orderCourseList == null){
                orderCourseList = new ArrayList<>(20);
                relationOrderCourseListMap.put(relation, orderCourseList);
            }
            orderCourseList.add(orderCourseId);
        }

        writer.write(generateAbnApply(teacherStudentRelationSet));
        writer.newLine();
        for(Entry<Pair<String, String>, List<String>> entry : relationOrderCourseListMap.entrySet()){
            Pair<String, String> relation = entry.getKey();
            List<String> orderCourseList = entry.getValue();
            for(String orderCourseId : orderCourseList){
                writer.write(new StringBuffer("select concat('insert into t_course_abn_refund_apply_detail(apply_id, order_course_id, create_time) value(', id, ").append("',").append(orderCourseId).append(", now())') from t_course_abn_refund_apply where teacher_id = ").append(relation.getKey()).append(" and student_id = ").append(relation.getValue()).append(" and create_user_type=5 and create_user_id = 1;\n").toString());
            }
        }

        reader.close();
        writer.close();
    }

    private static final String generateAbnApply(Set<Pair<String, String>>  teacherStudentRelationSet){
        StringBuilder sql = new StringBuilder("insert into t_course_abn_refund_apply(student_id, teacher_id, create_user_id, create_user_type, reason_num, other_reason, teacher_amount, teacher_paid_amount, platform_amount, student_amount, audit_remark, status, is_deleted, create_time) values");

        Iterator<Pair<String, String>> relationIterator = teacherStudentRelationSet.iterator();
        int totalSize = teacherStudentRelationSet.size();
        int idx = 0;
        while(relationIterator.hasNext()){
            Pair<String, String> relation = relationIterator.next();
            sql.append("(").append(relation.getValue()).append(",").append(relation.getKey()).append(", 1, 5, 4, '', 0, 0, 0, 0, '已走线下异常退费', 3, 0, now())");
            idx++;

            if(idx != totalSize){
                sql.append(",");
            }
        }
        sql.append(";");

        return sql.toString();
    }
}
