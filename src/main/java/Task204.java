import ru.pflb.mq.dummy.implementation.ConnectionImpl;
import ru.pflb.mq.dummy.implementation.DestinationImpl;
import ru.pflb.mq.dummy.implementation.ProducerImpl;
import ru.pflb.mq.dummy.interfaces.Connection;
import ru.pflb.mq.dummy.interfaces.Session;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Task204 {
    public static void main(String[] args){
        try {
            String FilePath = args[0];
            //String FilePath = "C:\\Program Files\\Java\\Program\\LoadSchoolHW\\SMS.dat";
            //String FilePath = "SMS.dat";
            Connection connection = new ConnectionImpl();
            Session session = connection.createSession(true);
            DestinationImpl destination = new DestinationImpl("MyQueue202");
            ProducerImpl producer =  new ProducerImpl(destination);

            Stream<String> stream = Files.lines(Paths.get(FilePath));
            List<String> message  = stream.collect(Collectors.toList());
            boolean a = true;
            while (a) {
                for (String s : message) {
                    producer.send(s);
                    Thread.sleep(2000);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
