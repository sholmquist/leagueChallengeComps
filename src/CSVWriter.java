/*  Custom CSVWriter class borrowed from William Rosenbaum.
    See his website here: https://www.willrosenbaum.com/
*/

import java.io.*;

public class CSVWriter {
    private String path;
    private StringBuilder curLine = new StringBuilder();
    private BufferedWriter out;

    public CSVWriter(String path) {
        this.path = path;
        try {
            out = new BufferedWriter(new FileWriter(path));
        } catch (IOException e) {
            System.err.println("Could not create file: " + path);
        }
    }

    public void addEntry(String str) {
        if (curLine.length() > 0) {
            curLine.append(',');
        }
        curLine.append(str);
    }

    public void addEntry(int value) {
        addEntry(Integer.toString(value));
    }

    public void endLine() {
        try {
            out.write(curLine.toString() + "\n");
        } catch (IOException e) {
	    
	    }
        curLine.setLength(0);
    }

    public void close() {
        try {
            out.close();
            System.out.println("Successfully wrote " + path);
        } catch (IOException e) {
            System.err.println("Could not close file " + path);
        }
    }

}