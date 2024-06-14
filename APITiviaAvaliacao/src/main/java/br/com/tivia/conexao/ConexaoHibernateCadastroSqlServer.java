package br.com.tivia.conexao;

import br.com.tivia.utils.HostPropertiesUtil;

import java.io.File;
import org.apache.commons.collections4.MapUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author lck.admin
 */
public class ConexaoHibernateCadastroSqlServer {

    private static final SessionFactory sessionFactory;
    private static final ThreadLocal<Session> threadLocal = new ThreadLocal<>();

    static {
        try {
            File f = new File("config" + File.separator + "hibernateconfig_cadastro_sqlserver.xml");
            Configuration config = new Configuration().configure(f);
            config.setProperty("hibernate.connection.url", MapUtils.getString(HostPropertiesUtil.getPropDataBaseConfig(), "CADASTRO_SQL_CONNECTION_URL")
                    + ":" + MapUtils.getString(HostPropertiesUtil.getPropDataBaseConfig(), "CADASTRO_SQL_CONNECTION_PORT"));

            config.setProperty("hibernate.connection.username", MapUtils.getString(HostPropertiesUtil.getPropDataBaseConfig(), "CADASTRO_SQL_CONNECTION_USER"));
            config.setProperty("hibernate.connection.password", MapUtils.getString(HostPropertiesUtil.getPropDataBaseConfig(), "CADASTRO_SQL_CONNECTION_PASS"));

            // Configure o protocolo SSL/TLS
            config.setProperty("hibernate.connection.sslProtocol", "TLSv1.2");

            // Configure o contexto SSL
            config.setProperty("hibernate.connection.sslContext", "javax.net.ssl.SSLContext");

            // Configure o socket factory
            config.setProperty("hibernate.connection.socketFactory", "org.postgresql.ssl.NonValidatingFactory");

            sessionFactory = config.buildSessionFactory();

        } catch (Exception e) {
            e.printStackTrace();
            throw new ExceptionInInitializerError(e);
            //Logger.getLogger(ConexaoHibernate.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public static Session getInstance() {
        Session session = sessionFactory.openSession();
        threadLocal.set(session);
        return session;
    }
}
