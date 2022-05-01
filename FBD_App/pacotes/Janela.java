package FBD_APP.pacotes;

import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import javax.swing.JOptionPane;


public class Janela {
    private JFrame janela= new JFrame("ZooDataBank");

    private JPanel tabela= new JPanel();
    private JLabel Ltabela= new JLabel("tabela:");
    private JTextField txtabela= new JTextField(10);

    private JPanel but= new JPanel();
    private JButton adicionar= new JButton("adicionar");
    private JButton modificar= new JButton("modificar");
    private JButton remover= new JButton("remover");
    private JButton consultar= new JButton("consultar");


    public void montar() {
        buffer();
        inicializa();

        adicionar();
        remover();
        modificar();
        consultar();
    }

    public void buffer() {
        tabela.setLayout(new FlowLayout());
        tabela.add(Ltabela);
        tabela.add(txtabela);

        but.setLayout(new FlowLayout());
        but.add(adicionar);
        but.add(remover);
        but.add(modificar);
        but.add(consultar);
    }

    public void inicializa() {
        janela.setLayout(new FlowLayout());
        janela.add(tabela);
        janela.add(but);
        
        janela.setSize(410,190);
        janela.setVisible(true);
        janela.setLocationRelativeTo(null);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    public void modificar(){
        modificar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e){
                try {
                    String tabela= txtabela.getText(); 
                    if     (tabela.equals("Cliente"))        modCliente();
                    else if(tabela.equals("cliente"))        modCliente();
                    else if(tabela.equals("Loja"))           modLoja();
                    else if(tabela.equals("loja"))           modLoja();
                    else if(tabela.equals("Bilheteria"))     modBilheteria();
                    else if(tabela.equals("bilheteria"))     modBilheteria();
                    else if(tabela.equals("Atracao"))        modAtracao();
                    else if(tabela.equals("Atração"))        modAtracao();
                    else if(tabela.equals("atracao"))        modAtracao();
                    else if(tabela.equals("atração"))        modAtracao();
                    

                } catch (NumberFormatException e2) { 
					JOptionPane.showMessageDialog(null, "falha ao remover da tabela");
				}
            }
        });
    }

    public JFrame modCliente(){
        JFrame aba= new JFrame();
        JButton mod= new JButton("procurar");
        JButton alt= new JButton("alterar");

        JLabel Lid= new JLabel("digite o id:");
        JTextField txtid= new JTextField(10);

        aba.setLayout(new FlowLayout());
        aba.add(Lid);
        aba.add(txtid);
        aba.add(mod);

        mod.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e3){
                try {
                    String sql= "SELECT * FROM cliente WHERE num_id='"+txtid.getText()+"';";
                    conexao con= new conexao();
                    ResultSet resposta= con.executasql(sql);

                    try {
                        while(resposta.next()){
                            JPanel novo= new JPanel();
                            String id= resposta.getString(1);
                            String nome= resposta.getString(2);
                            String idade= resposta.getString(3);
        
                            aba.add(new JLabel("*dado encontrado"));
                            novo.add(new JLabel("id: "));
                            novo.add(aux(id));
                            novo.add(new JLabel("nome: "));
                            novo.add(aux(nome));
                            novo.add(new JLabel("idade: "));
                            novo.add(aux(idade));
                            aba.add(novo);
                            aba.remove(mod);
                            aba.add(alt);
                            aba.setSize(220,150);

                            alt.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e3){
                                    aba.setVisible(false);
                                    addCliente(id,2);
                                }
                            });
                        }
                    } catch (Exception e) {
                        //TODO: handle exception
                    }
                } catch (Exception e) {
                    //TODO: handle exception
                }
            }
        });

        aba.setSize(200,120);
        aba.setVisible(true);
        aba.setLocationRelativeTo(null);
        aba.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        return aba;
    }

    public JFrame modLoja(){
        JFrame aba= new JFrame();
        JButton mod= new JButton("procurar");
        JButton alt= new JButton("alterar");

        JLabel Lid= new JLabel("digite o cnpj:");
        JTextField txtid= new JTextField(10);

        aba.setLayout(new FlowLayout());
        aba.add(Lid);
        aba.add(txtid);
        aba.add(mod);

        mod.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e3){
                try {
                    String sql= "SELECT * FROM loja WHERE cnpj="+txtid.getText()+";";
                    conexao con= new conexao();
                    ResultSet resposta= con.executasql(sql);

                    try {
                        while(resposta.next()){
                            JPanel novo= new JPanel();
                            String numero= resposta.getString(1);
                            String v_dia= resposta.getString(2);
                            String v_mes= resposta.getString(3);
                            String c_mes= resposta.getString(4);
        
                            aba.add(new JLabel("*dado encontrado"));
                            novo.add(new JLabel("numero: "));
                            novo.add(aux(numero));
                            novo.add(new JLabel("venda/dia: "));
                            novo.add(aux(v_dia));
                            novo.add(new JLabel("venda/mes: "));
                            novo.add(aux(v_mes));
                            novo.add(new JLabel("custo/mes: "));
                            novo.add(aux(c_mes));
                            aba.add(novo);
                            aba.remove(mod);
                            aba.add(alt);
                            aba.setSize(400,150);

                            alt.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e3){
                                    aba.setVisible(false);
                                    addLoja(numero,2);
                                }
                            });
                        }
                    } catch (Exception e) {
                        //TODO: handle exception
                    }
                } catch (Exception e) {
                    //TODO: handle exception
                }
            }
        });

        aba.setSize(200,120);
        aba.setVisible(true);
        aba.setLocationRelativeTo(null);
        aba.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        return aba;
    }

    public JFrame modAtracao(){
        JFrame aba= new JFrame();
        JButton mod= new JButton("procurar");
        JButton alt= new JButton("alterar");

        JLabel Lid= new JLabel("digite o nome:");
        JTextField txtid= new JTextField(10);

        aba.setLayout(new FlowLayout());
        aba.add(Lid);
        aba.add(txtid);
        aba.add(mod);

        mod.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e3){
                try {
                    String sql= "SELECT * FROM Atracao WHERE NOME='"+txtid.getText()+"';";
                    conexao con= new conexao();
                    ResultSet resposta= con.executasql(sql);

                    try {
                        while(resposta.next()){
                            JPanel novo= new JPanel();
                            String nome= resposta.getString(1);
                            String tipo= resposta.getString(2);
        
                            aba.add(new JLabel("*dado encontrado"));
                            novo.add(new JLabel("nome: "));
                            novo.add(aux(nome));
                            novo.add(new JLabel("tipo: "));
                            novo.add(aux(tipo));
                            aba.add(novo);
                            aba.remove(mod);
                            aba.add(alt);
                            aba.setSize(300,170);

                            alt.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e3){
                                    aba.setVisible(false);
                                    addAtracao(nome,2);
                                }
                            });
                        }
                    } catch (Exception e) {
                        //TODO: handle exception
                    }
                } catch (Exception e) {
                    //TODO: handle exception
                }
            }
        });

        aba.setSize(200,120);
        aba.setVisible(true);
        aba.setLocationRelativeTo(null);
        aba.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        return aba;
    }
    
    public JFrame modBilheteria(){
        JFrame aba= new JFrame();
        JButton mod= new JButton("procurar");
        JButton alt= new JButton("alterar");

        JLabel Lid= new JLabel("digite o numero:");
        JTextField txtid= new JTextField(10);

        aba.setLayout(new FlowLayout());
        aba.add(Lid);
        aba.add(txtid);
        aba.add(mod);

        mod.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e3){
                try {
                    String sql= "SELECT * FROM bilheteria WHERE numero='"+txtid.getText()+"';";
                    conexao con= new conexao();
                    ResultSet resposta= con.executasql(sql);

                    try {
                        while(resposta.next()){
                            JPanel novo= new JPanel();
                            String numero= resposta.getString(1);
                            String v_dia= resposta.getString(2);
                            String v_mes= resposta.getString(3);
                            String c_mes= resposta.getString(4);
                            String c_dia= resposta.getString(5);
        
                            aba.add(new JLabel("*dado encontrado"));
                            novo.add(new JLabel("numero: "));
                            novo.add(aux(numero));
                            novo.add(new JLabel("venda/dia: "));
                            novo.add(aux(v_dia));
                            novo.add(new JLabel("venda/mes: "));
                            novo.add(aux(v_mes));
                            novo.add(new JLabel("cliente/dia: "));
                            novo.add(aux(c_dia));
                            novo.add(new JLabel("cliente/mes: "));
                            novo.add(aux(c_mes));
                            aba.add(novo);
                            aba.remove(mod);
                            aba.add(alt);
                            aba.setSize(460,150);

                            alt.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e3){
                                    aba.setVisible(false);
                                    addBilheteria(numero,2);
                                }
                            });
                        }
                    } catch (Exception e) {
                        //TODO: handle exception
                    }
                } catch (Exception e) {
                    //TODO: handle exception
                }
            }
        });

        aba.setSize(200,120);
        aba.setVisible(true);
        aba.setLocationRelativeTo(null);
        aba.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        return aba;
    }

    public void remover(){
        remover.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e){
                try {
                    String tabela= txtabela.getText(); 
                    if     (tabela.equals("Cliente"))        remCliente();
                    else if(tabela.equals("cliente"))        remCliente();
                    else if(tabela.equals("Loja"))           remLoja();
                    else if(tabela.equals("loja"))           remLoja();
                    else if(tabela.equals("Bilheteria"))     remBilheteria();
                    else if(tabela.equals("bilheteria"))     remBilheteria();
                    else if(tabela.equals("Atracao"))        remAtracao();
                    else if(tabela.equals("Atração"))        remAtracao();
                    else if(tabela.equals("atracao"))        remAtracao();
                    else if(tabela.equals("atração"))        remAtracao();
                    

                } catch (NumberFormatException e2) { 
					JOptionPane.showMessageDialog(null, "falha ao remover da tabela");
				}
            }
        });
    }

    public JFrame remAtracao(){
        JFrame aba= new JFrame();
        JButton rem= new JButton("remover");

        JLabel Lid= new JLabel("digite o nome:");
        JTextField txtid= new JTextField(10);

        aba.setLayout(new FlowLayout());
        aba.add(Lid);
        aba.add(txtid);
        aba.add(rem);

        aba.setSize(200,120);
        aba.setVisible(true);
        aba.setLocationRelativeTo(null);
        aba.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        rem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e3){
                try {
                    String sql= "DELETE FROM ATRACAO WHERE NOME='"+txtid.getText()+"';";
                    conexao con= new conexao();
                    con.executaSQL(sql);

                    txtid.setText("");
   
                } catch  (NumberFormatException e4) { 
                    JOptionPane.showMessageDialog(null, "falha ao remover da tabela");
                }
            }
        });

        return aba;
    }

    public JFrame remBilheteria(){
        JFrame aba= new JFrame();
        JButton rem= new JButton("remover");

        JLabel Lid= new JLabel("digite o numero:");
        JTextField txtid= new JTextField(5);

        aba.setLayout(new FlowLayout());
        aba.add(Lid);
        aba.add(txtid);
        aba.add(rem);

        aba.setSize(150,120);
        aba.setVisible(true);
        aba.setLocationRelativeTo(null);
        aba.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        rem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e3){
                try {
                    String sql= "DELETE FROM BILHETERIA WHERE NUMERO="+txtid.getText()+";";
                    conexao con= new conexao();
                    con.executaSQL(sql);

                    txtid.setText("");
   
                } catch  (NumberFormatException e4) { 
                    JOptionPane.showMessageDialog(null, "falha ao remover da tabela");
                }
            }
        });

        return aba;
    }

    public JFrame remLoja(){
        JFrame aba= new JFrame();
        JButton rem= new JButton("remover");

        JLabel Lid= new JLabel("digite o cnpj:");
        JTextField txtid= new JTextField(8);

        aba.setLayout(new FlowLayout());
        aba.add(Lid);
        aba.add(txtid);
        aba.add(rem);

        aba.setSize(150,120);
        aba.setVisible(true);
        aba.setLocationRelativeTo(null);
        aba.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        rem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e3){
                try {
                    String sql= "DELETE FROM LOJA WHERE CNPJ="+txtid.getText()+";";
                    conexao con= new conexao();
                    con.executaSQL(sql);

                    txtid.setText("");
   
                } catch  (NumberFormatException e4) { 
                    JOptionPane.showMessageDialog(null, "falha ao remover da tabela");
                }
            }
        });

        return aba;
    }

    public JFrame remCliente(){
        JFrame aba= new JFrame();
        JButton rem= new JButton("remover");

        JLabel Lid= new JLabel("digite o id:");
        JTextField txtid= new JTextField(5);

        aba.setLayout(new FlowLayout());
        aba.add(Lid);
        aba.add(txtid);
        aba.add(rem);

        aba.setSize(150,120);
        aba.setVisible(true);
        aba.setLocationRelativeTo(null);
        aba.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        rem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e3){
                try {
                    String sql= "DELETE FROM CLIENTE WHERE NUM_ID="+txtid.getText()+";";
                    conexao con= new conexao();
                    con.executaSQL(sql);

                    txtid.setText("");
   
                } catch  (NumberFormatException e4) { 
                    JOptionPane.showMessageDialog(null, "falha ao remover da tabela");
                }
            }
        });

        return aba;
    }

    public void adicionar() {
        adicionar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e){
                try {
                    String tabela= txtabela.getText(); 
                    if     (tabela.equals("Cliente"))        addCliente("",1);
                    else if(tabela.equals("cliente"))        addCliente("",1);
                    else if(tabela.equals("Loja"))           addLoja("",1);
                    else if(tabela.equals("loja"))           addLoja("",1);
                    else if(tabela.equals("Bilheteria"))     addBilheteria("",1);
                    else if(tabela.equals("bilheteria"))     addBilheteria("",1);
                    else if(tabela.equals("Atracao"))        addAtracao("",1);
                    else if(tabela.equals("Atração"))        addAtracao("",1);
                    else if(tabela.equals("atracao"))        addAtracao("",1);
                    else if(tabela.equals("atração"))        addAtracao("",1);
                    

                } catch (NumberFormatException e2) { 
					JOptionPane.showMessageDialog(null, "falha ao adicionar na tabela");
				}
            }
        });
    }

    public JFrame addAtracao(String a,int tipo){
        JFrame aba= new JFrame();
        JButton add= new JButton("adicionar");

        JLabel Lnome= new JLabel("digite o nome da atracao:");
        JTextField txtnome= new JTextField(10);
        txtnome.setText(a);

        JLabel Ltipo= new JLabel("digite o tipo:");
        JTextField txttipo= new JTextField(10);

        aba.setLayout(new FlowLayout());
        aba.add(Lnome);
        aba.add(txtnome);
        aba.add(Ltipo);
        aba.add(txttipo);
        aba.add(add);

        
        aba.setSize(200,190);
        aba.setVisible(true);
        aba.setLocationRelativeTo(null);
        aba.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        if(tipo==1){
            add.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e3){
                    try {
                        String sql= "INSERT INTO ATRACAO(NOME,TIPO) VALUES('"+txtnome.getText()+"','"+txttipo.getText()+"')";
                        conexao con= new conexao();
                        con.executaSQL(sql);

                        txttipo.setText("");
                        txtnome.setText("");   
                    } catch  (NumberFormatException e4) { 
                        JOptionPane.showMessageDialog(null, "falha ao adicionar na tabela");
                    }
                }
            });
        }
        else if(tipo==2){
            add.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e3){
                    try {
                        String sql= "UPDATE ATRACAO SET TIPO='"+txttipo.getText()+"' WHERE NOME='"+txtnome.getText()+"'";
                        conexao con= new conexao();
                        con.executaSQL(sql);
    
                        txttipo.setText("");
                        txtnome.setText("");
                        aba.setVisible(false);   
                    } catch  (NumberFormatException e4) { 
                        JOptionPane.showMessageDialog(null, "falha ao adicionar na tabela");
                    }
                }
            });
        }

        return aba;
    }

    public JFrame addBilheteria(String a, int tipo){
        JFrame aba= new JFrame();
        JButton add= new JButton("adicionar");

        JLabel Lid= new JLabel("digite o numero:");
        JTextField txtid= new JTextField(8);
        txtid.setText(a);

        JLabel LvenD= new JLabel("digite as vendas diarias:");
        JTextField txtvenD= new JTextField(5);

        JLabel LvenM= new JLabel("digite as vendas mensais:");
        JTextField txtvenM= new JTextField(5);

        JLabel LcliD= new JLabel("digite os clientes diarios:");
        JTextField txtcliD= new JTextField(5);

        JLabel LcliM= new JLabel("digite os clientes mensais:");
        JTextField txtcliM= new JTextField(5);

        
        aba.setLayout(new FlowLayout());
        aba.add(Lid);
        aba.add(txtid);
        aba.add(LvenD);
        aba.add(txtvenD);
        aba.add(LvenM);
        aba.add(txtvenM);
        aba.add(LcliD);
        aba.add(txtcliD);
        aba.add(LcliM);
        aba.add(txtcliM);
        aba.add(add);

        aba.setSize(250,200);
        aba.setVisible(true);
        aba.setLocationRelativeTo(null);
        aba.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        if(tipo==1){
            add.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e3){
                    try {
                        String sql= "INSERT INTO BILHETERIA(NUMERO,VENDAS_DIARIAS,VENDAS_MENSAIS,CLIENTES_MENSAIS,CLIENTES_DIARIOS) VALUES("+txtid.getText()+","+txtvenD.getText()+","+txtvenM.getText()+","+txtcliM.getText()+","+txtcliD.getText()+")";
                        conexao con= new conexao();
                        con.executaSQL(sql);

                        txtid.setText("");
                        txtvenD.setText("");
                        txtvenM.setText("");   
                        txtcliD.setText(""); 
                        txtcliM.setText("");   
                    } catch  (NumberFormatException e4) { 
                        JOptionPane.showMessageDialog(null, "falha ao adicionar na tabela");
                    }
                }
            });
        } 
        else if(tipo==2){
            add.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e3){
                    try {
                        String sql= "UPDATE BILHETERIA SET VENDAS_DIARIAS="+txtvenD.getText()+",VENDAS_MENSAIS="+txtvenM.getText()+",CLIENTES_MENSAIS="+txtcliM.getText()+",CLIENTES_DIARIOS="+txtcliD.getText()+" WHERE NUMERO="+txtid.getText()+";";
                        conexao con= new conexao();
                        con.executaSQL(sql);

                        txtid.setText("");
                        txtvenD.setText("");
                        txtvenM.setText("");   
                        txtcliD.setText(""); 
                        txtcliM.setText("");
                        aba.setVisible(false);   
                    } catch  (NumberFormatException e4) { 
                        JOptionPane.showMessageDialog(null, "falha ao adicionar na tabela");
                    }
                }
            });
        }  

        return aba;
    }

    public JFrame addLoja(String a,int tipo){
        JFrame aba= new JFrame();
        JButton add= new JButton("adicionar");

        JLabel Lid= new JLabel("digite o cnpj:");
        JTextField txtid= new JTextField(8);
        txtid.setText(a);

        JLabel LvenD= new JLabel("digite as vendas diarias:");
        JTextField txtvenD= new JTextField(5);

        JLabel LvenM= new JLabel("digite as vendas mensais:");
        JTextField txtvenM= new JTextField(5);

        JLabel LcusM= new JLabel("digite o custo mensal:");
        JTextField txtcusM= new JTextField(5);

        aba.setLayout(new FlowLayout());
        aba.add(Lid);
        aba.add(txtid);
        aba.add(LvenD);
        aba.add(txtvenD);
        aba.add(LvenM);
        aba.add(txtvenM);
        aba.add(LcusM);
        aba.add(txtcusM);
        aba.add(add);

        aba.setSize(250,190);
        aba.setVisible(true);
        aba.setLocationRelativeTo(null);
        aba.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        if(tipo==1){
            add.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e3){
                    try {
                        String sql= "INSERT INTO LOJA(CNPJ,VENDAS_DIARIAS,VENDAS_MENSAIS,CUSTO_MENSAL) VALUES("+txtid.getText()+","+txtvenD.getText()+","+txtvenM.getText()+","+txtcusM.getText()+")";
                        conexao con= new conexao();
                        con.executaSQL(sql);

                        txtid.setText("");
                        txtvenD.setText("");
                        txtvenM.setText("");   
                        txtcusM.setText("");   
                    } catch  (NumberFormatException e4) { 
                        JOptionPane.showMessageDialog(null, "falha ao adicionar na tabela");
                    }
                }
            });
        }
        else if(tipo==2){
            add.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e3){
                    try {
                        String sql= "update LOJA set VENDAS_DIARIAS="+txtvenD.getText()+",VENDAS_MENSAIS="+txtvenM.getText()+",CUSTO_MENSAL="+txtcusM.getText()+" WHERE CNPJ="+txtid.getText()+";";
                        conexao con= new conexao();
                        con.executaSQL(sql);
    
                        txtid.setText("");
                        txtvenD.setText("");
                        txtvenM.setText("");   
                        txtcusM.setText("");
                        aba.setVisible(false);  
                    } catch  (NumberFormatException e4) { 
                        JOptionPane.showMessageDialog(null, "falha ao adicionar na tabela");
                    }
                }
            });
        }

        return aba;
    }

    public JFrame addCliente(String a,int tipo) {
        JFrame aba= new JFrame();
        JButton add= new JButton("adicionar");

        JLabel Lid= new JLabel("digite o id:");
        JTextField txtid= new JTextField(5);
        txtid.setText(a);

        JLabel Lnome= new JLabel("digite o nome:");
        JTextField txtnome= new JTextField(10);

        JLabel Lidade= new JLabel("digite a idade:");
        JTextField txtidade= new JTextField(5);

        aba.setLayout(new FlowLayout());
        aba.add(Lnome);
        aba.add(txtnome);
        aba.add(Lid);
        aba.add(txtid);
        aba.add(Lidade);
        aba.add(txtidade);
        aba.add(add);

        aba.setSize(195,190);
        aba.setVisible(true);
        aba.setLocationRelativeTo(null);
        aba.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        if(tipo==1){
            add.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e3){
                    try {
                        String sql= "INSERT INTO CLIENTE(NUM_ID,NOME,IDADE) VALUES("+txtid.getText()+",'"+txtnome.getText()+"',"+txtidade.getText()+")";
                        conexao con= new conexao();
                        con.executaSQL(sql);

                        txtid.setText("");
                        txtidade.setText("");
                        txtnome.setText("");   
                    } catch  (NumberFormatException e4) { 
                        JOptionPane.showMessageDialog(null, "falha ao adicionar na tabela");
                    }
                }
            });
        }
        else if(tipo==2){
            add.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e3){
                    try {
                        String sql= "UPDATE CLIENTE SET NOME='"+txtnome.getText()+"',IDADE="+txtidade.getText()+" WHERE NUM_ID="+txtid.getText()+";";
                        conexao con= new conexao();
                        con.executaSQL(sql);

                        txtid.setText("");
                        txtidade.setText("");
                        txtnome.setText(""); 
                        aba.setVisible(false);  
                    } catch  (NumberFormatException e4) { 
                        JOptionPane.showMessageDialog(null, "falha ao adicionar na tabela");
                    }
                }
            });
        }
        return aba;
    }

    public void consultar(){
        consultar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e){
                try {
                    String tabela= txtabela.getText(); 
                    if     (tabela.equals("Cliente"))        conCliente();
                    else if(tabela.equals("cliente"))        conCliente();
                    else if(tabela.equals("Loja"))           conLoja();
                    else if(tabela.equals("loja"))           conLoja();
                    else if(tabela.equals("Bilheteria"))     conBilheteria();
                    else if(tabela.equals("bilheteria"))     conBilheteria();
                    else if(tabela.equals("Atracao"))        conAtracao();
                    else if(tabela.equals("Atração"))        conAtracao();
                    else if(tabela.equals("atracao"))        conAtracao();
                    else if(tabela.equals("atração"))        conAtracao();


                } catch (NumberFormatException e2) { 
					JOptionPane.showMessageDialog(null, "falha ao consultar essa tabela");
				}
            }
        });
    }

    public JFrame conAtracao(){
        JFrame aba= new JFrame("Atrações cadastradas");

        try {
            String sql= "SELECT * FROM Atracao;";
            conexao con= new conexao();
            ResultSet resposta= con.executasql(sql);

            try {
                while(resposta.next()){
                    JPanel novo= new JPanel();
                    String espaco= "                                                                                                         ";
                    String nome= resposta.getString(1);
                    String tipo= resposta.getString(2);

                    novo.add(aux(espaco)); //gambiarra
                    novo.add(new JLabel("nome: "));
                    novo.add(aux(nome));
                    novo.add(new JLabel("tipo: "));
                    novo.add(aux(tipo));
                    novo.add(aux(espaco));
                    aba.add(novo);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "falha ao consultar essa tabela");
            }

        } catch  (NumberFormatException e4) { 
            JOptionPane.showMessageDialog(null, "falha ao consultar essa tabela");
        }

        aba.setLayout(new FlowLayout());
        aba.setSize(500,500);
        aba.setVisible(true);
        aba.setLocationRelativeTo(null);
        aba.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        return aba;
    }

    public JFrame conBilheteria(){
        JFrame aba= new JFrame("Bilheterias cadastradas");

        try {
            String sql= "SELECT * FROM Bilheteria;";
            conexao con= new conexao();
            ResultSet resposta= con.executasql(sql);

            try {
                while(resposta.next()){
                    JPanel novo= new JPanel();
                    String espaco= "                                                                                                         ";
                    String numero= resposta.getString(1);
                    String v_dia= resposta.getString(2);
                    String v_mes= resposta.getString(3);
                    String c_mes= resposta.getString(4);
                    String c_dia= resposta.getString(5);

                    novo.add(aux(espaco)); //gambiarra
                    novo.add(new JLabel("numero: "));
                    novo.add(aux(numero));
                    novo.add(new JLabel("vendas/dia: "));
                    novo.add(aux(v_dia));
                    novo.add(new JLabel("vendas/mes: "));
                    novo.add(aux(v_mes));
                    novo.add(new JLabel("clientes/dia: "));
                    novo.add(aux(c_dia));
                    novo.add(new JLabel("clientes/mes: "));
                    novo.add(aux(c_mes));
                    novo.add(aux(espaco));
                    aba.add(novo);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "falha ao consultar essa tabela");
            }

        } catch  (NumberFormatException e4) { 
            JOptionPane.showMessageDialog(null, "falha ao consultar essa tabela");
        }

        aba.setLayout(new FlowLayout());
        aba.setSize(500,500);
        aba.setVisible(true);
        aba.setLocationRelativeTo(null);
        aba.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        return aba;
    }

    public JFrame conLoja(){
        JFrame aba= new JFrame("Lojas cadastrados");

        try {
            String sql= "SELECT * FROM LOJA;";
            conexao con= new conexao();
            ResultSet resposta= con.executasql(sql);

            try {
                while(resposta.next()){
                    JPanel novo= new JPanel();
                    String espaco= "                                                                                                         ";
                    String cnpj= resposta.getString(1);
                    String v_dia= resposta.getString(2);
                    String v_mes= resposta.getString(3);
                    String c_mes= resposta.getString(4);

                    novo.add(aux(espaco)); //gambiarra
                    novo.add(new JLabel("cnpj: "));
                    novo.add(aux(cnpj));
                    novo.add(new JLabel("vendas/dia: "));
                    novo.add(aux(v_dia));
                    novo.add(new JLabel("vendas/mes: "));
                    novo.add(aux(v_mes));
                    novo.add(new JLabel("clientes/mes: "));
                    novo.add(aux(c_mes));
                    novo.add(aux(espaco));
                    aba.add(novo);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "falha ao consultar essa tabela");
            }

        } catch  (NumberFormatException e4) { 
            JOptionPane.showMessageDialog(null, "falha ao consultar essa tabela");
        }

        aba.setLayout(new FlowLayout());
        aba.setSize(500,500);
        aba.setVisible(true);
        aba.setLocationRelativeTo(null);
        aba.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        return aba;
    }

    public JFrame conCliente(){
        JFrame aba= new JFrame("Clientes cadastrados");

        try {
            String sql= "SELECT * FROM CLIENTE;";
            conexao con= new conexao();
            ResultSet resposta= con.executasql(sql);

            try {
                while(resposta.next()){
                    JPanel novo= new JPanel();
                    String espaco= "                                                                                                         ";
                    String nome= resposta.getString(2);
                    String id= resposta.getString(1);
                    String idade= resposta.getString(3);

                    novo.add(aux(espaco)); //gambiarra
                    novo.add(new JLabel("nome: "));
                    novo.add(aux(nome));
                    novo.add(new JLabel("id: "));
                    novo.add(aux(id));
                    novo.add(new JLabel("idade: "));
                    novo.add(aux(idade));
                    novo.add(aux(espaco));
                    aba.add(novo);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "falha ao consultar essa tabela");
            }

        } catch  (NumberFormatException e4) { 
            JOptionPane.showMessageDialog(null, "falha ao consultar essa tabela");
        }

        aba.setLayout(new FlowLayout());
        aba.setSize(500,500);
        aba.setVisible(true);
        aba.setLocationRelativeTo(null);
        aba.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        return aba;
    }

    public JLabel aux(String a) {
        JLabel aux= new JLabel(a);
        return aux;
    }
}
