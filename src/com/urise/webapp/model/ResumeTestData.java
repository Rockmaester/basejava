package com.urise.webapp.model;

import java.time.LocalDate;
import java.util.*;

public class ResumeTestData {

    public static void main(String[] args) {
        ResumeTestData.printResumeToConsole(ResumeTestData.getTestResume(UUID.randomUUID().toString(), "Ivan Ivanov"));
    }

    public static Resume getTestResume(String uuid, String fullName){
        // Создание объекта резюме
        Resume resume = new Resume(uuid, fullName);

        //Добавление контактов
        resume.getContacts().put(ContactType.EMAIL, "abc@xyz.com");
        resume.getContacts().put(ContactType.PHONE, "1234456");

        // Добавление секций
        resume.getSections().put(SectionType.PERSONAL, new TextSection("Очень хороший человек"));
        resume.getSections().put(SectionType.OBJECTIVE, new TextSection("Начальник транспортного цеха"));
        resume.getSections().put(SectionType.ACHIEVEMENT,
                new ListSection(new ArrayList<>(Arrays.asList("достижение 1", "достижение 2")))
        );
        resume.getSections().put(SectionType.QUALIFICATIONS,
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
        resume.getSections().put(
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
        resume.getSections().put(
                SectionType.EDUCATION, new CompanySection(new ArrayList<>(Arrays.asList(university, trainingCompany)))
        );
        return resume;
    }

    public static void printResumeToConsole(Resume resume){
        // Вывод в консоль
        // Имя
        System.out.println(resume.getFullName() + "\n");

        // Контакты
        for(Map.Entry<ContactType, String> pair : resume.getContacts().entrySet()){
            System.out.println(pair.getKey().getTitle() + ": " + pair.getValue());
        }
        System.out.println();
        // Секции
        for(SectionType type : SectionType.values()){
            // Печать заголовка секции
            System.out.println("     ===== " + type.getTitle() + " ===== ");
            // Извлечение секции по ключу
            Section section = resume.getSections().get(type);
            System.out.println(section);
        }
    }
}
