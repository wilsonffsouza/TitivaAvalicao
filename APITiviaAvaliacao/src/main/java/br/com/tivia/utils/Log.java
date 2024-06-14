/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tivia.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.collections4.MapUtils;

/**
 *
 * @author Wilson.Souza
 */
public class Log {

    public static void logOutErrosCriticos(String input) {
        System.out.println(dateTime() + " " + input + "");
        if (MapUtils.getIntValue(HostPropertiesUtil.getPropDataBaseConfig(), "HABILITA_LOG", 0) == 1) {
            BufferedWriter buffWrite;
            try {
                buffWrite = new BufferedWriter(new FileWriter(getDiretorioLogs() + System.getProperty("file.separator") + "erros_criticos.txt", true));
                buffWrite.append(dateTime() + " " + input + "\r\n");
                buffWrite.close();

            } catch (IOException ex) {
                Logger.getLogger(Log.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void logDiario(String input) {
        System.out.println(dateTime() + " " + input + "");
        if (MapUtils.getIntValue(HostPropertiesUtil.getPropDataBaseConfig(), "HABILITA_LOG", 0) == 1) {
            BufferedWriter buffWrite;
            try {
                buffWrite = new BufferedWriter(new FileWriter(getDiretorioLogs() + System.getProperty("file.separator") + "logDiario_" + dateTime().substring(0, 10) + ".json", true));
                buffWrite.append(input + "\r\n");
                buffWrite.close();

            } catch (IOException ex) {
                Logger.getLogger(Log.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static String getDiretorioLogs() {
        return "LOG";
    }

    public static String dateTime() {
        Date today = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(today);
    }

    protected static String date() {
        Date today = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(today);
    }

}
