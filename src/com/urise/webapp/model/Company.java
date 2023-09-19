package com.urise.webapp.model;

import java.util.List;
import java.util.Objects;

public class Company {

    private final Link link;
    private List<PeriodOfActivity> periods;


    public Company(String name, String url, List<PeriodOfActivity> periods) {

        this.link = new Link(name, url);
        this.periods = periods;
    }

    public List<PeriodOfActivity> getPeriods() {
        return periods;
    }

    public Link getLink() {
        return link;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return Objects.equals(link, company.link) && Objects.equals(periods, company.periods);
    }

    @Override
    public int hashCode() {
        return Objects.hash(link, periods);
    }

    @Override
    public String toString() {
        return "Company{" +
                "homePage=" + link +
                ", periods=" + periods +
                '}';
    }
}
