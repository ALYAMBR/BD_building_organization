package ru.nsu.ccfit.radeev.commonclient.clientdescription.queries.technicsonobject;

import ru.nsu.ccfit.radeev.commonclient.database.framework.tables.interfaces.tables.TableNewRecordEditors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Query implements ru.nsu.ccfit.radeev.commonclient.database.framework.tables.interfaces.queries.Query {
    private static final String query = "select\t \"BUILDING_OBJECT\".\"NAME\" as \"OBJECT\",\n" +
            "           \"TECHNICS\".\"NAME\" as \"TECHNICS\",\n" +
            "           \"TECHNICS_HISTORY_1\".\"START_TIME\" as \"START_TIME\",\n" +
            "           \"TECHNICS_HISTORY_1\".\"FINISH_TIME\" as \"FINISH_TIME\"\n" +
            "from\t \"TECHNICS_HISTORY\" \"TECHNICS_HISTORY_1\",\n" +
            "         \"BUILDING_OBJECT\" \"BUILDING_OBJECT\",\n" +
            "         \"TECHNICS\" \"TECHNICS\"\n" +
            "where   \"TECHNICS\".\"ID_CUR_OBJ\"=\"BUILDING_OBJECT\".\"ID\"\n" +
            "  and\t \"TECHNICS_HISTORY_1\".\"ID_TECH\"(+) =\"TECHNICS\".\"ID\"\n" +
            "  and   \"BUILDING_OBJECT\".\"ID\" = ?";
    private static final String description = "Техника на объекте";
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