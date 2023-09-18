package com.urise.webapp.model;

import java.util.List;

public class CompanySection extends Section {

    private List<Company> companies;

    public CompanySection(SectionType sectionType, List<Company> companies) {
        super(sectionType);
        this.companies = companies;
    }

    @Override
    List<Company> getContent() {
        return companies;
    }

    @Override
    void printContent() {
        String s = "";
        for(Company obj : companies){
            System.out.println(obj.getName() + " " + obj.getWebsite());
            for(Period prd : obj.getPeriods()){
                System.out.print("  " + prd.getStartDate() + " - " + prd.getEndDate() + "  ");
                System.out.println(prd.getObjective());
                String dscr = prd.getDescription();
                if(dscr != null){
                    System.out.println(prd.getDescription());
                }
            }
        }
    }
}
