package br.com.tivia.service;

import br.com.tivia.enums.TipoRetorno;
import br.com.tivia.form.BeneficiarioForm;
import br.com.tivia.model.Beneficiario;
import br.com.tivia.repositories.BeneficiarioRepository;
import br.com.tivia.utils.Log;
import br.com.tivia.validation.beneficiario.BeneficiarioValidation;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Wilson.Souza
 */
@Service
public class BeneficiarioServices {

    public static TipoRetorno save(BeneficiarioForm form) {
        Log.logDiario("Inicio do processamento BeneficiarioServices.save");
        TipoRetorno retorno = null;

        Beneficiario b = form.converter();

        retorno = BeneficiarioValidation.validation(b);
        if (retorno == null) {
            if (BeneficiarioRepository.save(b)) {
                retorno = TipoRetorno.SOCESSO;
            } else {
                retorno = TipoRetorno.FALHA_PERSISTENCIA;
            }
        }
        Log.logDiario("Finalizacao do processamento BeneficiarioServices.save");
        return retorno;
    }

    public static TipoRetorno update(BeneficiarioForm form) {
        Log.logDiario("Inicio do processamento BeneficiarioServices.update");
        TipoRetorno retorno = null;

        Beneficiario b = form.converter();

        retorno = BeneficiarioValidation.validation(b);
        if (retorno == null) {
            if (BeneficiarioRepository.update(b)) {
                retorno = TipoRetorno.SOCESSO;
            } else {
                retorno = TipoRetorno.FALHA_PERSISTENCIA;
            }
        }
        Log.logDiario("Finalizacao do processamento BeneficiarioServices.update");
        return retorno;
    }

    public static TipoRetorno delete(Long id) {
        Log.logDiario("Inicio do processamento BeneficiarioServices.update");
        TipoRetorno retorno = null;
        if (retorno == null) {
            Beneficiario b = BeneficiarioRepository.find(id);
            if (b != null) {
                if (BeneficiarioRepository.delete(b)) {
                    retorno = TipoRetorno.SOCESSO;
                } else {
                    retorno = TipoRetorno.FALHA_PERSISTENCIA;
                }
            } else {
                retorno = TipoRetorno.PARAMETRO_INVALIDO;
            }
        }
        Log.logDiario("Finalizacao do processamento BeneficiarioServices.update");
        return retorno;
    }

    public static List<BeneficiarioForm> findAll() {
        Log.logDiario("Inicio do processamento BeneficiarioServices.findAll");
        List<Beneficiario> lista = BeneficiarioRepository.findAll();
        if (lista != null && !lista.isEmpty()) {
            return BeneficiarioForm.converter(lista);
        }
        Log.logDiario("Finalizacao do processamento BeneficiarioServices.findAll");
        return null;
    }

    public static String find(Long id) {
        Log.logDiario("Inicio do processamento BeneficiarioServices.find");
        Beneficiario b = BeneficiarioRepository.find(id);
        if (b != null) {
            return BeneficiarioForm.toJson(b);
        }
        Log.logDiario("Finalizacao do processamento BeneficiarioServices.find");
        return null;
    }

}
