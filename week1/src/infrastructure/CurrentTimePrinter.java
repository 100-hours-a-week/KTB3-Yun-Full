package infrastructure;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CurrentTimePrinter implements Runnable {
    @Override
    public void run() {
        while(true){
            System.out.println("\n현재 시각 : " + DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm:ss")
                    .format(LocalDateTime.now()));
            try{
                Thread.sleep(10000);
            }catch(InterruptedException e){
                break;
            }
        }
    }
}
