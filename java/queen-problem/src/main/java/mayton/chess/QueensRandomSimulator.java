package mayton.chess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileOutputStream;
import java.util.Random;
import java.util.UUID;

public class QueensRandomSimulator {

    static Logger logger = LoggerFactory.getLogger(QueensRandomSimulator.class);

    public static void main(String[] args) throws Exception {

        int SIZE = 250;

        RestrictedClassicQueensDesk iqd = new RestrictedClassicQueensDesk(SIZE);

        Random r = new Random();
        int falseResults = 0;
        boolean prevRes = false;
        int i=0;
        while(true) {
            boolean res = iqd.tryToSetQueen(r.nextInt(SIZE), r.nextInt(SIZE));
            if (res == false && prevRes == false) {
                falseResults++;
            } else {
                falseResults=0;
            }
            prevRes = res;
            if (i>0) {
                if (falseResults > 100) {
                    logger.info("Stop by repeating false");
                    break;
                }
            }
            i++;
        }

        ImageUtils.writeImage(new FileOutputStream("images/" + UUID.randomUUID() + ".png"),iqd);

    }

}
