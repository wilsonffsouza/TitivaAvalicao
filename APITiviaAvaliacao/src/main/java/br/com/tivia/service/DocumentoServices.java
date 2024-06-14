package br.com.tivia.service;

import br.com.tivia.enums.TipoRetorno;
import br.com.tivia.form.DocumentoForm;
import br.com.tivia.model.Documentos;
import br.com.tivia.repositories.DocumentoRepository;
import br.com.tivia.utils.Log;
import br.com.tivia.validation.documento.DocumentosValidation;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Wilson.Souza
 */
@Service
public class DocumentoServices {

    public static TipoRetorno save(DocumentoForm form) {
        Log.logDiario("Inicio do processamento DocumentoServices.save");
        TipoRetorno retorno = null;

        Documentos d = form.converter();

        retorno = DocumentosValidation.validation(d);
        if (retorno == null) {
            if (DocumentoRepository.save(d)) {
                retorno = TipoRetorno.SOCESSO;
            } else {
                retorno = TipoRetorno.FALHA_PERSISTENCIA;
            }
        }
        Log.logDiario("Finalizacao do processamento DocumentoServices.save");
        return retorno;
    }

    public static TipoRetorno update(DocumentoForm form) {
        Log.logDiario("Inicio do processamento DocumentoServices.update");
        TipoRetorno retorno = null;

        Documentos d = form.converter();

        retorno = DocumentosValidation.validation(d);
        if (retorno == null) {
            if (DocumentoRepository.update(d)) {
                retorno = TipoRetorno.SOCESSO;
            } else {
                retorno = TipoRetorno.FALHA_PERSISTENCIA;
            }
        }
        Log.logDiario("Finalizacao do processamento DocumentoServices.update");
        return retorno;
    }

    public static TipoRetorno delete(Long id) {
        Log.logDiario("Inicio do processamento DocumentoServices.update");
        TipoRetorno retorno = null;
        if (retorno == null) {
            Documentos d = DocumentoRepository.find(id);
            if (d != null) {
                if (DocumentoRepository.delete(d)) {
                    retorno = TipoRetorno.SOCESSO;
                } else {
                    retorno = TipoRetorno.FALHA_PERSISTENCIA;
                }
            } else {
                retorno = TipoRetorno.PARAMETRO_INVALIDO;
            }
        }
        Log.logDiario("Finalizacao do processamento DocumentoServices.update");
        return retorno;
    }

    public static List<DocumentoForm> findAll() {
        Log.logDiario("Inicio do processamento DocumentoServices.findAll");
        ArrayList<Documentos> lista = DocumentoRepository.findAll();
        if (lista != null && !lista.isEmpty()) {
            return DocumentoForm.converter(lista);
        }
        Log.logDiario("Finalizacao do processamento DocumentoServices.findAll");
        return null;
    }

    public static String find(Long id) {
        Log.logDiario("Inicio do processamento DocumentoServices.find");
        Documentos d = DocumentoRepository.find(id);
        if (d != null) {
            return DocumentoForm.toJson(d);
        }
        Log.logDiario("Finalizacao do processamento DocumentoServices.find");
        return null;
    }

}
