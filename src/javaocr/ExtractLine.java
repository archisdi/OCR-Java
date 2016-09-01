/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaocr;

import java.io.File;
import net.sourceforge.javaocr.ocrPlugins.charExtractor.CharacterExtractor;
import net.sourceforge.javaocr.ocrPlugins.lineExtractor.LineExtractor;

/**
 *
 * @author dee
 */
public class ExtractLine {

    public void extractLine() {
                
        // input file image
//        File inputImage = new File("asciiShuffled.png");
        File inputImage = new File("mix2.png");
        
        // input directory output
        File outputDir = new File("outputDir");
        LineExtractor slicer = new LineExtractor();
        slicer.slice(inputImage, outputDir);
    }

}
