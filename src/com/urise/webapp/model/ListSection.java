package com.urise.webapp.model;

import java.util.List;

public class ListSection extends Section{
    private List<String> strings;

    public ListSection(List<String> strings) {
        this.strings = strings;
    }

    public List<String> getStrings() {
        return strings;
    }

    public void setStrings(List<String> strings) {
        this.strings = strings;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for(String str : strings){
            s.append(str).append("\n");
        }
        return s.toString();
    }
}
