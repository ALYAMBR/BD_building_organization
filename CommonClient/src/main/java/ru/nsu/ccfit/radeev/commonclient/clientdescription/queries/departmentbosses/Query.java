package ru.nsu.ccfit.radeev.commonclient.clientdescription.queries.departmentbosses;

import ru.nsu.ccfit.radeev.commonclient.database.framework.tables.interfaces.tables.TableNewRecordEditors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Query implements ru.nsu.ccfit.radeev.commonclient.database.framework.tables.interfaces.queries.Query {
    private static final String query = "select\t \"DEPARTMENT\".\"NAME\" as \"DEPARTMENT\",\n" +
            "\t \"EMPLOYEE\".\"NAME_FIRST\" as \"NAME_FIRST\",\n" +
            "\t \"EMPLOYEE\".\"NAME_SECOND\" as \"NAME_SECOND\",\n" +
            "\t \"EMPLOYEE\".\"NAME_THIRD\" as \"NAME_THIRD\" \n" +
            " from\t \"EMPLOYEE\" \"EMPLOYEE\",\n" +
            "\t \"DEPARTMENT\" \"DEPARTMENT\" \n" +
            " where   \"EMPLOYEE\".\"ID\"=\"DEPARTMENT\".\"ID_BOSS\"\n" +
            "order by DEPARTMENT.NAME ASC, EMPLOYEE.NAME_SECOND ASC, EMPLOYEE.NAME_FIRST ASC, EMPLOYEE.NAME_THIRD ASC";
    private static final String description = "Список начальников отделов";
    private static final String[] params = {};
    private static final List<String> paramNames = new ArrayList<String>();

    static {
        paramNames.addAll(Arrays.asList(params));
    }

    private static final TableNewRecordEditors editors = new Editors();

    public String getDescription() {
        return description;
    }

    public String getQuery() {
        return query;
    }

    public TableNewRecordEditors getEditors() {
        return editors;
    }

    public List<String> getParamNames() {
        return paramNames;
    }
}