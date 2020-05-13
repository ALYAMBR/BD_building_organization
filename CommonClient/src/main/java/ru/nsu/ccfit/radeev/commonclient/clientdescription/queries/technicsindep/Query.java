package ru.nsu.ccfit.radeev.commonclient.clientdescription.queries.technicsindep;

import ru.nsu.ccfit.radeev.commonclient.database.framework.tables.interfaces.tables.TableNewRecordEditors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Query implements ru.nsu.ccfit.radeev.commonclient.database.framework.tables.interfaces.queries.Query {
    private static final String query = "select\t \"TECHNICS\".\"NAME\" as \"TECHNICS\",\n" +
            "           \"DISTRICT\".\"NAME\" as \"DISTRICT\",\n" +
            "           \"BUILDING_OBJECT\".\"NAME\" as \"OBJECT\"\n" +
            "from\t \"BUILDING_OBJECT\" \"BUILDING_OBJECT\",\n" +
            "         \"DISTRICT\" \"DISTRICT\",\n" +
            "         \"DEPARTMENT\" \"DEPARTMENT\",\n" +
            "         \"TECHNICS\" \"TECHNICS\"\n" +
            "where   \"DEPARTMENT\".\"ID\"=\"TECHNICS\".\"ID_HOST_DEP\"\n" +
            "  and\t \"TECHNICS\".\"ID_CUR_DISTRICT\"=\"DISTRICT\".\"ID\"\n" +
            "  and\t \"TECHNICS\".\"ID_CUR_OBJ\"=\"BUILDING_OBJECT\".\"ID\"\n" +
            "  and \t \"DEPARTMENT\".\"ID\" = ?";
    private static final String description = "Техника по отделам";
    private static final String[] params = {"Отдел"};
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