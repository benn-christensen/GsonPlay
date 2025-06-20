import com.google.gson.FormattingStyle;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import models.Person;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Person person = createPerson();

        print(person);

        Gson gson = new Gson();

        try (FileWriter writer = new FileWriter("person.json")) {
            gson.toJson(person, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileReader reader = new FileReader("person.json")) {
            Person deserializedPerson = gson.fromJson(reader, Person.class);
            print(deserializedPerson);
        } catch (IOException e) {
            e.printStackTrace();
        }

        gson = new GsonBuilder().setFormattingStyle(FormattingStyle.PRETTY).create();
        String jsonString = gson.toJson(person);
        System.out.println("Formatted JSON:");
        System.out.println(jsonString);
    }

    private static void print(Person person) {
        System.out.println("Person: " + person.name());
        System.out.println("Books:");
        for (models.Book book : person.books()) {
            System.out.println("- " + book.title() + " (" + book.pages() + " pages)");
        }
        System.out.println();
    }

    private static Person createPerson() {
        models.Book book1 = new models.Book("1984", 328);
        models.Book book2 = new models.Book("To Kill a Mockingbird", 281);

        Person person = new Person("Alice");
        person.addBook(book1);
        person.addBook(book2);
        return person;
    }
}
