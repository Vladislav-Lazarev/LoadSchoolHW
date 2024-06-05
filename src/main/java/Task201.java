import ru.pflb.mq.dummy.implementation.ConnectionImpl;
import ru.pflb.mq.dummy.implementation.DestinationImpl;
import ru.pflb.mq.dummy.implementation.ProducerImpl;
import ru.pflb.mq.dummy.interfaces.Connection;
import ru.pflb.mq.dummy.interfaces.Session;

import java.io.BufferedReader;
import java.io.FileReader;

public class Task201 {
    public static void main(String[] args) {
        /*BufferedReader лучше всего подходит для чтения больших файлов, он считывает
        все данные и сохраняет его в буфере, после чего возвращает все символы не из
        входного потока, а из буфера, за счет чего считывание данных происходит быстрее
        используем его, если хотим быстро получить длинные строки из файла, а так же
        для работы с несколькими потоками.*/
        try {
            String FileName = args[0];
            //String FileName = "SMS.dat";
            //String FileName = "C:\\Program Files\\Java\\Program\\LoadSchoolHW\\SMS.dat";
            Connection connection = new ConnectionImpl();
            Session session = connection.createSession(true);
            DestinationImpl destination = new DestinationImpl("MyQueue201");
            ProducerImpl producer =  new ProducerImpl(destination);
            boolean a = true;
            while (a) {
                BufferedReader reader = new BufferedReader(new FileReader(FileName));
                producer.send(reader.readLine());
                Thread.sleep(2000);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
