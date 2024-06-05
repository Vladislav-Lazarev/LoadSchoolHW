
import ru.pflb.mq.dummy.implementation.ConnectionImpl;
import ru.pflb.mq.dummy.implementation.DestinationImpl;
import ru.pflb.mq.dummy.implementation.ProducerImpl;
import ru.pflb.mq.dummy.interfaces.Connection;
import ru.pflb.mq.dummy.interfaces.Session;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Task202 {
    public static void main(String[] args){
        /*Files.lines() Лучше подходит для считывания небольших файлов.
        Работает напрямую с файлом, можно легко управлять файлом, с помощью методов можно
        удобно создавать, удалять, перемещать и копировать файлы.*/
        try {
            String FilePath = args[0];
            //String FilePath = "C:\\Program Files\\Java\\Program\\LoadSchoolHW\\SMS.dat";
            //String FilePath = "SMS.dat";
            Connection connection = new ConnectionImpl();
            Session session = connection.createSession(true);
            DestinationImpl destination = new DestinationImpl("MyQueue202");
            ProducerImpl producer =  new ProducerImpl(destination);

            List<String> list = Files.lines(Paths.get(FilePath).normalize()).collect(Collectors.toList());
            boolean a = true;
            while (a) {
                for (String s : list) {
                    producer.send(s);
                }
                Thread.sleep(2000);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
