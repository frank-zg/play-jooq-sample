package service;

import mysql.dao.ScopedContext;
import mysql.model.generated.Tables;
import mysql.model.generated.tables.records.TUserinfoRecord;
import protocol.rsp.TestRsp;

import java.util.Optional;


/**
 * Created by zg on 2016/6/29.
 */
public class TestService extends Service {

    public TestRsp test(int userId) {
        ScopedContext context = newContext();
        Optional<TUserinfoRecord> record = excutor(() -> {
            return Optional.of(context.getDSLContext()
                    .selectFrom(Tables.T_USERINFO)
                    .where(Tables.T_USERINFO.ID.eq(userId))
                    .fetchOne());
        }, context);
        return record.isPresent() ? new TestRsp(record.get().getId(), record.get().getName()) : new TestRsp();
    }
}
