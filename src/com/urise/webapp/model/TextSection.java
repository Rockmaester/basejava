package com.urise.webapp.model;

public class TextSection extends Section{
    public String text;

    public TextSection(String content) {
        this.text = content;
    }

    @Override
    public String toString() {
        return text + "\n";
    }
}
