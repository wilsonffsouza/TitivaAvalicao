package br.com.tivia.repositories;

import br.com.tivia.conexao.ConexaoHibernateCadastroSqlServer;
import br.com.tivia.model.Beneficiario;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Wilson.Souza
 */
public class BeneficiarioRepository {

    public static boolean save(Beneficiario b) {
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
    
    public static boolean update(Beneficiario b) {
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
    
    public static boolean delete(Beneficiario b) {
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
    
    public static ArrayList<Beneficiario> findAll() {
        Session session = ConexaoHibernateCadastroSqlServer.getInstance();
        try {
            Query q = session.createQuery("FROM Beneficiario");
            List list = q.list();
            if(list != null && !list.isEmpty()){
                return (ArrayList<Beneficiario>) list;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }
    
    public static Beneficiario find(Long id) {
        Session session = ConexaoHibernateCadastroSqlServer.getInstance();
        try {
            Query q = session.createQuery("FROM Beneficiario WHERE id = :id");
            q.setParameter("id", id);
            List list = q.list();
            if(list != null && !list.isEmpty()){
                return (Beneficiario) list.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }
    
}
