package org.proorm;

import org.proorm.dialect.PostgreSQLDialect;
import org.proorm.executor.DefaultStatementExecutor;
import org.proorm.queryTarget.Database;
import org.proorm.queryTarget.DatabaseConfig;
import org.proorm.queryTarget.IDatabase;
import org.proorm.queryTarget.IDatabaseConfig;
import org.junit.jupiter.api.Test;
import org.postgresql.ds.PGSimpleDataSource;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class TestQueries {

    @Test
    public void testConnection() throws Exception {
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
//        Table creation :
        db.dropTable(Student.class);
        db.createTable(Student.class);

//        ORM queries :
        testORMQueries(db);

//        Generic selects :
//        testGenericQueries(db);
    }

    public void testORMQueries(IDatabase db) throws Exception {
        Student s1 = new Student(
                1,
                "Nguyen Van A",
                true,
                20,
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

        // Insert:
        db.insert(s1);

        // Update:
        s1.setAddress("HCMUS, Linh Trung");
        db.update(s1);

        // Select:
        List<Student> studentList = db.select(Student.class).list();
        System.out.println("Select all: " + studentList);

        Student s3 = db.select(Student.class).id(1).one();
        System.out.println("Select by id: " + s2);

        Student s4 = db.select(Student.class).where("name = ?", "Nguyen Van A").one();
        System.out.println("Select by name: " + s3);

//        Student s5 = db.select("select * from students").
    }

    @Test
    public void testTest() throws Exception {
        Database db;
        IDatabaseConfig config;

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


    private void testORMQueriescc(IDatabase db) throws Exception {
        db.delete(Bean.class).where("1=1");

        // Insert :
        Bean b1 = new Bean();
        b1.setLocalDate(LocalDate.now());
        b1.setLocalDateTime(LocalDateTime.now());
        b1.setDate(new Date());
        b1.setSomeInt(1);
        b1.setSomeBoolean(true);
        b1.setShortText("short text");
        b1.setLongText("long text");
        b1.setBytes("some bytes".getBytes(StandardCharsets.UTF_8));
        b1.setSubBeans(Arrays.asList(new SubBean(1), new SubBean(2)));
        b1.setTestNameEnum(EnumerationTest.FIRST);
        b1.setTestOrdinalEnum(EnumerationTest.SECOND);
        System.out.println("Created : " + b1);
        db.insert(b1);
        assertNotNull(b1.getId());


        Bean b2 = db.select(Bean.class).one();
//        List<Bean> listBean = db.select(Bean.class)
//                .addColumn(count("last_name"), "count")
//                .groupby("count").list();

        System.out.println("Read in database : " + b2);
        assertTrue(b1.compareWithoutId(b2));
        assertNotSame(b1, b2);

        // Update :
        b2.setShortText("a new short text");
        db.update(b2);
        Bean b3 = db.select(Bean.class).id(b2.getId()).one();
        assertTrue(b2.compareWithoutId(b3));

        // Delete :
        db.delete(b3);
        assertEquals(0, db.select(Bean.class).list().size());

        // ORM select :
        db.sql("truncate table beans");
        db.insert(Arrays.asList(
                new Bean("b1"),
                new Bean("b2"),
                new Bean("b3")));
//        Map<String, Bean> resultAsIndexedObject = RSUtils.index(db.select(Bean.class).list(), "shortText");
//        assertEquals(3, resultAsIndexedObject.size());
//        assertEquals("b1", resultAsIndexedObject.get("b1").getShortText());
    }

    private void testGenericQueriescc(IDatabase db) throws Exception {
        db.sql("truncate table beans");
        db.insert(Arrays.asList(
                new Bean("b1"),
                new Bean("b2"),
                new Bean("b3")));
//        Long countLong = db.select("select count(*) from beans").asPrimitive(Long.class).one();
//        assertEquals(new Long(3), countLong);
//        Integer countInteger = db.select("select count(*) from beans").asPrimitive(Integer.class).one();
//        assertEquals(new Integer(3), countInteger);
//        String countString = db.select("select count(*) from beans").asPrimitive(String.class).one();
//        assertEquals("3", countString);

//        Map<String, Map<String, Object>> resultAsIndexedMaps = RSUtils.index(db.select("select * from beans").toMap().list(), "short_text");
//        Map<String, Map<String, Object>> res = RSUtils.index(db.select("select * from beans").asMap(Bean.class).list(),
//                "short_text");
//        assertEquals(3, resultAsIndexedMaps.size());
//        assertEquals("b1", resultAsIndexedMaps.get("b1").get("short_text"));
    }

    private void testLocalDate(IDatabase db) throws Exception {
        db.sql("truncate table beans");
        Bean b = new Bean();
        b.setLocalDate(LocalDate.now());
        db.insert(b);
        Map<String, Object> data = db.select("select localDate from beans").toMap().one();
        assertEquals(LocalDate.now(), data.get("localDate"));
    }

}
