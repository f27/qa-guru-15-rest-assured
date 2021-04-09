package filter;

import io.qameta.allure.restassured.AllureRestAssured;

public class LogFilter {
    private static class InitLogFilter {
        private static final LogFilter logFilter = new LogFilter();
    }

    public static LogFilter filters() {
        return InitLogFilter.logFilter;
    }

    public AllureRestAssured withCustomTemplates() {
        AllureRestAssured FILTER = new AllureRestAssured();
        FILTER.setRequestTemplate("request.ftl");
        FILTER.setResponseTemplate("response.ftl");
        return FILTER;
    }
}