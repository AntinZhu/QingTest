package com.qingqing.test.manager;

import com.google.common.collect.Maps;
import com.qingqing.common.util.CollectionsUtil;
import com.qingqing.test.bean.common.CommonLink;
import com.qingqing.test.manager.ISyncable.SyncType;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.Map;

/**
 * Created by zhujianxing on 2019/5/28.
 */
@Component
public class CommonSyncManager implements ApplicationContextAware {

    private ApplicationContext applicationContext;
    private Map<SyncType, CommonLink<ISyncable>> syncTypeMapping;

    @PostConstruct
    public void init(){
        Map<String, ISyncable> qingSyncAbles = applicationContext.getBeansOfType(ISyncable.class);
        if(!CollectionsUtil.isNullOrEmpty(qingSyncAbles)){
            Map<SyncType, CommonLink<ISyncable>> tmpQingSyncMap = Maps.newHashMapWithExpectedSize(qingSyncAbles.size());
            for (ISyncable iQingSyncAble : qingSyncAbles.values()) {
                for (SyncType qingSyncType : iQingSyncAble.syncTypes()) {
                    CommonLink newQingSyncAbleLink = new CommonLink(iQingSyncAble, null);
                    CommonLink oldQingSyncAbleLink = tmpQingSyncMap.put(qingSyncType, newQingSyncAbleLink);
                    newQingSyncAbleLink.setNext(oldQingSyncAbleLink);
                }
            }

            syncTypeMapping = tmpQingSyncMap;
        }else{
            syncTypeMapping = Collections.emptyMap();
        }
    }

    public void sync(SyncType syncType){
        CommonLink<ISyncable> syncable = syncTypeMapping.get(syncType);
        while(syncable != null){
            syncable.getT().sync();
            syncable = syncable.getNext();
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
