package com.qingqing.test.masterslave;

import com.google.common.base.Preconditions;
import com.qingqing.test.aspect.masterslave.QingMasterSlaveDateSourceAspect;
import io.shardingjdbc.core.constant.SQLType;
import io.shardingjdbc.core.hint.HintManagerHolder;
import io.shardingjdbc.core.jdbc.core.datasource.MasterSlaveDataSource;
import io.shardingjdbc.core.jdbc.core.datasource.NamedDataSource;
import io.shardingjdbc.core.rule.MasterSlaveRule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by zhujianxing on 2019/3/6.
 */
public class QingMasterSlaveDataSource extends MasterSlaveDataSource {

    private static final Logger logger = LoggerFactory.getLogger(QingMasterSlaveDataSource.class);

    @Autowired
    private QingMasterSlaveDataSource self;

    public QingMasterSlaveDataSource(MasterSlaveRule masterSlaveRule, Map<String, Object> configMap) throws SQLException {
        super(masterSlaveRule, configMap);
    }

    @Override
    public NamedDataSource getDataSource(SQLType sqlType) {
        return self.doGetDataSource(sqlType);
    }

    public NamedDataSource doGetDataSource(SQLType sqlType){
        MasterSlaveRule masterSlaveRule = getMasterSlaveRule();
        if (isMasterRoute(sqlType)) {
//            logger.info("from master");
            return new NamedDataSource(masterSlaveRule.getMasterDataSourceName(), masterSlaveRule.getMasterDataSource());
        }
        String selectedSourceName = masterSlaveRule.getStrategy().getDataSource(masterSlaveRule.getName(),
                masterSlaveRule.getMasterDataSourceName(), new ArrayList<>(masterSlaveRule.getSlaveDataSourceMap().keySet()));
        DataSource selectedSource = selectedSourceName.equals(masterSlaveRule.getMasterDataSourceName())
                ? masterSlaveRule.getMasterDataSource() : masterSlaveRule.getSlaveDataSourceMap().get(selectedSourceName);
        Preconditions.checkNotNull(selectedSource, "");
//        logger.info("from slave");
        return new NamedDataSource(selectedSourceName, selectedSource);

    }

    public boolean isMasterRoute(SQLType sqlType){ // HintManagerHolder没有值默认走master
//        logger.info("SQLType.DQL != sqlType:" + (SQLType.DQL != sqlType));
//        logger.info("HintManagerHolder.get() == null :" + (HintManagerHolder.get() == null ));
//        logger.info("HintManagerHolder.isMasterRouteOnly() : " + HintManagerHolder.isMasterRouteOnly());
        return SQLType.DQL != sqlType || !QingMasterSlaveDateSourceAspect.isReadSlave() || HintManagerHolder.isMasterRouteOnly();
    }
}
