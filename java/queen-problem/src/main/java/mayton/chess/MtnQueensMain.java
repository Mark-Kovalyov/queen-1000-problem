package mayton.chess;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MtnQueensMain {

    static Logger logger = LoggerFactory.getLogger("Logger1");

    static org.apache.log4j.Logger log4jlogger = org.apache.log4j.Logger.getLogger("Logger2");

    static {
        log4jlogger.setLevel(Level.INFO);
        log4jlogger.setAdditivity(false);
    }

    public static void main(String[] args){

        BasicConfigurator.configure();

        logger.info("Hello from SLF4j with INFO-level");

        log4jlogger.info("Hello from Lo4j-1.2 with INFO-level");

        /*int processors = Integer.parseInt(args[0]);

        ThreadFactory threadFactory = new ThreadFactoryBuilder()
                .setNameFormat("QueensThread-%d")
                .setDaemon(true)
                .build();

        ExecutorService executorService = Executors.newFixedThreadPool(processors, threadFactory);

        executorService.shutdown();*/
    }

}
