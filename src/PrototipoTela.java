/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;
/**
 *
 * @author Lucas Lopes Fraga
 */
public class PrototipoTela {
    public ArrayList<String> code = new ArrayList();
    public String title = "";
    public String classname = "";
    public int x = 20;
    public int y = 20;
    
    public PrototipoTela() {
    }
    
    void define(PrototipoTela s, String classname, String packagename, ArrayList<String> variableNames, ArrayList<String> variableTypes){
        String title = classname + "Tela";
        code.add("package " + packagename + ";\n");
        code.add("import java.awt.*;");
        code.add("import javax.swing.*;");
        code.add("import java.util.ArrayList;\n");
        code.add("public class " + title + " {\n");
        code.add("public " + classname  + "InterfaceDAO dao;");
        code.add("public JTable tabTabela = new JTable();\n");
        for(int i=0; i != variableNames.size(); i++){
            code.add("JTextField " + variableNames.get(i) + "TextField = new JTextField();");
        }
        
        code.add("public " + title + "(){");
        code.add("dao = new " + classname + "DAO();");
        code.add("JFrame " + title +" = new JFrame(\"Tela de Gerenciamento de " + classname + "\");");
        code.add(title +".setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);");
        code.add("JPanel panel = new JPanel();");
        code.add("panel.setBounds(0,0,800,800);");
        code.add("GroupLayout layout = new GroupLayout(panel);");
        code.add("panel.setLayout(layout);");
        code.add("layout.setAutoCreateGaps(true);");
        code.add("layout.setAutoCreateContainerGaps(true);");
        this.title = title;
        this.classname = classname;
    }
    
    void createFields(ArrayList<String> variableNames, ArrayList<String> variableTypes){
        for(int i=0; i != variableNames.size(); i++){
            String nome_caps = variableNames.get(i).substring(0, 1).toUpperCase() + variableNames.get(i).substring(1);
            String nome_formatado = nome_caps.replace("_"," ");
            code.add("JLabel " + variableNames.get(i) + "Label = new JLabel(\""+ nome_formatado +":\");");
        }
        code.add("JScrollPane tabela = new JScrollPane();");
        code.add("tabela.setViewportView(tabTabela);");
        code.add("atualizarTabela();");
        code.add("JButton criarButton = new JButton(\"Criar\");");
        code.add("JButton deletarButton = new JButton(\"Deletar\");");
        code.add("JButton atualizarButton = new JButton(\"Atualizar\");");
    }
    
    void createLayout(ArrayList<String> variableNames, ArrayList<String> variableTypes){
        //Horizontal Group
        code.add("layout.setHorizontalGroup(");
        code.add("layout.createSequentialGroup()");
        code.add(".addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)");
        code.add(".addComponent(tabela)");
        code.add(".addGroup(layout.createSequentialGroup()");
        code.add(".addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)");
        for(int i=0; i != variableNames.size(); i++){
            code.add(".addComponent(" + variableNames.get(i) + "Label)");
        }
        code.add(")");
        code.add(".addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)");
        for(int i=0; i != variableNames.size(); i++){
            code.add(".addComponent(" + variableNames.get(i) + "TextField)");
        }
        code.add(".addGroup(layout.createSequentialGroup()");
        code.add(".addComponent(criarButton)");
        code.add(".addComponent(deletarButton)");
        code.add(".addComponent(atualizarButton)");
        code.add(")");
        code.add(")");
        code.add(")");
        code.add("));");
        
        //Vertical Group
        code.add("layout.setVerticalGroup(");
        code.add("layout.createSequentialGroup()");
        code.add(".addComponent(tabela,60,80,100)");
        for(int i=0; i != variableNames.size(); i++){
            code.add(".addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)");
            code.add(".addComponent(" + variableNames.get(i) + "Label)");
            code.add(".addComponent(" + variableNames.get(i) + "TextField)");
            code.add(")");
        }
        code.add(".addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)");
        code.add(".addComponent(criarButton)");
        code.add(".addComponent(deletarButton)");
        code.add(".addComponent(atualizarButton)");
        code.add(")");
        code.add(");");
    }
    
    void endLine(PrototipoTela s, String title, ArrayList<String> variableNames, ArrayList<String> variableTypes){
        //Event Listeners
        code.add("criarButton.addActionListener(new java.awt.event.ActionListener() {");
        code.add("public void actionPerformed(java.awt.event.ActionEvent evt) {");
        code.add("criarObjeto(evt);");
        code.add("}");
        code.add("});");
        
        code.add("deletarButton.addActionListener(new java.awt.event.ActionListener() {");
        code.add("public void actionPerformed(java.awt.event.ActionEvent evt) {");
        code.add("deletarObjeto(evt);");
        code.add("}");
        code.add("});");
        
        code.add("atualizarButton.addActionListener(new java.awt.event.ActionListener() {");
        code.add("public void actionPerformed(java.awt.event.ActionEvent evt) {");
        code.add("atualizarObjeto(evt);");
        code.add("}");
        code.add("});");

        code.add(title + ".add(panel);");
        code.add(title + ".setSize(816,839);");
        code.add(title + ".setLayout(null);");
        code.add(title + ".setVisible(true);}");
        
        //Funções
        code.add("private void atualizarTabela(){");
        code.add("ArrayList<" + this.classname + "> dadosTabela = dao.getLista();");
        code.add(this.classname + "TableAdaptator adaptadorTabela = new " + this.classname + "TableAdaptator(dadosTabela);");
        code.add("tabTabela.setModel(adaptadorTabela);");
        code.add("}\n");
        
        //CRUD
        
        //Adicionar
        code.add("private void criarObjeto(java.awt.event.ActionEvent evt) {");
        String lista = "";
        for(int i=0; i != variableNames.size(); i++){
            if(null == variableTypes.get(i)){
                code.add("String " + variableNames.get(i) + "Parameter = " + variableNames.get(i) + "TextField.getText();");
            } else switch (variableTypes.get(i)) {
                case "int":
                    code.add("int " + variableNames.get(i) + "Parameter = Integer.parseInt(" + variableNames.get(i) + "TextField.getText());");
                    break;
                case "float":
                    code.add("float " + variableNames.get(i) + "Parameter = Float.parseFloat(" + variableNames.get(i) + "TextField.getText());");
                    break;
                default:
                    code.add("String " + variableNames.get(i) + "Parameter = " + variableNames.get(i) + "TextField.getText();");
                    break;
            }
            lista = lista + variableNames.get(i) + "Parameter, ";
        }
        lista = lista.substring(0, lista.length() - 2);
        code.add(this.classname + " novoObjeto = new " + this.classname + "(" + lista + ");");
        code.add("dao.adicionar" + this.classname + "(novoObjeto);");
        for(int i=0; i != variableNames.size(); i++){
            code.add(variableNames.get(i) + "TextField.setText(\"\");");
        }
        code.add("atualizarTabela();");
        code.add("};\n");
        
        //Deletar
        code.add("private void deletarObjeto(java.awt.event.ActionEvent evt) {");
        code.add("int colunaId = 0;");
        code.add("int linhaSelecionada = tabTabela.getSelectedRow();");
        code.add("String idSelecionado = tabTabela.getModel().getValueAt(linhaSelecionada, colunaId).toString();");
        code.add(this.classname + " objetoBuscado = dao.buscar" + this.classname + "(idSelecionado);");
        code.add("dao.remover" + this.classname + "(objetoBuscado);");
        code.add("atualizarTabela();");
        code.add("};\n");
        
        //Atualizar
        code.add("private void atualizarObjeto(java.awt.event.ActionEvent evt) {");
        code.add("int linhaSelecionada = tabTabela.getSelectedRow();");
        for(int i=0; i != variableNames.size(); i++){
            code.add("String " + variableNames.get(i) + "Parameter = " + variableNames.get(i) + "TextField.getText();");
        }
        for(int i=0; i != variableNames.size(); i++){
            code.add("tabTabela.getModel().setValueAt(" + variableNames.get(i) + "Parameter, linhaSelecionada, " + (i+1) + ");");
        }
        code.add("atualizarTabela();");
        code.add("};\n");
        
        
        //Método Principal
        code.add("public static void main(String args[]){");
        code.add("new " + title + "();");
        code.add("}} ");
    }
    
    public String printCode(PrototipoTela s){
        String writtenCode = "";
        for(String i : s.code){
            writtenCode = writtenCode + i + "\n";
        }
        return writtenCode;
    }

    public String getTitle() {
        return title;
    }

}
