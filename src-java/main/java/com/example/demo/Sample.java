package com.example.demo;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import jakarta.persistence.*;
import jakarta.persistence.Version;
import lombok.Data;
import org.hibernate.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.stat.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component
public class Sample {




    //@CircuitBreaker(name = "userService", fallbackMethod = "fallback")
    public String getUser(Long id) {
        return null;// userService.getUser(id);
    }

    public String fallback(Long id, Throwable t) {
        return null;//new User(id, "Fallback User");
    }

@Autowired
    StudentRepository studentRepository;
    @Autowired
    EntityManagerFactory emf;


    @Entity
    @Cacheable
    @Data
    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
    class Foo2 {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "ID")
        private long id;

        @Column(name = "NAME")
        private String name;

        // getters and setters
    }

    @Entity
    @Cacheable
    @Data
    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    class Foo3 {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "ID")
        private long id;

        @Column(name = "NAME")
        private String name;

        // getters and setters
    }

    @Entity
    @Cacheable
    @Data
    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
    class Foo4 {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "ID")
        private long id;

        @Column(name = "NAME")
        private String name;

        // getters and setters
    }

    @Entity
    @Cacheable
    @Data
    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    class Foo5 {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "ID")
        private long id;

        @Column(name = "NAME")
        private String name;

        // getters and setters
    }


//    @SpringBootApplication
////    @EnableEurekaServer
//      class EurekaServerApplication {
//        public static void main(String[] args) {
//            SpringApplication.run(EurekaServerApplication.class, args);
//        }
//    }
//    @Entity
//    public class Student {
//
//        // fields, getters and setters
//
//    }
//
//    @Entity(name="student2")
//    public class Student2 {
//
//        // fields, getters and setters
//
//    }

    @Entity
    @Table(name="STUDENT3", schema="SCHOOL")

    public class Student3 {
        @Id
        @GeneratedValue(strategy= GenerationType.AUTO)
        private Long id;

        @Column(name="STUDENT_NAME", length=50, nullable=false, unique=false)
        private String name;

        @Transient
        private Integer age;
        @Temporal(TemporalType.DATE)
        private Date birthDate;
        @Enumerated(EnumType.STRING)
        private Gender gender;

        // getters and setters
    }
    public enum Gender {
        MALE,
        FEMALE
    }
    @Entity
    @Table(name = "users")
    public class User {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "id")
        private Long id;
        //...

        @OneToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "address_id", referencedColumnName = "id")
        private Address address;

        // ... getters and setters
    }

    @Entity
    @Table(name = "address")
    public class Address {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "id")
        private Long id;
        //...

        @OneToOne(mappedBy = "address")
        private User user;

        //... getters and setters
    }

    @Entity
    public class Person {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private int id;
        @Version
        private int version;


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<Address2> getAddresses() {
            return addresses;
        }

        public void setAddresses(List<Address2> addresses) {
            this.addresses = addresses;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        private String name;
        @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
       // @OneToMany(mappedBy = "person", cascade = CascadeType.REMOVE)
        //@OneToMany(mappedBy = "person", cascade = CascadeType.DETACH)
        //@OneToMany(mappedBy = "person", cascade = CascadeType.REFRESH)


        private List<Address2> addresses;

    }

    @Entity
    public class Address2 {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private int id;
        private String street;
        @Version
        private int version;


        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public int getHouseNumber() {
            return houseNumber;
        }

        public void setHouseNumber(int houseNumber) {
            this.houseNumber = houseNumber;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public int getZipCode() {
            return zipCode;
        }

        public void setZipCode(int zipCode) {
            this.zipCode = zipCode;
        }

        public Person getPerson() {
            return person;
        }

        public void setPerson(Person person) {
            this.person = person;
        }

        private int houseNumber;
        private String city;
        private int zipCode;
        @ManyToOne(fetch = FetchType.LAZY)
        private Person person;
    }

    void persist(){
        Person person = new Person();
        Address2 address = new Address2();
        address.setPerson(person);
        person.setAddresses(Arrays.asList(address));
        Connection connection = null;
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        Statistics statistics = sessionFactory.getStatistics();
        long secondLevelCacheHitCount = statistics.getSecondLevelCacheHitCount();
        long secondLevelCacheMissCount = statistics.getSecondLevelCacheMissCount();
        Session currentSession = sessionFactory.openSession();
        Transaction transaction = currentSession.beginTransaction();
        try{
            transaction = currentSession.beginTransaction();


        currentSession.persist(person);
            currentSession.persist(person);
            currentSession.flush();
            int personId = person.getId();
            currentSession.clear();

            Person savedPersonEntity = currentSession.find(Person.class, personId);
            currentSession.remove(savedPersonEntity);
            currentSession.flush();
            currentSession.detach(person);
            try {
                currentSession.update(person);
            }catch (StaleStateException e){

            }catch(TransientObjectException e){

            }catch( NonUniqueObjectException e){

            }
            currentSession.merge(person);
            currentSession.saveOrUpdate(person);


                    currentSession.unwrap(Session.class)
                    .buildLockRequest(new LockOptions(LockMode.NONE))
                    .lock(person);

            Query<Person> query = currentSession.createQuery("FROM Person WHERE id = :id", Person.class);
            query.setParameter("id", 1L);
            query.setLockOptions(new LockOptions(LockMode.PESSIMISTIC_READ));
            query.uniqueResult();
            query.setLockOptions(new LockOptions(LockMode.PESSIMISTIC_WRITE));

            currentSession.get(Person.class, personId,LockMode.OPTIMISTIC);
            currentSession.get(Person.class, personId,LockMode.OPTIMISTIC_FORCE_INCREMENT);
            currentSession.load(Person.class, personId);

            transaction.commit();}
        catch(Exception e){
            transaction.rollback();
        }finally {
            currentSession.close();
        }
        sessionFactory.close();


        Student student = new Student(
               1 , "John Doe", Student.Gender.MALE, "Eng2015001");
        studentRepository.save(student);
        Student retrievedStudent =
                studentRepository.findById("Eng2015001").get();
        retrievedStudent.setName("Richard Watson");
        studentRepository.save(student);
        studentRepository.deleteById(student.getId());
        Student engStudent = new Student(
                1, "John Doe", Student.Gender.MALE, "Eng2015001");
        Student medStudent = new Student(
                 2,"Gareth Houston", Student.Gender.MALE, "Med2015001");
        studentRepository.save(engStudent);
        studentRepository.save(medStudent);
        List<Student> students = new ArrayList<>();
        studentRepository.findAll().forEach(students::add);


        var cache = emf.getCache();

        Foo foo = new Foo();
//        fooService.create(foo);
//        fooService.findOne(foo.getId());
        var wasCached = cache.contains(Foo.class, foo.getId());


        emf.createEntityManager().createQuery("").executeUpdate(); //change query
        jakarta.persistence.Query nativeQuery1 = emf.createEntityManager().createNativeQuery("");//change query
        nativeQuery1
                .executeUpdate();

        nativeQuery1.executeUpdate();


        emf.createEntityManager().createQuery("")//change query
                .setHint("org.hibernate.cacheable", true)
                .getResultList();


        Query nativeQuery = (Query) emf.createEntityManager().createNativeQuery("");//change query
        nativeQuery.unwrap(org.hibernate.query.NativeQuery.class).addSynchronizedEntityClass(Foo.class);
        nativeQuery.executeUpdate();



//        session.persist(person);
//        session.flush();
//        session.clear();
    }
//    @Entity
//    @Table(name="STUDENT")
//    public class Student {
//
//        // fields, getters and setters
//
//    }
//
//
//    @Entity
//    @Table(name="STUDENT", schema="SCHOOL")
//    public class Student {
//
//        // fields, getters and setters
//
//    }

}
