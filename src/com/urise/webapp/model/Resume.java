package com.urise.webapp.model;

import java.io.Serializable;
import java.util.EnumMap;
import java.util.Map;
import java.util.UUID;

/**
 * Initial resume class
 */
public class Resume implements Comparable<Resume>, Serializable {
    private static final long serialVersionUID = 1L;
    private final String uuid;
    private String fullName;

    Map<ContactType, String> contacts = new EnumMap<>(ContactType.class);
    // Замена HashMap на EnumMap с целью оптимизации, т.к. ключи - тип enum-а. В конструкторе должен подаваться класс enum-а

    Map<SectionType, Section> sections = new EnumMap<>(SectionType.class);

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

    public Map<ContactType, String> getContacts() {
        return contacts;
    }

    public String getContact(ContactType type){
        return contacts.get(type);
    }

    public void setContacts(Map<ContactType, String> contacts) {
        this.contacts = contacts;
    }

    public Map<SectionType, Section> getSections() {
        return sections;
    }

    public Section getSection(SectionType type){
        return sections.get(type);
    }

    public void setSections(Map<SectionType, Section> sections) {
        this.sections = sections;
    }

    public void addContact(ContactType contactType, String value){
        contacts.put(contactType, value);
    }

    public void addSection(SectionType sectionType, Section section){
        sections.put(sectionType, section);
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
