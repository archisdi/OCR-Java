/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaocr;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dee
 */
public class JavaOCR {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        EdgeDetectChar g = new EdgeDetectChar();
        g.detectChar();
//
//        ExtractChar o = new ExtractChar();
//        o.extractLine();
//        
//        ExtractLine o = new ExtractLine();
//        o.extractLine();
//        
        OCRProcess r = new OCRProcess();
//        r.trainOCR();
//        r.saveModel();

        r.loadModel();
//        r.testOCR2("test.jpg",8);
        r.testOCR("mix.PNG");
//        r.processFolder("bea\\new folder");

    }

}
