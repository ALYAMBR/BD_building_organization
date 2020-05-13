package ru.nsu.ccfit.radeev.commonclient.clientdescription.queries.objectsindistanddep;

import ru.nsu.ccfit.radeev.commonclient.database.framework.tables.interfaces.tables.TableNewRecordEditors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Query implements ru.nsu.ccfit.radeev.commonclient.database.framework.tables.interfaces.queries.Query {
    private static final String query = "select\t \"DEPARTMENT\".\"NAME\" as \"DEPARTMENT\",\n" +
            "           \"DISTRICT\".\"NAME\" as \"DISTRICT\",\n" +
            "           \"BUILDING_OBJECT\".\"NAME\" as \"OBJECT\",\n" +
            "           \"BUILDING_OBJECT\".\"ID\" as \"OBJECT_ID\",\n" +
            "           \"WORKS\".\"FACT_START_TIME\" as \"FACT_START_TIME\",\n" +
            "           \"WORKS\".\"FACT_FINISH_TIME\" as \"FACT_FINISH_TIME\"\n" +
            "from\t \"WORKS\",\n" +
            "         \"BUILDING_OBJECT\",\n" +
            "         \"DEPARTMENT\",\n" +
            "         \"DEP_DISTRICT_CONNECTIONS\",\n" +
            "         \"DISTRICT\"\n" +
            "where   \"DEPARTMENT\".\"ID\"=\"DEP_DISTRICT_CONNECTIONS\".\"ID_DEP\"\n" +
            "  and\t \"DEP_DISTRICT_CONNECTIONS\".\"ID_DISTRICT\"=\"DISTRICT\".\"ID\"\n" +
            "  and\t \"BUILDING_OBJECT\".\"ID_DISTRICT\"=\"DEP_DISTRICT_CONNECTIONS\".\"ID_DISTRICT\"\n" +
            "  and\t \"WORKS\".\"ID_OBJ\"=\"BUILDING_OBJECT\".\"ID\"\n" +
            "  and    \"DEPARTMENT\".\"ID\" = ?\n" +
            "  and    \"DISTRICT\".\"ID\" = ?\n" +
            "order by DEPARTMENT, DISTRICT, BUILDING_OBJECT.NAME, BUILDING_OBJECT.ID\n" +
            "\n";
    private static final String description = "Объекты отделов и участков с графиками";
    private static final String[] params = {"Участок", "Отдел"};
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