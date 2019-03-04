package com.qingqing.test.dao.user;

import com.qingqing.test.domain.phone.UserAndPhoneNumber;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserPhoneMapper {

    List<UserAndPhoneNumber> selectStudentList(@Param("minExcludeId") Long minExcludeId, @Param("maxIncludeId") Long maxIncludeId);

    List<UserAndPhoneNumber> selectTeacherList(@Param("minExcludeId") Long minExcludeId, @Param("maxIncludeId") Long maxIncludeId);

    List<UserAndPhoneNumber> selectAssistantList(@Param("minExcludeId") Long minExcludeId, @Param("maxIncludeId") Long maxIncludeId);

    Long selectMaxStudentId();

    Long selectMaxTeacherId();

    Long selectMaxAssistantId();
}