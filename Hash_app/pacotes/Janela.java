package Hash_app.pacotes;

import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;


public class Janela extends JFrame {
    private JFrame janela= new JFrame("contratos RH");

    private JPanel nome= new JPanel();
    private JPanel cpf= new JPanel();
    private JPanel id= new JPanel();
    private JPanel contrato= new JPanel();
    private JPanel but= new JPanel();

    private JLabel Lnome= new JLabel("nome:");
    private JLabel Lcpf= new JLabel("cpf:");
    private JLabel Lid= new JLabel("id:");
    private JLabel Lcontrato= new JLabel("contratação de:");
    private JLabel L2contrato= new JLabel("até:");

    private JTextField txtnome= new JTextField(10);
    private JTextField txtcpf= new JTextField(10);
    private JTextField txtid= new JTextField(10);
    private JTextField txtcontrato_de= new JTextField(5);
    private JTextField txtcontrato_ate= new JTextField(5);

    private JButton adicionar= new JButton("adicionar");
    private JButton modificar= new JButton("modificar");
    private JButton remover= new JButton("remover");
    private JButton cadastros= new JButton("ver cadastros");


    private Map <Integer,Funcionario> contratos= new HashMap<>();

    public void montar() {
        buffer();
        inicializa("","","");

        adicionar();
        remover();
        modificar();
        ver_cadastros();
    }

    public void buffer() {
        nome.setLayout(new FlowLayout());
        nome.add(Lnome);
        nome.add(txtnome);

        cpf.setLayout(new FlowLayout());
        cpf.add(Lcpf);
        cpf.add(txtcpf);

        id.setLayout(new FlowLayout());
        id.add(Lid);
        id.add(txtid);

        contrato.setLayout(new FlowLayout());
        contrato.add(Lcontrato);
        contrato.add(txtcontrato_de);
        contrato.add(L2contrato);
        contrato.add(txtcontrato_ate);

        but.setLayout(new FlowLayout());
        but.add(adicionar);
        but.add(remover);
        but.add(modificar);
        but.add(cadastros);
    }

    public void inicializa(String nomeInicial,String cpfInicial,String idInicial) {

        txtnome.setText(nomeInicial);
        txtcpf.setText(cpfInicial);
        txtid.setText(idInicial);

        janela.setLayout(new FlowLayout());
        janela.add(nome);
        janela.add(cpf);
        janela.add(id);
        janela.add(contrato);
        janela.add(but);
        
        janela.setSize(410,190);
        janela.setVisible(true);
        janela.setLocationRelativeTo(null);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void adicionar() {
        adicionar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e){
                try {
                    String nome= txtnome.getText();
                    String cpf= txtcpf.getText();
                    Integer id= Integer.parseInt(txtid.getText());
                    Integer contrato_de= Integer.parseInt(txtcontrato_de.getText());
                    Integer contrato_ate= Integer.parseInt(txtcontrato_ate.getText());

                    Funcionario funcionario= new Funcionario(nome,cpf,id,contrato_de,contrato_ate);
                    contratos.put(id,funcionario);
                    limpar();

                } catch (NumberFormatException e2) {
					JOptionPane.showMessageDialog(null, "falha ao cadastrar funcionario");
				}
            }
        });
    }

    public void remover() {
        remover.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e){

                JTextField txtid= new JTextField(5);
                JButton remover= new JButton("procurar");

                JFrame aba= abaProcura();
                aba.add(txtid);
                aba.add(remover);

                remover.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e2){
                        try{
                            Integer id= Integer.parseInt(txtid.getText());
                            if(contratos.containsKey(id)) {contratos.remove(id); txtid.setText("");}
                            else JOptionPane.showMessageDialog(null, "        id não encontrado"); 
                        } catch(NumberFormatException e3) {
                            JOptionPane.showMessageDialog(null, "        id não encontrado"); 
                        }
                    }
                });
            }

        });
    }

    public void modificar() { //meio mal otimizado
        modificar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e){
                
                JTextField txtidprocura= new JTextField(5);
                JButton modificar= new JButton("procurar");

                JFrame aba= abaProcura();
                aba.add(txtidprocura);
                aba.add(modificar);

                modificar.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e2){
  
                        Integer idamudar= Integer.parseInt(txtidprocura.getText());
                        
                        if(contratos.containsKey(idamudar)) {

                            Funcionario funcionario= contratos.get(idamudar);
                            JFrame abaModifica= new JFrame();
                            JButton salvar= new JButton("salvar alterações");
                            JLabel Lstatus= new JLabel(funcionario.getContrato());
    
                            txtnome.setText(funcionario.getNome());
                            txtcpf.setText(funcionario.getCpf());
                            txtid.setText(funcionario.getCartao());
                    
                            abaModifica.setLayout(new FlowLayout());
                            abaModifica.add(nome);
                            abaModifica.add(cpf);
                            abaModifica.add(id);
                            abaModifica.add(contrato);
                            abaModifica.add(Lstatus);
                            abaModifica.add(salvar);
                            
                            abaModifica.setSize(400,190);
                            abaModifica.setVisible(true);
                            abaModifica.setLocationRelativeTo(null);
                            abaModifica.setDefaultCloseOperation(HIDE_ON_CLOSE);
                            
                            salvar.addActionListener(new ActionListener(){
                                @Override
                                public void actionPerformed(ActionEvent e3){
                                    contratos.remove(idamudar);

                                    try {
                                        String nome= txtnome.getText();
                                        String cpf= txtcpf.getText();
                                        Integer id= Integer.parseInt(txtid.getText());
                                        Integer contrato_de= Integer.parseInt(txtcontrato_de.getText());
                                        Integer contrato_ate= Integer.parseInt(txtcontrato_ate.getText());
                    
                                        Funcionario funcionario= new Funcionario(nome,cpf,id,contrato_de,contrato_ate);
                                        contratos.put(id,funcionario);
                                        limpar();
                                        abaModifica.setVisible(false);
                    
                                    } catch (NumberFormatException e2) {
                                        JOptionPane.showMessageDialog(null, "falha ao cadastrar funcionario");
                                    }
                                }
                            });
                        }
                        else  JOptionPane.showMessageDialog(null, "        id não encontrado"); 
                    }
                });

            }
        });
    }

    public void ver_cadastros() {
        cadastros.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {

                JFrame lista= new JFrame();

                contratos.forEach((key,a)->{
                    JPanel novo= new JPanel();
                    novo.add(new JLabel("nome: "));
                    novo.add(aux(a.getNome()));
                    novo.add(new JLabel("cpf: "));
                    novo.add(aux(a.getCpf()));
                    novo.add(new JLabel("id: "));
                    novo.add(aux(a.getCartao()));
                    novo.add(aux(a.getContrato()));
                    
                    lista.add(novo);
                  });

                lista.setLayout(new FlowLayout());
                lista.setSize(600,700);
                lista.setVisible(true);
                lista.setLocationRelativeTo(null);
                lista.setDefaultCloseOperation(HIDE_ON_CLOSE);
  
            }
        });
    }

    public void limpar() {

        txtnome.setText("");
        txtcpf.setText("");
        txtid.setText("");
        txtcontrato_ate.setText("");
        txtcontrato_de.setText("");
	}

    public JFrame abaProcura() {
        JFrame aba= new JFrame();
        JLabel Lid= new JLabel("digite o id:");

        aba.setLayout(new FlowLayout());
        aba.add(Lid);

        aba.setSize(150,100);
        aba.setVisible(true);
        aba.setLocationRelativeTo(null);
        aba.setDefaultCloseOperation(HIDE_ON_CLOSE);

        return aba;
    }

    public JLabel aux(String a) {
        JLabel aux= new JLabel(a);
        return aux;
    }

}
