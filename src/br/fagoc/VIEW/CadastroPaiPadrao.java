/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * CadastroPaiPadrao.java
 *
 * Created on 26/10/2011, 16:25:18
 */
package br.fagoc.VIEW;

import br.fagoc.CONTROLLER.ControlePai;
import java.awt.Component;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

/**
 *
 * @author Thiago Sartori
 */
public class CadastroPaiPadrao extends javax.swing.JFrame {
    protected DisparaBtnEsq DisparaEventoEsc;
    protected DisparaBtnF2 DisparaEventoF2;
    protected DisparaBtnF4 DisparaEventoF4;
    protected DisparaBtnF11 DisparaEventoF11;
    protected DisparaBtnF12 DisparaEventoF12;
    
    private Integer RetornoConsulta;
    private ControlePai objControle;
    protected ArrayList<String> DadosRegistro;
    protected Vector<String> ColunaGrade;
    protected Vector<String> CampoPesq;

    
    public void PreencheDadosTela(){};

    /** Creates new form CadastroPaiPadrao */
    public CadastroPaiPadrao(JFrame parent, boolean modal) {
        //super(parent,modal); //Qnd for um JDialog tem que tirar o comentario dessa linha.
        this.setResizable(false); // não permite ao usuario redimensionar a janela
        this.DadosRegistro = new ArrayList<String>();
        this.RetornoConsulta=-1;
        initComponents();

    }
    
    protected void CarregaComponentes(){

        DisparaEventoEsc = new DisparaBtnEsq();
        DisparaEventoF2 = new DisparaBtnF2();
        DisparaEventoF4 = new DisparaBtnF4();
        DisparaEventoF11 = new DisparaBtnF11();
        DisparaEventoF12 = new DisparaBtnF12();
        //Damos um nome para cada ação. Esse nome é útil pois mais de
        //uma tecla pode ser associada a cada ação, como veremos abaixo
        ActionMap actionMap = PainelPrincipal.getActionMap();
        actionMap.put("BtnEscActionPerformed", DisparaEventoEsc);
        actionMap.put("DisparaBtnF2", DisparaEventoF2);
        actionMap.put("DisparaBtnF4", DisparaEventoF4);
        actionMap.put("DisparaBtnF11", DisparaEventoF11);
        actionMap.put("DisparaBtnF12", DisparaEventoF12);

        this.PainelPrincipal.setActionMap(actionMap);

        //Pegamos o input map que ocorre sempre que a janela atual está em foco
        InputMap imap = PainelPrincipal.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        //Associamos o pressionar das teclas (keystroke) aos eventos.
        //O nome do KeyStroke pode ser obtido através da classe KeyEvent.
        //Lá está cheio de constantes como KeyEvent.VK_NUMPAD1.
        //Essa string é o nome sem o VK_
        imap.put(KeyStroke.getKeyStroke("ESCAPE"), "BtnEscActionPerformed");
        imap.put(KeyStroke.getKeyStroke("F2"), "DisparaBtnF2");
        imap.put(KeyStroke.getKeyStroke("F4"), "DisparaBtnF4");
        imap.put(KeyStroke.getKeyStroke("F11"), "DisparaBtnF11");
        imap.put(KeyStroke.getKeyStroke("F12"), "DisparaBtnF12");

    }
    
    protected class DisparaBtnEsq extends AbstractAction
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            btnESCAction();
        }
    }
    
    protected void btnESCAction(){
        Integer Sair = JOptionPane.showConfirmDialog(rootPane, "Tem certeza que deseja SAIR?", "Software Gerenciador :: Thiago Sartori" ,JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
        if(Sair==0){
            this.setVisible(false);
            this.dispose();
        }
    }
    
    protected class DisparaBtnF2 extends AbstractAction
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            Integer NovoRegistro = JOptionPane.showConfirmDialog(rootPane, "Iniciar um novo cadastro?", "Software Gerenciador :: Thiago Sartori" ,JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
            if(NovoRegistro==0){
                BtnNovoActionPerformed(null);
            }
        }
    }
    protected class DisparaBtnF4 extends AbstractAction
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            BtnPesquisaActionPerformed(null);
        }
    }
    
    protected class DisparaBtnF11 extends AbstractAction
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            BtnCancelarActionPerformed(null);
        }
    }
        
    protected class DisparaBtnF12 extends AbstractAction
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            Integer Sair = JOptionPane.showConfirmDialog(rootPane, "Confirma os dados digitados?", "Software Gerenciador :: Thiago Sartori" ,JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
            if(Sair==0){
                BtnSalvarActionPerformed(null);
            }
        }
    }
        
    public Vector<String> getCampoPesq() {
        return CampoPesq;
    }

    public void setCampoPesq(Vector<String> CampoPesquisa) {
        this.CampoPesq = CampoPesquisa;
    }

    public Vector<String> getColunaGrade() {
        return ColunaGrade;
    }

    public void setColunaGrade(Vector<String> ColunaGrad) {
        this.ColunaGrade = ColunaGrad;
    }

    public ControlePai getObjControle() {
        return objControle;
    }

    public void setObjControle(ControlePai objControle) {
        this.objControle = objControle;
    }
    protected void BtnEscPressed(){
        this.setVisible(false);
        this.dispose();
    }
    
    public int getRetornoConsulta() {
        return RetornoConsulta;
    }

    public void setRetornoConsulta(int RetornoConsulta) {
        this.RetornoConsulta = RetornoConsulta;
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
        PnCodigo = new javax.swing.JPanel();
        txtCodigo = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        PnNavegacao = new javax.swing.JPanel();
        BtnNavPrimeiro = new javax.swing.JButton();
        BtnNavAnterior = new javax.swing.JButton();
        BtnNavProximo = new javax.swing.JButton();
        BtnNavUltimo = new javax.swing.JButton();
        PageControlPrincipal = new javax.swing.JTabbedPane();
        TabPrincipal = new javax.swing.JPanel();
        PanelBotoes = new javax.swing.JPanel();
        BtnNovo = new javax.swing.JButton();
        BtnSalvar = new javax.swing.JButton();
        BtnCancelar = new javax.swing.JButton();
        BtnExcluir = new javax.swing.JButton();
        BtnPesquisa = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PnCodigo.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        PnCodigo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoActionPerformed(evt);
            }
        });
        txtCodigo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCodigoFocusLost(evt);
            }
        });
        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodigoKeyPressed(evt);
            }
        });
        PnCodigo.add(txtCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 70, -1));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Código:  ");
        PnCodigo.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 60, 20));

        PnNavegacao.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        PnNavegacao.setAlignmentY(0.0F);
        PnNavegacao.setLayout(new javax.swing.BoxLayout(PnNavegacao, javax.swing.BoxLayout.LINE_AXIS));

        BtnNavPrimeiro.setText("<<");
        BtnNavPrimeiro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnNavPrimeiroActionPerformed(evt);
            }
        });
        PnNavegacao.add(BtnNavPrimeiro);

        BtnNavAnterior.setLabel("<");
        BtnNavAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnNavPrimeiroActionPerformed(evt);
            }
        });
        PnNavegacao.add(BtnNavAnterior);

        BtnNavProximo.setText(">");
        BtnNavProximo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnNavPrimeiroActionPerformed(evt);
            }
        });
        PnNavegacao.add(BtnNavProximo);

        BtnNavUltimo.setText(">>");
        BtnNavUltimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnNavPrimeiroActionPerformed(evt);
            }
        });
        PnNavegacao.add(BtnNavUltimo);

        PnCodigo.add(PnNavegacao, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 6, -1, -1));

        PageControlPrincipal.setName(""); // NOI18N

        javax.swing.GroupLayout TabPrincipalLayout = new javax.swing.GroupLayout(TabPrincipal);
        TabPrincipal.setLayout(TabPrincipalLayout);
        TabPrincipalLayout.setHorizontalGroup(
            TabPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 545, Short.MAX_VALUE)
        );
        TabPrincipalLayout.setVerticalGroup(
            TabPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 212, Short.MAX_VALUE)
        );

        PageControlPrincipal.addTab("Principal", TabPrincipal);

        PanelBotoes.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        PanelBotoes.setAlignmentX(0.0F);
        PanelBotoes.setAlignmentY(0.0F);

        BtnNovo.setText("Novo - F2");
        BtnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnNovoActionPerformed(evt);
            }
        });

        BtnSalvar.setText("Salvar - F12");
        BtnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSalvarActionPerformed(evt);
            }
        });

        BtnCancelar.setText("Cancelar - F11");
        BtnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCancelarActionPerformed(evt);
            }
        });

        BtnExcluir.setText("Excluir");
        BtnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnExcluirActionPerformed(evt);
            }
        });

        BtnPesquisa.setText("Pesquisa - F4");
        BtnPesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPesquisaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelBotoesLayout = new javax.swing.GroupLayout(PanelBotoes);
        PanelBotoes.setLayout(PanelBotoesLayout);
        PanelBotoesLayout.setHorizontalGroup(
            PanelBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelBotoesLayout.createSequentialGroup()
                .addComponent(BtnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(BtnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BtnCancelar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BtnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(BtnPesquisa))
        );
        PanelBotoesLayout.setVerticalGroup(
            PanelBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(BtnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(BtnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(BtnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(BtnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(BtnPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout PainelPrincipalLayout = new javax.swing.GroupLayout(PainelPrincipal);
        PainelPrincipal.setLayout(PainelPrincipalLayout);
        PainelPrincipalLayout.setHorizontalGroup(
            PainelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelPrincipalLayout.createSequentialGroup()
                .addGroup(PainelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PnCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PageControlPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PainelPrincipalLayout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(PanelBotoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PainelPrincipalLayout.setVerticalGroup(
            PainelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelPrincipalLayout.createSequentialGroup()
                .addComponent(PnCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PageControlPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelBotoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(PainelPrincipal, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 550, 340));

        pack();
    }// </editor-fold>//GEN-END:initComponents

public void txtCodigoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCodigoFocusLost
        int CodAtual = 0;
        try{
            CodAtual = Integer.parseInt(txtCodigo.getText());
        } catch(Exception e) {
            CodAtual = 0;
            this.txtCodigo.setText("0");
        }

        if (CodAtual > 0){
            PreencherTelaComObjRecuperado(CodAtual);
        }

        HabilitaDesabilitaBotoes(true);
}//GEN-LAST:event_txtCodigoFocusLost

public void txtCodigoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER)
            TabPrincipal.requestFocus();
}//GEN-LAST:event_txtCodigoKeyPressed

public void BtnNavPrimeiroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnNavPrimeiroActionPerformed
        int iOpcao = -1;
        if (evt.getSource() == BtnNavPrimeiro )
            iOpcao = 0;
        else if (evt.getSource() == BtnNavAnterior)
            iOpcao = 1;
        else if (evt.getSource() == BtnNavProximo)
            iOpcao = 2;
        else if (evt.getSource() == BtnNavUltimo)
            iOpcao = 3;

        int CodAtual = 0;
        try{
            CodAtual = Integer.parseInt(txtCodigo.getText());
        } catch(Exception e){
            CodAtual = 0;
        }

        PreencherTelaComObjRecuperado(CodAtual, iOpcao);
        TabPrincipal.requestFocus();
}//GEN-LAST:event_BtnNavPrimeiroActionPerformed

public void BtnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnNovoActionPerformed
        LimparTela();

        HabilitaDesabilitaBotoes(false);
}//GEN-LAST:event_BtnNovoActionPerformed

public void BtnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSalvarActionPerformed
        int iCodAtual = Integer.parseInt(txtCodigo.getText());
        int iCodNovo = 0;

        PreencherRegistro();

        if (iCodAtual > 0){
            this.objControle.Atualizar(this.DadosRegistro);
        } else {
            iCodNovo = this.objControle.Salvar(this.DadosRegistro);
            txtCodigo.setText(String.valueOf(iCodNovo));
        }

        HabilitaDesabilitaBotoes(true);

}//GEN-LAST:event_BtnSalvarActionPerformed

public void BtnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCancelarActionPerformed
        int iCodAtual = Integer.parseInt(txtCodigo.getText());

        if (iCodAtual > 0) {
            PreencherTelaComObjRecuperado(iCodAtual);
        } else {
            LimparTela();
        }

        HabilitaDesabilitaBotoes(true);
}//GEN-LAST:event_BtnCancelarActionPerformed

public void BtnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnExcluirActionPerformed

    int iPodeExcluir = JOptionPane.showConfirmDialog(rootPane, "Tem certeza que deseja excluir o registro?", "iobERP: Sistema de Gestão Integrada", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (iPodeExcluir == 0) {
            this.objControle.Excluir(Integer.parseInt(txtCodigo.getText()));
            LimparTela();
        }

        HabilitaDesabilitaBotoes(true);
}//GEN-LAST:event_BtnExcluirActionPerformed

private void txtCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_txtCodigoActionPerformed

private void BtnPesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPesquisaActionPerformed
    new PesqPadrao(null, true, this.ColunaGrade , this.CampoPesq, this, null).setVisible(true);
    if(this.getRetornoConsulta()!=-1){
        txtCodigo.setText(String.valueOf(getRetornoConsulta()));
        setRetornoConsulta(-1);
        txtCodigoFocusLost(null);
    }
}//GEN-LAST:event_BtnPesquisaActionPerformed

    public void PreencherTelaComObjRecuperado(int CodAtual){
        PreencherTelaComObjRecuperado(CodAtual, -1);
    }

    public void PreencherTelaComObjRecuperado(int CodAtual, int iOpcao){
        
        if (iOpcao == -1)
             this.DadosRegistro = this.objControle.RecuperaObjeto(CodAtual);
        else
             this.DadosRegistro = this.objControle.RecuperaObjetoNavegacao(iOpcao, CodAtual);
        
        if (!this.DadosRegistro.get(0).equals("-1")){

            if (iOpcao != -1){
                txtCodigo.setText(this.DadosRegistro.get(0));
            }
            PreencheDadosTela();
        } else {
            if (iOpcao == -1) {
                JOptionPane.showMessageDialog(rootPane, "Registro não encontrado!");
                LimparTela();
            }
        }

    }

    public void LimparTela(){
        txtCodigo.setText("0");
    }

    public void HabilitaDesabilitaBotoes(boolean Habilitar){
        Component[] c;

        BtnNovo.setEnabled(Habilitar);
        BtnExcluir.setEnabled(Habilitar);
        BtnSalvar.setEnabled(!Habilitar);
        BtnCancelar.setEnabled(!Habilitar);
        BtnPesquisa.setEnabled(Habilitar);
        txtCodigo.setEnabled(Habilitar);
        c = PnNavegacao.getComponents();
        for (int i=0; i<c.length; i++)
          c[i].setEnabled(Habilitar);
    }

    public javax.swing.JPanel getTabPrincipal(){
        return this.TabPrincipal;
    }

    public void setTabPrincipal(javax.swing.JPanel tab){
        this.TabPrincipal=tab;
    }


    public void PreencherRegistro(){
        this.DadosRegistro.add(txtCodigo.getText());
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnCancelar;
    private javax.swing.JButton BtnExcluir;
    private javax.swing.JButton BtnNavAnterior;
    private javax.swing.JButton BtnNavPrimeiro;
    private javax.swing.JButton BtnNavProximo;
    private javax.swing.JButton BtnNavUltimo;
    private javax.swing.JButton BtnNovo;
    private javax.swing.JButton BtnPesquisa;
    private javax.swing.JButton BtnSalvar;
    private javax.swing.JTabbedPane PageControlPrincipal;
    private javax.swing.JPanel PainelPrincipal;
    private javax.swing.JPanel PanelBotoes;
    private javax.swing.JPanel PnCodigo;
    private javax.swing.JPanel PnNavegacao;
    private javax.swing.JPanel TabPrincipal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField txtCodigo;
    // End of variables declaration//GEN-END:variables
}
