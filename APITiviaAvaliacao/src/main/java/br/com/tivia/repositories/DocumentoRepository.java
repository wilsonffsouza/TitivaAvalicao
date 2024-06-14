package br.com.tivia.repositories;

import br.com.tivia.conexao.ConexaoHibernateCadastroSqlServer;
import br.com.tivia.model.Documentos;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Wilson.Souza
 */
public class DocumentoRepository {

    public static boolean save(Documentos b) {
        Session session = ConexaoHibernateCadastroSqlServer.getInstance();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(b);
            tx.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            session.close();
        }
        return false;
    }
    
    public static boolean update(Documentos b) {
        Session session = ConexaoHibernateCadastroSqlServer.getInstance();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(b);
            tx.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            session.close();
        }
        return false;
    }
    
    public static boolean delete(Documentos b) {
        Session session = ConexaoHibernateCadastroSqlServer.getInstance();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(b);
            tx.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            session.close();
        }
        return false;
    }
    
    public static ArrayList<Documentos> findAll() {
        Session session = ConexaoHibernateCadastroSqlServer.getInstance();
        try {
            Query q = session.createQuery("FROM Documentos");
            List list = q.list();
            if(list != null && !list.isEmpty()){
                return (ArrayList<Documentos>) list;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }
    
    public static Documentos find(Long id) {
        Session session = ConexaoHibernateCadastroSqlServer.getInstance();
        try {
            Query q = session.createQuery("FROM Documentos WHERE id = :id");
            q.setParameter("id", id);
            List list = q.list();
            if(list != null && !list.isEmpty()){
                return (Documentos) list.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }
    
}
