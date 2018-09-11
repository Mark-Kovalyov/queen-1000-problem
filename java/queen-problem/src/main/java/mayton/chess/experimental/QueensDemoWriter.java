package mayton.chess.experimental;

import com.jbrown.image2avi.core.AVIOutputStream;
import com.jbrown.image2avi.core.VideoFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import static mayton.chess.Constants.VIDEO_ROOT;

public class QueensDemoWriter {

    static Logger logger = LoggerFactory.getLogger(QueensDemoWriter.class);


    // $ ffmpeg -i video/queen-250x250.avi -vcodec h264 -an video/queen-250x250-h264.mp4
    //
    // $ ffmpeg -i video/queen-250x250.avi \
    //           -codec:v libx264
    //           -preset medium
    //           -crf 23
    //           -codec:a aac
    //           -strict experimental
    //           -b:a 192k
    //           -metadata title="A movie by Frdric_Lvesque" output.mp4
    public static void main(String[] args ) throws IOException {

        generate();

    }

    private static void generate() throws IOException {
        int WIDTH = 1600 / 4;
        int HEIGHT = 900 / 4;

        Random r = new Random();

        File outputFile = new File(VIDEO_ROOT + "/queen-250x250.avi");

        AVIOutputStream out = new AVIOutputStream(outputFile, VideoFormat.RAW);

        out.setVideoCompressionQuality(1f);
        out.setVideoDimension(WIDTH,HEIGHT);

        out.setTimeScale(1);
        out.setFrameRate(30);

        for (int k = 0; k<200; k++) {

            logger.info(":: Frame # {}", k);

            BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

            Graphics2D g = image.createGraphics();

            for(int y = 0; y < HEIGHT; y++) {
                for(int x = 0; x < WIDTH; x++) {
                    image.setRGB(x, y, 0xFF000000 |
                            (r.nextInt(255) << 16 |
                            (r.nextInt(255) << 8 |
                                    (r.nextInt(255)))));
                }
            }

            for(int s = 0; s < 10 ; s++) {
                g.setColor(Color.getHSBColor(r.nextFloat(),r.nextFloat(),r.nextFloat()));
                g.fillRect(r.nextInt(WIDTH),r.nextInt(HEIGHT),r.nextInt(WIDTH),r.nextInt(HEIGHT));
            }

            out.writeFrame(image);
        }

        out.close();
    }

}
