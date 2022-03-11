package ru.netology.graphics.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

import java.awt.image.WritableRaster;
import java.io.IOException;
import java.net.URL;


public class TextGraphicsConverterImpl implements TextGraphicsConverter {

    private int maxWidth;
    private int maxHeight;
    private double maxRatio;
    private TextColorSchema schema = new TextColorSchemaImpl();


    @Override
    public String convert(String url) throws IOException, BadImageSizeException {

        BufferedImage img = ImageIO.read(new URL(url));
        if (this.maxRatio != 0) {
            double imgRatio = (double) img.getWidth() / img.getHeight();
            if (imgRatio > this.maxRatio) {
                throw new BadImageSizeException(imgRatio, this.maxRatio);
            }
        }
        int newWidth = img.getWidth();
        int newHeight = img.getHeight();


        if (newWidth > this.maxWidth) {

            double ratio = (double) newWidth / this.maxWidth;
            newWidth = (int) (newWidth / ratio);
            newHeight = (int) (newHeight / ratio);
        }
        if (newHeight > this.maxHeight) {
            double ratio = (double) newHeight / this.maxHeight;
            newWidth = (int) (newWidth / ratio);
            newHeight = (int) (newHeight / ratio);
        }


        Image scaledImage = img.getScaledInstance(newWidth, newHeight, BufferedImage.SCALE_SMOOTH);


        BufferedImage bwImg = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_BYTE_GRAY);

        Graphics2D graphics = bwImg.createGraphics();

        graphics.drawImage(scaledImage, 0, 0, null);


        WritableRaster bwRaster = bwImg.getRaster();


        int[] intBuff = new int[3];
        StringBuffer sb = new StringBuffer();

        for (int h = 0; h < newHeight; h++) {
            for (int w = 0; w < newWidth; w++) {
                int color = bwRaster.getPixel(w, h, intBuff)[0];
                char c = schema.convert(color);
                sb.append(c);
                sb.append(c);
            }
            sb.append("\n");
        }

        return sb.toString();

    }

    @Override
    public void setMaxWidth(int width) {
        this.maxWidth = width;
    }

    @Override
    public void setMaxHeight(int height) {
        this.maxHeight = height;
    }

    @Override
    public void setMaxRatio(double maxRatio) {
        this.maxRatio = maxRatio;
    }

    @Override
    public void setTextColorSchema(TextColorSchema schema) {

        this.schema = schema;

    }
}
