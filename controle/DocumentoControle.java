/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import DAO.DocumentoDAO;
import DAO.FerramentaDAO;
import classe.Documento;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author Priscilla
 */
public class DocumentoControle {

    FerramentaDAO ferr = new FerramentaDAO();
    DocumentoDAO ddao = new DocumentoDAO();

    public void incluirDocumento(String caminho, String titulo, String criador, String assunto, String descricao, String data, String idioma, String fonte_public, String nm_arq, String dt_publicacao, String end_web) {
        try {
            Documento d = new Documento(caminho, titulo, criador, assunto, descricao, data, idioma, fonte_public, nm_arq, dt_publicacao, end_web);

            d.setCaminho_fisico(caminho);
            d.setTitulo(titulo);
            d.setCriador(criador);
            d.setAssunto(assunto);
            d.setDescricao(descricao);
            d.setData(data);
            d.setIdioma(idioma);
            d.setFonte_public(fonte_public);
            d.setNomeArq(nm_arq);
            d.setDt_publicacao(dt_publicacao);
            d.setEnd_web(end_web);
            DocumentoDAO.inserir(d);
            //Configurar destino
            String destino = FerramentaDAO.buscar().getRepositorio_doc() + "\\" + nm_arq;
            //Copiar documento para repositorio
            copiarRepositorio(new File(caminho), new File(destino));
            
        } catch (Exception e) {
            System.out.println("Erro na inclusão do documento");
        }
    }

    public void copiarRepositorio(File origem, File destino) {
        //Guardar documento no repositório
        try {
            CopiaArquivo.copiar(origem, destino);
        } catch (IOException ex) {
            System.err.println("Falha na cópia do arquivo: " + ex.getMessage());
        }
    }

    public Documento buscarDocumento(String nome_doc) {
        Documento documento = null;
        documento = DocumentoDAO.buscarId(nome_doc);
        return documento;
    }

    public Documento buscarDocumentoporId(String id) {
        Documento documento = null;
        documento = DocumentoDAO.buscarPorid(Integer.parseInt(id));
        return documento;
    }
}
