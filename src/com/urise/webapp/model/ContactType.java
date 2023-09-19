package com.urise.webapp.model;

public enum ContactType {
    PHONE("Телефон"),
    EMAIL("Почта"),
    SKYPE("Скайп"),
    LINKED_IN("Профиль LinkedIn"),
    GIT_HUB("Профиль GitHub"),
    STACK_OVERFLOW("Профиль StackOverFlow"),
    PERSONAL_PAGE("Личный сайт");
    private String title;

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
