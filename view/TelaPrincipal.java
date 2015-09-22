package view;

import controle.CorpusControle;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class TelaPrincipal extends javax.swing.JFrame {
        
    /** Creates new form TelaPrincipal */
    public TelaPrincipal() {
        initComponents();
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        MenuDocumentos = new javax.swing.JMenuItem();
        Menuontologia = new javax.swing.JMenuItem();
        mnu_corpus = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        MenuDocs = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        MenuFerramenta = new javax.swing.JMenuItem();
        MenuUsuario = new javax.swing.JMenuItem();
        menuSair = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Anotação de Documentos");
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 754, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 480, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 2, 754, 480);

        jMenuBar1.setBackground(new java.awt.Color(255, 255, 255));

        jMenu1.setText("Repositório");

        MenuDocumentos.setText("Documentos");
        MenuDocumentos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuDocumentosActionPerformed(evt);
            }
        });
        jMenu1.add(MenuDocumentos);

        Menuontologia.setText("Ontologias");
        Menuontologia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MenuontologiaMouseClicked(evt);
            }
        });
        Menuontologia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuontologiaActionPerformed(evt);
            }
        });
        jMenu1.add(Menuontologia);

        mnu_corpus.setText("Montar corpus");
        mnu_corpus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnu_corpusActionPerformed(evt);
            }
        });
        jMenu1.add(mnu_corpus);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Anotação");

        MenuDocs.setText("Documentos");
        MenuDocs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuDocsActionPerformed(evt);
            }
        });
        jMenu2.add(MenuDocs);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Configuração");

        MenuFerramenta.setText("Ferramenta");
        MenuFerramenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuFerramentaActionPerformed(evt);
            }
        });
        jMenu3.add(MenuFerramenta);

        MenuUsuario.setText("Usuário");
        MenuUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuUsuarioActionPerformed(evt);
            }
        });
        jMenu3.add(MenuUsuario);

        jMenuBar1.add(jMenu3);

        menuSair.setText("Sair");
        menuSair.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuSairMouseClicked(evt);
            }
        });
        menuSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuSairActionPerformed(evt);
            }
        });
        jMenuBar1.add(menuSair);

        setJMenuBar(jMenuBar1);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-762)/2, (screenSize.height-539)/2, 762, 539);
    }// </editor-fold>//GEN-END:initComponents

    private void menuSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuSairActionPerformed
        
    }//GEN-LAST:event_menuSairActionPerformed

    private void menuSairMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuSairMouseClicked
        System.exit(0);
    }//GEN-LAST:event_menuSairMouseClicked

    private void MenuontologiaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MenuontologiaMouseClicked
    }//GEN-LAST:event_MenuontologiaMouseClicked

    private void MenuontologiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuontologiaActionPerformed
        TelaOntologia onto = new TelaOntologia();
        onto.setVisible(true);
    }//GEN-LAST:event_MenuontologiaActionPerformed

    private void MenuFerramentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuFerramentaActionPerformed
        TelaFerramenta ferr = new TelaFerramenta();
        ferr.setVisible(true);
    }//GEN-LAST:event_MenuFerramentaActionPerformed

    private void MenuUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuUsuarioActionPerformed
        TelaUsuario usu = new TelaUsuario();
        usu.setVisible(true);
    }//GEN-LAST:event_MenuUsuarioActionPerformed

    private void MenuDocsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuDocsActionPerformed
        TelaAnotacao anot = new TelaAnotacao();
        anot.setVisible(true);
    }//GEN-LAST:event_MenuDocsActionPerformed

    private void MenuDocumentosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuDocumentosActionPerformed
        TelaDocumento doc = new TelaDocumento();
        doc.setVisible(true);
    }//GEN-LAST:event_MenuDocumentosActionPerformed

    private void mnu_corpusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnu_corpusActionPerformed
        CorpusControle cc = new CorpusControle();
        try {
            cc.calcularCorpus();
            JOptionPane.showMessageDialog(null, "Cálculos realizados com sucesso.");
        } catch (IOException ex) {
            Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_mnu_corpusActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new TelaPrincipal().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem MenuDocs;
    private javax.swing.JMenuItem MenuDocumentos;
    private javax.swing.JMenuItem MenuFerramenta;
    private javax.swing.JMenuItem MenuUsuario;
    private javax.swing.JMenuItem Menuontologia;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JMenu menuSair;
    private javax.swing.JMenuItem mnu_corpus;
    // End of variables declaration//GEN-END:variables
}
