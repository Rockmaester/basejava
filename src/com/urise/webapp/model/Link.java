package com.urise.webapp.model;

import java.io.Serializable;
import java.util.Objects;

// Создан доп.класс, т.к. конструкция "имя-ссылка" достаточно распространенная и может быть еще понадобится - вынесена в отдельный класс.
public class Link implements Serializable {
    private final String companyName;
    private final String url;

    public Link(String name, String url) {
        // Имя не может быть нулевым, url - теоретически может
        Objects.requireNonNull(name, "name must not be null");
        this.companyName = name;
        this.url = url;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "Link(" + companyName + ',' + url + ')';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Link link = (Link) o;

        if (!companyName.equals(link.companyName)) return false;
        return url != null ? url.equals(link.url) : link.url == null;

    }

    @Override
    public int hashCode() {
        int result = companyName.hashCode();
        result = 31 * result + (url != null ? url.hashCode() : 0);
        return result;
    }
}
