package org.example.api.utils;


public class Routes {
    public static final String BASE_PATH = "/api";
    public static final String ISU = BASE_PATH + "/isu";
    public static final String EXPELL_ALL_STUDENTS = ISU + "/group/{group-id}/expel-all";
    public static final String STATISTIC_COUNT_EXPELLED = ISU + "/statistics/count-expelled";
    public static final String URL_GET_ALL_GROUP = "/study-groups";
    public static final String URL_GET_GROUP = "/study-groups/{id}";

}
