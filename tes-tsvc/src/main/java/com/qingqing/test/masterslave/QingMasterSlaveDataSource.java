package com.qingqing.test.masterslave;

import com.google.common.base.Preconditions;
import io.shardingjdbc.core.constant.SQLType;
import io.shardingjdbc.core.hint.HintManagerHolder;
import io.shardingjdbc.core.jdbc.core.datasource.MasterSlaveDataSource;
import io.shardingjdbc.core.jdbc.core.datasource.NamedDataSource;
import io.shardingjdbc.core.rule.MasterSlaveRule;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by zhujianxing on 2019/3/6.
 */
public class QingMasterSlaveDataSource extends MasterSlaveDataSource {
    public QingMasterSlaveDataSource(MasterSlaveRule masterSlaveRule, Map<String, Object> configMap) throws SQLException {
        super(masterSlaveRule, configMap);
    }

    @Override
    public NamedDataSource getDataSource(SQLType sqlType) {
        MasterSlaveRule masterSlaveRule = getMasterSlaveRule();
        if (isMasterRoute(sqlType)) {
            return new NamedDataSource(masterSlaveRule.getMasterDataSourceName(), masterSlaveRule.getMasterDataSource());
        }
        String selectedSourceName = masterSlaveRule.getStrategy().getDataSource(masterSlaveRule.getName(),
                masterSlaveRule.getMasterDataSourceName(), new ArrayList<>(masterSlaveRule.getSlaveDataSourceMap().keySet()));
        DataSource selectedSource = selectedSourceName.equals(masterSlaveRule.getMasterDataSourceName())
                ? masterSlaveRule.getMasterDataSource() : masterSlaveRule.getSlaveDataSourceMap().get(selectedSourceName);
        Preconditions.checkNotNull(selectedSource, "");
        return new NamedDataSource(selectedSourceName, selectedSource);
    }

    public boolean isMasterRoute(SQLType sqlType){ // HintManagerHolder没有值默认走master
        return SQLType.DQL != sqlType || HintManagerHolder.get() == null || HintManagerHolder.isMasterRouteOnly();
    }
}
