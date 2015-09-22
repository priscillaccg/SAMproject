/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import DAO.PoliticaModDAO;
import classe.Documento;
import classe.Politica_Modularizacao;
import classe.Usuario;

/**
 *
 * @author Priscilla
 */
public class PoliticaModControle {

    public void incluir(Usuario usuario, Documento documento, String tpmod, String qtd) {
        Politica_Modularizacao p = new Politica_Modularizacao(usuario, documento, tpmod, qtd);
        try {
            p.setUsuario(usuario);
            p.setDocumento(documento);
            p.setTp_modularizacao(tpmod);
            p.setQtd_palavras(qtd);
            Politica_Modularizacao pol = PoliticaModDAO.inserir(p);
        } catch (Exception e) {
            System.out.println("Erro na inclusão da política de modolarização");
        }

    }
}
