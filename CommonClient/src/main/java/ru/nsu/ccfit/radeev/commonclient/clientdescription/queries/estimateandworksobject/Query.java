package ru.nsu.ccfit.radeev.commonclient.clientdescription.queries.estimateandworksobject;

import ru.nsu.ccfit.radeev.commonclient.database.framework.tables.interfaces.tables.TableNewRecordEditors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Query implements ru.nsu.ccfit.radeev.commonclient.database.framework.tables.interfaces.queries.Query {
    private static final String query = "select\t \"BUILDING_OBJECT\".\"NAME\" as \"OBJECT\",\n" +
            "           \"TYPE_WORKS\".\"NAME\" as \"WORKS\",\n" +
            "           \"WORKS\".\"SHED_START_TIME\" as \"SHED_START_TIME\",\n" +
            "           \"WORKS\".\"SHED_FINISH_TIME\" as \"SHED_FINISH_TIME\",\n" +
            "           \"ESTIMATE\".\"GOODS_NAME\" as \"GOODS_NAME\",\n" +
            "           \"ESTIMATE\".\"PLAN_QUANTITY\" as \"PLAN_QUANTITY\"\n" +
            "from\t \"TYPE_WORKS\" \"TYPE_WORKS\",\n" +
            "         \"WORKS\" \"WORKS\",\n" +
            "         \"ESTIMATE\" \"ESTIMATE\",\n" +
            "         \"BUILDING_OBJECT\" \"BUILDING_OBJECT\"\n" +
            "where   \"BUILDING_OBJECT\".\"ID\"=\"WORKS\".\"ID_OBJ\"\n" +
            "  and\t \"WORKS\".\"ID\"=\"ESTIMATE\".\"ID_WORK\"\n" +
            "  and\t \"WORKS\".\"ID_TYPE\"=\"TYPE_WORKS\".\"ID\"\n" +
            "  and   \"BUILDING_OBJECT\".\"ID\" = ?";
    private static final String description = "Смета и расписание на объекте";
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