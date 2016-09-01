/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaocr;

import com.google.gson.Gson;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import net.sourceforge.javaocr.ocrPlugins.handWriting.HandwritingOCR;
import net.sourceforge.javaocr.ocrPlugins.mseOCR.CharacterRange;
import net.sourceforge.javaocr.ocrPlugins.mseOCR.OCRScanner;
import net.sourceforge.javaocr.ocrPlugins.mseOCR.TrainingImage;
import net.sourceforge.javaocr.ocrPlugins.mseOCR.TrainingImageLoader;

/**
 *
 * @author dee
 */
public class OCRProcess {

    private HashMap<Character, ArrayList<TrainingImage>> trainingImages;
    private OCRScanner ocrScanner;
    private HandwritingOCR handOcr;

    public void saveModel() {
        FileWriter f = null;
        try {
            Gson g = new Gson();
            String json = g.toJson(ocrScanner);
            f = new FileWriter("model");
            f.write(json);
            f.flush();
            f.close();
        } catch (IOException ex) {
            Logger.getLogger(OCRProcess.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                f.close();
            } catch (IOException ex) {
                Logger.getLogger(OCRProcess.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void loadModel() {
        FileReader f = null;
        try {
            f = new FileReader("model");
            BufferedReader b = new BufferedReader(f);
            String json = "";
            String line;
            while ((line = b.readLine()) != null) {
                json += line;
            }
            Gson g = new Gson();
            ocrScanner = g.fromJson(json, OCRScanner.class);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(OCRProcess.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(OCRProcess.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                f.close();
            } catch (IOException ex) {
                Logger.getLogger(OCRProcess.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void trainOCR() {
        try {
            ocrScanner = new OCRScanner();
            trainingImages = new HashMap<Character, ArrayList<TrainingImage>>();
            TrainingImageLoader loader = new TrainingImageLoader();

//            String imageTrain = "lowercase.png";
            String imageTrain = "angka.png";
            int Start = "0".charAt(0);
            int End = "9".charAt(0);
            CharacterRange charRange = new CharacterRange(Start, End);
            loader.load(
                    imageTrain,
                    charRange,
                    trainingImages);

            // kalau misal mau nambah image untuk train
            String imageTrain2 = "uppercase.png";
            Start = "A".charAt(0);
            End = "Z".charAt(0);
            charRange = new CharacterRange(Start, End);

            loader.load(
                    imageTrain2,
                    charRange,
                    trainingImages);
            loader.load(
                    imageTrain2,
                    charRange,
                    trainingImages);

            ocrScanner.addTrainingImages(trainingImages);

//            String imageTarget = "mix.png";
//            BufferedImage targetImage = ImageIO.read(new File(imageTarget));
//            String text = ocrScanner.scan(targetImage, 0, 0, 0, 0, null);
//            System.out.println(text);
        } catch (IOException ex) {
            Logger.getLogger(OCRProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void testOCR(String imageTarget) {
        try {
            BufferedImage targetImage = ImageIO.read(new File(imageTarget));
            String text = ocrScanner.scan(targetImage, 0, 0, 0, 0, null);
            System.out.println("output: "+text);
        } catch (IOException ex) {
            Logger.getLogger(OCRProcess.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void testOCR2() {
        try {
            String imageTarget = "test.jpg";
            BufferedImage targetImage = ImageIO.read(new File(imageTarget));
            int total = 9;
            int spc = targetImage.getHeight() / total;
            String text = "";
            for (int i = 0; i < total; i++) {
                text = ocrScanner.scan(targetImage, i * spc, 0, 0, (i + 1) * spc, null);
            }
            System.out.println(text.replaceAll("  ", " "));
        } catch (IOException ex) {
            Logger.getLogger(OCRProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void testOCR2(String imageTarget, int total) {
        try {
            System.out.println("t=" + total);
            BufferedImage targetImage = ImageIO.read(new File(imageTarget));
            int spc = targetImage.getHeight() / total;
            String text = "";
            for (int i = 0; i < total; i++) {
                text = ocrScanner.scan(targetImage, i * spc, 0, 0, (i + 1) * spc, null);
            }
            System.out.println(text.replaceAll("   ", " ").replaceAll("  ", " ").replaceAll("MTERNAL", "INTERNAL"));
        } catch (IOException ex) {
//            Logger.getLogger(OCRProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void processFolder(String ffolder) {

        try {
            File folder = new File(ffolder);
            File[] listOfFiles = folder.listFiles();

            for (File file : listOfFiles) {
                if (file.isFile() && file.getName().endsWith("jpg")) {
//                    testOCR2(ffolder + "//" + file.getName(), Integer.parseInt(file.getName().substring(2, 4)));
                    testOCR(ffolder + "//" + file.getName());
                }
            }
        } catch (Exception e) {
            System.out.println("error ");
            e.printStackTrace();
        }

    }

}
