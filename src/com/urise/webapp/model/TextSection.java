package com.urise.webapp.model;

public class TextSection extends Section{
    public String text;

    public TextSection(SectionType sectionType, String content) {
        super(sectionType);
        this.text = content;
    }

    public String getContent(){
        return text;
    }

    @Override
    void printContent(){
        System.out.println(text);
    }
}
