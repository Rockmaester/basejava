package com.urise.webapp.model;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Initial resume class
 */
public class Resume implements Comparable<Resume>{

    private final String uuid;
    private String fullName;

    Map<ContactType, Contact> contacts = new HashMap<>();

    Map<SectionType, Section> sections = new HashMap<>();

    public Resume(String uuid, String fullName) {
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public Resume(String fullName) {
        this.uuid = UUID.randomUUID().toString();
        this.fullName = fullName;
    }

    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Map<ContactType, Contact> getContacts() {
        return contacts;
    }

    public void setContacts(Map<ContactType, Contact> contacts) {
        this.contacts = contacts;
    }

    public Map<SectionType, Section> getSections() {
        return sections;
    }

    public void setSections(Map<SectionType, Section> sections) {
        this.sections = sections;
    }

    @Override
    public String toString() {
        return "резюме под номером " + uuid;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || this.getClass() != obj.getClass()) return false;
        Resume resume = (Resume)obj;
        return this.uuid.equals(resume.uuid);
    }

    @Override
    public int hashCode() {
        return uuid.hashCode();
    }

    @Override
    public int compareTo(Resume resume) {
        return this.uuid.compareTo(resume.uuid);
    }
}
