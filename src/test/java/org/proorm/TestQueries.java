package org.proorm;

import org.proorm.dialect.PostgreSQLDialect;
import org.proorm.executor.DefaultStatementExecutor;
import org.proorm.queryTarget.Database;
import org.proorm.queryTarget.DatabaseConfig;
import org.proorm.queryTarget.IDatabase;
import org.proorm.queryTarget.IDatabaseConfig;
import org.junit.jupiter.api.Test;
import org.postgresql.ds.PGSimpleDataSource;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;


public class TestQueries {

    @Test
    public void test() throws Exception {
        Database db;


        // Create a DataSource for your database connection:
        PGSimpleDataSource ds = new PGSimpleDataSource();
        ds.setUrl("jdbc:postgresql://dpg-cm257smn7f5s73esa0s0-a.singapore-postgres.render.com/random_jsfh");
        ds.setUser("random_jsfh_user");
        ds.setPassword("1HYm8DGIlaZjfZuC56KC6miLZlArJdwd");

        // Pass datasource, statement executor and dialect to the database config:

        DatabaseConfig.getInstance().dataSource(ds)
                .statementExecutor(new DefaultStatementExecutor())
                .dialect(new PostgreSQLDialect())
                .build();

        db = new Database(DatabaseConfig.getInstance());
        testQueriesOnDatabase(db);
    }

    public void testQueriesOnDatabase(IDatabase db) throws Exception {
        // Table creation :
        db.dropTable(Student.class);
        db.createTable(Student.class);

        testORMQueries(db);
    }

    public void testORMQueries(IDatabase db) throws Exception {
        Student s1 = new Student(
                1,
                "Nguyen Van A",
                true,
                19,
                "HCMUS, Nguyen Van Cu",
                Arrays.asList("Math", "Physics"),
                new Date());
        Student s2 = new Student(
                1,
                "Tran Van B",
                true,
                20,
                "HCMUS, Nguyen Van Cu",
                Arrays.asList("Math", "Physics"),
                new Date());
        Student s3 = new Student(
                1,
                "Le Van C",
                true,
                20,
                "HCMUS, Nguyen Van Cu",
                Arrays.asList("Math", "Physics"),
                new Date());

        // Insert:
        db.insert(s1);
        db.insert(Arrays.asList(s2, s3));

        // Update:
        s1.setAddress("HCMUS, Linh Trung");
        db.update(s1);

        // Select:
        // select all
        List<Student> studentList = db.select(Student.class).list();
        System.out.println("Select all: " + studentList);

        // select by id
        Student res1 = db.select(Student.class).id(1).one();
        System.out.println("Select by id: " + res1);

        // select by name
        Student res2 = db.select(Student.class).where("name = ?", "Nguyen Van A").one();
        System.out.println("Select by name: " + res2);

        // select all, groop by age
        List<Map<String, Object>> res3 =
                db.select("select age, COUNT(age) as age_count from students group by age").toMap().list();
        System.out.println("Select, group by age: " + res3);

        // select all, groop by age, having age_count>1
        List<Map<String, Object>> res4 =
                db.select("select age, COUNT(*) as age_count from students group by age having count(*)>1").toMap().list();
        System.out.println("Select, having age_count>1: " + res4);


        // Delete:
        db.delete(Student.class).where("name = ?", "Nguyen Van A").execute();
        db.delete(Student.class).id(2).execute();
    }
}
