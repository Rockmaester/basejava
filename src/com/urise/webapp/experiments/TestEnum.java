package com.urise.webapp.experiments;

import com.urise.webapp.model.SectionType;

public class TestEnum {
    public static void main(String[] args) {

        for(SectionType type:SectionType.values()){
            System.out.println(type.getTitle());
        }
    }
}
