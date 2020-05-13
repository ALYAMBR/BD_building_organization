package ru.nsu.ccfit.radeev.commonclient.clientdescription.queries.brigadesonobject;

import ru.nsu.ccfit.radeev.commonclient.database.framework.tables.interfaces.tables.TableNewRecordEditors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Query implements ru.nsu.ccfit.radeev.commonclient.database.framework.tables.interfaces.queries.Query {
    private static final String query = "select\t \"EMPLOYEE\".\"NAME_FIRST\" as \"NAME_FIRST\",\n" +
            "           \"EMPLOYEE\".\"NAME_SECOND\" as \"NAME_SECOND\",\n" +
            "           \"EMPLOYEE\".\"NAME_THIRD\" as \"NAME_THIRD\",\n" +
            "           \"EMPLOYEE\".\"JOB\" as \"JOB\",\n" +
            "           \"WORKS\".\"INDEX_NUMBER\" as \"BRIGADE_WORK_INDEX\"\n" +
            "from\t \"EMPLOYEE\" \"EMPLOYEE\",\n" +
            "         \"SLAVE\" \"SLAVE\",\n" +
            "         \"BRIGADE\" \"BRIGADE\",\n" +
            "         \"WORKS\" \"WORKS\",\n" +
            "         \"BUILDING_OBJECT\" \"BUILDING_OBJECT\"\n" +
            "where   \"WORKS\".\"ID_OBJ\"=\"BUILDING_OBJECT\".\"ID\"\n" +
            "  and\t \"EMPLOYEE\".\"ID\"=\"SLAVE\".\"ID\"\n" +
            "  and\t \"SLAVE\".\"ID_BRIGADE\"=\"BRIGADE\".\"ID\"\n" +
            "  and\t \"BRIGADE\".\"ID\"=\"WORKS\".\"ID_BRIGADE\"\n" +
            "  and    \"BUILDING_OBJECT\".\"ID\" = ?\n" +
            "order by WORKS.INDEX_NUMBER ASC, BUILDING_OBJECT.NAME ASC, BUILDING_OBJECT.ID ASC";
    private static final String description = "Бригады на объектах с указанием порядка работ";
    private static final String[] params = {"Объект"};
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