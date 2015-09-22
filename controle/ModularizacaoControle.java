/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import DAO.CorpusDAO;
import DAO.ModularizacaoDAO;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ModularizacaoControle {

    private String[] targets;

    public void extrairModulo(String ont_id, String caminho_fisico, ArrayList semente, ArrayList termosrel, String nm_modulo) {
        String modulo = null;
        OntologiaControle ont = new OntologiaControle();
        ArrayList<String> newTargets = new ArrayList<String>();
        //ArrayList<String> termos_rel = new ArrayList<String>();
        String conteudo = "";

        //Pego cada item passado no array de termos relacionados e monto a semente para extração
        ArrayList termos_relevantes = CorpusDAO.termosRelevantes(termosrel);
        for (int i = 0; i < termos_relevantes.size(); i++) {
            ArrayList retorno = ModularizacaoDAO.buscarPorlabel(Integer.parseInt(ont_id),
                    termos_relevantes.get(i).toString().trim());
            for (int k = 0; k < retorno.size(); k++) {
                conteudo = retorno.get(k).toString().trim();
                conteudo = conteudo.replace("[", "");
                conteudo = conteudo.replace("]", "");
                newTargets.add(conteudo);
            }
        }

        //Ler a lista de classes e termos usados na anotação
        ArrayList anotados = semente;
        for (int i = 0; i < anotados.size(); i++) {
            ArrayList retorno = ModularizacaoDAO.buscarPorlabel(Integer.parseInt(ont_id),
                    termos_relevantes.get(i).toString().trim());
            for (int k = 0; k < retorno.size(); k++) {
                conteudo = retorno.get(k).toString().trim();
                conteudo = conteudo.replace("[", "");
                conteudo = conteudo.replace("]", "");
                newTargets.add(conteudo);
            }
        }
        if (newTargets.size() > 0) {
            //tirar duplicados
            Set<String> set = new HashSet<String>(newTargets);
            for (String val : set) {
                semente.add(val);
            }
            targets = (String[]) semente.toArray(new String[semente.size()]);
        }
        try {
            Segment.extraiModulo(caminho_fisico, targets, nm_modulo);
        } catch (Exception ex) {
            Logger.getLogger(ModularizacaoControle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
