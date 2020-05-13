package ru.nsu.ccfit.radeev.commonclient.clientdescription.queries.workersindistanddeps;

import ru.nsu.ccfit.radeev.commonclient.database.framework.tables.interfaces.tables.TableNewRecordEditors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Query implements ru.nsu.ccfit.radeev.commonclient.database.framework.tables.interfaces.queries.Query {
    private static final String query = "select\t \"DISTRICT\".\"NAME\" as \"NAME\",\n" +
            "           \"DEPARTMENT\".\"NAME\" as \"NAME\",\n" +
            "           \"EMPLOYEE\".\"NAME_FIRST\" as \"NAME_FIRST\",\n" +
            "           \"EMPLOYEE\".\"NAME_SECOND\" as \"NAME_SECOND\",\n" +
            "           \"EMPLOYEE\".\"NAME_THIRD\" as \"NAME_THIRD\",\n" +
            "           \"EMPLOYEE\".\"JOB\" as \"JOB\"\n" +
            "from\t \"GROUP_WORKERS\" \"GROUP_WORKERS_1\",\n" +
            "         \"WORKERS\" \"WORKERS\",\n" +
            "         \"DEPARTMENT\" \"DEPARTMENT\",\n" +
            "         \"DISTRICT\" \"DISTRICT\",\n" +
            "         \"EMPLOYEE\" \"EMPLOYEE\"\n" +
            "where   \"WORKERS\".\"ID\"=\"EMPLOYEE\".\"ID\"\n" +
            "  and\t \"WORKERS\".\"ID_GROUP\"=\"GROUP_WORKERS_1\".\"ID\"\n" +
            "  and\t \"GROUP_WORKERS_1\".\"ID_DISTRICT\"=\"DISTRICT\".\"ID\"\n" +
            "  and\t \"EMPLOYEE\".\"ID_DEP\"=\"DEPARTMENT\".\"ID\"\n" +
            "  and    \"DEPARTMENT\".\"ID\" = ?\n" +
            "  and    \"DISTRICT\".\"ID\" = ?";
    private static final String description = "Специалисты на участках и в отделах";
    private static final String[] params = {"Участок", "Отдел"}; //TODO make it with settings
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