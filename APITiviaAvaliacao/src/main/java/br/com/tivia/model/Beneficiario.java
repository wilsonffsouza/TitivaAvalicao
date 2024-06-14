package br.com.tivia.model;

import java.io.Serializable;
import java.time.ZonedDateTime;
import static java.time.format.DateTimeFormatter.ofPattern;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author Wilson.Souza
 */
@Data
@Entity
@Table(name = "tivia.dbo.beneficiarios")
public class Beneficiario implements Serializable {
    
     
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private String telefone;
    private String data_nascimento;
    private String data_created = ZonedDateTime.now().format(ofPattern("yyyy-MM-dd HH:mm:ss"));
    private String data_updated;
    
}
