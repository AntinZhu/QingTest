package com.qingqing.test.aspect.masterslave.backup;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标记方法内某些class读slave
 * enableClassed表示读slave, disableClasses表示不读slave
 * 如果class同时在enableClasses和disableClasses，disableClasses优先级更高。
 * 如果class不在enableClasses和disableClasses，走defaultValue逻辑：true表示读slave，false表示不读slave
 * Created by zhujianxing on 2019/3/6.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
@Inherited
public @interface QingReadSlaveDataSource {

    /**
     * 允许读slave的class
     * @return
     */
    Class<?>[] enableClasses() default {};

    /**
     * 不允许读slave的class
     * @return
     */
    Class<?>[] disableClasses() default {};

    /**
     * 默认值，默认为false，true:全部可读slave的都读slave
     * @return
     */
    boolean defaultValue() default false;
}
