package by.epam.finalTask.controller.command.Impl;

import by.epam.finalTask.controller.command.Command;
import by.epam.finalTask.controller.command.CommandException;
import by.epam.finalTask.controller.util.RequestParameterName;
import by.epam.finalTask.controller.util.ResourceManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class GetTrack implements Command {

    private final static Logger logger= LogManager.getLogger(GetTrack.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {

        String filename=req.getParameter(RequestParameterName.FILENAME);

        if(filename!=null && !filename.isEmpty()){

            ServletContext servletContext=req.getServletContext();
            String filePath=servletContext.getRealPath("\\..\\..")+ File.separator+ ResourceManager.UPLOAD_DIRECTORY+filename;
            String mime=servletContext.getMimeType(filePath);

            if (mime == null) {
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                logger.warn("mime of ("+filePath+") is null");
                return;
            }

            resp.setContentType(mime);
            File file = new File(filePath);
            resp.setContentLength((int) file.length());

            try {
                OutputStream outputStream = resp.getOutputStream();
                FileInputStream inputStream = new FileInputStream(file);

                byte[] buf = new byte[1024 * 1024 * 20];
                int count = 0;
                while ((count = inputStream.read(buf)) >= 0) {
                    outputStream.write(buf, 0, count);
                }
                outputStream.close();
                inputStream.close();
            } catch (IOException e) {

                logger.error(e);
                throw new CommandException(e);
            }

        }
    }
}
