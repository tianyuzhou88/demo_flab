package org.webdriver.seleniumUI.utils;

import java.io.*;

/**
 * Created by Administrator on
 */
public class CopyReportResources {

     public  static void main(String[] args)
     {
         CopyReportResources copyReportResources=new CopyReportResources();
         copyReportResources.copyResources();
     }
     public void copyResources()
     {
         this.copyFile("src/main/resources/reportResources/back.gif", "test-output/back.gif");
         this.copyFile("src/main/resources/reportResources/close.gif", "test-output/close.gif");
         this.copyFile("src/main/resources/reportResources//logo1.png", "test-output/logo1.png");
         this.copyFile("src/main/resources/reportResources/Chart.js", "test-output/Chart.js");
         this.copyFile("src/main/resources/reportResources/jquery-1.7.1.min.js", "test-output/jquery-1.7.1.min.js");
         this.copyFile("src/main/resources/reportResources/loadxmldoc.js", "test-output/loadxmldoc.js");
         this.copyFile("src/main/resources/reportResources/report.css", "test-output/report.css");
         this.copyFile("src/main/resources/reportResources/report.js", "test-output/report.js");
         this.copyFile("src/main/resources/reportResources/imageshow.css", "test-output/imageshow.css");
     }
    /**
     *
     * @param sourceRelativePath source path
     * @param targetRelativePath target path
     */
    private void copyFile(String sourceRelativePath,String targetRelativePath)
    {
        FileInputStream fileInputStream=null;
        OutputStream imageOutputStream=null;
        try {
             fileInputStream=new FileInputStream(new File(sourceRelativePath));
            imageOutputStream=new FileOutputStream(targetRelativePath);//
            byte[] b = new byte[1024];
            int startbyte = -1;
            while ((startbyte= fileInputStream.read(b)) != -1) {// reading
                imageOutputStream.write(b, 0, startbyte);//
            }
            System.out.println("successfully copy the file");
        } catch (IOException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        finally{
            if (fileInputStream!=null) {

                try {
                    fileInputStream.close();

                } catch (IOException e2) {
                    // TODO: handle exception
                    System.out.println("fail to read input stream");
                }
            }
            if (imageOutputStream!=null) {

                try {
                    imageOutputStream.close();
                } catch (Exception e2) {
                    // TODO: handle exception
                    System.out.println("fial to turn off output stream");
                }

            }
        }

    }
}
