package mayton.chess.simulator;

import mayton.chess.ImageUtils;
import mayton.chess.datastructures.*;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Random;

import static java.lang.String.format;

public class QueensRandomSimulator {

    private static final int REPEAT_TRIES = 2000;

    static Logger logger = LoggerFactory.getLogger(QueensRandomSimulator.class);

    static Random r = new Random(System.currentTimeMillis());

    public static boolean drawDesk(
                                RestrictedQueensDeskFactory factory,
                                CSVPrinter csvPrinter,
                                int queensPercent,
                                int queensGap,
                                String filename,
                                String technology) throws Exception {

        RestrictedQueensDesk iqd = factory.getRestrictedQueensDesk();

        int size = iqd.getSize();

        int falseResults = 0;
        int queens = 0;
        boolean prevRes = false;
        int i=0;
        int cellsUnderFire = 0;

        boolean breakByOverflow = false;

        while(!breakByOverflow) {
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
                    logger.warn("Abandoned after {} repeating tries on {} queens for {} tecnhology stack",
                            REPEAT_TRIES,
                            queens,
                            technology);
                    breakByOverflow = true;
                    break;
                }
            }
            i++;
        }

        if (!breakByOverflow) {

            new File("images/"+technology).mkdirs();
            ImageUtils.writeImage(new FileOutputStream("images/" + technology + "/" + filename + ".png"), iqd);

            for (int x = 0; x < size; x++) {
                for (int y = 0; y < size; y++) {
                    if (iqd.getValue(x, y) || iqd.isUnderFire(x, y)) {
                        cellsUnderFire++;
                    }
                }
            }

            int square = size * size;

            Object[] objects = new Object[5];
            objects[0] = queensPercent;
            objects[1] = queens;
            objects[2] = cellsUnderFire;
            objects[3] = square;
            objects[4] = (double) cellsUnderFire / (double) square;

            csvPrinter.printRecord(objects);

        }

        return breakByOverflow;
    }




    public static void main(String[] args) throws Exception {

        int SIZE = 250;

        RestrictedQueensDeskFactory classicFactory = new RestrictedClassicQueensDeskFactory(SIZE);
        RestrictedQueensDeskFactory torusFactory = new RestrictedTorusQueensDeskFactory(SIZE);

        simulateCustomRestrictedDesk("torus", 100, 1, SIZE, torusFactory);
        simulateCustomRestrictedDesk("classic", 100, 1, SIZE, classicFactory);

        return;

    }

    private static void simulateCustomRestrictedDesk(
            String technologyStack, int percentGep, int step, int size,
            RestrictedQueensDeskFactory factory) throws Exception {

        new File("reports/"+technologyStack).mkdirs();

        CSVPrinter csvPrinter = new CSVPrinter(
                new PrintWriter(
                        format("reports/%s/%s-density-report-%dx%d.csv",
                                technologyStack,
                                technologyStack,
                                size,
                                size)),
                        CSVFormat.EXCEL.
                                withHeader(
                                        "Quenns(%)",
                                        "Queens",
                                        "Cells Under Fire(CUF)",
                                        "Desk Space(DS)",
                                        "CUF/DS ratio"));


        for(int i = 0; i <= percentGep; i += step) {

            boolean breakByOverflow = drawDesk(
                    factory,
                    csvPrinter,
                    i,
                    (i * size) / 100,
                    format(technologyStack + "desk-%dx%d-with-%03d-percent-quens-filling",
                        size,
                        size,
                        i
                    ),
                    technologyStack
            );

            if (breakByOverflow) break;
        }

        csvPrinter.close();
    }

}
