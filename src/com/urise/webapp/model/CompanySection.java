package com.urise.webapp.model;

import java.util.List;

public class CompanySection extends Section {

    private final List<Company> companies;

    public CompanySection(List<Company> companies) {
        this.companies = companies;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for(Company obj : companies){
            s.append(obj.getName()).append(" ").append(obj.getWebsite()).append("\n");
            for(Period prd : obj.getPeriods()){
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
}
