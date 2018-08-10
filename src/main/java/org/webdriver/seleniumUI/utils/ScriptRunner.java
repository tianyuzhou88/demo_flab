package org.webdriver.seleniumUI.utils;

import java.io.*;


public class ScriptRunner {

    public String resultOutput = null;

    public static void main(String[] args) throws Exception {
       ScriptRunner cm = new ScriptRunner();
       cm.runScript("script/PLR_Reserve.expect","filename1.txt","172.20.222.72","BAAAvm-i3y5mT-2Uceak-HjwGPq-eboHFz-R76w");

   //    System.out.println(cm.readLineVarFile("outfile_ReturnPLR.txt"));

    }

    public void runScript(String scriptPath, String OutputFile) throws Exception{
        try {
            ProcessBuilder pb = new ProcessBuilder(scriptPath);
            Process ps = pb.start();
            ps.waitFor();
            BufferedReader br = new BufferedReader(new InputStreamReader(ps.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            String result = sb.toString();
            resultOutput = result;
            // System.out.println(result);
            try (PrintWriter out = new PrintWriter(OutputFile)) {
                out.println(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void runScript(String scriptPath, String OutputFile, String IP) throws Exception{
        try {
            ProcessBuilder pb = new ProcessBuilder(scriptPath,IP);
            Process ps = pb.start();
            ps.waitFor();
            BufferedReader br = new BufferedReader(new InputStreamReader(ps.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            String result = sb.toString();
            // System.out.println(result);
            resultOutput = result;
            try (PrintWriter out = new PrintWriter(OutputFile)) {
                out.println(result);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void runScript(String scriptPath, String OutputFile, String IP, String AuthCode) throws Exception{
        try {
            ProcessBuilder pb = new ProcessBuilder(scriptPath,IP,AuthCode);
            Process ps = pb.start();
            ps.waitFor();
            BufferedReader br = new BufferedReader(new InputStreamReader(ps.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            String result = sb.toString();
            // System.out.println(result);
            resultOutput = result;
            try (PrintWriter out = new PrintWriter(OutputFile)) {
                out.println(result);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

     public int getTotalLines(String fileName) throws IOException {
         BufferedReader in = new BufferedReader(new InputStreamReader(
                 new FileInputStream(fileName)));
         LineNumberReader reader = new LineNumberReader(in);
         String s = reader.readLine();
         int lines = 0;
         while (s != null) {
             lines++;
             s = reader.readLine();
         }
         reader.close();
         in.close();
         return lines;
     }


    public String readLineVarFile(String fileName) throws IOException {
        String returnCode = null;
        int lineNumber = getTotalLines(fileName) - 2;
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                new FileInputStream(fileName)));
        String line = reader.readLine();
        if (lineNumber < 0 || lineNumber > getTotalLines(fileName)) {
            System.out.println("the line is not within the range");
        }
        int num = 0;
        while (line != null) {
            if (lineNumber == ++num) {
                System.out.println("line     " + lineNumber + ":     " + line);
                returnCode = line;
            }
            line = reader.readLine();

        }
        reader.close();
        return returnCode;
    }
}
