package com.urise.webapp.model;

import java.util.List;

public class ListSection extends Section{
    private List<String> strings;

    public ListSection(SectionType sectionType, List<String> strings) {
        super(sectionType);
        this.strings = strings;
    }

    public List<String> getStrings() {
        return strings;
    }

    public void setStrings(List<String> strings) {
        this.strings = strings;
    }

    @Override
    List<String> getContent() {
        return strings;
    }

    @Override
    void printContent() {
        for(String str : strings){
            System.out.println(str);
        }
    }
}
