/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaocr;

import java.io.File;
import net.sourceforge.javaocr.ocrPlugins.charExtractor.CharacterExtractor;

/**
 *
 * @author dee
 */
public class ExtractChar {

    public void extractChar() {
        
        int pixelWidth = 75;
        int pixelHeight = 75;
        
        // input file image
//        File inputImage = new File("asciiShuffled.png");
        File inputImage = new File("mix.png");
        
        // input directory output
        File outputDir = new File("outputDir");
        CharacterExtractor slicer = new CharacterExtractor();
        slicer.slice(inputImage, outputDir, pixelWidth, pixelHeight);
    }

}
