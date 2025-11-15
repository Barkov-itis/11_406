package ru.itis.servlets;

import ru.itis.models.FileInfo;
import ru.itis.services.FilesService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/uploaded/files")
public class FilesServlet extends HttpServlet {

    private FilesService filesService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fileId = req.getParameter("id");
        // полцчили информацию о загружаемом файле
        FileInfo fileInfo = filesService.getFileInfo(Long.parseLong(fileId));
        // в ответ указали тип данных
        resp.setContentType(fileInfo.getType());
        // в ответ указали размер данных
        resp.setContentLength(fileInfo.getSize().intValue());
        // в ответ указали оригинальное название файла
        resp.setHeader("Content-Disposition", "filename=\"" + fileInfo.getOriginalFileName());
        // записали даные файла в ответ
        filesService.writeFileFromStorage(Long.parseLong(fileId), resp.getOutputStream());
        resp.flushBuffer();

    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.filesService = (FilesService) config.getServletContext().getAttribute("filesService");
    }
}
