package com.urise.webapp.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ResumeTestData {
    public static void main(String[] args) {
        // Создание объекта резюме
        Resume RESUME_1 = new Resume("Ivan Ivanov");
        Resume RESUME_2 = new Resume("Petr Petrov");
        Resume RESUME_3 = new Resume("Alexander Aleksandrov");
        Resume RESUME_4 = new Resume("Ivan Ivanov");

        //Добавление контактов
        RESUME_1.getContacts().put(ContactType.EMAIL, "abc@xyz.com");
        RESUME_1.getContacts().put(ContactType.PHONE, "1234456");

        // Добавление секций
        RESUME_1.getSections().put(SectionType.PERSONAL, new TextSection("Очень хороший человек"));
        RESUME_1.getSections().put(SectionType.OBJECTIVE, new TextSection("Начальник транспортного цеха"));
        RESUME_1.getSections().put(SectionType.ACHIEVEMENT,
                new ListSection(new ArrayList<>(Arrays.asList("достижение 1", "достижение 2")))
        );
        RESUME_1.getSections().put(SectionType.QUALIFICATIONS,
                new ListSection(new ArrayList<>(Arrays.asList("навык 1", "навык 2", "навык 3")))
        );

        PeriodOfActivity period1 = new PeriodOfActivity(
                LocalDate.of(2005, 9, 15),
                LocalDate.of(2010, 11, 25),
                "Должность 1", "Выполнял круг обязанностей 1"
        );
        PeriodOfActivity period2 = new PeriodOfActivity(
                LocalDate.of(2010, 12, 18),
                LocalDate.of(2020, 9, 23),
                "Должность 2", "Выполнял круг обязанностей 2"
        );
        List<PeriodOfActivity> periods1 = new ArrayList<>();
        periods1.add(period1);
        periods1.add(period2);
        Company company1 = new Company("Компания1", "www.abc.zzz", periods1);
        PeriodOfActivity period3 = new PeriodOfActivity(LocalDate.of(
                2020, 9, 25),
                LocalDate.now(),"Должность 1", "Выполнял круг обязанностей 1");
        List<PeriodOfActivity> periods2 = new ArrayList<>();
        periods2.add(period3);
        Company company2 = new Company("Компания2", "www.hello.zzz", periods2);
        RESUME_1.getSections().put(
                SectionType.EXPERIENCE,
                new CompanySection(new ArrayList<>(Arrays.asList(company1, company2)))
        );

        PeriodOfActivity periodLearn1 = new PeriodOfActivity(
                LocalDate.of(1998, 9, 1),
                LocalDate.of(2003, 7, 25),
                "Студент"
        );
        PeriodOfActivity periodLearn2 = new PeriodOfActivity(
                LocalDate.of(2003, 9, 15),
                LocalDate.of(2005, 8, 25),
                "Аспирантура"
        );
        List<PeriodOfActivity> periodsLearning = new ArrayList<>();
        periodsLearning.add(periodLearn1);
        periodsLearning.add(periodLearn2);
        Company university = new Company("Государственный институт", "www.university.zzz", periodsLearning);

        PeriodOfActivity periodLearn3 = new PeriodOfActivity(
                LocalDate.of(2007, 9, 15),
                LocalDate.of(2008, 8, 25),
                "Курсы повышения квалификации"
        );
        List<PeriodOfActivity> periodsLearning2 = new ArrayList<>();
        periodsLearning2.add(periodLearn3);
        Company trainingCompany = new Company("SchoolFactory", "www.schoolfactory.zzz", periodsLearning2);
        RESUME_1.getSections().put(
                SectionType.EDUCATION, new CompanySection(new ArrayList<>(Arrays.asList(university, trainingCompany)))
        );

        // Вывод в консоль
        // Имя
        System.out.println(RESUME_1.getFullName() + "\n");

        // Контакты
        for(Map.Entry<ContactType, String> pair : RESUME_1.getContacts().entrySet()){
            System.out.println(pair.getKey().getTitle() + ": " + pair.getValue());
        }

        System.out.println();

        // Секции
        for(SectionType type : SectionType.values()){
            // Печать заголовка секции
            System.out.println("     ===== " + type.getTitle() + " ===== ");
            // Извлечение секции по ключу
            Section section = RESUME_1.getSections().get(type);
            System.out.println(section);
        }
    }
}
