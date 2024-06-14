package br.com.tivia.utils;


import java.io.FileInputStream;
import java.util.Properties;
import org.apache.commons.collections4.MapUtils;

/**
 * @author Wilson.Souza
 * Descricao: Obtem propriedades do arquivo de configuracoes
 */
public class HostPropertiesUtil {

    /**
     * Descricao: Obtem propriedades do arquivo de host.properties
     * @return 
     */
    public static Properties getProp() {
        try {
            Properties props = new Properties();
            FileInputStream file = new FileInputStream("./config/host.properties");
            props.load(file);
            return props;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Descricao: Obtem propriedades do arquivo de database_config.properties
     * @return 
     */
    public static Properties getPropDataBaseConfig() {
        try {
            Properties props = new Properties();
            FileInputStream file = new FileInputStream("./config/database_config.properties");
            props.load(file);
            return props;
        } catch (Exception e) {
            return null;
        }
    }
    
    public static String getPropVersionToApiInfo() {
        try {
            Properties props = new Properties();
            FileInputStream file = new FileInputStream("./config/version.properties");
            props.load(file);

            String versao = MapUtils.getString(props, "DOC_BUILD");
            return versao;
        } catch (Exception e) {
            return null;
        }
    }
}
