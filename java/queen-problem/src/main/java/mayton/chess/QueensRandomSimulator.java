package mayton.chess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileOutputStream;
import java.util.Random;
import java.util.UUID;

import static java.lang.String.format;

public class QueensRandomSimulator {

    private static final int REPEAT_TRIES = 2000;

    static Logger logger = LoggerFactory.getLogger(QueensRandomSimulator.class);

    public static void drawDesk(int queensPecent,int size, int queensGap, String filename) throws Exception {

        RestrictedClassicQueensDesk iqd = new RestrictedClassicQueensDesk(size);

        Random r = new Random();
        int falseResults = 0;
        int queens = 0;
        boolean prevRes = false;
        int i=0;
        int cellsUnderFire = 0;
        while(true) {
            boolean res = iqd.tryToSetQueen(r.nextInt(size), r.nextInt(size));
            if (res) {
                queens++;
            }
            if (queens > queensGap) {
                break;
            }
            if (!res && !prevRes) {
                falseResults++;
            } else {
                falseResults=0;
            }
            prevRes = res;
            if (i>0) {
                if (falseResults > REPEAT_TRIES) {
                    logger.info("Stop by repeating false");
                    break;
                }
            }
            i++;
        }

        ImageUtils.writeImage(new FileOutputStream("images/" + filename + ".png"),iqd);

        for(int x = 0;x<size;x++) {
            for(int y = 0;y<size;y++){
                if (iqd.getValue(x,y) || iqd.isUnderFire(x,y)){
                    cellsUnderFire++;
                }
            }
        }

        logger.info("{};{};{};{};{}",
                queensPecent,
                queens,
                cellsUnderFire,
                size * size,
                (double)cellsUnderFire / (double)(size * size) );

    }


    public static void main(String[] args) throws Exception {
        logger.info("Queens(%);Queens;Cells Under Fire(CUF);Desk Space(DS);CUF/DS ratio");
        int STEP = 5;
        int SIZE = 250;
        for(int i = 0; i < 90; i += STEP) {
            drawDesk(i, SIZE, (i * SIZE) / 100,
                    format("desk-%dx%d-with-%03d-percent-quens-filling",
                        SIZE,
                        SIZE,
                        i
                    )
            );
        }
    }

}
