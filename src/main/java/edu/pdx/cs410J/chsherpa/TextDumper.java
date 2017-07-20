package edu.pdx.cs410J.chsherpa;

import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.AirlineDumper;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
/**
 * Created by chsherpa on 7/18/17.
 */
public class TextDumper implements AirlineDumper {
    String newFileName;

    public TextDumper(String fileName, Airline airline ) {
        this.newFileName = new String(fileName);
        fileCreate(this.newFileName);

        try { dump( airline ); }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Function acts much like touch command of unix
     *
     * @param fileName File to be created
     */
    public void fileCreate(String fileName) {
        File newFile = new File(fileName);
        //Check if file exist; if not, create one
        try {
            newFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dump(AbstractAirline airline) throws IOException {
        FileWriter write = new FileWriter(this.newFileName);
    }
}
