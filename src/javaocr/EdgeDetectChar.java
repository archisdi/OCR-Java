/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaocr;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import net.sourceforge.javaocr.ocrPlugins.charTracer.CharacterTracer;

/**
 *
 * @author dee
 */
public class EdgeDetectChar {

    public void detectChar() {

        // input file image
//        File inputImage = new File("angka.png");
        File inputImage = new File("graphic1a.jpg");
        System.out.println(inputImage.getAbsolutePath());

        CharacterTracer2 tracer = new CharacterTracer2();
        BufferedImage img = tracer.getTracedImage(inputImage,1);
        int width = img.getWidth();

        //check if width > 100 or 1000
//        System.out.println("w = "+width);
        if (width > 100) {
            try {
                //Make image always 1000px wide
                double scaleAmount = 1000.0 / width;

                AffineTransform tx = new AffineTransform();
                tx.scale(scaleAmount, scaleAmount);

                AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

                img = op.filter(img, null);
                File outputfile = new File("edgeDetectResult.png");
                ImageIO.write(img, "png", outputfile);
            } catch (IOException ex) {
                System.out.println("error");
            }
        }
    }
}
