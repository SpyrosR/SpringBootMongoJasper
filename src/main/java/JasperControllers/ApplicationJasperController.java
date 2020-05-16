package JasperControllers;

import net.sf.jasperreports.engine.*;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class ApplicationJasperController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public byte[] createPdfReport() throws IOException, JRException {

        logger.info("ApplicationJasperController ::: createPdfReport() called");
        byte[] bytes = null;

        try {
            final InputStream stream = this.getClass().getResourceAsStream("/application.jrxml");
            final JasperReport report = JasperCompileManager.compileReport(stream);
            final Map<String, Object> parameters = new HashMap<>();
            final JasperPrint print = JasperFillManager.fillReport(report, parameters, new JREmptyDataSource());
            final String filePath = "\\";

            bytes = JasperExportManager.exportReportToPdf(print);
            logger.info("ApplicationJasperController ::: createPdfReport() ::: returning normally");
        }catch (Exception e){
            logger.error(e.getMessage());
            logger.info("ApplicationJasperController ::: createPdfReport() ::: returning with ERROR");
        }

        return bytes;

    }
}
