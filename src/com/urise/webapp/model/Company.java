package com.urise.webapp.model;

import com.urise.webapp.util.DateUtil;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.urise.webapp.util.DateUtil.NOW;

public class Company implements Serializable {
    private final Link link;
    private List<Position> positions = new ArrayList<>();

    public Company(String name, String url, Position...  positions) {
        this(new Link(name, url), Arrays.asList(positions));
    }

    public Company(Link link, List<Position> positions) {
        this.link = link;
        this.positions = positions;
    }
    public List<Position> getPositions() {
        return positions;
    }
    public Link getLink() {
        return link;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return Objects.equals(link, company.link) && Objects.equals(positions, company.positions);
    }
    @Override
    public int hashCode() {
        return Objects.hash(link, positions);
    }

    @Override
    public String toString() {
        return "Company{" +
                "homePage=" + link +
                ", periods=" + positions +
                '}';
    }


    // Общее правило: если есть возможность сделать внутренний класс статическим - нужно делать статическим (если ему не нужны члены внешнего класса)
    public static class Position implements Serializable{
        private final LocalDate startDate;
        private final LocalDate endDate;
        private final String objective;
        private String description;

        public Position(int startYear, Month startMonth, String title, String description){
            this(DateUtil.of(startYear, startMonth), NOW, title, description);
        }

        public Position(int startYear, Month startMonth, int endYear, Month endMonth, String title, String description){
            this(DateUtil.of(startYear, startMonth), DateUtil.of(endYear, endMonth), title, description);
        }

        public Position(LocalDate startDate, LocalDate endDate, String objective, String description) {
            Objects.requireNonNull(startDate, "startDate must not be null");
            Objects.requireNonNull(endDate, "endDate must not be null");
            Objects.requireNonNull(objective, "objective must not be null");
            this.startDate = startDate;
            this.endDate = endDate;
            this.objective = objective;
            this.description = description;
        }

        public Position(LocalDate startDate, LocalDate endDate, String objective) {
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
            Position that = (Position) o;
            return Objects.equals(startDate, that.startDate)
                    && Objects.equals(endDate, that.endDate)
                    && Objects.equals(objective, that.objective)
                    && Objects.equals(description, that.description);
        }

        @Override
        public int hashCode() {
            return Objects.hash(startDate, endDate, objective, description);
        }

        @Override
        public String toString() {
            return "\nPosition{" +
                    "startDate=" + startDate +
                    ", endDate=" + endDate +
                    ", objective='" + objective + '\'' +
                    ", description='" + description + '\'' +
                    '}';
        }
    }
}
