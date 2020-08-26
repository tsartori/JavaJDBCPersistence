/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PesqCidade.java
 *
 * Created on 01/11/2011, 21:19:48
 */
package br.fagoc.VIEW;

import br.fagoc.CONTROLLER.ControlePai;
import java.awt.event.ActionEvent;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Acer
 */
public final class PesqPadrao extends javax.swing.JFrame {
    private DisparaBtnEsq DisparaEventoEsc;
    //private JDialog TelaPai;
    private JFrame TelaPai;
    private Vector<String> ColunaGrade;
    private ControlePai ContPai;
    
    /** Creates new form PesqCidade
     * @param parent
     * @param modal
     * @param ColunaGrade
     * @param CampoPesq
     * @param TelaP
     * @param ContP */
    public PesqPadrao(java.awt.Frame parent, boolean modal, Vector<String> ColunaGrade, Vector<String> CampoPesq, JFrame TelaP, ControlePai ContP) {
        
        //super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        CarregaComponentes();
        
        this.cbEmTodaParte.setSelected(true);
        this.TelaPai=TelaP;
        this.ContPai=ContP;
        
        this.ColunaGrade = ColunaGrade;
        
        for(int i=0; i<CampoPesq.size(); i++){
            String Parametro = CampoPesq.get(i);
            cbCondPesq.addItem(Parametro);
        }
        //Criar abstração da tabela
        //Metodo construtor esta sobrecarregado. Parametros: Colunas e quantidade de linhas
        DefaultTableModel MinhaTabela = new DefaultTableModel(this.ColunaGrade, 0);

        //Adicionando a tabela ao componente visual
        tbResultado.setModel(MinhaTabela);
        tfValorPesq.requestFocus();
    }
    
    protected void CarregaComponentes(){

        DisparaEventoEsc = new DisparaBtnEsq();
        
        //Damos um nome para cada ação. Esse nome é útil pois mais de
        //uma tecla pode ser associada a cada ação, como veremos abaixo
        ActionMap actionMap = PainelPrincipal.getActionMap();
        actionMap.put("BtnEscActionPerformed", DisparaEventoEsc);
        
        this.PainelPrincipal.setActionMap(actionMap);

        //Pegamos o input map que ocorre sempre que a janela atual está em foco
        InputMap imap = PainelPrincipal.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        //Associamos o pressionar das teclas (keystroke) aos eventos.
        //O nome do KeyStroke pode ser obtido através da classe KeyEvent.
        //Lá está cheio de constantes como KeyEvent.VK_NUMPAD1.
        //Essa string é o nome sem o VK_
        imap.put(KeyStroke.getKeyStroke("ESCAPE"), "BtnEscActionPerformed");
        
    }
    
    
    protected class DisparaBtnEsq extends AbstractAction
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            btnCancelarActionPerformed(null);
        }
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PainelPrincipal = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbResultado = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        cbCondPesq = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        tfValorPesq = new javax.swing.JTextField();
        cbEmTodaParte = new javax.swing.JCheckBox();
        btnPesq = new javax.swing.JButton();
        btnAbrir = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gerenciador :: Thiago Sartori - Pesquisa");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Resultado"));

        tbResultado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Código", "Nome", "Estado", "CEP"
            }
        ));
        tbResultado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbResultadoKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tbResultado);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE)
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Opções de Pesquisa"));

        cbCondPesq.setToolTipText("");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbCondPesq, 0, 126, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cbCondPesq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Critério de Pesquisa"));

        tfValorPesq.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tfValorPesqKeyPressed(evt);
            }
        });

        cbEmTodaParte.setText("Em toda parte");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tfValorPesq, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbEmTodaParte))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(tfValorPesq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(cbEmTodaParte))
        );

        btnPesq.setText("Pesquisar");
        btnPesq.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesqActionPerformed(evt);
            }
        });
        btnPesq.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnPesqKeyPressed(evt);
            }
        });

        btnAbrir.setText("Abrir");
        btnAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbrirActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PainelPrincipalLayout = new javax.swing.GroupLayout(PainelPrincipal);
        PainelPrincipal.setLayout(PainelPrincipalLayout);
        PainelPrincipalLayout.setHorizontalGroup(
            PainelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PainelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PainelPrincipalLayout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPesq)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAbrir, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar)))
                .addContainerGap())
        );
        PainelPrincipalLayout.setVerticalGroup(
            PainelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PainelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PainelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, 0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PainelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnPesq, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAbrir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PainelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PainelPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void btnAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbrirActionPerformed

    int Linha = tbResultado.getSelectedRow();

    if(Linha == -1) {
        JOptionPane.showMessageDialog(rootPane, "Selecione um registro para depois abri-lo!", "Erro ao consultar cadastro", JOptionPane.ERROR_MESSAGE);
    }
    else{
        
        int Codigo = Integer.valueOf(tbResultado.getValueAt(Linha, 0).toString());
        
        if(TelaPai instanceof CadastroPaiPadrao){
            CadastroPaiPadrao c = new CadastroPaiPadrao(null, false);
            c = (CadastroPaiPadrao)TelaPai;
            c.setRetornoConsulta(Codigo);
        }else{
                try {
                    
                    //Object vClasseInstanciada = Class.forName(TelaPai.getClass().getSimpleName()).newInstance();//getSimpleName retorna somente o nome da classe
                    //getName() retorna o nome da classe e o caminho dela. Ex: br.fagoc.MODEL.Cliente
                    String vNomeClasse = TelaPai.getClass().getName();
                    Class vClasseInstanciada = Class.forName(vNomeClasse);
                    
                    Class partypes[] = {Integer.class};
                    
                    Object arglist[] = new Object[1];  
                    arglist[0] = new Integer(Codigo);
                    Method MetodoInvocado;
                    
                    MetodoInvocado = vClasseInstanciada.getMethod("setRetornoConsulta", partypes);
                    
                    MetodoInvocado.invoke(TelaPai, arglist);
                        
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(PesqPadrao.class.getName()).log(Level.SEVERE, null, ex);
                }catch (NoSuchMethodException ex) {
                    Logger.getLogger(PesqPadrao.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SecurityException ex) {
                    Logger.getLogger(PesqPadrao.class.getName()).log(Level.SEVERE, null, ex);
                }catch (IllegalAccessException ex) {
                    Logger.getLogger(PesqPadrao.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalArgumentException ex) {
                    Logger.getLogger(PesqPadrao.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InvocationTargetException ex) {
                    Logger.getLogger(PesqPadrao.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
        
        this.setVisible(false);
        this.dispose();
    }

}//GEN-LAST:event_btnAbrirActionPerformed

private void btnPesqActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesqActionPerformed

    ArrayList<String> Parametros = new ArrayList<String>();
    Parametros.add(String.valueOf(cbCondPesq.getSelectedIndex()));
    Parametros.add(tfValorPesq.getText().toUpperCase());
    if(cbEmTodaParte.isSelected()) {
        Parametros.add("S");
    }
    else {
        Parametros.add("N");
    }

    //Criando a estrutura da tabela que ira conter os dados
    DefaultTableModel ObjTabela = new DefaultTableModel(this.ColunaGrade, 0);

    if(TelaPai instanceof CadastroPaiPadrao && this.ContPai==null){
        CadastroPaiPadrao TelaPadrao =new CadastroPaiPadrao(null, false);
        TelaPadrao = (CadastroPaiPadrao)TelaPai;
        
        ObjTabela = TelaPadrao.getObjControle().PesquisaObjeto(Parametros, ObjTabela);
    }
    else {
        ObjTabela = this.ContPai.PesquisaObjeto(Parametros, ObjTabela);
    }
    
    //Atribuindo tabela ao JTable
    tbResultado.setModel(ObjTabela);

}//GEN-LAST:event_btnPesqActionPerformed

private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
    this.setVisible(false);
    this.dispose();
}//GEN-LAST:event_btnCancelarActionPerformed

private void tfValorPesqKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfValorPesqKeyPressed
    if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
        btnPesqActionPerformed(null);
    }
}//GEN-LAST:event_tfValorPesqKeyPressed

private void tbResultadoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbResultadoKeyPressed
    if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
        btnAbrirActionPerformed(null);
    }
}//GEN-LAST:event_tbResultadoKeyPressed

private void btnPesqKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnPesqKeyPressed
    if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
        btnPesqActionPerformed(null);
    }
}//GEN-LAST:event_btnPesqKeyPressed

  
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PainelPrincipal;
    private javax.swing.JButton btnAbrir;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnPesq;
    private javax.swing.JComboBox cbCondPesq;
    private javax.swing.JCheckBox cbEmTodaParte;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbResultado;
    private javax.swing.JTextField tfValorPesq;
    // End of variables declaration//GEN-END:variables
}