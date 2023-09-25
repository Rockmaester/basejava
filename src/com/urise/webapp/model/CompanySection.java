package com.urise.webapp.model;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class CompanySection extends Section {

    private final List<Company> companies;

    public CompanySection(Company... companies) {
        this(Arrays.asList(companies));
    }
    public CompanySection(List<Company> companies) {
        Objects.requireNonNull(companies, "list of companies must not be null");
        this.companies = companies;
    }

    public List<Company> getCompanies() {
        return companies;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for(Company obj : companies){
            s.append(obj.getLink().getCompanyName()).append(" ").append(obj.getLink().getUrl()).append("\n");
            for(Company.Position prd : obj.getPositions()){
                s.append(" ").append(prd.getStartDate()).append(" - ").append(prd.getEndDate()). append(" ")/*.append("\n")*/;
                s.append(prd.getObjective()).append("\n");
                String dscr = prd.getDescription();
                if(dscr != null){
                    s.append(prd.getDescription()).append("\n");
                }
            }
        }
        return s.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompanySection that = (CompanySection) o;
        return Objects.equals(companies, that.companies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companies);
    }
}
