/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaocr;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import net.sourceforge.javaocr.ocrPlugins.charTracer.CharacterTracer;
import net.sourceforge.javaocr.scanner.DocumentScanner;
import net.sourceforge.javaocr.scanner.PixelImage;

/**
 *
 * @author dee
 */
public class CharacterTracer2 extends CharacterTracer{
    private DocumentScanner documentScanner = new DocumentScanner();
    private BufferedImage bfImage;
    private Graphics2D bfImageGraphics;
    private static final Logger LOG = Logger.getLogger(CharacterTracer.class.getName());

    public BufferedImage getTracedImage(File inputImage, int total)
    {
        try
        {
            bfImage = ImageIO.read(inputImage);
            bfImageGraphics = bfImage.createGraphics();

            Image img = ImageIO.read(inputImage);
            PixelImage pixelImage = new PixelImage(img);
            pixelImage.toGrayScale(true);
            pixelImage.filter();
            int spc = pixelImage.height / total;
//            int i = 0;
            for (int i = 0; i < total; i++) {
//                {
//                System.out.println(pixelImage.height+" - "+spc+" - ii = "+(i*spc)+" - "+((i+1)*spc));
                documentScanner.scan(pixelImage, this, 0, i*spc, pixelImage.width, (i+1)*spc);
//                documentScanner.scan(pixelImage, this, 0, 0, pixelImage.width, pixelImage.height);
            }
            
        }
        catch (IOException ex)
        {
            LOG.log(Level.SEVERE, null, ex);
        }
        bfImageGraphics.dispose();

        return bfImage;
    }
     @Override
    public void processChar(PixelImage pixelImage, int x1, int y1, int x2, int y2, int rowY1, int rowY2)
    {
        try
        {
            bfImageGraphics.setStroke(new BasicStroke(4));
            bfImageGraphics.setColor(Color.red);
            bfImageGraphics.drawRect(x1, y1, x2 - x1, y2 - y1);
        }
        catch (Exception ex)
        {
            LOG.log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void processSpace(PixelImage pixelImage, int x1, int y1, int x2, int y2)
    {
        try
        {
            bfImageGraphics.setStroke(new BasicStroke(4));
            bfImageGraphics.setColor(Color.yellow);
            bfImageGraphics.drawRect(x1, y1, x2 - x1, y2 - y1);
        }
        catch (Exception ex)
        {
           LOG.log(Level.SEVERE, null, ex);
        }
    }
}
