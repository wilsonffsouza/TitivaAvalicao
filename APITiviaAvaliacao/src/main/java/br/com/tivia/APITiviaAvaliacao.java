package br.com.tivia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import br.com.tivia.utils.Log;

import java.io.File;

@SpringBootApplication
public class APITiviaAvaliacao {

    
    public static void main(String[] args) {
        File f = new File(Log.getDiretorioLogs());
        if (!f.exists()) {
            f.mkdir();
        }

        SpringApplication.run(APITiviaAvaliacao.class, args);
    }

}
