package mayton.chess;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

public class ImageUtils {

    private ImageUtils() {
    }

    public void fillRect(BufferedImage image, Rectangle rectangle, int color) {
        for (int y = rectangle.y1; y < rectangle.y2; y++) {
            for (int x = rectangle.x1; x < rectangle.x2; x++) {
                image.setRGB(x, y, color);
            }
        }
    }


    public static void writeImage(OutputStream os, RestrictedClassicQueensDesk iqd) throws IOException {

        int size = iqd.getSize();

        int cellSize = 4;
        int imageSize = cellSize * size;

        BufferedImage image = new BufferedImage(imageSize, imageSize, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();

        Color YELLOW = Color.YELLOW;
        Color GREEN = Color.GREEN;
        Color BLACK = Color.BLACK;
        Color GRAY = Color.LIGHT_GRAY;

        // Fill black back
        g.setColor(BLACK);
        g.fillRect(0, 0, imageSize, imageSize);

        for (int y = 0; y < iqd.getSize(); y++) {
            for (int x = 0; x < iqd.getSize(); x++) {

                int xx = x * cellSize;
                int yy = y * cellSize;

                if (iqd.getValue(x, y)) {
                    g.setColor(BLACK);
                } else if (iqd.isUnderFire(x, y)) {
                    g.setColor(YELLOW);
                } else {
                    g.setColor(GREEN);
                }

                g.fillRect(xx, yy, cellSize - 1, cellSize - 1);
            }
        }

        ImageIO.write(image, "PNG", os);
    }

}
