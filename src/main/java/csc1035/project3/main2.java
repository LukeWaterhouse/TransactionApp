package csc1035.project3;
import org.hibernate.Session;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class main2 {

    public static void main(String[] args) {

        Session session = HibernateUtil.getSessionFactory().openSession();


        Student s1 = new Student("b1234", "Jordan",24);
        Student s2 = new Student("b4321", "Jane",23);
        Student s3 = new Student("b4654", "Laura",22);
        Student s4 = new Student("b3541", "Rich",25);

        Set<Student> sl = new HashSet<>(Arrays.asList(s1,s2,s3,s4));

        Module m1 = new Module("csc1031", "Math");
        Module m2 = new Module("csc1032", "Arch");
        Module m3 = new Module("csc1033", "DB");
        Module m4 = new Module("csc1034", "P1");
        Module m5 = new Module("csc1035", "P2");

        Set<Module> ml = new HashSet<>(Arrays.asList(m1,m2,m3,m4,m5));


        session.beginTransaction();

        for (Student stu : sl) {
            session.saveOrUpdate(stu);
        }

        for (Module mod : ml) {
            session.saveOrUpdate(mod);
        }


        for (Student stu : sl) {

            stu.setModules(ml);
            session.persist(stu);
        }
        session.getTransaction().commit();
    }
}

