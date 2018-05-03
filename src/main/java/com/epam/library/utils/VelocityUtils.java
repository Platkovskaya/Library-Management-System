package com.epam.library.utils;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.StringWriter;

/**
 * Created by Irina on 17.04.2018.
 */
public class VelocityUtils {

    private VelocityUtils(){}

    public static String getString(String templateName, VelocityContext vContext) {
        BufferedWriter bw = null;
        try {
            Velocity.init();
            Template template = Velocity.getTemplate(templateName);
            StringWriter sw = new StringWriter();
            bw = new BufferedWriter(sw);
            template.merge(vContext, bw);
            bw.flush();
            return sw.toString();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    throw new RuntimeException(e.getMessage(), e);
                }
            }
        }
    }

}
