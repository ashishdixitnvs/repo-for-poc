package com.example.samplecrudapp.config;

import java.sql.Types;
import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.function.SQLFunctionTemplate;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.dialect.function.VarArgsSQLFunction;
import org.hibernate.type.StringType;
import org.hibernate.dialect.pagination.LimitHandler;
import org.hibernate.dialect.pagination.LimitOffsetLimitHandler;

public class SQLiteDialect extends Dialect {

    public SQLiteDialect() {
        registerColumnType(Types.BIT, "boolean");
        registerColumnType(Types.TINYINT, "tinyint");
        registerColumnType(Types.SMALLINT, "smallint");
        registerColumnType(Types.INTEGER, "integer");
        registerColumnType(Types.BIGINT, "bigint");
        registerColumnType(Types.FLOAT, "float");
        registerColumnType(Types.DOUBLE, "double");
        registerColumnType(Types.VARCHAR, "varchar($l)");
        registerColumnType(Types.CHAR, "char($l)");
        registerColumnType(Types.LONGVARCHAR, "longvarchar");
        registerColumnType(Types.DATE, "date");
        registerColumnType(Types.TIMESTAMP, "datetime");
        registerColumnType(Types.BLOB, "blob");

        registerFunction("concat", new VarArgsSQLFunction(StringType.INSTANCE, "", "||", ""));
        registerFunction("mod", new SQLFunctionTemplate(StringType.INSTANCE, "?1 % ?2"));
        registerFunction("substr", new StandardSQLFunction("substr", StringType.INSTANCE));
        registerFunction("substring", new StandardSQLFunction("substr", StringType.INSTANCE));
    }

    @Override
    public boolean supportsIdentityColumns() {
        return true;
    }

    @Override
    public String getIdentityColumnString() {
        return "integer";
    }

    @Override
    public String getIdentitySelectString() {
        return "select last_insert_rowid()";
    }

    @Override
    public boolean hasDataTypeInIdentityColumn() {
        return false;
    }

    @Override
    public boolean supportsLimit() {
        return true;
    }

    @Override
    public LimitHandler getLimitHandler() {
        return LimitOffsetLimitHandler.INSTANCE;
    }

    @Override
    public String getAddColumnString() {
        return "add column";
    }

    @Override
    public boolean supportsTemporaryTables() {
        return true;
    }

    @Override
    public String getDropForeignKeyString() {
        return "";
    }

    @Override
    public String getDropTableString(String tableName) {
        return "drop table if exists " + tableName;
    }

    @Override
    public boolean supportsIfExistsBeforeTableName() {
        return true;
    }

    @Override
    public boolean supportsCascadeDelete() {
        return false;
    }
}
