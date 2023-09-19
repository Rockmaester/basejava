package com.urise.webapp.model;

import java.time.LocalDate;
import java.util.Objects;

public class PeriodOfActivity {

    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String objective;
    private String description;

    public PeriodOfActivity(LocalDate startDate, LocalDate endDate, String objective, String description) {
        Objects.requireNonNull(startDate, "startDate must not be null");
        Objects.requireNonNull(endDate, "endDate must not be null");
        Objects.requireNonNull(objective, "objective must not be null");
        this.startDate = startDate;
        this.endDate = endDate;
        this.objective = objective;
        this.description = description;
    }

    public PeriodOfActivity(LocalDate startDate, LocalDate endDate, String objective) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.objective = objective;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String getObjective() {
        return objective;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PeriodOfActivity that = (PeriodOfActivity) o;
        return Objects.equals(startDate, that.startDate)
                && Objects.equals(endDate, that.endDate)
                && Objects.equals(objective, that.objective)
                && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDate, endDate, objective, description);
    }
}
