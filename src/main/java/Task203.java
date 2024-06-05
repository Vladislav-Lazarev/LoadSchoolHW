import ru.pflb.mq.dummy.implementation.ConnectionImpl;
import ru.pflb.mq.dummy.implementation.DestinationImpl;
import ru.pflb.mq.dummy.implementation.ProducerImpl;
import ru.pflb.mq.dummy.interfaces.Connection;
import ru.pflb.mq.dummy.interfaces.Session;

import java.io.File;
import java.util.Scanner;

public class Task203 {
    public static void main(String[] args) {
        /*Scanner выполняет синтаксический анализ входных данных, с использованием разделителя
        разбивает поток на примитивные типы данных*/
        try {
            //String FileName = args[0];
            String FileName = "C:\\Program Files\\Java\\Program\\LoadSchoolHW\\SMS.dat";
            //String FileName = "SMS.dat";
            Connection connection = new ConnectionImpl();
            Session session = connection.createSession(true);
            DestinationImpl destination = new DestinationImpl("MyQueue203");
            ProducerImpl producer = new ProducerImpl(destination);
            boolean a = true;
            while(a) {
                Scanner scanner = new Scanner(new File(FileName));
                while (scanner.hasNextLine()) {
                    producer.send(scanner.nextLine());
                    Thread.sleep(2000);
                }
                scanner.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
