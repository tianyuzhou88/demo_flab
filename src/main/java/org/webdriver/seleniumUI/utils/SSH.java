package org.webdriver.seleniumUI.utils;
import com.jcraft.jsch.*;
import java.io.*;

import java.util.Properties;

public class SSH {

    private static JSch jSch = new JSch();
    private static Session session;
    private static Channel channel;
    private Properties config = new Properties();
    private final String ip;
    private final String usr;
    private final String pwd;

    public SSH(String ip, String usr, String pwd) {
        this.ip = ip;
        this.usr = usr;
        this.pwd = pwd;
    }

    public void sshConnect() {
        try {
            session = jSch.getSession(usr, ip);
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.setPassword(pwd);
            session.connect(60000);
        } catch (JSchException e) {
            System.out.println("Connection error: either config is incorrect or you have made too many attempts.");
            System.out.println("Check your work, or wait a few.");
            e.printStackTrace();
        }
    }

    public void executeCommand(String ... commands) throws Exception {
        for (String command : commands) {
            StringBuilder stringBuilder = new StringBuilder();
            channel = session.openChannel("exec");
            ((ChannelExec) channel).setCommand(command);
            InputStream inputStream = channel.getInputStream();
            channel.connect();
            channel.disconnect();
            // this collects output from the executed command and prints to console
            int readBytes = inputStream.read();
            while(readBytes != 0xffffffff) {
                stringBuilder.append((char) readBytes);
                readBytes = inputStream.read();
            }
            stringBuilder.append((char) readBytes);
            System.out.println(stringBuilder.toString());
        }
    }

    public String executeShell(String ... commands) throws Exception {
        channel = session.openChannel("shell");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        OutputStream outputStream = channel.getOutputStream();
        channel.connect();
        for (String command : commands) {
            outputStream.write((command + "\n").getBytes());
            outputStream.flush();
            // ensure enough time has elapsed before executing next command
            Thread.sleep(1500);
            channel.setOutputStream(byteArrayOutputStream);

        }
        channel.disconnect();
        return new String(byteArrayOutputStream.toByteArray());
    }

    public void disconnectConnection() {
        session.disconnect();
    }
}