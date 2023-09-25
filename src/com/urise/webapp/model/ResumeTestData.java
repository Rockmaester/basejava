package com.urise.webapp.model;

import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

public class ResumeTestData {

    public static void main(String[] args) {
        ResumeTestData.printResumeToConsole(ResumeTestData.getTestResume(UUID.randomUUID().toString(), "Ivan Ivanov"));
    }

    public static Resume getTestResume(String uuid, String fullName){
        // Создание объекта резюме
        Resume resume = new Resume(uuid, fullName);

        //Добавление контактов
//        resume.getContacts().put(ContactType.EMAIL, "abc@xyz.com");
        resume.addContact(ContactType.EMAIL, "abc@xyz.com");
        resume.addContact(ContactType.PHONE, "1234456");

        // Добавление секций
//        resume.getSections().put(SectionType.PERSONAL, new TextSection("Очень хороший человек"));
        resume.addSection(SectionType.PERSONAL, new TextSection("Очень хороший человек"));
        resume.addSection(SectionType.OBJECTIVE, new TextSection("Начальник транспортного цеха"));
//        resume.addSection(SectionType.ACHIEVEMENT,
//                new ListSection(new ArrayList<>(Arrays.asList("достижение 1", "достижение 2")))
//        );
        resume.addSection(SectionType.ACHIEVEMENT, new ListSection("достижение 1", "достижение 2"));
        resume.addSection(SectionType.QUALIFICATIONS, new ListSection("навык 1", "навык 2", "навык 3"));

        Company.Position period1 = new Company.Position(
                LocalDate.of(2005, 9, 15),
                LocalDate.of(2010, 11, 25),
                "Должность 1", "Выполнял круг обязанностей 1"
        );
        Company.Position period2 = new Company.Position(
                LocalDate.of(2010, 12, 18),
                LocalDate.of(2020, 9, 23),
                "Должность 2", "Выполнял круг обязанностей 2"
        );

        Company company1 = new Company("Компания1", "www.abc.zzz", period1, period2);
        Company.Position period3 = new Company.Position(LocalDate.of(
                2020, 9, 25),
                LocalDate.now(),"Должность 1", "Выполнял круг обязанностей 1");
        Company company2 = new Company("Компания2", "www.hello.zzz", period3);
        resume.addSection(SectionType.EXPERIENCE, new CompanySection(company1, company2));

        Company.Position periodLearn1 = new Company.Position(
                LocalDate.of(1998, 9, 1),
                LocalDate.of(2003, 7, 25),
                "Студент"
        );
        Company.Position periodLearn2 = new Company.Position(
                LocalDate.of(2003, 9, 15),
                LocalDate.of(2005, 8, 25),
                "Аспирантура"
        );

        Company university = new Company("Государственный институт", "www.university.zzz", periodLearn1, periodLearn2);

        Company.Position periodLearn3 = new Company.Position(
                LocalDate.of(2007, 9, 15),
                LocalDate.of(2008, 8, 25),
                "Курсы повышения квалификации"
        );

        Company trainingCompany = new Company("SchoolFactory", "www.schoolfactory.zzz", periodLearn3);
        resume.addSection(SectionType.EDUCATION, new CompanySection(university, trainingCompany));
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
