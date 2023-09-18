package com.urise.webapp.model;

import java.time.LocalDate;

public class Period {

    private LocalDate startDate;
    private LocalDate endDate;
    private String objective;
    private String description;

    public Period(LocalDate startDate, LocalDate endDate, String objective, String description) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.objective = objective;
        this.description = description;
    }

    public Period(LocalDate startDate, LocalDate endDate, String objective) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.objective = objective;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
